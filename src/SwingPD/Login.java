
package SwingPD;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JFrame
{
     private Connection connection;
    private JLabel l1,l2,l3;
    private JTextField t1,t2;
    private JButton b1,b2,b3;
    private JPanel p1,p2,p3;
   
    
    private void initcomponentEvent()
    {
        l1=new JLabel("Email");
        l2=new JLabel("Password");
        
        t1=new JTextField(20);
        t2=new JTextField(20);
        
        b1=new JButton("Login");
        b2=new JButton("Reset");
        b3=new JButton("Signup");
        this.getRootPane().setDefaultButton(b1);
        
        p1=new JPanel(new GridLayout(2,2));
        p1.setBorder(BorderFactory.createTitledBorder("Login"));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        
        p2=new JPanel(new GridLayout(1,2));
        p2.add(b1);
        p2.add(b2);
        
        p3=new JPanel(new GridLayout(1,2,2,2));
        p3.setBorder(BorderFactory.createTitledBorder("Signup"));
        p3.add(b3);
        
        
        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.CENTER);
        this.getContentPane().add(p3,BorderLayout.SOUTH);
        
    }
    public Login()
    {
        //this.connection=c
        this.initWiondoEvent();
       this.initcomponentEvent();
      
       this.initButtonEvent();
        this.setTitle("Login Form");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //this.setSize(300,300);
       this.setResizable(false);
        this.pack();
    }
         
    private void connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/swing","root","");
            if(!this.connection.isClosed())
            {
                //JOptionPane.showMessageDialog(null,"Welcome to Phone Book");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
            System.exit(0);
        }
    }
    
    private void disconnect()
    {
        try
        {
            if(!this.connection.isClosed())
            {
                this.connection.isClosed();
            }
        }
        catch(Exception e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
           System.exit(0);
        }
    }

    private void initWiondoEvent()
    {
        this.addWindowListener(new WindowAdapter() 
        {
           
            @Override
            public void windowOpened(WindowEvent e)
            {
                connect();
            }
            @Override
            public void windowClosing(WindowEvent e)
            {
                disconnect();
            }
            
        });
    }
    
    private void initButtonEvent()
    {
        b1.addActionListener(new ActionListener()
        {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                  Login();
              }
        });
        b2.addActionListener(new ActionListener()
        {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                  Reset();
              }
        });
        b3.addActionListener(new ActionListener()
        {
           
              @Override
              public void actionPerformed(ActionEvent e)
              {
                  Registration obj=new Registration(connection);
              }
        });
    }
    private void Reset()
   {
       t1.setText("");
       t2.setText("");
       t1.requestFocus();
   }
   
    private void Login()
   {
      
    try
       {
           int flag=1;
             final Connection connection=DriverManager.getConnection("jdbc:Mysql://127.0.0.1:3306/swing","root","");
             Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("select * from phonedirectory");
      while(rs.next())
         {
             if(rs.getString(3).equals(t1.getText())  && rs.getString(4).equals(t2.getText()) )
            {
               flag=0;
               break;
            }
         }
      if(flag==0)
      {
          //JOptionPane.showMessageDialog(null,"Valid User");
          
          b1.addActionListener(new ActionListener()
      {

           @Override
           public void actionPerformed(ActionEvent e)
           {
             MainForm obj=new MainForm(connection);
           }
       });
          
          
      }
      else 
             {
              JOptionPane.showMessageDialog(null,"Invalid User");
              }
         }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null,e);
       }
        
       }
   
    
}
