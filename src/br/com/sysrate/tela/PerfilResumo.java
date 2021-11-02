/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class PerfilResumo {
    
    private JFrame janela;
    
    private JPanel panelUP;
    
    private JPanel panelDOWN;
    
    public PerfilResumo (){
    
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        ImageIcon imagemFotoPerfil = new ImageIcon(getClass().getResource("fotoPerfil.png"));
        
        JButton buttonFazerAvaliacao;
        
        JLabel labelFotoPerfil = new JLabel(imagemFotoPerfil);
        labelFotoPerfil.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
        labelFotoPerfil.setBackground(Color.WHITE);
        labelFotoPerfil.setBounds(70, 65, 200, 200);
        
        JLabel labelNomeProfessor = new JLabel("Professor: + Método Atributo Nome, Tabela Professor");
        labelNomeProfessor.setBounds(40, 20, 400, 20);
        labelNomeProfessor.setForeground(Color.BLACK);
        labelNomeProfessor.setFont(new Font(null, Font.BOLD, 20) {
        });
        
        JLabel labelNomeDisciplina = new JLabel("Disciplina: + Método Atributo Nome, Tabela Disciplina");
        labelNomeDisciplina.setBounds(300, 225, 400, 20);
        labelNomeDisciplina.setForeground(Color.BLACK);
        labelNomeDisciplina.setFont(new Font(null, Font.PLAIN, 16) {
        });
        
        JLabel labelNomeCurso = new JLabel("Curso: + Método Atributo Nome, Tabela Curso");
        labelNomeCurso.setBounds(300, 245, 400, 20);
        labelNomeCurso.setForeground(Color.BLACK);
        labelNomeCurso.setFont(new Font(null, Font.PLAIN, 16) {
        });
        
        
        
        panelUP = new JPanel();
        panelUP.setLayout(null);
        panelUP = painel(0, 30, 1000, 310, "");
        panelUP.setBackground(Color.WHITE);
        panelUP.add(labelNomeProfessor);
        panelUP.add(labelFotoPerfil);
        panelUP.add(buttonFazerAvaliacao = new Button(830, 20, 130, 30, "Fazer Avaliação", Color.DARK_GRAY, Color.WHITE));
        panelUP.add(label("EMOJI DESTAQUE", 400, 60, 180, 30, 16, Color.BLACK));
        panelUP.add(label("NOTA RESUMO", 700, 60, 180, 30, 16, Color.BLACK));
        panelUP.add(labelNomeDisciplina);
        panelUP.add(labelNomeCurso);      
        
        
        panelDOWN = new JPanel();
        panelDOWN.setLayout(null);
        panelDOWN = painel(0, 340, 1000, 310, "");
        panelDOWN.setBackground(Color.GRAY);
        panelDOWN.add(labelTitulo("DIDÁTICA", 40, 20, 200, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("QUALIDADE DO MATERIAL", 40, 70, 300, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("FEEDBACK AVALIATIVO", 40, 120, 300, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("RECEPTIVIDADE", 40, 170, 300, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("RESPEITO", 40, 220, 300, 30, 20, Color.BLACK));
        
        
        janela.getContentPane().add(panelUP);
        janela.getContentPane().add(panelDOWN);
        
    }
    
    public JPanel painel(int x, int y, int l, int a, String tabela){
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(x,y,l,a);
        painel.setVisible(true);
        painel.add(label(tabela, 20, 5, 100, 20, 18, Color.BLACK));
        

        //painel.setBackground(Color.orange);
        return painel;
    }
    
    public JLabel label(String nome, int x, int y, int l, int a, int f, Color fontColor){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.PLAIN, f));
        label.setForeground(fontColor);
        
        
        
        return label;
    }
    
    public JLabel labelTitulo (String nome, int x, int y, int l, int a, int f, Color fontColor){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.BOLD, f));
        label.setForeground(fontColor);
        
        return label;
    }
    
    //public JLabel labelEstrelaCinza 
    
    //public JLabel labelEstrelaAmarela 
    
}
    
