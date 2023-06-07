/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SwingPD;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class updatepersonform extends JFrame {
    private final Connection connection;
    JComboBox cbox;
    JTextField t1,t2,t3,t4;
    JButton b1,b2;
    JPanel p1,p2,p3;
    
    
    private void initComponent() {

        cbox=new JComboBox();
        
        t1=new JTextField(15);
        t2=new JTextField(15);
        t3=new JTextField(15);
        t4=new JTextField(15);
        
        b1=new JButton("Reset");
        b2=new JButton("Update");
        this.getRootPane().setDefaultButton(b2);
        
        p1=new JPanel(new GridLayout(1,1,5,5));
        p1.setBorder(BorderFactory.createTitledBorder("Select Person"));
        p1.add(cbox);
        
       p2=new JPanel(new GridLayout(4,2,5,5));
       p2.setBorder(BorderFactory.createTitledBorder("Update Person Detail"));
       p2.add(new JLabel("Name"));
       p2.add(t1);
       p2.add(new JLabel("Mobile"));
       p2.add(t2);
       p2.add(new JLabel("Email"));
       p2.add(t3);
       p2.add(new JLabel("Address"));
       p2.add(t4);
       
       p3=new JPanel(new GridLayout(1,3,5,5));
       p3.setBorder(BorderFactory.createTitledBorder("Actions"));
       p3.add(b1);
       p3.add(b2);
               
       
        this.getContentPane().add(p1,BorderLayout.NORTH);
        this.getContentPane().add(p2,BorderLayout.CENTER);
        this.getContentPane().add (p3,BorderLayout.SOUTH);
    }

    private void initComboBoxEvent() {
        cbox.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent fe)
            {
                fillComboBox();
            }
        });
        cbox.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                fillPersonData();
            }
        });
    }
    
    private void fillComboBox()
    {
     
        try
        {
            cbox.removeAllItems();
            PreparedStatement ps=connection.prepareStatement("select email,name from create_person order by name asc");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                String item=rs.getString(1)+"-"+rs.getString(2);
                cbox.addItem(item);
            }
        }
        catch(Exception e){}
  }
    
    private void fillPersonData()
    {
        try
        {
           String item=cbox.getSelectedItem().toString();
           String a[]=item.split("-");
           PreparedStatement ps=connection.prepareStatement("select * from create_person where email=?");
           ps.setString(1,a[1]);
           ResultSet rs=ps.executeQuery();
       
           if(rs.next())
           {
              t1.setText(rs.getString(1));
               t2.setText(rs.getString(2));
               t3.setText(rs.getString(3));
               t4.setText(rs.getString(4));
           }
        }
        catch(Exception e){}
    }

    private void initButtonEvent() {
        
        b1.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e)
           {
               reset();
           }
       });
       b2.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e)
           {
              update();
           }
       });
        
    }
    
    private void reset() {
         t1.setText("");
       t2.setText("");
       t3.setText("");
       t4.setText("");
       t1.requestFocus();
    }
    
    private void update() {
           
        try
       {
            System.out.println("update1");
            PreparedStatement ps=connection.prepareStatement("update create_person set name=?,phone=?,email=?,address=? where email=?");
            System.out.println("update2");
            ps.setString(1,t1.getText());
            ps.setString(2,t2.getText());
            ps.setString(3,t3.getText());
            ps.setString(4,t4.getText());
            System.out.println("update3");
            ps.setString(5, cbox.getSelectedItem().toString().split("-")[0]);
            System.out.println("update4");
            int a=ps.executeUpdate();  
            System.out.println("update5");
            if(a>0)
            {
                reset();
            }
            System.out.println("update6");
       }
       catch(Exception e)
       {
           
       }
        
    }
    
    updatepersonform(Connection connection) {
        
        this.initComponent();
        //txt1.requestFocus();
        this.connection=connection;
       this.initComboBoxEvent();
        this.initButtonEvent();
        this.setTitle("Update Person Form");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setSize(300,300);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
}
