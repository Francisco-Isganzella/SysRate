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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  A ideia inicial é que paineis sejam adicionados na tela conforme os profs são cadastrados no BD
 * 
 */
public class Inicio {
    
    private JFrame janela;
    
    public Inicio() {
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        JPanel painelPrincipal = new JPanel();
            painelPrincipal.setSize(1000, 650);
            painelPrincipal.setBackground(Color.YELLOW); 
            painelPrincipal.setLayout(new GridBagLayout());
            janela.add(painelPrincipal);
            JPanel painelSecundario = new JPanel();
                painelSecundario.setPreferredSize(new Dimension(700, 500));
                painelSecundario.setBackground(Color.WHITE);
                painelPrincipal.add(painelSecundario, new GridBagConstraints());
    }
}