/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Client;

/**
 *
 * @author sohai
 */
public class ClientDriver
{
    public static void main(String[] args)
    {
        Client c = new Client("localhost", 8046, "abc");
    }
}
