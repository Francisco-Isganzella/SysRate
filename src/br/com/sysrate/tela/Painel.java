/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author franc
 */
public class Painel extends JPanel{
    
    public void Painel(int y){
        setPreferredSize(new Dimension(900,y));
        setBackground(Color.orange);
        setVisible(true);
        
    }
    
}
