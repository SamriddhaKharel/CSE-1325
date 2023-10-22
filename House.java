/*
 * Samriddha Kharel 1001918169
 */
package code5_1001918169;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author samdm
 */
public abstract class House
{
    private String houseName;
    
    HashMap<String, Integer> CandyList = new HashMap<>();
    public House(String houseName,HashMap<String,Integer>CandyList)
    {
        this.houseName =houseName;
        this.CandyList =CandyList;
    }
    public abstract String ringDoorbell(TrickOrTreater TOT);
    
}
