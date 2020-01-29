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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

/**
 *
 * @author sohai
 */
public class DbUtil
{
    private static Connection conn;

    static void addFriend(User user, String availableUser)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
            
            String query = "INSERT INTO chat_list VALUES (?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            
            st.setString(1, user.getRollNo());
            st.setString(2, availableUser);
            
            
            st.executeUpdate();
            
            
            query = "INSERT INTO chat_list VALUES (?,?)";
            st = conn.prepareStatement(query);

            st.setString(1, availableUser);
            st.setString(2, user.getRollNo());

            st.executeUpdate();
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }

    static void addHistory(String s_id, String r_id)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
            String query = "INSERT INTO chat_list VALUES (?,?)";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, r_id);
            st.setString(2, s_id);

            st.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }


    static void initChatTable(User me, String r_id)
    {
                        System.out.println("in func");

        try
        {
            if(conn.isClosed())            
            {
                connectDb();
            }
            connectDb();
                String query = "CREATE TABLE " + me.getRollNo() + "_" + r_id + ""+  " (flow varchar(10), message varchar(1000), time datetime)";
                PreparedStatement pstat = conn.prepareStatement(query);
                pstat.execute();       
                query = "CREATE TABLE " + r_id + "_" + me.getRollNo() + ""+  " (flow varchar(10), message varchar(1000), time datetime)";
                pstat = conn.prepareStatement(query);
                pstat.execute();  
                
                System.out.println("created");
                
                query = "INSERT INTO " + me.getRollNo() + "_" + r_id + " " + "VALUES (? , ? , CURRENT_TIMESTAMP)";
                pstat = conn.prepareStatement(query);
                pstat.setString(1, "null");
                pstat.setString(2, "You added " + r_id);
                pstat.executeUpdate();
                
                query = "INSERT INTO " + r_id + "_" + me.getRollNo() + " " + "VALUES (? , ? , CURRENT_TIMESTAMP)";
                pstat = conn.prepareStatement(query);
                pstat.setString(1, "null");
                pstat.setString(2, me.getRollNo() + " added you...");
                pstat.executeUpdate();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void eraseChat(User user, JLabel receiverRollNo)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
            String query = "TRUNCATE TABLE " + user.getRollNo() + "_" + receiverRollNo.getText();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void deleteUser(User user, String r_id)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
            String query = "DROP TABLE " + user.getRollNo() + "_" + r_id;
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            
            query = "DELETE chat_list WHERE sender_id = ? AND receiver_id = ?";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, user.getRollNo());
            stat.setString(2, r_id);
            stat.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    

  
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
                ref.setProgram(res.getString(4));
                ref.setDegree(res.getString(5));
                ref.setSection(res.getString(6));
                ref.setFall(res.getString(7));   

  
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
            
            String query = "SELECT DISTINCT receiver_id FROM chat_list WHERE sender_id = ?"; 
            
            pstat = conn.prepareStatement(query);
            pstat.setString(1, user.getRollNo());
            res = pstat.executeQuery();
            
            while(res.next())
            {
                User temp = new User(res.getString(1));
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
           String query = "CREATE TABLE " + tableName + " (sender_id varchar(20), receiver_id varchar(20), message varchar(1000), time datetime)";
           pstat = conn.prepareStatement(query);
           pstat.execute();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    static void showChat(String r_table, String s_table, HomeGUI ref)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
//            String query = "SELECT " + s_table + ".message , " + r_table + ".message "
//                    + "FROM " + s_table + " , " + r_table + " "
//                    + "WHERE " + s_table + ".receiver_id(+) = " + r_table + ".sender_id";
            
//            String query = "SELECT message, time, sender_id FROM " + s_table + " "
//                    + " WHERE receiver_id = '" + r_table 
//                    + "' UNION All "
//                    + "SELECT message, time, sender_id FROM " + r_table 
//                    + " WHERE receiver_id = '" + s_table 
//                    + "' ORDER BY time";
            String query = "SELECT flow, message, time FROM " + s_table + "_" + r_table + "";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
//                if(rs.getString(3).equalsIgnoreCase(s_table))
//                {
//                    ref.chatBox.append("ME: " + '\n');
//                }
//                else 
//                {
//                    ref.chatBox.append(r_table.toUpperCase()+ ": \n");
//                }
//                ref.chatBox.append(rs.getString(1) + "\n\n");
                
                
                if(rs.getString(1).equalsIgnoreCase("sent"))
                {
                    ref.chatBox.append("ME: " + '\n');
                }
                else 
                {
                    ref.chatBox.append(r_table.toUpperCase()+ ": \n");
                }
                ref.chatBox.append(rs.getString(2) + "\n\n");
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
    }

    static void addMessage(String message, String s_id, String r_id)
    {
        try
        {
            if(conn.isClosed())
            connectDb();
            //ADDING TO OWN TABLE
            String query = "INSERT INTO " + s_id + "_" + r_id + "" + " VALUES "
                    + "("
                    + "?, ?, CURRENT_TIMESTAMP "
                    + ")";
                    
                  
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, "sent");
            st.setString(2, message);

            st.executeUpdate();
            //ADDING TO RECEIVER TABLE
            query = "INSERT INTO " + r_id + "_" + s_id + "" + " VALUES "
                    + "("
                    + "?, ?, CURRENT_TIMESTAMP "
                    + ")";
                    
                  
            st = conn.prepareStatement(query);
            st.setString(1, "received");
            st.setString(2, message);

            st.executeUpdate();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

    static int searchUser(User me, String searchedUser)
    {
        try
        {
            if(conn.isClosed())
                connectDb();
            
            String query = "SELECT rollno FROM users WHERE rollno = ? AND rollno <> ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, searchedUser);
            st.setString(2, me.getRollNo());
            
            ResultSet s = st.executeQuery();
            
            if(s.next())
            {
                String q = "SELECT receiver_id FROM chat_list WHERE sender_id = ? AND receiver_id = ?";
                PreparedStatement stat = conn.prepareStatement(q);
                stat.setString(1, me.getRollNo());
                stat.setString(2, searchedUser);
                ResultSet s2 = stat.executeQuery();
                if(s2.next())
                    return 2;       //ALREADY ADDED
                else
                    return 1;       //NOT ADDED
            }
            
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }
}
