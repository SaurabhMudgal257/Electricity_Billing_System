package electricity.billing.system;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;


public class PayBill extends JFrame implements ActionListener {
    Choice cmonth;
    String meter;
    JButton pay,back;
    PayBill(String meter){
        super("Pay Bill");
        this.meter = meter;
        setLayout(null);
        setBounds(200,50,900,600);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35,80,200,20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(300,80,200,20);
        add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,130,200,20);
        add(lblname);
        
        JLabel lname = new JLabel("");
        lname.setBounds(300,130,200,20);
        add(lname);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35,180,200,20);
        add(lblmonth);
       
        cmonth = new Choice();
        cmonth.setBounds(300,180,200,20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35,230,200,20);
        add(lblunits);
        
        JLabel lunits = new JLabel("");
        lunits.setBounds(300,230,200,20);
        add(lunits);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35,280,200,20);
        add(lbltotalbill);
        
        JLabel ltotalbill = new JLabel("");
        ltotalbill.setBounds(300,280,200,20);
        add(ltotalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35,330,200,20);
        add(lblstatus);
        
        JLabel lstatus = new JLabel("");
        lstatus.setBounds(300,330,200,20);
        lstatus.setForeground(Color.red);
        add(lstatus);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no ='"+meter+"' ");
            while(rs.next()){
                meternumber.setText(meter);
                lname.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter_no ='"+meter+"' and month = '"+cmonth.getSelectedItem()+"' ");
            while(rs.next()){
               lunits.setText(rs.getString("units"));
                
                ltotalbill.setText(rs.getString("totalbill"));
                lstatus.setText(rs.getString("status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        cmonth.addItemListener(new ItemListener (){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                  Conn c = new Conn();
            
                  ResultSet rs = c.s.executeQuery("select * from bill where meter_no ='"+meter+"' and month = '"+cmonth.getSelectedItem()+"' ");
                 while(rs.next()){
                    lunits.setText(rs.getString("units"));
                    ltotalbill.setText(rs.getString("totalbill"));
                    lstatus.setText(rs.getString("status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            }
        });
        
        pay = new JButton("Pay");
        pay.setBounds(100,460,100,25);
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("Back");
        back.setBounds(230,460,100,25);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==pay){
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no ='"+meter+"' and month = '"+cmonth.getSelectedItem()+"' ");
                
            }catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        }else{
            setVisible(false);
        }
    }
    public static void main(String [] args){
        new PayBill("");
    }
}
