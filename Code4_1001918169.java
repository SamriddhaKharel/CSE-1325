/*
 * Samriddha Kharel 1001918169
 */
package code4_1001918169;

/**
 *
 * @author samdm
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

enum ACTION
{
    DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
}
public class Code4_1001918169
{

    public static String displayMoney(int price)
    {
        String dollar = String.valueOf(price /100);
        String cent = String.valueOf(price %100);
        String money = "$" + dollar + "." + (cent.length() == 1 ? "0" : "") +cent;
        return money;
    }
    
    public static void ReadFile(String fileName,ArrayList<CokeMachine> Machine)throws FileNotFoundException
    {
        File fileIn = new File(fileName);
        Scanner scanFile = null;
        try
        {
            scanFile = new Scanner(fileIn);
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("%s file name does not exist...exiting\n",fileName);
            System.exit(0);
        }
        String FileLine[]=new String[4];
        int intArray[]= new int[3];

        while(scanFile.hasNextLine())
        {
            FileLine = scanFile.nextLine().split("\\|");       
        }
            scanFile.close();   
    }
    public static void WriteFile(String outputFileName,ArrayList<CokeMachine> Machine)throws FileNotFoundException
    {
        
        Scanner scan = new Scanner (System.in);
        PrintWriter writer = new PrintWriter(outputFileName);
        File fileIn = new File(outputFileName);
        Scanner scanFile = null;
        try
        {
            scanFile = new Scanner(fileIn);
        }
        catch(FileNotFoundException e)
        {
            System.out.print("Unable to write output file\n");
            System.out.println(e.getMessage());
            throw new FileNotFoundException();
        }
        for(CokeMachine s:Machine)
        {
            writer.print(s.getMachineName()+"|"+s.getCokePrice()+"|"+s.getChangeLevel()+"|"+s.getInventoryLevel());
            writer.printf("\n");
        }
        writer.close();
        
    }
 
    public static int cokeMenu()
    {
        int choice =0;
        Scanner scan = new Scanner (System.in);
        do
        {
            System.out.printf("\n0. Walk Away\n");
            System.out.printf("1. Buy a Coke\n");
            System.out.printf("2. Restock Machine\n");
            System.out.printf("3. Add change\n");
            System.out.printf("4. Display Machine Info\n");
            System.out.printf("5. Update Machine Name\n");
            System.out.printf("6. Update Coke Price\n");
            System.out.print("\nEnter a choice ");
            try
            {
                choice = scan.nextInt();
            }
            catch (Exception e)
            {
                choice = -1;
                scan.nextLine();  
            }
            if(choice < 0 || choice > 6)
            {
                System.out.printf("Invalid menu choice. Please choose again.\n\n");
            }
        }
        while(choice < 0 || choice > 6);
        return choice;
    }
  
    public static int machineMenu(ArrayList<CokeMachine> SetOfCokeMachines)
    {
        int machineReturn=-1;
        Scanner scan = new Scanner(System.in);     
        do
        { 
            System.out.println("Pick a Coke Machine \n");
            System.out.printf("0. Exit\n");
            for(int i =0;i< SetOfCokeMachines.size();i++)
            {
                System.out.printf("%d. "+SetOfCokeMachines.get(i).getMachineName(),i+1);
                System.out.println("");
            }       
            System.out.print(SetOfCokeMachines.size()+1+". Add new machine ");
            System.out.print("\n\nEnter a choice ");
            try
            {
                machineReturn = scan.nextInt();
            }

            catch (Exception e)
            {
                machineReturn = -1;
                scan.nextLine();  
            }
            if(machineReturn < 0 || machineReturn > SetOfCokeMachines.size()+1)
            {
                System.out.printf("Invalid menu choice. Please choose again.\n\n");
            }  
        }
        while(machineReturn < 0 || machineReturn > SetOfCokeMachines.size()+1);    
        return machineReturn;
    }

    public static void main(String[] args)throws FileNotFoundException
    {
        ArrayList <CokeMachine> SetOfCokeMachines = new ArrayList<>();

        CokeMachine MyCokeMachine = new CokeMachine("CSE 1325 Coke Machine", 70, 500, 80);
        Scanner scan = new Scanner (System.in);

        int choice = 0;
        int machineReturn =-1;
        int inventoryLevel = MyCokeMachine.getInventoryLevel();
        int changelevel= MyCokeMachine.getChangeLevel();
        int totalpayment=0;
        int newCokePrice;
        final int PRICE =MyCokeMachine.getCokePrice();
        String newMachineName;
        String strInput =args[0];
        String inputFileName = strInput.substring(14);
        String strOutput =args[1];
        String outputFileName = strOutput.substring(15);
        if (args.length != 2)
        {
            System.out.println("Missing command line parameters – - Usage : INPUTFILENAME= OUTPUTFILENAME=");
            System.exit(0);
        }
        else
        {
                ReadFile(inputFileName,SetOfCokeMachines);     
        }
            machineReturn = machineMenu(SetOfCokeMachines);

        while(machineReturn !=0)
        {
            if(machineReturn == SetOfCokeMachines.size()+1)
            {    
                MyCokeMachine = new CokeMachine();
                SetOfCokeMachines.add(MyCokeMachine);
                machineReturn = machineMenu(SetOfCokeMachines);
            }
            else
            {
                MyCokeMachine = SetOfCokeMachines.get(machineReturn-1); 
                do
                {
                    System.out.println("\n"+MyCokeMachine.getMachineName());           
                    choice = cokeMenu();    
                    switch(choice)
                    {
                        case 0:    
                        machineReturn = machineMenu(SetOfCokeMachines);
                        break;  
                        case 1: 
                        if (MyCokeMachine.getInventoryLevel()>= 0 && MyCokeMachine.getInventoryLevel()<=MyCokeMachine.getMaxInventoryLevel())
                        {
                            System.out.printf("A coke cost %s\n", displayMoney(SetOfCokeMachines.get(machineReturn-1).getCokePrice()));
                            System.out.print("Enter your payment (in Cents): ");
                            totalpayment = scan.nextInt();

                            switch(SetOfCokeMachines.get(machineReturn-1).buyACoke(totalpayment))
                            {
                                case EXACTPAYMENT:
                                System.out.printf("Thank your for exact payment.\nHere's your Coke\n\n");
                                break;
                                case INSUFFICIENTFUNDS:
                                System.out.print("Insufficent payment...returning you payment\n\n");
                                break;
                                case INSUFFICIENTCHANGE:
                                System.out.print("Unable to give change at this time... returning your payment.\n\n");
                                break;
                                case DISPENSECHANGE:
                                System.out.printf("Here's your coke and your change of %s\n\n",displayMoney(SetOfCokeMachines.get(machineReturn-1).getchangeDispensed()));
                                break;
                                case NOINVENTORY:
                                System.out.printf("The Coke dispenser is out of inventory\n\n\n");
                                System.out.println("Unable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment.\n ");
                                break;

                                default:
                                System.out.println("Unable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment.\n ");  
                            }      
                            break;  
                        }
                        case 2:
                        System.out.println("How much product are you adding to the machine?   ");
                        int addInventory = scan.nextInt();
                        if(SetOfCokeMachines.get(machineReturn-1).incrementInventoryLevel(addInventory)== true)
                        {
                            System.out.println("The coke machine has been restocked");
                            System.out.printf("Your inventory level is %d\n\n",SetOfCokeMachines.get(machineReturn-1).getInventoryLevel());
                        }
                        else
                        {
                            System.out.printf("You have exceeded your machine's max capacity - no inventory was added\n");
                            System.out.printf("Your inventory level is %d\n\n",SetOfCokeMachines.get(machineReturn-1).getInventoryLevel());  
                        }

                        break;
                        case 3:
                        System.out.println("How much change are you adding to the machine?");
                        int addChange = scan.nextInt();
                        if(MyCokeMachine.incrementChangeLevel(addChange)== true)
                        {
                            System.out.println("Your change Level has been updated");
                            System.out.printf("Your change level is %s with a max capacity of %s\n\n",displayMoney(SetOfCokeMachines.get(machineReturn-1).getChangeLevel()),displayMoney(SetOfCokeMachines.get(machineReturn-1).getMaxChangeCapacity()));  
                        }
                        else
                        {
                            System.out.printf("You have exceeded your machine's max capacity - no change was added\n");
                            System.out.printf("Your change level is %s with a max capacity of %s\n\n",displayMoney(SetOfCokeMachines.get(machineReturn-1).getChangeLevel()),displayMoney(SetOfCokeMachines.get(machineReturn-1).getMaxChangeCapacity()));  
                        }
                        break;

                        case 4:
                        System.out.println(MyCokeMachine);
                        break;

                        case 5:
                        System.out.println("Enter a new Machine Name ");
                        newMachineName = scan.nextLine();
                        MyCokeMachine.setMachineName(newMachineName);
                        System.out.println("\n\nMachine name has been updated ");
                        break;

                        case 6:
                        System.out.println("Enter a new Coke Price ");
                        newCokePrice=scan.nextInt();
                        MyCokeMachine.setCokePrice(newCokePrice);
                        System.out.println("\n\nCoke Price has been updated ");
                        break;
                    }
                    int cokeSold = MyCokeMachine.getNumberOfCokesSold();
                }
                while(choice != 0);                     
            }   
        }
        System.out.printf("%d Coke(s) were sold today!\n",MyCokeMachine.getNumberOfCokesSold());
        WriteFile(outputFileName,SetOfCokeMachines);
    }    
}