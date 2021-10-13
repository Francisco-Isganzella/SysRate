/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysrate;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author franc
 */
public class Cadastro {
    
    private JFrame janela;
    private JPanel paneltitulo;
    private JPanel panelAluno;
    private JPanel panelProfessor;
    private JPanel panelDisciplina;
    private JPanel panelCurso;
    
    public Cadastro(){
        JTextField fieldNomeAluno;
        JTextField fieldMatricula;
        JTextField fieldCurso;
        JButton buttonCadastrarAluno;
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        paneltitulo = new JPanel();
        paneltitulo = painel(30, 30,"");
        paneltitulo.setBackground(Color.ORANGE);
        paneltitulo.add(label("CADASTROS E EXCLUSÕES", 360, 5, 300, 20, 20));
        
        
        panelAluno = new JPanel();
        panelAluno = painel(60, 140, "Aluno");
        panelAluno.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelAluno.add(label("Matricula", 300, 25, 100, 20, 12));
        panelAluno.add(label("Curso", 595, 25, 100, 20, 12));
        panelAluno.add(fieldNomeAluno = new Field(105, 25, 180, 25));
        panelAluno.add(fieldMatricula = new Field(365, 25, 220, 25));
        panelAluno.add(fieldCurso = new Field(640, 25, 220, 25));
        panelAluno.add(buttonCadastrarAluno = new Button(885,25,100,25, "Cadastrar", Color.GREEN));
        
        // CONTINUAR A CRIAR A TABELA
        DefaultTableModel tabelaModelo = new DefaultTableModel();
        JTable tabela = new JTable(tabelaModelo);
        tabela.setLayout(null);
        tabela.setBounds(100, 35, 800, 105);
        tabelaModelo.addColumn("Nome completo");
        tabelaModelo.addColumn("Matrícula");
        tabelaModelo.addColumn("Curso");
        
        panelAluno.add(new JScrollPane(tabela));
        
        panelProfessor = new JPanel();
        panelProfessor = painel(200, 140,"Professor");
        panelProfessor.setBackground(Color.ORANGE);
        
        panelDisciplina = new JPanel();
        panelDisciplina = painel(340, 140, "Disciplina");
        
        panelCurso = new JPanel();
        panelCurso = painel(480, 140, "Curso");
        panelCurso.setBackground(Color.ORANGE);
        
        janela.getContentPane().add(paneltitulo);
        janela.getContentPane().add(panelAluno);
        janela.getContentPane().add(panelCurso);
        janela.getContentPane().add(panelDisciplina);
        janela.getContentPane().add(panelProfessor);
    }
    
    
    public JPanel painel(int x, int height, String tabela){
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(0, x, 1000, height);
        painel.setVisible(true);
        painel.add(label(tabela, 20, 5, 100, 20, 18));
        

        //painel.setBackground(Color.orange);
        return painel;
    }
    
    public JLabel label(String nome, int x, int y, int l, int a, int f){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.PLAIN, f));
        
        
        return label;
    }
    
}
