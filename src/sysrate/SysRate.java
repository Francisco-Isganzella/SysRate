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
        janela();
    }
    
    public JFrame janela(){
        JFrame janela = new JFrame("SysRate");
        janela.setSize(500,650);
        janela.getContentPane().setBackground(Color.orange);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return janela;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SysRate();
        
    }
    
}
