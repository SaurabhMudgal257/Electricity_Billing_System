
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{
    String meter;
    JButton back;
    Paytm(String meter){
        super("Paytm");
        this.meter = meter;
       
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try{
            j.setPage("https://paytm.com/electricity-bill-payment");
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<htnl>Could not load<html>");
            
            
        }
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640,20,80,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        j.add(back);
       setSize(800,600);
       setLocation(250,50);
       setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new PayBill(meter);
    }
    public static void main(String [] args){
        new Paytm("");
    }
}
