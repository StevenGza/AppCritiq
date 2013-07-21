   import java.io.*;
   import java.sql.*;


   public class UseCDsDB
   {
      Connection conn;
   
      UseCDsDB ()
      
      {
         connect (); 
      }
   
   
   
   
      void connect ()
      {
      
      //load the driver
         try
         {
         //Connect to the database
            Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
         }
         
            catch (ClassNotFoundException c)
            {
               System.out.println ("Unable to load Driver");
            }
      //connect to the database
         try
         {
            conn = DriverManager.getConnection ("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ= CDCollection");
            System.out.println ("Connection to databse successfully established");
         }
            catch (Exception e)
            {
               System.out.println ("Unable to connect the database");
            }
      }
   
   
      public static void main (String[] args) throws IOException
      {
         new UseCDsDB ();
      }
   }


