/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;


/**
 *
 * @author ASUS
 */
public class ConnectionProvider {
    
   private static final String dbName ="attendancejframe";


    public static Connection getCon() throws Exception {
        
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "nimbles3000");
        Statement stmt = con.createStatement();
        
        
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded.");
            
            if(!databaseExists(con,dbName)){
                //Create database 
                stmt.executeUpdate("CREATE DATABASE "+dbName+";");
                System.out.println("Database "+dbName+" created successfully.");
                
            }else{
                // Establish the database connection
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/attendancejframe", "postgres", "nimbles3000");
            System.out.println("Database connected.");
            }
            

        } catch (Exception ex) {
            // Print the exception for debugging purposes
            ex.printStackTrace();
            // You could throw the exception again if you want it to propagate
            throw new Exception("Error while connecting to the database", ex);
        }

        return con;
    }
    
    
    private static boolean databaseExists(Connection con,String dbName)throws Exception   // THIS CHECK EXISTANECE OF DATABASE 
    {
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = '"+dbName+"';").next();
    }
}





