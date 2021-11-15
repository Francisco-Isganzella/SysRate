/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JRadioButton;

/**
 *
 * @author migue
 */
public class Radio extends JRadioButton {
    
    //private Integer nota;
    
    public Radio(int x, int y){
        
        setBounds(x, y, 20, 20);
        setBackground(Color.GRAY);
        
    }
    

}
