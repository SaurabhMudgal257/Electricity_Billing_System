package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class CustomerDetails extends JFrame implements ActionListener {
    
    JTable table;
    JButton  print;
    CustomerDetails(){
        super("Customer Details"); //Frame title
        
        setSize(1100,650);
        setLocation(100,20);
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        print = new JButton("Print");
       
        print.addActionListener(this);
        add(print, "South");
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
            try {
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    
    public static void main(String[] args){
        new CustomerDetails();
    }
}
