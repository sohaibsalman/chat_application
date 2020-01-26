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
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author sohai
 */
public class DbUtil
{
    private static Connection conn;

    private PreparedStatement pstat;
    private ResultSet res;

    public DbUtil()
    {
        connectDb();
    }
    
    public static void connectDb() 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "");

        } catch (Exception ex)
        {
            ex.printStackTrace();
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
    
    public static void initUser(String rollNo, User ref)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
            String query = "SELECT * FROM users WHERE rollno = ?";
            
            ResultSet res;
            PreparedStatement st = conn.prepareStatement(query);
            
            st.setString(1, rollNo);
            res = st.executeQuery();

            res.next();

            ref.setRollNo(res.getString(1));
            ref.setName(res.getString(2));
            ref.setPassword(res.getString(3));
            ref.setDegree(res.getString(4));
            ref.setSection(res.getString(5));
            ref.setFall(res.getString(6));   
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }           
    }
    
    static void initChatList(DefaultListModel<User> model, User user)
    {
        try
        {
            //Check DB Connection
            if(conn.isClosed())
                connectDb();
            
            PreparedStatement pstat;
            ResultSet res;
            
            String query = "SELECT * FROM chat_list WHERE sender_id = ?";
            
            pstat = conn.prepareStatement(query);
            pstat.setString(1, user.getRollNo());
            
            res = pstat.executeQuery();
            
            while(res.next())
            {
                User temp = new User(res.getString(2));
                model.addElement(temp);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void createUserTable(String tableName)
    {
        try
        {
           if(conn.isClosed())
               connectDb();
           String query = "CREATE TABLE " + tableName + " (sender_id varchar(10), receiver_id varchar(10), message varchar(1000), time datetime)";
           pstat = conn.prepareStatement(query);
           pstat.execute();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    static void showChat(String r_table, String s_table)
    {
        try
        {
            
            String query = "SELECT " + s_table + ".message, " + r_table + ".message "
                    + "FROM " + s_table + ", " + r_table + " "
                    + "WHERE " + s_table + ".sender_id = " + r_table + ".receiver_id";
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
    }

}
