/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sohai
 */
public class Client
{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    HomeGUI gui;

    public Client(String ip, int port, String name, User u)
    {
        gui = new HomeGUI(u);
        gui.getFrame().setTitle(name);
        gui.setVisible(true);
        
        try
        {
            
            socket = new Socket(ip, port);
            
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            Client c = this;
            
            Thread t = new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    while (true)
                    {                
                        try
                        {
                            //Send Message
                            BtnHnd hnd = new BtnHnd(c);
                            gui.sendBtn.addActionListener(hnd);
                            
                            //Receive message
                            String messageReceived = in.readUTF();
                            System.out.println(messageReceived);
                            gui.chatBox.append(gui.receiverNameLabel.getText() + ": \n");
                            gui.chatBox.append(messageReceived + "\n\n");
                        } 
                        catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            t.start();
            
            
            
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
