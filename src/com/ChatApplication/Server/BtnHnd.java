/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sohai
 */
public class BtnHnd implements ActionListener
{
    Server ref;

    public BtnHnd(Server ref)
    {
        this.ref = ref;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equalsIgnoreCase("Start Server"))
        {
            ref.gui.btnStart.setEnabled(false);
            ref.gui.btnStop.setEnabled(true);
            ref.startServer();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Stop Server"))
        {
            ref.gui.btnStart.setEnabled(true);
            ref.gui.btnStop.setEnabled(false);
        }
    }
    
}
