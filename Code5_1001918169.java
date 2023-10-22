/*
 * Samriddha Kharel 1001918169
 */
package code5_1001918169;


import static java.lang.Math.random;
import java.io.File;
import java.io.FileNotFoundException;
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


public class Code5_1001918169
{

    public static void CreateCandyList(String inputFileName, HashMap<String, Integer> candy)
    { 
        File fileIn = new File(inputFileName);
        Scanner scanFile = null;
        try
        {
            scanFile = new Scanner(fileIn);
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("%s file name does not exist...exiting\n",inputFileName);
            System.exit(0);
        }
            String candyArray[]=new String[4];

        while(scanFile.hasNextLine())
        {
            candyArray = scanFile.nextLine().split("\\"+"|"); 
            for (String number: candyArray) 
            {
                candy.put(candyArray[0],Integer.parseInt(candyArray[1]));
            }          
        }
        scanFile.close();   
           
    } 
    
    public static String CreateHouses(String InputHOUSES,  ArrayList<House> House,HashMap<String, Integer> hash)
    {
        String HouseHeading = "           ";
        String FileLine = " ";
        File fileIn = new File(InputHOUSES);
        Scanner scanFile = null;
        try
        {
            scanFile = new Scanner(fileIn);
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("%s file name does not exist...exiting\n",InputHOUSES);
            System.exit(0);
        }
        while(scanFile.hasNextLine())
        {
            FileLine = scanFile.nextLine();
            HouseHeading = HouseHeading + FileLine;
            
            for (int i = 0; i < 11 - FileLine.length(); i++) 
            { 
              HouseHeading += " "; 
            }
            Random ran = new Random();
            int x = ran.nextInt(3)+0;   
                if(x == 0)
            {
                House.add(new CandyHouse(FileLine,hash));
            }
            else
            {
                House.add(new ToothbrushHouse(FileLine,hash));
            }
           
        }
        
        scanFile.close();
        HouseHeading =HouseHeading + "\n\n";
        return HouseHeading;
    }
     public static void CreateTOTs(String InputCANDY,ArrayList<House> house, ArrayList<TrickOrTreater> TrickOrTreater )
     {
        File fileIn = new File(InputCANDY);
        Scanner scanFile = null;
        String FileLine = " ";

        try
        {
            scanFile = new Scanner(fileIn);
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("%s file name does not exist...exiting\n",InputCANDY);
            System.exit(0);
        }
        while(scanFile.hasNextLine())
        {
            FileLine = scanFile.nextLine();
            TrickOrTreater.add(new TrickOrTreater(FileLine, house));
        }
        scanFile.close();
     }
    


    public static void main(String[] args)
    {
        String CANDY  = " ";
        String HOUSE = " ";
        String TOT = " ";
        String ScreenOutput = " ";
        String BucketContents = "Candy!!\n\n";
        String PrintBucketReturn = " ";
        String HouseHeading = " ";

        String strInputTOT =args[0];
        String InputTOT = strInputTOT.substring(6);
        String strInputHOUSES =args[1];
        String InputHOUSES = strInputHOUSES.substring(6);
        String strInputCANDY =args[2];
        String InputCANDY = strInputCANDY.substring(4);
  
        if (args.length != 3)
        {
            System.out.println("Missing command line parameters – - Usage : INPUTFILENAME= OUTPUTFILENAME=");
            System.exit(0);
        }
        HashMap<String, Integer> candyList = new HashMap<>();
        HashMap<String, Integer> houseList = new HashMap<>();
        HashMap<String, Integer> totList = new HashMap<>();

        ArrayList<House> HouseArray = new ArrayList<House>();
        ArrayList<TrickOrTreater> TOTArray = new ArrayList<TrickOrTreater>();
        
        CreateCandyList(InputTOT,candyList);
        HouseHeading = CreateHouses(InputHOUSES,HouseArray, candyList);
        CreateTOTs(InputCANDY, HouseArray,TOTArray);
    
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(TrickOrTreater it: TOTArray)
        {
            executorService.execute(it);
        }
        TextAreaFrame TAF = new TextAreaFrame(); 
        TAF.setVisible(true);
        while(TrickOrTreater.ImDone != TOTArray.size())
        {
            ScreenOutput = String.format("%s", HouseHeading); 
            for(TrickOrTreater it: TOTArray)
            {
                ScreenOutput += String.format("%s%s\n", it.getPath(), it.getName());
            }
                TAF.textArea.setText(ScreenOutput); 
        }
        executorService.shutdown();    
        for(TrickOrTreater x: TOTArray)    
        {
            BucketContents += x.printBucket(); 
        }
        TAF.textArea.setText(BucketContents);   
    
    }
    
}
