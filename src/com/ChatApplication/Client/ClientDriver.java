/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Client;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author sohai
 */
public class ClientDriver
{
    public static void main(String[] args)
    {
        
        //Client c = new Client("localhost", 8046, "abc");
        new LoginGUI().setVisible(true);
    }
}
