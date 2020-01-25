/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sohai
 */
public class Server
{
    //main Server components
    private  ServerSocket server;
    private  Socket socket;
    private  DataInputStream in;
    private  DataOutputStream out;
    
    //RECORD OF USERS
    static int userNum = 0;
    static Vector<RequestHandler> list;
    
    //SERVER GUI
    ServerGUI gui;

    
    public Server()
    {
        gui = new ServerGUI();
        gui.setVisible(true);
        
        BtnHnd btnHnd = new BtnHnd(this);
        gui.btnStart.addActionListener(btnHnd);
        gui.btnStop.addActionListener(btnHnd);
    }
    
    public void startServer()
    {
        try
        {
            server = new ServerSocket(8046);
            System.out.println("a");
            gui.txtStats.append("Server Started");
            list = new Vector<>();
            
            Thread thread = new Thread( new Runnable()
            {

                @Override
                public void run()
                {
                    while (true)
                    {
                        try
                        {
                            gui.txtStats.setText(gui.txtStats.getText() + "\n\n" + "Server is waiting for client...");
                            socket = server.accept();
                            
                            gui.txtStats.setText(gui.txtStats.getText() + "\n\n" + "Connection established with client " + socket);
                            
                            in = new DataInputStream(socket.getInputStream());
                            out = new DataOutputStream(socket.getOutputStream());
                            
                            //Assigning to new thread
                            gui.txtStats.setText(gui.txtStats.getText() + "\n\n" + "Assigning Request to new thread...");
                            
                            RequestHandler hnd = new RequestHandler("user" + userNum, socket, in, out);
                            
                            Thread t = new Thread(hnd);
                            t.start();
                            
                            gui.txtStats.setText(gui.txtStats.getText() + "\n\n" + "Connection Established...");

                            list.add(hnd);
                            
                            userNum++;
                        } 
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
               
                    }   
                }
            });
            
            thread.start();
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void stopServer()
    {
        try
        {
            server.close();
            socket.close();
            in.close();
            out.close();
            gui.txtStats.setText("Server Stopped...");
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
   
    
}
