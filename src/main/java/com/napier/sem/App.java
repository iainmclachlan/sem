package com.napier.sem;
import  java.sql.*;

public class App
{
    public  static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load the SQL Driver");
            System.exit(-1);
        }

        //Connect To Database
        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to Database....");
            try
            {
                //Waiting for DB To start
                Thread.sleep(30000);
                con = DriverManager.getConnection("jdbcP:mysql://db:3306/employees?useSSL=false","root","example");
                System.out.println("Connection Successful");

                //Wait
                Thread.sleep(10000);
                //Exit Loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed To Connect to Database: Attempt " + Integer.toString(i));
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread Interupted, This should not happen");
            }
        }

        if (con != null)
        {
            try
            {
                //Close Connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error Closing Connection to Database");
            }
        }
    }
}
