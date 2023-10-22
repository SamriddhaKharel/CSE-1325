/*
 * Samriddha Kharel 1001918169
 */
package code2_1001918169;
/**
 *
 * @author samdm
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

enum ACTION
{
    DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
}
public class Code2_1001918169
{
    public static String randomColors(ArrayList<String> colors)
    {
        Random num = new Random();
        int rand= num.nextInt(colors.size());
        String randColor = colors.get(rand);
        return randColor;    
    }
    
    public static void ReadFile(int refer[],String fileName,ArrayList<String> colors)throws FileNotFoundException
    {
        File fileIn = new File(fileName);
        Scanner scanFile = null;
        try
        {
            scanFile = new Scanner(fileIn);
        }
        catch(FileNotFoundException e)
        {
            System.out.print("You are missing a file.\n");
            System.exit(0);
        }
        
        String FileLine1 = scanFile.nextLine();
        String FileLine2 = scanFile.nextLine();

        String ints[] = FileLine1.split(" ");
        refer[0] = Integer.parseInt(ints[0]);
        refer[1] = Integer.parseInt(ints[1]);
        
        String colorArray[] = FileLine2.split(" ");
        
        for(String text:colorArray) 
        {
            colors.add(text);
        }
        scanFile.close();   
    }
    
    public static String displayMoney(int price)
    {
        String dollar = String.valueOf(price /100);
        String cent = String.valueOf(price %100);
        String money = "$" + dollar + "." + cent + (cent.length() == 1 ? "0" : "");
        return money;
    }
     
    public static int PencilMenu()
    {
        int choice;
        Scanner scan = new Scanner (System.in);
        do
        {
            
            System.out.printf("\n\nPlease choose the following options\n"); 
            System.out.printf("0. No pencil for me today\n");
            System.out.printf("1. Purchase pencils\n");
            System.out.printf("2. Check inventory level\n");
            System.out.printf("3. Check change level\n");
            System.out.printf("\nChoice : ");
            //choice = scan.nextInt();
            try
            {
                choice = scan.nextInt();
            }
            catch (Exception e)
            {
                choice = -1;
                scan.nextLine();  
            }
            if(choice < 0 || choice > 3)
            {
                System.out.printf("Invalid menu choice. Please choose again.");

            }
        }
        while(choice < 0 || choice > 3);
        return choice;
    }
           
    public static ACTION buyPencils(int totalpayment, int refer[],int quantity,int PRICE,String array[]) throws FileNotFoundException
    {
        int cost = quantity*PRICE;
        ACTION rating =ACTION.EXACTPAYMENT;
        int inventoryLevel = refer[0];
        int changelevel = refer[1];
        if(totalpayment == cost && quantity > 0)
        {
            inventoryLevel=refer[1]-quantity;
            changelevel=refer[0]+totalpayment;
            refer[0]=changelevel;
            refer[1]=inventoryLevel;
            
            rating =ACTION.EXACTPAYMENT;
        }
        if(totalpayment > cost)
        {
            int change = totalpayment-cost;
            if (change < refer[0])
            {
                inventoryLevel=refer[1]-quantity;
                changelevel=refer[0]+totalpayment;

                refer[1]=inventoryLevel;
                refer[0]=changelevel-change;
 
                array[0]=displayMoney(change);
                rating =ACTION.DISPENSECHANGE;
            }
        }
        if (totalpayment < cost)
        {
            rating =ACTION.INSUFFICIENTFUNDS;
        }
        if(changelevel < totalpayment )
        {
            array[2]=displayMoney(PRICE);
            rating =ACTION.INSUFFICIENTCHANGE;
        }
            return rating;        
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        int choice=1;
        int inventoryLevel = 0;
        int changelevel=0;
        int totalpayment=0;
        int quantity=0;
        int cost=0;
        String str =args[0];
        String fileName = str.substring(9);
        
        String priceString = args[1];
        priceString = priceString.substring(11);
        
        int PRICE=Integer.parseInt(priceString); 
        int change = totalpayment-cost;
        int refer[]= {changelevel,inventoryLevel,totalpayment};
        String[] array = new String[5];
        inventoryLevel=refer[1];
        changelevel =refer[0];
        
        ArrayList <String> colors = new ArrayList<>();
        ReadFile(refer,fileName,colors);
        System.out.println("Welcome to my Pencil Machine");
        inventoryLevel=refer[1];
        changelevel=refer[0];
        do
        {
            Scanner scan = new Scanner (System.in);
            choice = PencilMenu();
            PrintWriter writer = new PrintWriter(fileName);
  
            writer.print(refer[0]+" ");
            writer.print(refer[1]+" ");
            writer.printf("\n");
            for(String s:colors)
            {
                writer.print(s+" ");      
            }
            writer.close();

            switch(choice)
            {
                case 0:
                choice =0;
                break; 
                case 1:
                if (inventoryLevel == 0)   
                {
                    System.out.printf("The Pencil dispenser is out of inventory\n");
                }
                    
                if (inventoryLevel!= 0)
                {
                    System.out.printf("A pencil cost %s\n", displayMoney(PRICE));
                    System.out.print("How many pencils would you like to purchase? ");
                    quantity = scan.nextInt();
                    
                    if (quantity > 0 && quantity <= inventoryLevel )
                    {
                        cost = quantity*PRICE;
                        System.out.printf("Your total is %s\n", displayMoney(cost));
                        System.out.print("Enter your payment (in Cents): ");
                        totalpayment = scan.nextInt();
                        ACTION action =buyPencils(totalpayment,refer,quantity,PRICE,array);

                        switch(action)
                        {
                            case EXACTPAYMENT:
                                String n = randomColors(colors);
                                System.out.printf("Here's your %s pencils. Thank you for exact payment ",n);
                                break;
                            case INSUFFICIENTFUNDS:
                                System.out.print("You did not enter a sufficent fund. No pencils for you.");
                                break;
                            case INSUFFICIENTCHANGE:
                                System.out.print("The pencil Dispenser does not have enough change and cannot accept your payment.");
                                break;
                            case DISPENSECHANGE:
                                String c = randomColors(colors);
                                System.out.printf("Here's your %s pencils and your change of %s ",c,array[0]);
                                break;
                            default:
                                System.out.println("something unknown happened");
                        }
                        
                    }
                    while(quantity > inventoryLevel || quantity < 1)
                    {  
                        System.out.print("Cannnot sell that quantity of pencils. Please reenter quantity ");
                        quantity = scan.nextInt();
                        if (quantity > 0 && quantity <= inventoryLevel )
                        {
                            cost = quantity*PRICE;
                            System.out.printf("Your total is %s\n", displayMoney(cost));
                            System.out.print("Enter your payment (in Cents): ");
                            totalpayment = scan.nextInt();
                            ACTION action =buyPencils(totalpayment,refer,quantity,PRICE,array);
                            switch(action)
                            {
                                case EXACTPAYMENT:
                                String n = randomColors(colors);
                                System.out.printf("Here's your %s pencils. Thank you for exact payment ",n);                                
                                break;
                                
                                case INSUFFICIENTFUNDS:
                                System.out.print("You did not enter a sufficent fund. No pencils for you.");
                                break;
                                
                                case INSUFFICIENTCHANGE:
                                System.out.print("The pencil Dispenser does not have enough change and cannot accept your payment.");
                                break;
                                
                                case DISPENSECHANGE:
                                String c = randomColors(colors);
                                System.out.printf("Here's your %s pencils and your change of %s ",c,array[0]);                                
                                break;
                                
                                default:
                                System.out.println("something unknown happened");
                
                            }
                        }
                    }
                }
                    break;
                case 2:
                    inventoryLevel=refer[1];
                    changelevel =refer[0];
                    System.out.printf("Your inventory is %d",inventoryLevel);
                    break;
                case 3:
                    inventoryLevel=refer[1];
                    changelevel =refer[0];
                    System.out.printf("The current change level is %s\n", displayMoney(changelevel));
                    break;
            }
        }
        while(choice != 0);  
           
    }    
}
    


        
















