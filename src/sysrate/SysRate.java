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
    
    private JFrame janela;
    
    public SysRate(){
        
        janela();
        
    }
    
    public JFrame janela(){
        janela = new JFrame("SysRate");
        janela.setSize(1000,650);
        janela.getContentPane().setBackground(Color.orange);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        
        return janela;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SysRate();
    }
    
}
