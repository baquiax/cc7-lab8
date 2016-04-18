package edu.galileo.baquiax;

public class Person extends RandomGenerator implements Runnable {
    public static enum Direction {
        LEFT, 
        RIGHT
    }
    private Direction direction;    
    private Bridge sharedBridge;
    private int id;
    private int timeToCross;
              
    public Person(Bridge bridge, Direction dir) {
        this.sharedBridge = bridge;
        this.direction = dir;            
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void print(String s) {
        System.out.println(this + ": " + s);
    }
    
    public String directionToString(Person.Direction d) {
        if (d != Person.Direction.RIGHT && d != Person.Direction.LEFT)
            return "NONE";
        
        return (d == Person.Direction.RIGHT) ? "RIGHT" : "LEFT";
    }
    
    public void run() {
        try {
            this.print("Come to bridge, its direction is: " + this.directionToString(this.direction));            
            this.timeToCross = this.getRandomNumber(1,3);            
            this.sharedBridge.startCrossing(this);                     
            this.sharedBridge.finishCrossing(this);
            this.print("Has crossed to: " + this.directionToString(this.direction));            
        } catch (Exception e) {
            this.print("A person has crashed");
        }        
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getTimeToCross() {
        return this.timeToCross;    
    }
    
    public String toString() {
        return "INGE#" + this.id;
    }
}