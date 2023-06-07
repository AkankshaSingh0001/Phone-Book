
package SwingPD;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
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
import javax.swing.JPanel;

/**
 *
 * @author Rohit
 */
public class deleteperson1 extends JFrame
{
    private final Connection connection;
    private JPanel p1,p2;
    private JComboBox cbox;
    private JButton b1;
    
    public void initcomponentevent()
    {
        cbox=new JComboBox();
      
       
       b1=new JButton("Delete");
       this.getRootPane().setDefaultButton(b1);
       p1=new JPanel(new GridLayout(1,1));
       p1.setBorder(BorderFactory.createTitledBorder("Select Person"));
       p1.add(cbox);
       
       p2=new JPanel(new GridBagLayout());
       p2.setBorder(BorderFactory.createTitledBorder("Action"));
       p2.add(b1);
       
       
       this.getContentPane().add(p1,BorderLayout.NORTH);
       this.getContentPane().add(p2,BorderLayout.SOUTH);
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
    }
    
    private void fillComboBox()
    {
     
        try
        {
            cbox.removeAllItems();
            PreparedStatement ps=connection.prepareStatement("select name,email from create_person order by name asc");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                String item=rs.getString(1)+"-"+rs.getString(2);
                cbox.addItem(item);
            }
        }
        catch(Exception e){}
  }
    
  private void initButtonEvent()
     {
       b1.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e)
           {
              delete();
           }
       });
   }
   
    private void delete()
   {
       try
       {
          
            PreparedStatement ps=connection.prepareStatement("delete from create_person where email=?");
                ps.setString(1, cbox.getSelectedItem().toString().split("-")[1]);
            ps.executeUpdate();       
       } 
       catch(Exception e)
       {
           
       }
       
   }
    
    
    public deleteperson1(Connection c)
    {
        this.connection=c;
        this.initcomponentevent();
        this.initComboBoxEvent();
        this.initButtonEvent();
        this.setTitle("Delete Person Form");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300,300);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
}
