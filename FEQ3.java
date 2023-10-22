/*
 * Samriddha Kharel 1001918169
 */
package feq3;
import java.util.ArrayList;

/**
 *
 * @author samdm
 */
public class FEQ3
{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        
        ArrayList<UTAPerson> ABC = new ArrayList<>();
        
        ABC.add(new Student("Student"));
        
        ABC.add(new Employee("Employee"));
        
        for( UTAPerson it : ABC)
        {
            
            it.WhoAmI();
            
        }
        
    }
    
}