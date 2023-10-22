/*
 * Samriddha Kharel 1001918169
 */
package code3_1001918169;

/**
 *
 * @author samdm
 */
import java.util.Scanner;

enum ACTION
{
    DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
}
public class Code3_1001918169
{

    public static String displayMoney(int price)
    {
        String dollar = String.valueOf(price /100);
        String cent = String.valueOf(price %100);
        String money = "$" + dollar + "." + (cent.length() == 1 ? "0" : "") +cent;
        return money;
    }
     
    public static int cokeMenu()
    {
        int choice;
        Scanner scan = new Scanner (System.in);
        do
        {
            System.out.printf("\n\nPlease choose the following options\n"); 
            System.out.printf("0. Walk Away\n");
            System.out.printf("1. Buy a Coke\n");
            System.out.printf("2. Restock Machine\n");
            System.out.printf("3. Add change\n");
            System.out.printf("4. Display Machine Info\n");
            System.out.printf("\nChoice : ");
            try
            {
                choice = scan.nextInt();
            }
            catch (Exception e)
            {
                choice = -1;
                scan.nextLine();  
            }
            if(choice < 0 || choice > 4)
            {
                System.out.printf("Invalid menu choice. Please choose again.\n\n");
            }
        }
        while(choice < 0 || choice > 4);
        return choice;
    }

    public static void main(String[] args)
    {
        CokeMachine MyCokeMachine = new CokeMachine("CSE 1325 Coke Machine", 50, 500, 80);

        int choice=1;
        int inventoryLevel = MyCokeMachine.getInventoryLevel();
        int changelevel= MyCokeMachine.getChangeLevel();
        int totalpayment=0;
        //int cost = MyCokeMachine.getCokePrice();
        final int PRICE =MyCokeMachine.getCokePrice();
        //int change = totalpayment-cost;
        //int refer[]= {changelevel,inventoryLevel,totalpayment};
        //String[] array = new String[5];
        //inventoryLevel=refer[1];
        //changelevel =refer[0];
        do
        {
            System.out.println(MyCokeMachine.getMachineName());
            Scanner scan = new Scanner (System.in);
            choice = cokeMenu();         
            switch(choice)
            {
                case 0:
                if (MyCokeMachine.getNumberOfCokesSold() == 0) 
                {
                    System.out.println("Are you sure you aren't really thirsty and need a Coke?\n\n");
                }
                else
                {
                     System.out.println("Bye - enjoy your Coke.\n\n");
                }
                choice =0;
                break; 
                
                case 1:
                 
                if (MyCokeMachine.getInventoryLevel()>= 0 && MyCokeMachine.getInventoryLevel()<=MyCokeMachine.getMaxInventoryLevel())
                {
                        System.out.printf("A coke cost %s\n", displayMoney(MyCokeMachine.getCokePrice()));
                        System.out.print("Enter your payment (in Cents): ");
                        totalpayment = scan.nextInt();
                  
                        switch(MyCokeMachine.buyACoke(totalpayment))
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
                                System.out.printf("Here's your coke and your change of %s\n\n",displayMoney(MyCokeMachine.getchangeDispensed()));
                                break;
                            case NOINVENTORY:
                                System.out.printf("The Coke dispenser is out of inventory\n\n\n");
                                System.out.println("Unable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment.\n ");
                                break;
                                
                            default:
                                System.out.println("Unable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment.\n ");
                        }      
                }
                break;
                   
                case 2:
                    System.out.println("How much product are you adding to the machine?   ");
                    //inventoryLevel=refer[1];
                    int addInventory = scan.nextInt();
                    //addInventory +=inventoryLevel ;
                    if(MyCokeMachine.incrementInventoryLevel(addInventory)== true)
                    {
                        System.out.println("The coke machine has been restocked");
                        System.out.printf("Your inventory level is %d\n\n",MyCokeMachine.getInventoryLevel());
                    }
                    else
                    {
                        System.out.printf("You have exceeded your machine's max capacity - no inventory was added\n");
                        System.out.printf("Your inventory level is %d\n\n",MyCokeMachine.getInventoryLevel());  
                    }
                        
                    break;
                case 3:
                    System.out.println("How much change are you adding to the machine?");
                    //changelevel =refer[0];
                    int addChange = scan.nextInt();
                    //addChange += changelevel;
                    if(MyCokeMachine.incrementChangeLevel(addChange)== true)
                    {
                        System.out.println("Your change Level has been updated");
                        System.out.printf("Your change level is %s with a max capacity of %s\n\n",displayMoney(MyCokeMachine.getChangeLevel()),displayMoney(MyCokeMachine.getMaxChangeCapacity()));  

                    }
                    else
                    {
                        System.out.printf("You have exceeded your machine's max capacity - no change was added\n");
                        System.out.printf("Your change level is %s with a max capacity of %s\n\n",displayMoney(MyCokeMachine.getChangeLevel()),displayMoney(MyCokeMachine.getMaxChangeCapacity()));  
                    }

                    break;
               
                case 4:
                    System.out.println(MyCokeMachine);
                    break;

            }
        }
        while(choice != 0);  
           
    }    
}