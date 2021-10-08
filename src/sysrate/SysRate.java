/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysrate;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author franc
 */
public class SysRate {

    public SysRate(){
        home();
    }
    
    public JFrame home(){
        JFrame home = new JFrame("SysRate");
        home.setSize(500,650);
        home.getContentPane().setBackground(Color.orange);
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        home.setLayout(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return home;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SysRate();
        
    }
    
}
