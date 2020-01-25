/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ChatApplication.Client;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author sohai
 */
public class LoginGUI extends javax.swing.JFrame
{

    /**
     * Creates new form LoginGUI
     */
    public LoginGUI()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel2 = new javax.swing.JPanel();
        heading = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnCreate = new javax.swing.JButton();
        btnLogin1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        heading.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        heading.setText("PUCIT Chat Application");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Login");

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createTitledBorder("Username"));
        txtID.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtIDActionPerformed(evt);
            }
        });

        txtPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));

        btnCreate.setBackground(new java.awt.Color(153, 0, 51));
        btnCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCreateActionPerformed(evt);
            }
        });

        btnLogin1.setBackground(new java.awt.Color(51, 153, 0));
        btnLogin1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin1.setText("Login");
        btnLogin1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLogin1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtID)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(heading)
                .addGap(112, 112, 112))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(heading)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtIDActionPerformed
    {//GEN-HEADEREND:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCreateActionPerformed
    {//GEN-HEADEREND:event_btnCreateActionPerformed
        // TODO add your handling code here:
        new SignUpGUI().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLogin1ActionPerformed
    {//GEN-HEADEREND:event_btnLogin1ActionPerformed
        
        Vector<String> user = new Vector<>();
        user.add(txtID.getText());
        user.add(txtPassword.getText());
        
        DbUtil d = new DbUtil();
        Client c;
        
        JOptionPane option = new JOptionPane();
        
        if(d.loginUser(user))
        {
            option.showMessageDialog(this, "Login Successful", "Success", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            
            c = new Client("localhost", 8046, "Chat Application " + txtID.getText());
        } 
        else 
        {
            option.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnLogin1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new LoginGUI().setVisible(true);
    }

    public JTextField getTxtID()
    {
        return txtID;
    }

    public JPasswordField getTxtPassword()
    {
        return txtPassword;
    }

    public void setTxtID(JTextField txtID)
    {
        this.txtID = txtID;
    }

    public void setTxtPassword(JPasswordField txtPassword)
    {
        this.txtPassword = txtPassword;
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnLogin1;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtID;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
