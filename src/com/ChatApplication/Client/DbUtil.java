/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author sohai
 */
public class DbUtil
{
    private Connection conn;
    private PreparedStatement pstat;
    private ResultSet res;

    public DbUtil()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public boolean signUpUser(Vector<String> arr)
    {
        String name = arr.get(0);
        String rollNo = arr.get(1);
        String password = arr.get(2);
        String program = arr.get(3);
        String degree = arr.get(4);
        String section = arr.get(5);
        String fall = arr.get(6);
        
        try 
        {
            String sql = "SELECT * FROM users WHERE rollno = ?";
        
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, rollNo);
            
            res = pstat.executeQuery();
            
            //See if user already exists
            if(res.next())
                return false;
            
            sql = "INSERT INTO users VALUES "
                    + "("
                    + "?,?,?,?,?,?,?"
                    + ")";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, rollNo);
            pstat.setString(2, name);
            pstat.setString(3, password);
            pstat.setString(4, program);
            pstat.setString(5, degree);
            pstat.setString(6, section);
            pstat.setString(7, fall);
            
            pstat.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean loginUser(Vector<String> user)
    {        
        String rollNo = user.get(0);
        String password = user.get(1);
        
        try
        {
            String sql = "SELECT rollno, password FROM users WHERE rollNo = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, rollNo);
            
            res = pstat.executeQuery();
            
            if(!res.next())
                return false;
            else 
            {
                String dbRollNo = res.getString(1);
                String dbPass = res.getString(2);
                                
                if(dbRollNo.equalsIgnoreCase(rollNo) && dbPass.equalsIgnoreCase(password))
                    return true;
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
