package edu.galileo.baquiax;
import edu.galileo.baquiax.Person;
//Shared resource!
public class Bridge {
    public static int MAX_PEOPLE = 4;     
    private int peopleWhoHaveCrossed;
    private int peopleTryingCrossToLeft;
    private int peopleTryingCrossToRight;
    private boolean printInfo;
    private Person.Direction currentDirection;
    
    public Bridge(boolean pi) {   
        this.printInfo = pi;
    }
    
    public void print(String s) {
        System.out.println("BRIDGE: " + s);
    }
    
    public void printInfo(String s) {
        if (!printInfo) return;
        System.out.println("BRIDGE-INFO: " + s);
    }
    
    public String directionToString(Person.Direction d) {
        if (d != Person.Direction.RIGHT && d != Person.Direction.LEFT)
            return "NONE";
        
        return (d == Person.Direction.RIGHT) ? "RIGHT" : "LEFT";
    }
    public synchronized void startCrossing(Person p) throws Exception {
        this.print(p + " Is trying to cross to: " + this.directionToString(p.getDirection()));        
        increaseAttemptCounter(p);
        
        if ((currentDirection == Person.Direction.RIGHT && peopleTryingCrossToRight > MAX_PEOPLE) ||
            (currentDirection == Person.Direction.LEFT && peopleTryingCrossToLeft > MAX_PEOPLE)) {
            this.print(p + " need wait.");
            this.wait();            
        }      
                                     
        if (this.currentDirection == null) {
            this.printInfo("Current direction: " + this.directionToString(this.currentDirection));
            this.currentDirection = p.getDirection();
            this.printInfo("People now can cross to " + ((this.currentDirection == Person.Direction.RIGHT) ? "RIGHT" : "LEFT"));    
        } else if (p.getDirection() != currentDirection) {            
            this.print(p + " need wait.");
            this.wait();            
        }                     
                
        this.printInfo(p + " take " + p.getTimeToCross() + " secs to cross. Now is crossing.");
        Thread.sleep(p.getTimeToCross() * 1000);
        this.peopleWhoHaveCrossed++;                
    }
    
    private int increaseAttemptCounter(Person p) {
        if (p.getDirection() == Person.Direction.RIGHT) {
            return ++this.peopleTryingCrossToRight;
        } else {
            return ++this.peopleTryingCrossToLeft;
        }                
    }
    
    private int decreaseAttemptCounter(Person p) {
        if (p.getDirection() == Person.Direction.RIGHT) {
            return --this.peopleTryingCrossToRight;
        } else {
            return --this.peopleTryingCrossToLeft;
        }
    }
    
    public void changeDirection() {
        if (this.currentDirection == Person.Direction.RIGHT && this.peopleTryingCrossToLeft > 0) {
            this.currentDirection = Person.Direction.LEFT;
        } if (this.currentDirection == Person.Direction.LEFT && this.peopleTryingCrossToRight > 0) {
            this.currentDirection = Person.Direction.RIGHT;
        } else {
            this.currentDirection = null;
        }
    }
    
    public synchronized void finishCrossing(Person p) throws Exception {       
       int peopleWaiting = this.decreaseAttemptCounter(p);
       
        if (this.peopleWhoHaveCrossed >= MAX_PEOPLE || peopleWaiting == 0) {           
            this.peopleWhoHaveCrossed = 0;
            if (this.currentDirection != null) {
                this.changeDirection();
                this.printInfo("Allowed direction has changed: " + this.directionToString(this.currentDirection));
                if (this.currentDirection == null) {
                    this.printInfo("Current direction is NONE because there aren't people waiting.");               
                }    
            }           
            this.notifyAll();    
        }       
    }
}