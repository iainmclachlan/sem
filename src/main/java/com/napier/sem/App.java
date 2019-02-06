package com.napier.sem;
import  java.sql.*;

public class App {
    public static void main(String[] args) {
        //Creating New App
        App a = new App();

        //Connect To DB
        a.connect();

        //Get Employee
        Employee emp = a.getEmployee(255530);

        //Display Results
        a.displayEmployee(emp);


        //Disconnect from DB
        a.disconnect();


    }

    //Connection To MySQL Database
    private Connection con = null;

    public void connect() {
        try {
            //Load Database Driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not Load SQL Driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to Database...");

            try {
                //Wait for DB to Start
                Thread.sleep(30000);
                //Connect To database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Connection Successful");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted, This should not happen");
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                //Close Connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error Closing Connection to Database");
            }
        }
    }

    public Employee getEmployee(int ID)
    {
        try {
            //Create a SQL Statement
            Statement stmt = con.createStatement();
            //Create String for SQL Statement
            String strSelect =
                    "SELECT emp_no, first_name, last_name"
                            + "FROM employees"
                            + "WHERE emp_no = " + ID;

            //Execute SQL Statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Return new employee if valid
            //Check if Returned
            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Employee Details");
            return null;
        }
    }

    public  void displayEmployee(Employee emp)
    {
        if(emp != null )
        {
            System.out.println(
                    emp.emp_no + " "
                    + emp.first_name + " "
                    + emp.last_name + "\n"
                    + emp.title + "\n"
                    + "Salary: " + emp.salary + "\n"
                    + emp.dept_name + "\n"
                    + "Manager: " + emp.manager + "\n");
        }
    }
}
