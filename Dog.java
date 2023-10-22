/*
 * Samriddha Kharel 1001918169
 */
package object;

/**
 *
 * @author samdm
 */
public class Dog
{
    private String color;
    private String make;
    private String model;
    private int mileage =10;
    private int doors;
    private boolean kLMno;
    //private int age;
    
    public Dog(String CarColor,String CarMake,String CarModel,int NumDoors)
    {
        color=CarColor;
        make=CarMake;
        model=CarModel;
        doors=NumDoors;

    }
     
    public void setMileAge(int mileage)
    {
        if(mileage > this.mileage)
        {
            this.mileage = mileage;
        }
    }
    public int getMileAge()
    {
        return mileage;
    }
    private boolean isNewCar()
    {
        if (mileage < 100)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public String toString()
    {
        return String.format("My Car is a " + color +" "+ make +" " + model +" with "+mileage+" miles on it which meaans it is " +(isNewCar() == true? "new":"old"));

    }
}
