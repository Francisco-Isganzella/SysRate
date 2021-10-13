/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysrate;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author franc
 */
public class Button extends JButton{

    public Button(int x, int y, int l, int a, String nome, Color cor) {
        setBounds(x, y, l, a);
        setText(nome);
        setBackground(cor);
    }
    
}
