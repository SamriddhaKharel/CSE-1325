/*
 * Samriddha Kharel 1001918169
 */
package code4_1001918169;

/**
 *
 * @author samdm
 */

public class CokeMachine
{

    
    enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT,NOINVENTORY,TRASH
    }
    
    private String machineName;
    private int changeLevel;
    private int maxChangeCapacity =5000;
    private int inventoryLevel;
    private int maxInventoryCapacity=100;
    private int CokePrice;
    private int changeDispensed;
    private static int numberOfCokesSold=0;

    
    CokeMachine(String name,int cost,int change, int inventory)
    {
        machineName=name;
        CokePrice=cost;
        changeLevel=change;
        inventoryLevel=inventory;  
    }
    CokeMachine()
    {
        machineName= "New Machine";
        CokePrice =50;
        changeLevel=500;
        inventoryLevel=100;
    }
    
    public void setMachineName(String newMachineName)
    {
        machineName = newMachineName;

    }
    public void setCokePrice(int newCokePrice)
    {
        CokePrice = newCokePrice;
    }
    public String getMachineName()
    {
        return machineName;            
    }
    
    public int getChangeLevel()
    {
        return changeLevel;
  
    }
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity;
  
    }
    public int getInventoryLevel()
    {
        return inventoryLevel;
    }
    public int getMaxInventoryLevel()
    {
        return maxInventoryCapacity;
  
    }
    public int getCokePrice()
    {
        return CokePrice;
  
    }
    public int getchangeDispensed()
    {
        return changeDispensed;
  
    }
    public static int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
  
    }
    public boolean incrementInventoryLevel(int amountToAdd)
    {
        
        if( inventoryLevel+amountToAdd <= maxInventoryCapacity )
        {
            inventoryLevel+=amountToAdd;
            return true;

        }
        else
        {
            return false;
        }
        
    }
    public boolean incrementChangeLevel(int amountToAdd)
    {
        if(changeLevel+amountToAdd <= maxChangeCapacity)
        {
            changeLevel += amountToAdd;
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public ACTION buyACoke(int payment)
    {
        ACTION rating =ACTION.EXACTPAYMENT;
        int refer[]= {changeLevel,inventoryLevel,payment};
        if(payment == CokePrice && inventoryLevel!=0)
        {
            
            inventoryLevel=inventoryLevel-1;
            changeLevel=changeLevel+payment;
            if(changeLevel+payment > maxChangeCapacity)
            {
                inventoryLevel=inventoryLevel+1;
                changeLevel=changeLevel-payment;
                rating =ACTION.TRASH;
            }
            else
            {
                
                numberOfCokesSold++;
                rating =ACTION.EXACTPAYMENT;
            }

        }
       
        
        if(payment > CokePrice && inventoryLevel!=0)
        {    
            if (payment-CokePrice < refer[0])
            {
                changeDispensed = payment-CokePrice;
                changeLevel=changeLevel-changeDispensed;
                inventoryLevel=inventoryLevel-1;
                changeLevel=changeLevel+payment;
                if(changeLevel+payment > maxChangeCapacity)
                {
                    inventoryLevel=inventoryLevel+1;
                    changeLevel=changeLevel-payment;
                    rating =ACTION.TRASH;
                }
                else
                {
                   
                    numberOfCokesSold++;
                    rating =ACTION.DISPENSECHANGE;
                }
            }
        }
        if (payment < CokePrice && inventoryLevel!=0)
        {
            rating =ACTION.INSUFFICIENTFUNDS;
        }
        if(changeLevel < payment && inventoryLevel!=0)
        {
            rating =ACTION.INSUFFICIENTCHANGE;
        }
        if(inventoryLevel == 0 )
        {
            rating=ACTION.NOINVENTORY;
        }
        return rating;
    }
    
    @Override
    public String toString()
    {  
        return String.format("Machine Name \t\t\t%-36s \nCurrent Inventory Level \t%-6d \nCurrent Change Level \t\t%-6d \nLast Change Dispensed \t\t%-7d \nInventory Capacity \t\t%-12d \nChange Capacity \t\t%-16d \nCoke Price \t\t\t%-19d\nCoke Sold \t\t\t%-19d",machineName,inventoryLevel,changeLevel,changeDispensed,maxInventoryCapacity,maxChangeCapacity,CokePrice,numberOfCokesSold);    
    }
    
    
    }

