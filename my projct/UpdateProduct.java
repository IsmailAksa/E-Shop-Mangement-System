import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class UpdateProduct extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField t2,t3,t4,t6,t8,t9,t11,t12;
	JLabel l2,l3,l4,l6,l8,l9,l11,l12,ln;
    JButton b0,b1,b2,b3;
    JComboBox msname,tabtype;
    String s,sid1,tabt;
	Font f;
    Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

	UpdateProduct()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    ln=new JLabel("Update Product");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,300,40);
	    jf.add(ln);


		l2 = new JLabel("Product name*");
		//l2.setFont(f);
   l2.setBounds(50,140,200,25);
		jf.add(l2);

    	t2 = new JTextField(20);
		t2.setBounds(250,140,200,25);t2.setToolTipText("Enter product name");
		jf.add(t2);

		l3 = new JLabel("Product company*");
		//l3.setFont(f);
  l3.setBounds(50,180,200,25);
		jf.add(l3);

     	t3 = new JTextField(20);
		t3.setBounds(250,180,200,25);t3.setToolTipText("Enter product company");
		jf.add(t3);

		l4 = new JLabel("Product quantity*");
		//l4.setFont(f);
  l4.setBounds(50,220,200,25);
    	jf.add(l4);

        t4= new JTextField(20);
		t4.setBounds(250,220,100,25);t4.setToolTipText("Enter product quantity");
		jf.add(t4);


	    l6= new JLabel("Product purchase date*");
		//l6.setFont(f);
  l6.setBounds(50,260,200,25);
    	jf.add(l6);
		
		t6= new JTextField(20);
		t6.setBounds(250,260,200,25);t4.setToolTipText("Enter purchase date");
		jf.add(t6);

  


		l8= new JLabel("Product purchase price*");
		//l8.setFont(f);
 l8.setBounds(470,140,220,25);
    	jf.add(l8);

        t8 = new JTextField(20);
		t8.setBounds(720,140,100,25);t8.setToolTipText("Enter product purchase price");
		jf.add(t8);

		l9 = new JLabel("Product sale price*");
	//	l9.setFont(f);
  l9.setBounds(470,180,200,25);
    	jf.add(l9);

        t9 = new JTextField(20);
		t9.setBounds(720,180,100,25);t9.setToolTipText("Enter Product sale price");
		jf.add(t9);



		l11 = new JLabel("Supplier name*");
		//l11.setFont(f);
	l11.setBounds(470,260,180,25);
    	jf.add(l11);

    	t11 = new JTextField(20);
		t11.setBounds(720,260,100,25);
		jf.add(t11);

    	l12 = new JLabel("Supplier id");
	//	l12.setFont(f);
 l12.setBounds(470,300,180,25);
    	jf.add(l12);

    	t12 = new JTextField(20);
	    t12.setBounds(720,300,100,25);
	    jf.add(t12);


        msname=new JComboBox();
        msname.setBounds(600,260,110,25);msname.setToolTipText("select product supplier name");
        jf.add(msname);
        msname.addActionListener(new ActionListener()
	     {
		   public void actionPerformed(ActionEvent ae)
		   {
		    s =(String)msname.getSelectedItem();
		    t11.setText(s);
		   }
          });

        try
		{
			Class.forName("org.sqlite.JDBC");
		    con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
			System.out.println("Connected to database.");
			 ps=con.prepareStatement("select Productid,ProductName from Product1");
		    rs=ps.executeQuery();
    		while(rs.next())
    		{
    	     sid1=rs.getString(1);
    		 String sname1=rs.getString(2);
    		 msname.addItem(sname1);
    		}

    	con.close();
       }
       catch(SQLException se)
       {
       System.out.println(se);
       }
      catch(Exception e)
       {
       System.out.println(e);
       }

        b0 = new JButton("Open",new ImageIcon("images//open.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("click to open product details");
		jf.add(b0);b0.addActionListener(this);

		b1 = new JButton("Update",new ImageIcon("images//update.png"));
		b1.setBounds(300,330,110,35);b1.setToolTipText("click to update product details");
	    jf.add(b1); b1.addActionListener(this);

        b2= new JButton("Clear",new ImageIcon("images//clear.png"));
		b2.setBounds(450,330,110,35);b2.setToolTipText("click to clear all textfields");
		jf.add(b2); b2.addActionListener(this);

    	b3 = new JButton("All",new ImageIcon("images//all.png"));
		b3.setBounds(600,330,110,35);b3.setToolTipText("click to view all product details");
		jf.add(b3); b3.addActionListener(this);

	    scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

        model.addColumn("P_NAME");
        model.addColumn("P_COMPANY");
        model.addColumn("P_QUANTITY");
      
        model.addColumn("P_PURDATE");
   
        model.addColumn("P_SALEPRICE");
        model.addColumn("P_PURPRICE");
    
        model.addColumn("P_SID");
        model.addColumn("P_SNAME");


	     jf.setTitle("Update Product ");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
 }

 public void actionPerformed(ActionEvent ae)
  {
  	if(ae.getSource()==b0)
	  {
	  	 	 if(((t2.getText()).equals(""))&&((t3.getText()).equals("")))
	        {
		    JOptionPane.showMessageDialog(this,"Please enter product name or company !","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        }
	        else
	        {

	      try
	       {//fetch
	      int foundrec = 0;
	    Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
		System.out.println("Connected to database.");
			ps=con.prepareStatement("Select Productid from Product1 where ProductName='"+s+"'");
    		  rs=ps.executeQuery();
    		  while(rs.next())
    		  {
    		  	String sid1=rs.getString(1);
	    	   t12.setText(sid1);
    	      }

		  ps=con.prepareStatement("select * from Product1 where ProductName='"+t2.getText()+"' or company='"+t3.getText()+"' ");
		  rs=ps.executeQuery();
		  while(rs.next())
	      {
	        t2.setText(rs.getString(2));
	        t3.setText(rs.getString(3));
	        t4.setText(rs.getString(4));
		
	        t8.setText(rs.getString(8));
	        t9.setText(rs.getString(9));
		    t12.setText(rs.getString(11));
		    t11.setText(rs.getString(12));
		     foundrec = 1;
	       }
	       if (foundrec == 0)
                {
                    JOptionPane.showMessageDialog(null,"Record is not available","Dialogs",JOptionPane.WARNING_MESSAGE);
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
  }
  else if(ae.getSource()==b1)
	   {
	   try
	    {
	    	if(((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||((t8.getText()).equals(""))||((t9.getText()).equals(""))||((t11.getText()).equals(""))||((t12.getText()).equals("")))
	        {
		    JOptionPane.showMessageDialog(this,"* Detail are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        }
	        else
	        {
			Class.forName("org.sqlite.JDBC");
		    con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
			System.out.println("Connected to database.");
			stmt=con.createStatement();
String str1="UPDATE Product1 SET ProductName='"+t2.getText()+"',Company='"+t3.getText()+"',Quantity='"+t4.getText()+"',Purchasedate='"+t6.getText()+"',Purchaseprice='"+t8.getText()+"',Saleprice='"+t9.getText()+"',sid='"+t12.getText()+"',sname='"+t11.getText()+"' where Company='"+t3.getText()+"'or ProductName='"+t2.getText()+"'";
    	stmt.executeUpdate(str1);
    	JOptionPane.showMessageDialog(null, "Record is updated");
    	
	    t2.setText("");
		t3.setText("");
	    t4.setText("");
        t6.setText("");
		t8.setText("");
	    t9.setText("");
   
    	t11.setText("");
    	t12.setText("");
    	con.close();
       }
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
  else if(ae.getSource()==b2)
      {  
           t2.setText("");
           t3.setText("");
           t4.setText("");
           t6.setText("");   
		   t8.setText("");
           t9.setText("");
      
           t11.setText("");
           t12.setText("");
      }
 else if(ae.getSource()==b3)
    {//list
  	int r = 0;
     try
     {
         Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("SELECT * from Product1" );
          while(rs.next())
            {
model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)});
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
 }
 public static void main(String args[])
 {
  new UpdateProduct();
 }
}


