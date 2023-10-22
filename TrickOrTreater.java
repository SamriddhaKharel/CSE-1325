/*
 * Samriddha Kharel 1001918169
 */
package code5_1001918169;

/**
 *
 * @author samdm
 */
import java.util.HashMap;
import static java.lang.Math.random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrickOrTreater implements Runnable 
{ 
    public static int ImDone; 

    
    private String name; 
    private String path = "."; 
    private ArrayList<House>ListOfHouses = new ArrayList<>(); 
    private HashMap<String, Integer>Bucket = new HashMap<>(); 
     
    /* constructor accepts a name and an ArrayList of Houses */ 
    public TrickOrTreater(String name, ArrayList<House>ListOfHouses)
    {
        this.name =name;
        this.ListOfHouses =ListOfHouses;  
    }
     
    /* instance method getName() that returns the name */ 
    public String getName()
    {
        return name;    
    }
     
    /* instance method getPath() that return the path */ 
    public String getPath()
    {
        return path;    
    }
 
    /* void instance method addToPath that accepts a string that it concatenates */ 
    public  void addToPath(String concate)
    {
        path += concate;
    }
         
    /* private void instance method Walk that accepts a speed */ 
    private void Walk(int speed)
    {
        for (int i =0; i < 10; i++ )
        {
            path += ".";
        }
        try
        {
            Thread.sleep(speed);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        } 
        
    }
       
    /* public instance method named printBucket that returns a String */  
    public String printBucket()
    { 
        /* declare two strings – Candy and BucketContents                 */ 
        String Candy =" ";
        String BucketContents= " ";
        /* declare an integer named CandyCount and set to 0               */ 
        int CandyCount = 0;
         
        BucketContents = String.format("%-10s - ", name); 
        for (HashMap.Entry bucketElement : Bucket.entrySet())
        {
            Candy = (String)bucketElement.getKey();
            CandyCount = (int)bucketElement.getValue();
            BucketContents += String.format("%15s %d ", Candy, CandyCount);
        }
       
        BucketContents += "\n"; 
        return BucketContents;  
    } 
    
    @Override
    public void run()
    {
        int speed =0;
        int count =0;
        String Candy= ""; 
        
        for(House x: ListOfHouses)
        {
            Random ran = new Random();
            speed = ran.nextInt(1501)+1;
            Walk(speed);
            Candy = x.ringDoorbell(this);
        
            if(Bucket.containsKey(Candy))
            {
                count = Bucket.get(Candy);
                Bucket.put(Candy,++count);
            }
            else
            {
                Bucket.put(Candy,1);
            }
            
        }
        synchronized(this)
        {
            ImDone++;
        }
    }
    
    
     
}