/*
 * Samriddha Kharel 1001918169
 */
package code5_1001918169;

/**
 *
 * @author samdm
 */
import static java.lang.Math.random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CandyHouse extends House
{

    public CandyHouse(String houseName, HashMap<String, Integer> Candylist)
    {
         super(houseName,Candylist);
    }

    @Override
    public synchronized String ringDoorbell(TrickOrTreater TOT)
    {
        
        String Candy = " ";
        TOT.addToPath ("+");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }    
        Random ran = new Random();
        int x = (int)(Math.random() * CandyList.size() + 1);
        
        for (HashMap.Entry candyElement : CandyList.entrySet())
        {
            if((int)candyElement.getValue() == x)
            {
                Candy = (String)candyElement.getKey();      
            }
            
        }
        return Candy;
    }
    
    
}
