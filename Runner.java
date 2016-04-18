import edu.galileo.baquiax.Bridge;
import edu.galileo.baquiax.Person;
import edu.galileo.baquiax.RandomGenerator;
import java.util.Scanner;

public class Runner  {
    
    public static void main(String args[]) {
        int numberOfPersons = 0;       
        Scanner inputReader = null;        
        final boolean printInfo = (args.length > 0 && args[0].equals("-v")) ? true : false; 
        
        while(numberOfPersons < 1) {
            System.out.println("Give me the amount of people will cross: ");
            try {
                if (inputReader == null) {
                    inputReader = new Scanner(System.in);
                }
                numberOfPersons = inputReader.nextInt();
            } catch (Exception e) {
                inputReader = null;
                numberOfPersons = 0;
                System.out.println("Ingrese un numero, por favor");    
            }            
        }
        System.out.println("BRIDGE: The people has started to come!");        
        final int people = numberOfPersons;
        
        new Thread( new Runnable() {
            @Override
            public void run() {
                RandomGenerator r = new RandomGenerator();
                Bridge b =  new Bridge(printInfo);                
                try {
                    for(int i = 0; i < people; i++) {                
                        Person p = new Person(b, ((r.getRandomNumber(1,10) > 5) ? Person.Direction.RIGHT : Person.Direction.LEFT));
                        p.setId(i + 1);                
                        Thread t = new Thread(p);
                        t.start();
                        Thread.sleep(r.getRandomNumber(0,3) * 1000);
                    }
                } catch (Exception e) {
                    System.out.println("The app has crashed.");
                }
            }
        }).start();                
    }            
}