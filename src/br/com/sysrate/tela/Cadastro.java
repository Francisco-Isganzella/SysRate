/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import br.com.sysrate.dao.CursoDao;
import br.com.sysrate.dao.DisciplinaDao;
import br.com.sysrate.dao.ProfessorDao;
import br.com.sysrate.dao.TurmaDao;
import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Curso;
import br.com.sysrate.entidade.Disciplina;
import br.com.sysrate.entidade.Professor;
import br.com.sysrate.entidade.Turma;
import br.com.sysrate.entidade.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
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
    String curs[];
    int cursoID;
    int disciplinaID;
    int turmaID;
    int alunoID;
    int professorID;
    int idProf;
    DefaultTableModel tabelaModeloAluno;
    DefaultTableModel tabelaModeloProfessor;
    String disciplina;
    String curso;
    public Cadastro() throws SQLException{
        
        JTextField fieldNomeAluno;
        JTextField fieldMatricula;
        JButton buttonCadastrarAluno;
        JButton buttonExcluiAluno;
        JButton buttonCancelaAluno;
        JComboBox comboCursos = new Combo().ComboCurso(100, 53, 375, 20);
        
        JTextField fieldNomeProfessor;
        JTextField fieldProfessorTurma;
        
        JButton buttonCadastrarProfessor;
        JButton buttonExcluiProfessor;
        JButton buttonCancelaProfessor;
        JComboBox comboProfessorCursos = new Combo().ComboCurso(100, 53, 160, 20);
        JComboBox comboProfessorDisciplina = new Combo().ComboDisciplina(335, 53, 150, 20);
        
        JTextField fieldNomeDisciplina;
        JButton buttonCadastraDisciplina;
        JButton buttonExcluiDisciplina;
        JButton buttonCancelaDisciplina;
        
        JTextField fieldNomeCurso;
        JButton buttonCadastraCurso;
        JButton buttonExcluiCurso;
        JButton buttonCancelaCurso;
        
        JLabel labelError = new JLabel();
        labelError.setForeground(Color.red);
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        paneltitulo = new JPanel();
        paneltitulo = painel(0, 30, 1000, 30,"");
        paneltitulo.setBackground(Color.ORANGE);
        paneltitulo.add(label("CADASTROS E EXCLUSÕES", 360, 5, 300, 20, 20));
        janela.getContentPane().add(paneltitulo);
        
        panelCurso = new JPanel();
        panelCurso = painel(0, 60, 500, 230, "Curso");
        panelCurso.add(label("Nome do curso", 10, 25, 150, 25, 12));
        panelCurso.add(fieldNomeCurso = new Field(100, 28, 290, 20));
        panelCurso.add(buttonCadastraCurso = new Button(398, 25, 90, 25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastraCurso.setFont(new Font("Null", Font.BOLD, 11));
        panelCurso.add(buttonExcluiCurso = new Button(150, 195, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiCurso.setFont(new Font("Null", Font.BOLD, 11));
        panelCurso.add(buttonCancelaCurso = new Button(250, 195, 90, 25, "Cancelar", Color.DARK_GRAY, Color.orange) );
        buttonCancelaCurso.setFont(new Font("Null", Font.BOLD, 11));
        
        DefaultTableModel tabelaModeloCurso = new DefaultTableModel();
        JTable tabelaCurso = new JTable(tabelaModeloCurso);
        tabelaCurso.setLayout(null);
        tabelaCurso.setBounds(10, 120, 480, 130);
        tabelaModeloCurso.addColumn("Id");
        tabelaModeloCurso.addColumn("Curso");
        tabelaCurso.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaCurso.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaCurso.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaCurso.setDefaultEditor(Object.class, null);
        alinhaCentro(tabelaCurso, 0);
        alinhaCentro(tabelaCurso, 1);
        
        carregarTableModel(tabelaModeloCurso, "curso");
        
        buttonCancelaCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cursoID = 0;
            fieldNomeCurso.setText("");
            buttonCadastraCurso.setText("Cadastrar");
            }
        });
        
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
                            c.setVisivelCurso(Boolean.TRUE);

                            CursoDao cDao = new CursoDao();
                            if (cursoID == 0) {
                                cDao.salvar(c);
                                comboCursos.addItem(c.getCurso());
                                comboProfessorCursos.addItem(c.getCurso());
                            } else{
                                comboCursos.removeItem(curso);
                                comboProfessorCursos.removeItem(curso);
                                c.setCursoID(cursoID);
                                cDao.alterar(c);
                                cursoID = 0;
                                buttonCadastraCurso.setText("Cadastrar");
                                comboCursos.addItem(c.getCurso());
                                comboProfessorCursos.addItem(c.getCurso());
                            }
                    } catch (SQLException e) {
                        System.err.println("Erro ao salvar botão curso:" + e.getMessage());
                    }
                    carregarTableModel(tabelaModeloCurso, "curso");
                    fieldNomeCurso.setText("");
                    }
                
                
            }
        });
        tabelaCurso.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            fieldNomeCurso.setText("");
            
            cursoID = Integer.parseInt(tabelaCurso.getValueAt(tabelaCurso.getSelectedRow(), 0).toString());
            fieldNomeCurso.setText(tabelaCurso.getValueAt(tabelaCurso.getSelectedRow(), 1).toString());
            buttonCadastraCurso.setText("Atualizar");
            curso = tabelaCurso.getValueAt(tabelaCurso.getSelectedRow(), 1).toString();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        
        buttonExcluiCurso.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Curso c = new Curso();
                CursoDao cDao = new CursoDao();
                
                c.setVisivelCurso(false);
                c.setCurso(fieldNomeCurso.getText());
                c.setCursoID(cursoID);
                
                cDao.alterar(c);
                carregarTableModel(tabelaModeloProfessor, "professor");
                comboCursos.removeItem(c.getCurso());
                comboProfessorCursos.removeItem(c.getCurso());
                
                c.setVisivelCurso(false);
            } catch (SQLException ex) {
                System.err.println("Erro no botão excluir curso\n"+ex.getMessage());
            }
                carregarTableModel(tabelaModeloCurso, "curso");
                carregarTableModel(tabelaModeloAluno, "aluno");
                carregarTableModel(tabelaModeloProfessor, "professor");
                fieldNomeCurso.setText("");
                buttonCadastraCurso.setText("Cadastrar");
                cursoID = 0;
                
        }
        });
        
        JScrollPane scrollPaneCurso = new JScrollPane(tabelaCurso);
        scrollPaneCurso.setBounds(10, 120, 480, 130);
        
        panelDisciplina = new JPanel();
        panelDisciplina = painel(500, 60, 500, 230, "Disciplina");
        panelDisciplina.add(label("Nome disciplina", 10, 25, 100, 25, 12));
        panelDisciplina.add(fieldNomeDisciplina = new Field(110, 28, 270, 20));
        panelDisciplina.add(buttonCadastraDisciplina = new Button(387, 25, 90, 25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastraDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        panelDisciplina.add(buttonExcluiDisciplina = new Button(150, 195, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        panelDisciplina.add(buttonCancelaDisciplina = new Button(250, 195, 90, 25, "Cancelar", Color.DARK_GRAY, Color.orange));
        buttonCancelaDisciplina.setFont(new Font("Null", Font.BOLD, 11));
        
        DefaultTableModel tabelaModeloDisciplina = new DefaultTableModel();
        JTable tabelaDisciplina = new JTable(tabelaModeloDisciplina);
        tabelaDisciplina.setLayout(null);
        tabelaDisciplina.setBounds(510, 120, 470, 130);
        tabelaModeloDisciplina.addColumn("Id");
        tabelaModeloDisciplina.addColumn("Disciplina");
        tabelaDisciplina.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaDisciplina.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaDisciplina.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaDisciplina.setDefaultEditor(Object.class, null);
        alinhaCentro(tabelaDisciplina, 0);
        alinhaCentro(tabelaDisciplina, 1);
        
        carregarTableModel(tabelaModeloDisciplina, "disciplina");
        List<Disciplina> lista_disciplinas = new ArrayList();
        DisciplinaDao dDaoL = new DisciplinaDao();
        lista_disciplinas = dDaoL.listarDisciplina();

        buttonCancelaDisciplina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            disciplinaID = 0;
            fieldNomeDisciplina.setText("");
            buttonCadastraDisciplina.setText("Cadastrar");
            }
        });
        
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
                    d.setVisivelDisciplina(Boolean.TRUE);
                    
                    DisciplinaDao dDao = new DisciplinaDao();
                    
                      if (disciplinaID == 0) {
                          dDao.salvar(d);
                          comboProfessorDisciplina.addItem(d.getDisciplina());
                      } else{
                          comboProfessorDisciplina.removeItem(disciplina);
                          d.setDisciplinaID(disciplinaID);
                          dDao.alterar(d);
                          disciplinaID = 0;
                          buttonCadastraDisciplina.setText("Cadastrar");
                          comboProfessorDisciplina.addItem(d.getDisciplina());
                      }
                } catch (SQLException e) {
                    System.err.println("Erro ao salvar botao disciplina\n" + e.getMessage());
                }
                  
                carregarTableModel(tabelaModeloDisciplina, "disciplina");
                fieldNomeDisciplina.setText("");  
                }
                
            }
        });
        
        tabelaDisciplina.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            fieldNomeDisciplina.setText("");
            
            disciplinaID = Integer.valueOf(tabelaDisciplina.getValueAt(tabelaDisciplina.getSelectedRow(), 0).toString());
            fieldNomeDisciplina.setText(tabelaDisciplina.getValueAt(tabelaDisciplina.getSelectedRow(), 1).toString());
            buttonCadastraDisciplina.setText("Atualizar");
            disciplina = tabelaDisciplina.getValueAt(tabelaDisciplina.getSelectedRow(), 1).toString();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        buttonExcluiDisciplina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Disciplina d = new Disciplina();
                    DisciplinaDao dDao = new DisciplinaDao();
                    
                    d.setDisciplina(fieldNomeDisciplina.getText());
                    d.setVisivelDisciplina(false);
                    d.setDisciplinaID(disciplinaID);
                    
                    dDao.alterar(d);
                    comboProfessorDisciplina.removeItem(d.getDisciplina());
                } catch (SQLException sQLException) {
                    System.err.println("Erro botão excluir disciplina\n"+sQLException);
                }
                fieldNomeDisciplina.setText("");
                carregarTableModel(tabelaModeloDisciplina, "disciplina");
                carregarTableModel(tabelaModeloAluno, "aluno");
                carregarTableModel(tabelaModeloProfessor, "professor");
                buttonCadastraDisciplina.setText("Cadastrar");
                disciplinaID = 0;
            }
        });
        
        JScrollPane scrollPaneDisciplina = new JScrollPane(tabelaDisciplina);
        scrollPaneDisciplina.setBounds(510, 120, 470, 130);
        
        panelProfessor = new JPanel();
        panelProfessor = painel(0, 290, 500, 360,"Professor");
        panelProfessor.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelProfessor.add(label("Disciplina", 270, 50, 60, 20, 12));
        panelProfessor.add(label("Curso", 5, 50, 70, 20, 12));
        panelProfessor.add(label("Turma", 270, 25, 100, 20, 12));
        panelProfessor.add(fieldNomeProfessor = new Field(100, 28, 160, 20));
        panelProfessor.add(comboProfessorCursos/* = new Combo().ComboCurso(100, 53, 160, 20)*/);
        panelProfessor.add(comboProfessorDisciplina/* = new Combo().ComboDisciplina(335, 53, 150, 20)*/);
        panelProfessor.add(fieldProfessorTurma = new Field(335, 28, 150, 20));
        panelProfessor.add(buttonCadastrarProfessor = new Button(398,74,90,25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastrarProfessor.setFont(new Font("null", Font.BOLD, 11));
        panelProfessor.add(buttonExcluiProfessor = new Button(150, 290, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiProfessor.setFont(new Font("Null", Font.BOLD, 11));
        panelProfessor.add(buttonCancelaProfessor = new Button(250, 290, 90, 25, "Cancelar", Color.DARK_GRAY, Color.orange));
        buttonCancelaProfessor.setFont(new Font("Null", Font.BOLD, 11));
        
        tabelaModeloProfessor = new DefaultTableModel();
        JTable tabelaProfessor = new JTable(tabelaModeloProfessor);
        tabelaProfessor.setLayout(null);
        tabelaProfessor.setBounds(10, 390, 480, 185);
        tabelaModeloProfessor.addColumn("Id");
        tabelaModeloProfessor.addColumn("Nome completo");
        tabelaModeloProfessor.addColumn("Turma");
        tabelaModeloProfessor.addColumn("Curso");
        tabelaModeloProfessor.addColumn("Disciplina");
        tabelaProfessor.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaProfessor.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaProfessor.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        tabelaProfessor.setDefaultEditor(Object.class, null);
        
        alinhaCentro(tabelaProfessor, 0);
        alinhaCentro(tabelaProfessor, 1);
        alinhaCentro(tabelaProfessor, 2);
        alinhaCentro(tabelaProfessor, 3);
        alinhaCentro(tabelaProfessor, 4);
        
        carregarTableModel(tabelaModeloProfessor, "professor");
        
        buttonCancelaProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            professorID = 0;
            turmaID = 0;
            cursoID = 0;
            disciplinaID = 0;
            idProf =0;
            fieldNomeProfessor.setText("");
            fieldProfessorTurma.setText("");
            comboProfessorDisciplina.setSelectedIndex(0);
            comboProfessorCursos.setSelectedIndex(0);
            buttonCadastrarProfessor.setText("Cadastrar");
            }
        });
        
        buttonCadastrarProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Validacao.validaVazio(fieldNomeProfessor.getText()) 
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
                    // INSERT PROFESSOR (nomeProfessor, visivel);
                    Professor p = new Professor();
                    p.setNomeProfessor(fieldNomeProfessor.getText());
                    p.setVisivelProfessor(Boolean.TRUE);

                    ProfessorDao pDao = new ProfessorDao();
                      if (professorID == 0) {
                          pDao.salvar(p);
                          p = pDao.pesquisarPorNome(fieldNomeProfessor.getText());
                          idProf = p.getProfessorID();
                      }
                    // SELECT CURSO (pelo nome, para retornar seu id);
                    Curso c2 = new Curso();
                    CursoDao cDao2 = new CursoDao();
                    c2 = cDao2.pesquisarPorNome(comboProfessorCursos.getSelectedItem().toString());
                    int idCur = c2.getCursoID();
                    // SELECT DISCIPLINA (pelo nome, para retornar seu id)
                    Disciplina d = new Disciplina();
                    DisciplinaDao dDao = new DisciplinaDao();
                    d = dDao.pesquisaPorNome(comboProfessorDisciplina.getSelectedItem().toString());
                    int idDis = d.getDisciplinaID();
                    // INSERT TURMA (disciplinaID, professorID, cursoID, turma, visivel);
                    Turma t = new Turma();
                    t.setDisciplinaID(idDis);
                    t.setCursoID(idCur);
                    t.setTurma(fieldProfessorTurma.getText());
                    t.setProfessorID(idProf);
                    t.setVisivelTurma(Boolean.TRUE);
                    
                    TurmaDao tDao = new TurmaDao();
                      if (turmaID == 0) {
                          tDao.salvar(t);
                          comboProfessorCursos.setSelectedIndex(0);
                          comboProfessorDisciplina.setSelectedIndex(0);
                      }else{
                          p.setProfessorID(professorID);
                          t.setTurmaID(turmaID);
                          tDao.alterar(t);
                          pDao.alterar(p);
                          turmaID = 0;
                          professorID = 0;
                      }
                    
                } catch (SQLException e) {
                    System.err.println("Erro ao salvar botao professor:" + e.getMessage());
                }

                  carregarTableModel(tabelaModeloProfessor, "professor");
                fieldNomeProfessor.setText("");
                fieldProfessorTurma.setText("");  
                }
                
            }
        });
        
        tabelaProfessor.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fieldNomeProfessor.setText("");
                fieldProfessorTurma.setText("");
                
                Curso c = new Curso();
                c.setCurso(tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 3).toString());
                
                Disciplina d = new Disciplina();
                d.setDisciplina(tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 4).toString());
                
                Professor p = new Professor();
                ProfessorDao pDao = new ProfessorDao();
                DisciplinaDao dDao = new DisciplinaDao();
                CursoDao cDao = new CursoDao();
                try {
                    c = cDao.pesquisarPorNome(c.getCurso());
                    cursoID = c.getCursoID();
                    d = dDao.pesquisaPorNome(d.getDisciplina());
                    disciplinaID = d.getDisciplinaID();
                    p = pDao.pesquisarPorNome(tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 1).toString());
                    professorID = p.getProfessorID();
                } catch (SQLException ex) {
                    System.err.println("erropesquisaporprofessor"+ex.getMessage());
                }
                
                turmaID = Integer.valueOf(tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 0).toString());
                fieldNomeProfessor.setText(tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 1).toString());
                fieldProfessorTurma.setText(tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 2).toString());
                comboProfessorCursos.setSelectedItem(c.getCurso());
                comboProfessorDisciplina.setSelectedItem(d.getDisciplina());
                
                buttonCadastrarProfessor.setText("Atualizar");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        buttonExcluiProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Turma t = new Turma();
                TurmaDao tDao = new TurmaDao();
                Professor p = new Professor();
                ProfessorDao pDao = new ProfessorDao();
                Disciplina d = new Disciplina();
                DisciplinaDao dDao = new DisciplinaDao();
                
                t.setTurma(fieldProfessorTurma.getText());
                t.setVisivelTurma(false);
                t.setTurmaID(turmaID);
                t.setDisciplinaID(disciplinaID);
                t.setCursoID(cursoID);
                p.setProfessorID(professorID);
                p.setVisivelProfessor(Boolean.FALSE);
                p.setNomeProfessor(fieldNomeProfessor.getText());
                try {
                    pDao.alterar(p);
                    tDao.alterar(t);
                } catch (SQLException ex) {
                    System.err.println("Erro botao excluir professor\n"+ex.getMessage());
                }
                carregarTableModel(tabelaModeloProfessor, "professor");
                buttonCadastrarProfessor.setText("Cadastrar");
                turmaID = 0;
            }
        });
        
        panelProfessor.add(comboProfessorCursos);
        JScrollPane scrollPaneProfessor = new JScrollPane(tabelaProfessor);
        scrollPaneProfessor.setBounds(10, 390, 480, 185);
        
        panelAluno = new JPanel();
        panelAluno = painel(500, 290, 500, 360, "Aluno");
        panelAluno.add(label("Nome completo", 5, 25, 100, 20, 12));
        panelAluno.add(label("Matricula", 270, 25, 100, 20, 12));
        panelAluno.add(label("Curso", 5, 50, 100, 20, 12));
        panelAluno.add(fieldNomeAluno = new Field(100, 28, 160, 20));
        panelAluno.add(fieldMatricula = new Field(325, 28, 150, 20));
        panelAluno.add(comboCursos/* = new Combo().ComboCurso(100, 53, 375, 20)*/);
        
        panelAluno.add(buttonCadastrarAluno = new Button(387,74,90,25, "Cadastrar", Color.gray, Color.WHITE));
        buttonCadastrarAluno.setFont(new Font("Null", Font.BOLD, 11));
        panelAluno.add(buttonExcluiAluno = new Button(150, 290, 90, 25, "Excluir", Color.DARK_GRAY, Color.WHITE));
        buttonExcluiAluno.setFont(new Font("Null", Font.BOLD, 11));
        panelAluno.add(buttonCancelaAluno = new Button(250, 290, 90, 25, "Cancelar", Color.DARK_GRAY, Color.orange));
        buttonCancelaAluno.setFont(new Font("Null", Font.BOLD, 11));
        
        tabelaModeloAluno = new DefaultTableModel();
        JTable tabelaAluno = new JTable(tabelaModeloAluno);
        tabelaAluno.setLayout(null);
        tabelaAluno.setBounds(510, 390, 470, 185);
        tabelaModeloAluno.addColumn("Id");
        tabelaModeloAluno.addColumn("Nome completo");
        tabelaModeloAluno.addColumn("Matricula");
        tabelaModeloAluno.addColumn("Curso");
        tabelaAluno.setDefaultEditor(Object.class, null);
        tabelaAluno.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaAluno.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaAluno.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        alinhaCentro(tabelaAluno, 0);
        alinhaCentro(tabelaAluno, 1);
        alinhaCentro(tabelaAluno, 2);
        alinhaCentro(tabelaAluno, 3);
        
        carregarTableModel(tabelaModeloAluno, "aluno");
        
        buttonCancelaAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cursoID = 0;
            alunoID = 0;
            fieldNomeAluno.setText("");
            fieldMatricula.setText("");
            comboCursos.setSelectedIndex(0);
            buttonCadastrarAluno.setText("Cadastrar");
            }
        });
        
        buttonCadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Validacao.validaVazio(fieldNomeAluno.getText()) 
                    || Validacao.validaVazio(fieldMatricula.getText())) {
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
                        // SELECT NO CURSO
                        Curso c = new Curso();
                        CursoDao cDao = new CursoDao();
                        
                        c = cDao.pesquisarPorNome(comboCursos.getSelectedItem().toString());
                        int id = c.getCursoID();
                        // INSERT NO USUARIO
                        Usuario u = new Usuario();

                        u.setNomeUsuario(fieldNomeAluno.getText());
                        u.setMatricula(fieldMatricula.getText());
                        u.setCursoID(id);
                        u.setAtivoOnline(Boolean.FALSE);
                        u.setPermissao(Boolean.FALSE);

                        UsuarioDao uDao = new UsuarioDao();
                        if (alunoID == 0) {
                            if (Validacao.validaMatricula(fieldMatricula.getText()) == false) {
                                uDao.salvar(u);
                                labelError.setText("");
                            } else {
                                panelAluno.add(labelError);
                                labelError.setBounds(180, 80, 200, 20);
                                labelError.setText("Matricula já cadastrada");
                            }
                        }else {
                            u.setUsuarioID(alunoID);
                            u.setCursoID(id);
                            uDao.alterar(u);
                            alunoID = 0;
                            cursoID = 0;
                            buttonCadastrarAluno.setText("Cadastrar");
                        }
                    } catch (SQLException e) {
                        System.err.println("Erro ao salvar botão usuario:" + e.getMessage());
                    }
                    
                    carregarTableModel(tabelaModeloAluno, "aluno");
                    fieldNomeAluno.setText("");
                    fieldMatricula.setText("");
                    comboCursos.setSelectedIndex(0);
                }
                
            }
        });
        
        tabelaAluno.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fieldNomeAluno.setText("");
                fieldMatricula.setText("");
                
                Curso c = new Curso();
                
                alunoID = Integer.valueOf(tabelaAluno.getValueAt(tabelaAluno.getSelectedRow(), 0).toString());
                fieldNomeAluno.setText(tabelaAluno.getValueAt(tabelaAluno.getSelectedRow(), 1).toString());
                fieldMatricula.setText(tabelaAluno.getValueAt(tabelaAluno.getSelectedRow(), 2).toString());
                c.setCurso(tabelaAluno.getValueAt(tabelaAluno.getSelectedRow(), 3).toString());
                comboCursos.setSelectedItem(c.getCurso());
                
                buttonCadastrarAluno.setText("Atualizar");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        buttonExcluiAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            UsuarioDao uDao = new UsuarioDao();
                try {
                    uDao.excluir(alunoID);
                } catch (SQLException ex) {
                    System.err.println("Erro botao excluir aluno\n"+ex.getMessage());
                }
            carregarTableModel(tabelaModeloAluno, "aluno");
            fieldNomeAluno.setText("");
            fieldMatricula.setText("");
            buttonCadastrarAluno.setText("Cadastrar");
            alunoID = 0;
            cursoID = 0;
            comboCursos.setSelectedIndex(0);
            }
        });
        
        JScrollPane scrollPaneAluno = new JScrollPane(tabelaAluno);
        scrollPaneAluno.setBounds(510, 390, 470, 185);
        
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
    
    public static void carregarTableModel(DefaultTableModel tabelaModelo, String tabela){
    try {

        switch (tabela) {
            case "curso":
                tabelaModelo.getDataVector().clear();
                List<Curso> lista_cursos = new ArrayList();
                CursoDao cDaoL = new CursoDao();
                lista_cursos = cDaoL.listarCurso();
                if (!lista_cursos.isEmpty()) {
                    for (Curso c : lista_cursos) {
                        if (c.getVisivelCurso()== true) {
                            tabelaModelo.addRow(new Object[]{c.getCursoID(), c.getCurso()});
                        }
                    }
                }       break;
            case "disciplina":
                tabelaModelo.getDataVector().clear();
                List<Disciplina> lista_disciplinas = new ArrayList();
                DisciplinaDao dDaoL = new DisciplinaDao();
                lista_disciplinas = dDaoL.listarDisciplina();
                if (!lista_disciplinas.isEmpty()) {
                    for (Disciplina d : lista_disciplinas) {
                        if (d.getVisivelDisciplina()== true) {
                            tabelaModelo.addRow(new Object[]{d.getDisciplinaID(), d.getDisciplina()});
                        } 
                    }
                }       break;
            case "professor":
                tabelaModelo.getDataVector().clear();
                List<Turma> lista_turma = new ArrayList();
                TurmaDao tDaoL = new TurmaDao();
                lista_turma = tDaoL.listarTurmaInner();
                if (!lista_turma.isEmpty()) {
                    for (Turma t : lista_turma) {
                        if (t.getVisivelTurma()) {
                                tabelaModelo.addRow(new Object[]{t.getTurmaID(), t.getNomeProfessor(),
                                    t.getTurma(), t.getCurso(), t.getDisciplina(),t.getProfessorID()
                                });
                        }
                    }
                }       break;
            case "aluno":
                tabelaModelo.getDataVector().clear();
                List<Usuario> lista_usuario = new ArrayList<Usuario>();
                UsuarioDao uDaoL = new UsuarioDao();
                lista_usuario = uDaoL.listarUsuarioInner();
                if (!lista_usuario.isEmpty()) {
                    for (Usuario u : lista_usuario) {
                        if (u.getVisivelCurso() == true) {
                            tabelaModelo.addRow(new Object[]{u.getUsuarioID(), u.getNomeUsuario(),
                                u.getMatricula(), u.getCurso()});
                        }
                    }
                }      break; 
            default:
                break;
        }
        
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tabela\n" + e.getMessage());
        }
    }
    
    private void alinhaCentro(JTable table, int column) {
        DefaultTableCellRenderer alinhar = new DefaultTableCellRenderer();
        alinhar.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(column).setCellRenderer(alinhar);
    }
}
