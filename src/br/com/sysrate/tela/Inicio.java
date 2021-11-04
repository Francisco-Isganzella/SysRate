/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *  A ideia inicial é que paineis sejam adicionados na tela conforme os profs são cadastrados no BD
 * 
 */
public class Inicio {
    
    private JFrame janela;
    
    public Inicio() {
        new InicioPaineis();
       
    }
}



