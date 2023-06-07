
package SwingPD;

import com.sun.jmx.snmp.BerDecoder;
import java.awt.BorderLayout;
import static java.awt.Color.black;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registration extends JFrame{
    
    private Connection connection;
    private JLabel l1,l2,l3,l4;
    private JTextField t1,t2,t3,t4;
    private JButton b1,b2;
    private JPanel p1,p2;

   /* Registration(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    
    private void initcomponentevent()
    {
        
        l1=new JLabel("Enter Name:");
        l2=new JLabel("Email Address:");
        l3=new JLabel("Enter Password:");
        l4=new JLabel("Conform Password:");
        
        t1=new JTextField(20);
        t2=new JTextField(20);
        t3=new JTextField(20);
        t4=new JTextField(20);
        
        b1=new JButton("Register");
        b2=new JButton("Reset");
        this.getRootPane().setDefaultButton(b1);
        
        p1=new JPanel(new GridLayout(4,4,10,4));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.setBorder(BorderFactory.createLineBorder(black,2));
        
        p2=new JPanel(new GridLayout(1,1));
        p1.setBorder(BorderFactory.createLoweredBevelBorder());
        p2.add(b1);
        p2.add(b2);
        
        
        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.SOUTH);
        
        
        
    }
    
    public Registration(Connection c)
    {
         this.connection=c;
        this.initcomponentevent();
        this.initbuttonevent();
        this.setTitle("Registration form");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
    }
    
    public void initbuttonevent()
    {
        b1.addActionListener(new ActionListener()
        {
        
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    Register();
                }
        }
        );
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Reset();
            }
        });
    }
    
    public void Reset()
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t1.requestFocus();
    }
    
    private void Register()
    {
        try
        {
            System.out.print("Hello1");
            int n=new Random().nextInt(1000);
            System.out.print(n);
           // PreparedStatement ps=connection.prepareStatement("insert into phonedirectory values(?,?,?,?)");
            PreparedStatement ps=connection.prepareStatement("insert into phonedirectory values(?,?,?,?)");
            System.out.print("Hello1");
            String n1=Integer.toString(n);
            ps.setString(1, n1);
            System.out.print("Hello1");
           
            ps.setString(2,t1.getText());
            ps.setString(3,t2.getText());
            ps.setString(4,t3.getText());
            System.out.print("Hello1");
            
             int a=ps.executeUpdate();
             if(a>0)
             {
                 Reset();
             }
                    
        }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
    }
}
