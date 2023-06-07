/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SwingPD;

import java.awt.BorderLayout;
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

/**
 *
 * @author Rohit
 */
public class createpersonform extends JFrame
{
    
    private Connection connection;
    private JTextField t1,t2,t3,t4;
    private JButton b1,b2;
    private JPanel p1,p2;
    private JLabel l1,l2,l3,l4;
    

  

 

    private void initcomponent() {
        t1=new JTextField(15);
        t2=new JTextField(15);
        t3=new JTextField(15);
        t4=new JTextField(15);
        
        l1=new JLabel("Name");
         l2=new JLabel("Mobile");
         l3=new JLabel("Email");
         l4=new JLabel("Address");
         
         b1=new JButton("Reset");
         b2=new JButton("Save");
         
         p1=new JPanel(new GridLayout(4,2,5,5));
         p1.setBorder(BorderFactory.createTitledBorder("Enter Person Details"));
         p1.add(l1);
         p1.add(t1);
         p1.add(l2);
         p1.add(t2);
         p1.add(l3);
         p1.add(t3);
         p1.add(l4);
         p1.add(t4);
         
         p2=new JPanel(new GridLayout(1,4,5,5));
         p2.setBorder(BorderFactory.createTitledBorder("Actions"));
         p2.add(b1);
         p2.add(new JLabel());
         p2.add(new JLabel());
         p2.add(b2);
         
         this.getContentPane().add(p1,BorderLayout.NORTH);
         this.getContentPane().add(p2,BorderLayout.SOUTH);
    }
    
      createpersonform(Connection connection) {
        this.connection=connection;
        this.initcomponent();
        this.initbuttonevent();
        this.setTitle("Create Person");
         this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.setResizable(false);
         this.pack();
         this.setLocationRelativeTo(null);
    }

    private void initbuttonevent() {
        
        b1.addActionListener(new ActionListener() 
         {

             @Override
             public void actionPerformed(ActionEvent ae) 
             {
                 reset();
             }
         });
         b2.addActionListener(new ActionListener() 
         {

             @Override
             public void actionPerformed(ActionEvent ae) 
             {
                 save();
             }
         });
        
    }
    
    private void reset()
     {
         t1.setText("");
         t2.setText("");
         t3.setText("");
         t4.setText("");
         t1.requestFocus();
     }
    private void save()
     {
         try
         {
             
             PreparedStatement ps=connection.prepareStatement("insert into create_person values(?,?,?,?)");
             
             ps.setString(1, t1.getText());
             ps.setString(2, t2.getText());
             ps.setString(3, t3.getText());
             ps.setString(4, t4.getText());
             
             int a=ps.executeUpdate();
             
             if(a>0)
             {
                 reset();
             }
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.toString());
         }
     }
    
    
}
