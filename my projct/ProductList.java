import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class ProductList extends JFrame
 {
    JFrame jf=new JFrame();
    JLabel ln;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
 	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

  public ProductList()
  {
    jf.setLayout(null);
  	ln = new JLabel("Stock Of Products");
    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
    ln.setForeground(Color.blue);
    ln.setBounds(300,30,300,25);
    jf.add(ln);

    scrlPane.setBounds(0,80,900,600);
    jf.add(scrlPane);
    tabGrid.setFont(new Font ("Times New Roman",0,15));

    model.addColumn("Productid");model.addColumn("ProductName");model.addColumn("Company");model.addColumn("Quantity");
  	model.addColumn("Purcahasedate");model.addColumn("Purchaseprice");
  	model.addColumn("Saleprice");model.addColumn("sid");model.addColumn("sname");
  		int r = 0;
     try
     {

     	Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from Product1");
          while(rs.next())
            {
            	model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});

            }

             con.close();
       }
      catch(SQLException se)
       {
       	  System.out.println(se);
          JOptionPane.showMessageDialog(null,"SQL Error:"+se);
       }
       catch(Exception e)
       {
       	   System.out.println(e);
           JOptionPane.showMessageDialog(null,"Error:"+e);
       }


    jf.setTitle("ProductList List");
    jf.setSize(900,700);
	jf.setLocation(20,20);
	jf.setResizable(false);
    jf.getContentPane().setBackground(Color.cyan);
    jf.setVisible(true);
  }


  public static void main(String args[])
    {
    	new ProductList();
    }
}
