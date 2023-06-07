package SwingPD;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class MainForm extends JFrame
{
    private Connection connection;
    private JMenuBar menubar;
    private JMenu menu1,menu2,menu3;
    private JMenuItem item1,item2,item3,item4,item5,item6,item7;
    
    private JToolBar toolbar;
    private JButton b1,b2,b3,b4,b5,b6,b7;
    
    private void initmenubar()
    {
        item1=new JMenuItem("create person");
        item2=new JMenuItem("delete person");
        item3=new JMenuItem("update person");
        item4=new JMenuItem("email");
      item5=new JMenuItem("HTML Report");
      item6=new JMenuItem("PDF Report");
      item7=new JMenuItem("EXCEL Report");
      
      menu1=new JMenu("Persons");
      menu2=new JMenu("Options");
      menu3=new JMenu("Report");
      
      menu1.add(item1);
      menu1.add(item2);
      menu1.add(item3);
      
      menu3.add(item5);
      menu3.add(item6);
      menu3.add(item7);
      
      menu2.add(item4);
      menu2.add(menu3);
      
      menubar=new JMenuBar();
      menubar.add(menu1);
      menubar.add(menu2);
      this.setJMenuBar(menubar);
    }
        
    private void inittoolbar()
    {
        b1=new JButton(new ImageIcon("/src/images/create.gif"));
        b2=new JButton(new ImageIcon("/src/images/delete.GIF"));
        b3=new JButton(new ImageIcon("/src/images/update.gif"));
        b4=new JButton(new ImageIcon("/src/images/email.gif"));
        b5=new JButton(new ImageIcon("/src/images/html.gif"));
        b6=new JButton(new ImageIcon("/src/images/pdf.gif"));
        b7=new JButton(new ImageIcon("/src/images/excel.gif"));
        
        toolbar=new JToolBar();
        toolbar.add(b1);
        toolbar.add(b2);
        toolbar.add(b3);
        toolbar.add(b4);
        toolbar.add(b5);
        toolbar.add(b6);
        toolbar.add(b7);
        
        b1.setToolTipText("Create Person CTRL+F1");
        b2.setToolTipText("Update Person CTRL+F2");
        b3.setToolTipText("Delete Person CTRL+F3");
        b4.setToolTipText("Email CTRL+F4");
        b5.setToolTipText("Generate PDF CTRL+F5");
        b6.setToolTipText("Generate Excel CTRL+F6");
        b7.setToolTipText("generate HTML CTRL+F7");
        
this.getContentPane().add(toolbar,BorderLayout.NORTH); 
    }
    
    private void inititemevent()
    {
        item1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createpersonform obj=new createpersonform(connection);
                obj.setVisible(true);
                
            }
        });
        
        item2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteperson1 obj1=new deleteperson1(connection);
                obj1.setVisible(true);
            }
        });
        
        item3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updatepersonform obj2=new updatepersonform(connection);
            }
        });
        
        item4.addActionListener(new ActionListener()
      {

           @Override
           public void actionPerformed(ActionEvent e)
           {
             EmailForm obj=new EmailForm(connection);
           }
      });
    }
    
    public MainForm(Connection c)
    {
        this.connection=c;
        
        this.initmenubar();
        this.inittoolbar();
        this.inititemevent();
       
        this.setTitle("Phone Directory");
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
      this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
}
