import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class SearchProduct extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField t2,t3,t4,t6,t8,t9,t11,t12;
	JLabel l2,l3,l4,l6,l8,l9,l11,l12,ln;
    JButton b0,b1,b2;
    JComboBox msname;
    String s;
	Font f;
    Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

	SearchProduct()
	{
		jf=new JFrame();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    ln=new JLabel("Search Product");
	    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
	    ln.setForeground(Color.blue);
	    ln.setBounds(300,30,300,40);
	    jf.add(ln);

		

		l2 = new JLabel("Product name*");
		//l2.setFont(f);
   l2.setBounds(50,140,200,25);
		jf.add(l2);

    	t2 = new JTextField(20);
		t2.setBounds(250,140,200,25);t2.setToolTipText("Enter Product name to search");
		jf.add(t2);

		l3 = new JLabel("Product company");
		//l3.setFont(f);
   l3.setBounds(50,180,200,25);
		jf.add(l3);

     	t3 = new JTextField(20);
		t3.setBounds(250,180,200,25);
		jf.add(t3);

		l4 = new JLabel("Product quantity");
		//l4.setFont(f);
   l4.setBounds(50,220,200,25);
    	jf.add(l4);

        t4= new JTextField(20);
		t4.setBounds(250,220,100,25);
		jf.add(t4);

	

		l6= new JLabel("Product purchase date");
		//l6.setFont(f);
    l6.setBounds(50,300,250,25);
    	jf.add(l6);

        t6= new JTextField(20);
		t6.setBounds(250,300,100,25);
		jf.add(t6);

	
		l8= new JLabel("Product purchase price");
		//l8.setFont(f);
   l8.setBounds(470,140,220,25);
    	jf.add(l8);

        t8 = new JTextField(20);
		t8.setBounds(720,140,100,25);
		jf.add(t8);

		l9 = new JLabel("Product sale price");
		//l9.setFont(f);
    l9.setBounds(470,180,200,25);
    	jf.add(l9);

        t9 = new JTextField(20);
		t9.setBounds(720,180,100,25);
		jf.add(t9);

		l11 = new JLabel("Supplier name");
		//l11.setFont(f);
   l11.setBounds(470,260,180,25);
    	jf.add(l11);

        t11 = new JTextField(20);
	   	t11.setBounds(720,260,100,25);
	   	jf.add(t11);

	   	l12 = new JLabel("Supplier id");
		//l12.setFont(f);
 	l12.setBounds(470,300,180,25);
    	jf.add(l12);

    	t12 = new JTextField(20);
	    t12.setBounds(720,300,100,25);
	    jf.add(t12);

        b0 = new JButton("Search",new ImageIcon("images//search.png"));
        b0.setBounds(150,330,110,35);b0.setToolTipText("click to search product details");
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

    
        model.addColumn("productid");
        model.addColumn("productname");
        model.addColumn("company");
 
        model.addColumn("quantity");

        model.addColumn("saleprice");
        model.addColumn("purchaseprice");
   
        model.addColumn("sid");
        model.addColumn("sname");

	     jf.setTitle("Search Product");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==b0)
	 {//fetch
	     try
	      {
	      	 if(((t2.getText()).equals(""))&&((t3.getText()).equals("")))
	        {
		    JOptionPane.showMessageDialog(this,"Please enter product name or cmpny !","Warning!!!",JOptionPane.WARNING_MESSAGE);
	        }
	        else
	        {
	         int foundrec = 0;

	    Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection("jdbc:sqlite:D:/Product1.db");
		System.out.println("Connected to database.");

		  ps=con.prepareStatement("select * from Product1 where ProductName='"+t2.getText()+"' or Company='"+t3.getText()+"'");
		  rs=ps.executeQuery();
		  while(rs.next())
	      {
		
	        t2.setText(rs.getString(2));
	        t3.setText(rs.getString(3));
	        t4.setText(rs.getString(4));
		
		    t6.setText(rs.getString(6));
	  
	        t8.setText(rs.getString(8));
	        t9.setText(rs.getString(9));
		
		    t12.setText(rs.getString(11));
		    t11.setText(rs.getString(12));
		     foundrec = 1;
	       }
	        if (foundrec == 0)
                {
                    JOptionPane.showMessageDialog(null,"Record is not available","Dialog",JOptionPane.WARNING_MESSAGE);
                }
	         }
	         con.close();
        }
      catch(SQLException se)
		{
	      System.out.println(se);
	     JOptionPane.showMessageDialog(null,"SQL Error."+se);
        }
	    catch(Exception e)
	     {
	     System.out.println(e);
		 JOptionPane.showMessageDialog(null,"Error."+e);
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
			
				    t11.setText("");
				    t12.setText("");
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
  new SearchProduct();
 }
}


