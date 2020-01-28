/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Server;

import com.ChatApplication.Client.HomeGUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author sohai
 */
public class ClientData
{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    HomeGUI gui;

    public ClientData(Socket socket, DataInputStream in, DataOutputStream out, HomeGUI gui)
    {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.gui = gui;
    }
    
}
