import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AddNewProduct extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField t2,t3,t4,t6,t8,t9;
	JLabel l2,l3,l4,l,l8,l9,l11,ln;
    JButton b0,b1,b2;
    JComboBox msname;
    String s,sid1;
	Font f;
    Date date1;
    GregorianCalendar calendar;
    String strDate;
    Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

	AddNewProduct()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,15);
		jf.setLayout(null);

	    ln=new JLabel("New Product details");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,400,40);
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
		
		
		t6= new JTextField(20);
		t6.setBounds(250,260,150,25);
		jf.add(t6);

		

		l= new JLabel("Product purchase date*");
		//l.setFont(f);
    l.setBounds(50,260,200,25);
    	jf.add(l);

     

		date1= new Date();
     	calendar=new GregorianCalendar();
	    calendar.setTime(date1);
        strDate =calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
	   // t6.setText(strDate);

	

		l8= new JLabel("Product purchase price*");
		//l8.setFont(f);
    l8.setBounds(470,140,220,25);
    	jf.add(l8);

        t8 = new JTextField(20);
		t8.setBounds(720,140,100,25);t8.setToolTipText("Enter product purchase price");
		jf.add(t8);

		l9 = new JLabel("Product sale price*");
		//l9.setFont(f);
   l9.setBounds(470,180,200,25);
    	jf.add(l9);

        t9 = new JTextField(20);
		t9.setBounds(720,180,100,25);t9.setToolTipText("Enter product sale price");
		jf.add(t9);

		
		l11 = new JLabel("Supplier name*");
		//l11.setFont(f);
    l11.setBounds(470,260,250,25);
    	jf.add(l11);


        msname=new JComboBox();
        msname.setBounds(720,260,130,25);msname.setToolTipText("select product supplier name");
        jf.add(msname);
        msname.addActionListener(new ActionListener()
	     {
		   public void actionPerformed(ActionEvent ae)
		   {
		    s =(String)msname.getSelectedItem();
		   }
          });

        try
		{
			Class.forName("org.sqlite.JDBC");
		    con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
			System.out.println("Connected to database.");
			 ps=con.prepareStatement("select sname from Product1");
		    rs=ps.executeQuery();
    		while(rs.next())
    		{
    		 String sname1=rs.getString(1);
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

        b0 = new JButton("Save",new ImageIcon("images//save.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("click to save product details");
		jf.add(b0);b0.addActionListener(this);

		b1 = new JButton("Clear",new ImageIcon("images//clear.png"));
		b1.setBounds(300,330,110,35);b1.setToolTipText("click to clear all textfields");
	    jf.add(b1); b1.addActionListener(this);

        b2= new JButton("All",new ImageIcon("images//all.png"));
		b2.setBounds(450,330,110,35);b2.setToolTipText("click to view all product details");
		jf.add(b2); b2.addActionListener(this);

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

	     jf.setTitle("Add New Product ");
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
	   try
	    {
	    	if(((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||
	    	((t6.getText()).equals(""))||((t8.getText()).equals(""))||((t9.getText()).equals("")))
	        {
		    JOptionPane.showMessageDialog(this,"* Details are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        }
	        else
	        {
                	float a=Float.parseFloat(t8.getText());
					float b=Float.parseFloat(t9.getText());
	        	if(b<a)
	        	{
	        	JOptionPane.showMessageDialog(this,"sale price should be greater than puchase price!","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        	}
	        	else
	        	{

			Class.forName("org.sqlite.JDBC");
		    con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
			System.out.println("Connected to database.");

			ps=con.prepareStatement("Select sid from Product1 where sname='"+s+"'");
    		  rs=ps.executeQuery();
    		  while(rs.next())
    		  {
    		  	 sid1=rs.getString(1);
    	      }
 ps=con.prepareStatement("insert into Product1 (Productid,ProductName,Company,Quantity,Purchasedate,Purchaseprice,Saleprice,sid,sname)values"+"(?,?,?,?,?,?,?,?,?)");

			ps.setString(1,t6.getText());
		    ps.setString(2,t2.getText());
		    ps.setString(3,t3.getText());
			ps.setInt(4,Integer.parseInt(t4.getText()));
          
			ps.setFloat(8,Float.parseFloat(t8.getText()));
            ps.setFloat(9,Float.parseFloat(t9.getText()));
		 
		    ps.setInt(11,Integer.parseInt(sid1));
		    ps.setString(12,s);
		  	ps.executeUpdate();

  int reply=JOptionPane.showConfirmDialog(null,"Product added successfully.Do you want add more Products?","Added Product",JOptionPane.YES_NO_OPTION);

	             if (reply == JOptionPane.YES_OPTION)
	   			{
	   		       jf.setVisible(false);
	   		       new AddNewProduct();
	   		    }
	   		  else if (reply == JOptionPane.NO_OPTION)
	   			{
	   			  jf.setVisible(false);
		        }
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
  else if(ae.getSource()==b1)
      { 
           t2.setText("");
           t3.setText("");
           t4.setText("");
		   t6.setText("");
		   t8.setText("");
           t9.setText("");
     
      }

    else if(ae.getSource()==b2)
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
  model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
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
	      new AddNewProduct();
	}
}

