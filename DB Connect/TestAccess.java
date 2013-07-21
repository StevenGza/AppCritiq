/**
 * Opens a Microsoft Access Database without having need to have access rights
 * to the Administrative Tools on Windows to set up ODBC..
 * 
 * Maak 'n Microsoft Access databasis oop in Java sonder dat enige verstellings
 * in die Administrative Tools gemaak hoef te word vir ODBC.
 * 
 * Die res van die kommentaar is meestal in Engels, ek is seker daarvan dat almal darem
 * sal saam stem dat dit moeilik is om Java kode in "suiwer" Afrikaans te verduidelik.
 */

import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ewald Horn
 * @company JavaK
 */
public class TestAccess
{

    public static Connection con;
    public static final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    public static final String url = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=";
    /**
     * The path to the database file. It is important to remember that on Windows
     * platforms the path character \ must be escaped with another \.
     */
    String path;

////////////////////////////////////////////////////////////////////////////////    
    /** Constructor 
     * Sets the path to the database.
     * 
     * Stel die pad na die databasis.
     */
    public TestAccess()
    {
	path = "D:\\VideoShop.mdb";
    }

////////////////////////////////////////////////////////////////////////////////
    /**
     * Runner method for the TestAccess class.
     */
    private void go()
    {
	makeConnection();
	displayStatus();
	closeConnection();
    }

////////////////////////////////////////////////////////////////////////////////    
    /**
     * Creates the database connection.
     * 
     * Skep die brug na die databasis.
     */
    public void makeConnection()
    {
	System.out.println("\n\n\nOpening database...");
	try
	{
	    Class.forName(driver);
	    con = DriverManager.getConnection(url + path);
	} catch (Exception ex)
	{
	    Toolkit q = Toolkit.getDefaultToolkit();
	    q.beep();
	    JOptionPane.showMessageDialog(null, "An error occurred while trying to set up the connection to database.\n",
		    "Database Error", JOptionPane.ERROR_MESSAGE);
	    System.err.println(ex);
	    System.exit(0);
	}
    }

////////////////////////////////////////////////////////////////////////////////    
    /**
     * Closes the connection cleanly.
     * 
     * Sluit die databasis af.
     */
    public void closeConnection()
    {
	System.out.println("\nClosing database.");
	try
	{
	    con.close();
	} catch (Exception ex)
	{
	    Toolkit q = Toolkit.getDefaultToolkit();
	    q.beep();
	    JOptionPane.showMessageDialog(null, "An error occurred while trying to close the database.\n",
		    "Database Error", JOptionPane.ERROR_MESSAGE);
	    System.err.println(ex);
	}
    }

////////////////////////////////////////////////////////////////////////////////
    /**
     * Displays some information regarding the database connection as well
     * as a list of the general TABLES in the database.
     * 
     * Vertoon inligting oor die databasis asook die gewone TABLES in die
     * databasis.
     */
    public void displayStatus()
    {
	if (con != null)
	{
	    try
	    {
		System.out.println(con.getCatalog()); // display the database

		DatabaseMetaData metaData = con.getMetaData(); // retrieve information about the database
		System.out.println("Database Product : " + metaData.getDatabaseProductName());
		System.out.println("Database Driver  : " + metaData.getDriverName());
		System.out.println("Tables for " + metaData.getURL() + " :\n");

		ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
		while (rs.next())
		{
		    System.out.println(rs.getString(3)); // 3rd String contains the table name
		}

		rs.close();

	    } catch (Exception e)
	    {
		System.err.println("Error : " + e);
	    }
	}
    }

////////////////////////////////////////////////////////////////////////////////
    /**
     * Main method for TestAccess.java
     * @param args
     */
    public static void main(String args[])
    {
	TestAccess testAccess = new TestAccess();
	testAccess.go();
    }
}
