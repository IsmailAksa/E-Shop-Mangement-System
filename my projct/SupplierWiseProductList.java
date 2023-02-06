import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class SupplierWiseProductList extends JFrame implements ActionListener
 {
    JFrame jf;
    JButton submit,clear;
    JLabel l1,ln;
    JTextField t1;
    Font f;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
 	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

  public SupplierWiseProductList()
  {
   jf=new JFrame();
   f = new Font("Times New Roman",Font.BOLD,20);
   jf.setLayout(null);

  	ln = new JLabel("Supplier wise Product report");
    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
    ln.setForeground(Color.blue);
    ln.setBounds(300,30,500,25);
    jf.add(ln);

    l1 = new JLabel("Enter Supplier name:");
    l1.setFont(f);
  l1.setBounds(50,100,200,25);
    jf.add(l1);

    t1=new JTextField(10);
    t1.setBounds(250,100,200,25);t1.setToolTipText("Enter supplier name");
    jf.add(t1);



    submit = new JButton("Submit",new ImageIcon("images//open.png"));
    submit.setBounds(120,150,110,35); submit.setToolTipText("click to open supplier wise product report");
    jf.add(submit);submit.addActionListener(this);

    clear = new JButton("Clear",new ImageIcon("images//clear.png"));
    clear.setBounds(300,150,110,35);clear.setToolTipText("click to clear textfield");
    jf.add(clear);clear.addActionListener(this);

    scrlPane.setBounds(0,200,900,600);
    jf.add(scrlPane);
    tabGrid.setFont(new Font ("Times New Roman",0,15));

        
        model.addColumn("P_NAME");
        model.addColumn("P_COMPANY");
        model.addColumn("P_QUANTITY");
       
        model.addColumn("P_PURDATE");
        
        model.addColumn("P_PURPRICE");
        model.addColumn("P_SALEPRICE");
        
        model.addColumn("P_SID");
        model.addColumn("P_SNAME");


    jf.setTitle("Supplier Wise Product Report");
    jf.setSize(900,700);
	jf.setLocation(20,20);
	jf.setResizable(false);
    jf.getContentPane().setBackground(Color.cyan);
    jf.setVisible(true);
  }

  	public void actionPerformed(ActionEvent ae)
	{
	if(ae.getSource()==submit)
	 {//list
  	int r = 0;
     try
     {
     		if(((t1.getText()).equals("")))
	        {
		    JOptionPane.showMessageDialog(this,"Please supplier name !","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        }
	        else
	        {
	         int foundrec = 0;
     	Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection("jdbc:sqlite:D:/product.db");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("SELECT pname,pcompany,pqty,ppurdate,ppurprice,psaleprice,sid,sname from product where sname='"+t1.getText()+"' ");
          while(rs.next())
            {
  model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
              foundrec = 1;
            }
              if (foundrec == 0)
                {
                    JOptionPane.showMessageDialog(null,"Not any product provide by given supplier","Dialog",JOptionPane.WARNING_MESSAGE);
                }
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
	}

	 else if(ae.getSource()==clear)
	 {
	 	t1.setText("");
	 }
    }

  public static void main(String args[])
    {
    	new SupplierWiseProductList();
    }
}
