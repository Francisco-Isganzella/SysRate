/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton buttonExcluiAluno;
        
        JTextField fieldNomeProfessor;
        JTextField fieldProfessorDisciplina;
        JTextField fieldProfessorCurso;
        JTextField fieldProfessorTurma;
        JButton buttonCadastrarProfessor;
        JButton buttonExcluiProfessor;
        
        JTextField fieldNomeDisciplina;
        JTextField fieldDisciplinaCurso;
        JTextField fieldDisciplinaSemestre;
        JButton buttonCadastraDisciplina;
        JButton buttonExcluiDisciplina;
        
        JTextField fieldNomeCurso;
        JButton buttonCadastraCurso;
        JButton buttonExcluiCurso;
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        paneltitulo = new JPanel();
        paneltitulo = painel(30, 30,"");
        paneltitulo.setBackground(Color.ORANGE);
        paneltitulo.add(label("CADASTROS E EXCLUSÕES", 360, 5, 300, 20, 20));
        janela.getContentPane().add(paneltitulo);
        
        
        panelAluno = new JPanel();
        panelAluno = painel(60, 140, "Aluno");
        panelAluno.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelAluno.add(label("Matricula", 310, 25, 100, 20, 12));
        panelAluno.add(label("Curso", 595, 25, 100, 20, 12));
        panelAluno.add(fieldNomeAluno = new Field(100, 25, 200, 25));
        panelAluno.add(fieldMatricula = new Field(365, 25, 220, 25));
        panelAluno.add(fieldCurso = new Field(640, 25, 255, 25));
        panelAluno.add(buttonCadastrarAluno = new Button(902,25,90,25, "Cadastrar", Color.DARK_GRAY, Color.WHITE));
        buttonCadastrarAluno.setFont(new Font("Null", Font.BOLD, 11));
        panelAluno.add(buttonExcluiAluno = new Button(902, 110, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiAluno.setFont(new Font("Null", Font.BOLD, 11));
        janela.getContentPane().add(panelAluno);
        
        
        
        
        DefaultTableModel tabelaModeloAluno = new DefaultTableModel();
        JTable tabelaAluno = new JTable(tabelaModeloAluno);
        tabelaAluno.setLayout(null);
        tabelaAluno.setBounds(50, 115, 900, 80);
        tabelaModeloAluno.addColumn("Nome completo");
        tabelaModeloAluno.addColumn("Matricula");
        tabelaModeloAluno.addColumn("Curso");
        
        buttonCadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] vetorAluno = {fieldNomeAluno.getText(), fieldMatricula.getText(), fieldCurso.getText()};
                tabelaModeloAluno.addRow(vetorAluno);
                fieldNomeAluno.setText("");
                fieldMatricula.setText("");
                fieldCurso.setText("");
            }
        });
        
        
        JScrollPane scrollPaneAluno = new JScrollPane(tabelaAluno);
        scrollPaneAluno.setBounds(100, 115, 800, 80);
        
        
        
        
        
        
        panelProfessor = new JPanel();
        panelProfessor = painel(200, 140,"Professor");
        panelProfessor.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelProfessor.add(label("Disciplina", 270, 25, 100, 20, 12));
        panelProfessor.add(label("Curso", 510, 25, 100, 20, 12));
        panelProfessor.add(label("Turma", 730, 25, 100, 20, 12));
        panelProfessor.add(fieldNomeProfessor = new Field(100, 25, 160, 25));
        panelProfessor.add(fieldProfessorDisciplina = new Field(335, 25, 165, 25));
        panelProfessor.add(fieldProfessorCurso = new Field(550, 25, 170, 25));
        panelProfessor.add(fieldProfessorTurma = new Field(770, 25, 125, 25));
        panelProfessor.add(buttonCadastrarProfessor = new Button(900, 25, 90, 25, "Cadastrar", Color.DARK_GRAY, Color.WHITE));
        buttonCadastrarProfessor.setFont(new Font("null", Font.BOLD, 11));
        panelProfessor.add(buttonExcluiProfessor = new Button(902, 110, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiProfessor.setFont(new Font("Null", Font.BOLD, 11));
        
        DefaultTableModel tabelaModeloProfessor = new DefaultTableModel();
        JTable tabelaProfessor = new JTable(tabelaModeloProfessor);
        tabelaProfessor.setLayout(null);
        tabelaProfessor.setBounds(50, 115, 900, 80);
        tabelaModeloProfessor.addColumn("Nome completo");
        tabelaModeloProfessor.addColumn("Disciplina");
        tabelaModeloProfessor.addColumn("Curso");
        tabelaModeloProfessor.addColumn("Turma");
        
        buttonCadastrarProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] vetorProfessor = {fieldNomeProfessor.getText(), fieldProfessorDisciplina.getText(), fieldProfessorCurso.getText(), fieldProfessorTurma.getText()};
                tabelaModeloProfessor.addRow(vetorProfessor);
                fieldNomeProfessor.setText("");
                fieldProfessorDisciplina.setText("");
                fieldProfessorCurso.setText("");
                fieldProfessorTurma.setText("");
            }
        });
        
        
        JScrollPane scrollPaneProfessor = new JScrollPane(tabelaProfessor);
        scrollPaneProfessor.setBounds(100, 255, 800, 80);
        
        
        panelDisciplina = new JPanel();
        panelDisciplina = painel(340, 140, "Disciplina");
        panelDisciplina.add(label("Nome disciplina", 5, 25, 100, 25, 12));
        panelDisciplina.add(label("Curso", 350, 25, 100, 25, 12));
        panelDisciplina.add(label("Semestre", 635, 25, 100, 25, 12));
        panelDisciplina.add(fieldNomeDisciplina = new Field(100, 25, 245, 25));
        panelDisciplina.add(fieldDisciplinaCurso = new Field(395, 25, 225, 25));
        panelDisciplina.add(fieldDisciplinaSemestre = new Field(700, 25, 195, 25));
        panelDisciplina.add(buttonCadastraDisciplina = new Button(900, 25, 90, 25, "Cadastrar", Color.DARK_GRAY, Color.WHITE));
        buttonCadastraDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        panelDisciplina.add(buttonExcluiDisciplina = new Button(902, 110, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        
        
        DefaultTableModel tabelaModeloDisciplina = new DefaultTableModel();
        JTable tabelaDisciplina = new JTable(tabelaModeloDisciplina);
        tabelaDisciplina.setLayout(null);
        tabelaDisciplina.setBounds(50, 115, 900, 80);
        tabelaModeloDisciplina.addColumn("Disciplina");
        tabelaModeloDisciplina.addColumn("Curso");
        tabelaModeloDisciplina.addColumn("Semestre");
        
        buttonCadastraDisciplina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] vetorDisciplina = {fieldNomeDisciplina.getText(), fieldDisciplinaCurso.getText(), fieldDisciplinaSemestre.getText()};
                tabelaModeloDisciplina.addRow(vetorDisciplina);
                fieldNomeDisciplina.setText("");
                fieldDisciplinaCurso.setText("");
                fieldDisciplinaSemestre.setText("");
            }
        });
        
        
        JScrollPane scrollPaneDisciplina = new JScrollPane(tabelaDisciplina);
        scrollPaneDisciplina.setBounds(100, 395, 800, 80);
        
        panelCurso = new JPanel();
        panelCurso = painel(480, 140, "Curso");
        panelCurso.add(label("Nome do curso", 5, 25, 150, 25, 12));
        panelCurso.add(fieldNomeCurso = new Field(100, 25, 245, 25));
        panelCurso.add(buttonCadastraCurso = new Button(900, 25, 90, 25, "Cadastrar", Color.DARK_GRAY, Color.WHITE));
        buttonCadastraCurso.setFont(new Font("Null", Font.BOLD, 11));
        panelCurso.add(buttonExcluiCurso = new Button(902, 110, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiCurso.setFont(new Font("Null", Font.BOLD, 11));
        
        DefaultTableModel tabelaModeloCurso = new DefaultTableModel();
        JTable tabelaCurso = new JTable(tabelaModeloCurso);
        tabelaCurso.setLayout(null);
        tabelaCurso.setBounds(50, 115, 900, 80);
        tabelaModeloCurso.addColumn("Curso");
        
        buttonCadastraCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] vetorCurso = {fieldNomeCurso.getText()};
                tabelaModeloCurso.addRow(vetorCurso);
                fieldNomeCurso.setText("");
            }
        });
        
        
        JScrollPane scrollPaneCurso = new JScrollPane(tabelaCurso);
        scrollPaneCurso.setBounds(100, 535, 800, 80);
        
        janela.getContentPane().add(scrollPaneCurso);
        janela.getContentPane().add(scrollPaneDisciplina);
        janela.getContentPane().add(scrollPaneProfessor);
        janela.getContentPane().add(scrollPaneAluno);
        
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
        painel.setBackground(Color.orange);
        
        return painel;
    }
    
    public JLabel label(String nome, int x, int y, int l, int a, int f){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.PLAIN, f));
        
        
        return label;
    }
    
}
