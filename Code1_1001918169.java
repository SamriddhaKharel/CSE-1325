/*
 * Samriddha Kharel 1001918169
 */
package code1_1001918169;

/**
 *
 * @author samdm
 */
import java.util.Scanner;
enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
public class Code1_1001918169
{


    public static String displayMoney(int price,int inventory,int choice,int level,int cost,int totalpayment)
    {
        int x =price;
        if(choice == 3)
        {
            String dollar = String.valueOf(level / 100);
            String cent = String.valueOf(level % 100);
            return "The current change level is $" +dollar + "." + cent + (cent.length() == 1 ? "0" : "");        
        }
        if(choice ==2)
        {
            int change = totalpayment-cost;
            String dollar = String.valueOf(change / 100);
            String cent = String.valueOf(change % 100);
            return "Here's your pencils and your change of $" +dollar + "." + cent + (cent.length() == 1 ? "0" : "");
        }
        if(choice == 1)        
        {
            String dollar = String.valueOf(price / 100);
            String cent = String.valueOf(price % 100);
            return "A pencil costs $"+dollar + "." + cent + (cent.length() == 1 ? "0" : "");
        }
     
            String dollar = String.valueOf(cost / 100);
            String cent = String.valueOf(cost % 100);
            return "Your total is $"+dollar + "." + cent + (cent.length() == 1 ? "0" : "");

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
            System.out.printf("\n\nChoice : ");
            choice = scan.nextInt();

            if(choice < 0 && choice > 3)
            {
                System.out.printf("Invalid menu choice");

            }
        }
        while(choice < 0 || choice > 3);
        return choice;
    }
    
    public static ACTION buyPencils(int inventoryLevel ,int choice, int changelevel,int totalpayment, int refer[],int quantity,int PRICE,String array[])
    {
        int cost = quantity*PRICE;
        ACTION rating =ACTION.EXACTPAYMENT;
        if(totalpayment == cost && quantity > 0)
        {
            inventoryLevel=refer[0]-quantity;
            changelevel=refer[1]+totalpayment;
            refer[0]=inventoryLevel;
            refer[1]=changelevel;
            rating =ACTION.EXACTPAYMENT;
        }
        if(totalpayment > cost)
        {
            int change = totalpayment-cost;
            if (change < refer[1])
            {
                inventoryLevel=refer[0]-quantity;
                changelevel=refer[1]+totalpayment;

                refer[0]=inventoryLevel;
                refer[1]=changelevel-change;
                choice =2;
                array[0]=displayMoney(PRICE,inventoryLevel,choice,changelevel,cost,totalpayment);
                rating =ACTION.DISPENSECHANGE;
            }
        }
        if (totalpayment < cost)
        {

            rating =ACTION.INSUFFICIENTFUNDS;
        }
        if(changelevel < totalpayment )
        {
            array[2]=displayMoney(PRICE,inventoryLevel,choice,changelevel,cost,totalpayment);
            rating =ACTION.INSUFFICIENTCHANGE;
        }
            return rating;        
    }
    
    public static void main(String[] args)
    {
        System.out.println("Welcome to my Pencil Machine");
        boolean cap = true;
        int choice=1;
        int inventoryLevel = 100;
        int changelevel=500;
        int insufficent =0;
        int totalpayment=0;
        int quantity=0;
        int cost=0;
        int change = totalpayment-cost;
        final int PRICE = 60;
        int refer[]= {inventoryLevel,changelevel,totalpayment};
        String[] array = new String[5];
        inventoryLevel=refer[0];
        changelevel =refer[1];


        do
        {
            Scanner scan = new Scanner (System.in);
            choice = PencilMenu();
            switch(choice)
            {
                case 0:
                choice =0;
                break; 
                case 1:

                    if (inventoryLevel == 0)                        
                    {
                        System.out.print("The Pencil dispenser is out of inventory\n");
                    }
                    if (inventoryLevel!= 0)
                    {
                        choice =1;
                        System.out.printf("%s\n", displayMoney(PRICE,inventoryLevel,choice,changelevel,cost,totalpayment));
                        choice =11;
                        System.out.print("How many pencils would you like to purchase? ");
                        quantity = scan.nextInt();
                        if (quantity > 0 && quantity <= inventoryLevel )
                        {
                            cost = quantity*PRICE;
                            System.out.printf("%s\n", displayMoney(PRICE,inventoryLevel,choice,changelevel,cost,totalpayment));
                            System.out.print("Enter your payment (in Cents): ");
                            totalpayment = scan.nextInt();
                            ACTION action =buyPencils(inventoryLevel,choice,changelevel,totalpayment,refer,quantity,PRICE,array);

                            switch(action)
                            {
                                case EXACTPAYMENT:
                                System.out.printf("Here's your pencils. Thank you for exact payment ");
                                break;

                                case INSUFFICIENTFUNDS:
                                System.out.print("You did not enter a sufficent fund. No pencils for you.");
                                break;

                                case INSUFFICIENTCHANGE:
                                System.out.print("The pencil Dispenser does not have enough change and cannot accept your payment.");
                                break;

                                case DISPENSECHANGE:
                                System.out.printf("%s ",array[0]);
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
                                System.out.printf("%s\n", displayMoney(PRICE,inventoryLevel,choice,changelevel,cost,totalpayment));
                                System.out.print("Enter your payment (in Cents): ");
                                totalpayment = scan.nextInt();
                                 ACTION action =buyPencils(inventoryLevel,choice,changelevel,totalpayment,refer,quantity,PRICE,array);
                                switch(action)
                                {
                                    case EXACTPAYMENT:
                                    System.out.printf("Here's your pencils. Thank you for exact payment ");
                                    break;
                                        
                                    case INSUFFICIENTFUNDS:
                                    System.out.print("You did not enter a sufficent fund. No pencils for you.");
                                    break;
                                        
                                    case INSUFFICIENTCHANGE:
                                    System.out.print("The pencil Dispenser does not have enough change and cannot accept your payment.");
                                    break;
                                        
                                    case DISPENSECHANGE:
                                    System.out.printf("%s ",array[0]);
                                    break;
                                        
                                    default:
                                    System.out.println("something unknown happened");
                                    }
                                }
                            }
                    }          
                break;
                
                case 2:
                inventoryLevel=refer[0];
                changelevel =refer[1];
                System.out.printf("\nYour inventory is %d",inventoryLevel);
                break;
                
                case 3:
                inventoryLevel=refer[0];
                changelevel =refer[1];
                System.out.printf("%s\n", displayMoney(PRICE,inventoryLevel,choice,changelevel,cost,totalpayment));
                break;
                
                default:
                System.out.println("Invalid menu choice. Please choose again.");
            }
        }
        while(choice != 0); 
    }    
}
    


        
















