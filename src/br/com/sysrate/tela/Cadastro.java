/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import br.com.sysrate.dao.CursoDao;
import br.com.sysrate.dao.DisciplinaDao;
import br.com.sysrate.dao.ProfessorDao;
import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Curso;
import br.com.sysrate.entidade.Disciplina;
import br.com.sysrate.entidade.Professor;
import br.com.sysrate.entidade.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
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
    Boolean semestre = true;
    
    public Cadastro(){
        JButton buttonSemestre;
        JLabel labelSemestre = new JLabel();
        
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
        JButton buttonCadastraDisciplina;
        JButton buttonExcluiDisciplina;
        
        JTextField fieldNomeCurso;
        JButton buttonCadastraCurso;
        JButton buttonExcluiCurso;
        
        JLabel labelError = new JLabel();
        labelError.setForeground(Color.red);
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        paneltitulo = new JPanel();
        paneltitulo = painel(0, 30, 1000, 30,"");
        paneltitulo.setBackground(Color.ORANGE);
        paneltitulo.add(label("CADASTROS E EXCLUSÕES", 360, 5, 300, 20, 20));
        paneltitulo.add(buttonSemestre = new Button(640, 5, 152, 20, "Ativar avaliações", Color.gray, Color.white));
        labelSemestre.setBounds(810, 5, 200, 20);
        paneltitulo.add(labelSemestre);
        janela.getContentPane().add(paneltitulo);
        
        
        
        buttonSemestre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (semestre == true) {
                    semestre = false;
                } else {
                    semestre = true;
                }
                
                if (semestre == true) {
                    labelSemestre.setForeground(Color.red);
                    labelSemestre.setText("AVALIAÇÕES DESATIVADAS");
                    buttonSemestre.setText("Ativar avaliações");
                } else {
                    labelSemestre.setForeground(Color.black);
                    labelSemestre.setText("AVALIAÇÕES ATIVADAS");
                    buttonSemestre.setText("Desativar avaliações");
                    
                }
            }
        });
        
        panelCurso = new JPanel();
        panelCurso = painel(0, 60, 500, 230, "Curso");
        panelCurso.add(label("Nome do curso", 10, 25, 150, 25, 12));
        panelCurso.add(fieldNomeCurso = new Field(100, 28, 290, 20));
        panelCurso.add(buttonCadastraCurso = new Button(398, 25, 90, 25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastraCurso.setFont(new Font("Null", Font.BOLD, 11));
        panelCurso.add(buttonExcluiCurso = new Button(210, 195, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiCurso.setFont(new Font("Null", Font.BOLD, 11));
        
        
        DefaultTableModel tabelaModeloCurso = new DefaultTableModel();
        JTable tabelaCurso = new JTable(tabelaModeloCurso);
        tabelaCurso.setLayout(null);
        tabelaCurso.setBounds(10, 120, 480, 130);
        tabelaModeloCurso.addColumn("Curso");
        
        
        buttonCadastraCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    if (Validacao.validaVazio(fieldNomeCurso.getText())) {
                        panelCurso.add(labelError);
                        labelError.setBounds(180, 45, 150, 15);
                        labelError.setText("Preencha o campo curso");
                        
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                        labelError.setText("");
                        timer.cancel();
                            }
                        };
                        timer.schedule(task, 2000L);
                    } else {
                        try {
                        Curso c = new Curso();
                        c.setCurso(fieldNomeCurso.getText());
                        c.setVisivel(Boolean.TRUE);

                        CursoDao cDao = new CursoDao();

                        cDao.salvar(c);
                    } catch (Exception e) {
                        System.err.println("Erro ao salvar botão curso:" + e.getMessage());
                    }

                    // MOSTRA OS DADOS DO FIELD DIRETO NO TABLE SEM BUSCAR NO BD
                    String[] vetorCurso = {fieldNomeCurso.getText()};
                    tabelaModeloCurso.addRow(vetorCurso);
                    fieldNomeCurso.setText("");
                    }
                
                
            }
        });
        
        JScrollPane scrollPaneCurso = new JScrollPane(tabelaCurso);
        scrollPaneCurso.setBounds(10, 120, 480, 130);
        
        panelDisciplina = new JPanel();
        panelDisciplina = painel(500, 60, 500, 230, "Disciplina");
        panelDisciplina.add(label("Nome disciplina", 10, 25, 100, 25, 12));
        panelDisciplina.add(fieldNomeDisciplina = new Field(110, 28, 280, 20));
        panelDisciplina.add(buttonCadastraDisciplina = new Button(398, 25, 90, 25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastraDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        panelDisciplina.add(buttonExcluiDisciplina = new Button(210, 195, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        
        DefaultTableModel tabelaModeloDisciplina = new DefaultTableModel();
        JTable tabelaDisciplina = new JTable(tabelaModeloDisciplina);
        tabelaDisciplina.setLayout(null);
        tabelaDisciplina.setBounds(510, 120, 480, 130);
        tabelaModeloDisciplina.addColumn("Disciplina");
        
        buttonCadastraDisciplina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Validacao.validaVazio(fieldNomeDisciplina.getText())) {
                    panelDisciplina.add(labelError);
                    labelError.setBounds(180, 45, 200, 15);
                    labelError.setText("Preencha o campo disciplina");
                    
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                    labelError.setText("");
                    timer.cancel();
                        }
                    };
                    timer.schedule(task, 2000L);
                } else {
                  try {
                    Disciplina d = new Disciplina();
                    d.setDisciplina(fieldNomeDisciplina.getText());
                    d.setVisivel(Boolean.TRUE);
                    
                    DisciplinaDao dDao = new DisciplinaDao();
                    
                    dDao.salvar(d);
                } catch (Exception e) {
                    System.err.println("Erro ao salvar botao disciplina:" + e.getMessage());
                }
                // MOSTRA OS DADOS DO FIELD DIRETO NO TABLE SEM BUSCAR NO BD
                String[] vetorDisciplina = {fieldNomeDisciplina.getText()};
                tabelaModeloDisciplina.addRow(vetorDisciplina);
                fieldNomeDisciplina.setText("");  
                }
                
            }
        });
        
        JScrollPane scrollPaneDisciplina = new JScrollPane(tabelaDisciplina);
        scrollPaneDisciplina.setBounds(510, 120, 480, 130);
        
        panelProfessor = new JPanel();
        panelProfessor = painel(0, 290, 500, 360,"Professor");
        panelProfessor.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelProfessor.add(label("Disciplina", 270, 25, 100, 20, 12));
        panelProfessor.add(label("Curso", 5, 50, 70, 20, 12));
        panelProfessor.add(label("Turma", 270, 50, 60, 20, 12));
        panelProfessor.add(fieldNomeProfessor = new Field(100, 28, 160, 20));
        panelProfessor.add(fieldProfessorDisciplina = new Field(335, 28, 155, 20));
        panelProfessor.add(fieldProfessorCurso = new Field(100, 53, 160, 20));
        panelProfessor.add(fieldProfessorTurma = new Field(335, 53, 155, 20));
        panelProfessor.add(buttonCadastrarProfessor = new Button(398,74,90,25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastrarProfessor.setFont(new Font("null", Font.BOLD, 11));
        panelProfessor.add(buttonExcluiProfessor = new Button(210, 302, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiProfessor.setFont(new Font("Null", Font.BOLD, 11));
        
        DefaultTableModel tabelaModeloProfessor = new DefaultTableModel();
        JTable tabelaProfessor = new JTable(tabelaModeloProfessor);
        tabelaProfessor.setLayout(null);
        tabelaProfessor.setBounds(10, 390, 480, 200);
        tabelaModeloProfessor.addColumn("Nome completo");
        tabelaModeloProfessor.addColumn("Disciplina");
        tabelaModeloProfessor.addColumn("Curso");
        tabelaModeloProfessor.addColumn("Turma");
        
        
        
        buttonCadastrarProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Validacao.validaVazio(fieldNomeProfessor.getText()) 
                    || Validacao.validaVazio(fieldProfessorDisciplina.getText())
                    || Validacao.validaVazio(fieldProfessorCurso.getText())
                    || Validacao.validaVazio(fieldProfessorTurma.getText())) {
                    panelProfessor.add(labelError);
                    labelError.setBounds(180, 80, 200, 20);
                    labelError.setText("Preencha todos os campos");
                    
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                    labelError.setText("");
                    timer.cancel();
                        }
                    };
                    timer.schedule(task, 2000L);
                } else {
                  try {
                    Professor p = new Professor();
                    p.setNomeProfessor(fieldNomeProfessor.getText());
                    p.setVisivel(Boolean.TRUE);

                    ProfessorDao pDao = new ProfessorDao();
                    pDao.salvar(p);
                    
                } catch (Exception e) {
                    System.err.println("Erro ao salvar botao professor:" + e.getMessage());
                }
                //MOSTRA OS DADOS DO FIELD DIRETO NO TABLE SEM BUSCAR NO BD
                String[] vetorProfessor = {fieldNomeProfessor.getText(), fieldProfessorDisciplina.getText(), fieldProfessorCurso.getText(), fieldProfessorTurma.getText()};
                tabelaModeloProfessor.addRow(vetorProfessor);
                fieldNomeProfessor.setText("");
                fieldProfessorDisciplina.setText("");
                fieldProfessorCurso.setText("");
                fieldProfessorTurma.setText("");  
                }
                
            }
        });
        
        JScrollPane scrollPaneProfessor = new JScrollPane(tabelaProfessor);
        scrollPaneProfessor.setBounds(10, 390, 480, 200);
        
        panelAluno = new JPanel();
        panelAluno = painel(500, 290, 500, 360, "Aluno");
        panelAluno.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelAluno.add(label("Matricula", 270, 25, 100, 20, 12));
        panelAluno.add(label("Curso", 5, 50, 100, 20, 12));
        panelAluno.add(fieldNomeAluno = new Field(100, 28, 160, 20));
        panelAluno.add(fieldMatricula = new Field(325, 28, 163, 20));
        panelAluno.add(fieldCurso = new Field(100, 53, 390, 20));
        panelAluno.add(buttonCadastrarAluno = new Button(398,74,90,25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastrarAluno.setFont(new Font("Null", Font.BOLD, 11));
        panelAluno.add(buttonExcluiAluno = new Button(210, 302, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiAluno.setFont(new Font("Null", Font.BOLD, 11));
        
        
        DefaultTableModel tabelaModeloAluno = new DefaultTableModel();
        JTable tabelaAluno = new JTable(tabelaModeloAluno);
        tabelaAluno.setLayout(null);
        tabelaAluno.setBounds(510, 390, 480, 200);
        tabelaModeloAluno.addColumn("Nome completo");
        tabelaModeloAluno.addColumn("Matricula");
        tabelaModeloAluno.addColumn("Curso");
        
        buttonCadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Validacao.validaVazio(fieldNomeAluno.getText()) 
                    || Validacao.validaVazio(fieldMatricula.getText()) 
                    || Validacao.validaVazio(fieldCurso.getText())) {
                    panelAluno.add(labelError);
                    labelError.setBounds(180, 80, 200, 20);
                    labelError.setText("Preencha todos os campos");
                    
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                    labelError.setText("");
                    timer.cancel();
                        }
                    };
                    timer.schedule(task, 2000L);
                } else {
                    try {
                        
                        Curso c = new Curso();
                        CursoDao cDao = new CursoDao();
                        
                        cDao.pesquisarPorNome(fieldCurso.getText());
                        System.out.println(c.getCurso());
                        
                        /*Usuario u = new Usuario();

                        u.setNomeUsuario(fieldNomeAluno.getText());
                        u.setMatricula(fieldMatricula.getText());
                        u.setAtivoOnline(Boolean.FALSE);
                        u.setPermissao(Boolean.FALSE);




                        UsuarioDao uDao = new UsuarioDao();
                        uDao.salvar(u);*/
                    } catch (Exception e) {
                        System.err.println("Erro ao salvar botão usuario:" + e.getMessage());
                    }
                // MOSTRA OS DADOS DO FIELD DIRETO NO TABLE SEM BUSCAR NO BD
                String[] vetorAluno = {fieldNomeAluno.getText(), fieldMatricula.getText(), fieldCurso.getText()};
                tabelaModeloAluno.addRow(vetorAluno);
                fieldNomeAluno.setText("");
                fieldMatricula.setText("");
                fieldCurso.setText("");
                }
                
            }
        });
        
        JScrollPane scrollPaneAluno = new JScrollPane(tabelaAluno);
        scrollPaneAluno.setBounds(510, 390, 480, 200);
        
        janela.getContentPane().add(scrollPaneCurso);
        janela.getContentPane().add(scrollPaneDisciplina);
        janela.getContentPane().add(scrollPaneProfessor);
        janela.getContentPane().add(scrollPaneAluno);
        
        janela.getContentPane().add(panelAluno);
        janela.getContentPane().add(panelAluno);
        janela.getContentPane().add(panelCurso);
        janela.getContentPane().add(panelDisciplina);
        janela.getContentPane().add(panelProfessor);
    }
    
    
    public JPanel painel(int x, int y,int weight, int height, String tabela){
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(x, y, weight, height);
        painel.setVisible(true);
        painel.add(label(tabela, 20, 5, 100, 20, 18));
        painel.setBackground(Color.orange);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        return painel;
    }
    
    public JLabel label(String nome, int x, int y, int l, int a, int f){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.PLAIN, f));
        
        
        return label;
    }
    
}
