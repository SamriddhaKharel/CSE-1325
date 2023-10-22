/*
 * Samriddha Kharel 1001918169
 */
package code5_1001918169;

import java.util.HashMap;
import static java.lang.Math.random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author samdm
 */
public class ToothbrushHouse extends House
{

    public ToothbrushHouse(String houseName, HashMap<String, Integer> Candylist)
    {
         super(houseName,Candylist);
    }
    @Override
    public synchronized String ringDoorbell(TrickOrTreater TOT)
    {
        String Toothbrush = "Toothbrush";
        TOT.addToPath ("-");
        try
    {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }    
       
        return Toothbrush;
    }
    
    
}
