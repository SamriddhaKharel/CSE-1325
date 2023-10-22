/*
 * Samriddha Kharel 1001918169
 */
package code6_1001918169;

/**
 *
 * @author samdm
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing. Icon;
import javax.swing. ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.Random;
import javax.swing.JOptionPane;


public class GameFrame extends JFrame
{
    private final JLabel label1;
    private final JButton OKButton;
    private final JButton CancelButton; 
    private final JTextField textField1;
    String CCName; 

    public GameFrame()
    {
      
        super("Welcome to My Guessing Game"); 	
        setLayout (new FlowLayout());        
        int random;
	    Random ran = new Random();
        random = ran.nextInt(4)+1;

        switch (random) 
        {
            case 1:
            CCName="scooby";    
            break;
            
            case 2:
            CCName="shaggy";    
            break;
            
            case 3:
            CCName="fred";      
            break;
            
            case 4:
            CCName="daphne";                
            break;
        }

        Icon CC = new ImageIcon(getClass().getResource(CCName + ".png"));   
        label1 = new JLabel("Label with text and icon", CC, SwingConstants.LEFT);
        label1.setText("What is cartoon character's name?");
        label1.setIcon(CC);
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setVerticalTextPosition(SwingConstants.BOTTOM);
        label1.setToolTipText("Character from Scooby-Doo");
        add(label1);
        EventHandler handler = new EventHandler();
        textField1 = new JTextField("Type your answer here"); 
        textField1.selectAll();
        add(textField1);
        textField1.addActionListener(handler);
        OKButton = new JButton("OK");
        add(OKButton);
        OKButton.addActionListener(handler);
        CancelButton = new JButton("Cancel");
        add(CancelButton);
        CancelButton.addActionListener(handler);
                                   
           
    }
    
    private class EventHandler implements ActionListener
    {
        
        @Override
        
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            String text="";  

            boolean guess = false;
            if (event.getSource() == textField1)      
            {
                text = event.getActionCommand().toLowerCase();  
            }
            if (event.getSource() == OKButton)      
            {
                text = textField1.getText().toLowerCase();  

            }

            if (text.equals(CCName))      
            {
                string = String.format("You guessed correctly");
                guess =true;
            }
            else
            {
                string = String.format("This is not correct. Please try again.");
   
            }
            if (event.getSource() == OKButton || event.getSource() == textField1 )      
            {
                if(guess == true)
                {
                    JOptionPane.showMessageDialog(null, string);
                    System.exit(0);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, string);

                }
            }
            else if(event.getSource() == CancelButton)
            {
                System.exit(0);
            }
             
            
            
            
            
            
            
        }
    }
   
}
