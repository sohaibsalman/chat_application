/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sohai
 */
public class RequestHandler extends Thread
{
    DataInputStream in;
    DataOutputStream out;
    Socket socket;
    String username;

    public RequestHandler(String username, Socket socket, DataInputStream in, DataOutputStream out)
    {
        this.username = username;
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run()
    {
        String msgReceived  = "";
        while (true)
        {            
            try
            {
                msgReceived = in.readUTF();
                
                StringTokenizer token = new StringTokenizer(msgReceived, "@");
                String message = token.nextToken();
                String receiver = token.nextToken();
                
                for(RequestHandler hnd : Server.list)
                {
                    if(hnd.username.equals(receiver))
                    {
                        hnd.out.writeUTF(username + " : " + message);
                        break;
                    }
                }
            } 
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    
    
    
    
}
