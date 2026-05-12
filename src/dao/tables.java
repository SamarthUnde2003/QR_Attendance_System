/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;


/**
 *
 * @author ASUS
 */
public class tables {
    
    public static void main(String args[])throws Exception{
        Connection con = null;
        Statement st = null;
        
        try{
            con = ConnectionProvider.getCon();
            
            st = con.createStatement();
            
            System.out.println("towards table creation.");
            
            if(!tableExit(st,"userdetails")){  
                st.executeUpdate("CREATE TABLE userdetails(id SERIAL PRIMARY KEY, name VARCHAR(200) not null, gender VARCHAR(50) not null, email VARCHAR(200) not null, contact VARCHAR(20) not null, address VARCHAR(500) not null, state VARCHAR(100) not null, country VARCHAR(100) not null, uniqueregid VARCHAR(100) not null, imagename VARCHAR(100));");
                System.out.println("userdetail table created");
            }
            
            if(!tableExit(st,"userattendance")){
                st.executeUpdate("CREATE TABLE userattendance (userid INT NOT NULL,date DATE NOT NULL,checkin TIMESTAMP,checkout TIMESTAMP,workduration VARCHAR(100));");
                System.out.println("userattendance table created");
            }
            System.out.println("table create done.");
            
            JOptionPane.showMessageDialog(null,"Table checked/Created successfully.");
            
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }finally{
            try{
                if(con!=null)
                {
                    con.close();
                }
                if(st!=null)
                {
                    st.close();
                }
                
            }catch(Exception ex){
                ex.printStackTrace(); 
                
            }
            
        }
        
        
    }
       
    
    //TO CHECK WHETHER TABLE EXIST OR NOT
    
    
    private static boolean tableExit(Statement st,String tableName)throws Exception {
        ResultSet rs = st.executeQuery("select exists(select from pg_tables where schemaname = 'public' and tablename = '"+tableName+"');");
        
        if(rs.next()){
            return rs.getBoolean(1);
        }else{
            return false;
        }
        
    }
    
}

