/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sohai
 */
public class BtnHnd implements ActionListener
{
    Client client;
    SignUpGUI signup;

    public BtnHnd(Client client)
    {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Send"))
        {
            try 
            {
                String message = client.gui.messageField.getText();
                client.gui.messageField.setText(null);
                client.gui.chatBox.append("ME: " + '\n' + message + "\n\n");
                client.out.writeUTF(message);
                
                //Store to db
                DbUtil.addMessage(message, client.gui.user.getRollNo(), client.gui.receiverNameLabel.getText());
                
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        if(e.getActionCommand().equalsIgnoreCase("sign up"))
        {
            
        }
    }
    
}
