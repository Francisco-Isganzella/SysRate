package br.com.sysrate.tela;

import br.com.sysrate.dao.CursoDao;
import br.com.sysrate.dao.DisciplinaDao;
import br.com.sysrate.dao.NotasDao;
import br.com.sysrate.entidade.Usuario;
import br.com.sysrate.dao.ProfessorDao;
import br.com.sysrate.dao.TurmaDao;
import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Curso;
import br.com.sysrate.entidade.Disciplina;
import br.com.sysrate.entidade.Notas;
import br.com.sysrate.entidade.Professor;
import br.com.sysrate.entidade.Turma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Thiago
 */
public class InicioPaineis {
    private static int profID = 0;
    public Integer i = 1;
    private JFrame frame;
    
    public InicioPaineis() throws SQLException{
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
                }
                
                frame = new JFrame("SysRate");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new PainelPrincipal());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);           
                frame.setResizable(false);
            }
        });
    }
        
        public class PainelPrincipal extends JPanel {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1000, 650);
        }            

        public PainelPrincipal() {
            setLayout(new BorderLayout());
            
            JPanel cabecalho = new JPanel();
                cabecalho.setLayout(null);
                cabecalho.setPreferredSize(new Dimension(1000, 30));
                cabecalho.setBackground(Color.LIGHT_GRAY);

                JLabel nomeUsuario = new JLabel(/*if usuario tiver logado settext(nomeUser) else settext(null)*/"Nome Login");
                nomeUsuario.setBounds(710, 05, 120, 20);

                JButton botaoLoginLogout = new JButton(/*if usuario tiver logado settext("Logout") else settext(Login) */ "Login");
                botaoLoginLogout.setBounds(870, 05, 80, 20);
                botaoLoginLogout.setBackground(Color.DARK_GRAY);
                botaoLoginLogout.setForeground(Color.WHITE);
                botaoLoginLogout.setBorder(null);
                
                if (Validacao.getValidaOnline() == true) {
                    botaoLoginLogout.setText("Logout");
                    nomeUsuario.setText(Validacao.getValidaNome());
                } else {
                    botaoLoginLogout.setText("Login");
                }
                
                Usuario u = new Usuario();
                UsuarioDao uDao = new UsuarioDao();
        
                botaoLoginLogout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (Validacao.getValidaOnline() == true) {
                                Validacao.setValidaOnline(Boolean.FALSE);

                                u.setAtivoOnline(Boolean.FALSE);

                                u.setUsuarioID(Validacao.getValidaID());
                                uDao.alterarLogin(u);

                                new InicioPaineis();
                                InicioPaineis.this.frame.dispose();

                            } else {
                                
                                new LoginCadastro();
                                InicioPaineis.this.frame.dispose();
                            }



                        } catch (SQLException x) {
                            System.err.println("Erro LoginLogout: "+x.getMessage());
                        }
                    }
                });

                ImageIcon imagemBotaoHome = new ImageIcon(getClass().getResource("botaoHome.png"));

                JButton botaoHome = new JButton(imagemBotaoHome);
                botaoHome.setBounds(20, 0, 30, 30);
                botaoHome.setBackground(Color.LIGHT_GRAY);
                botaoHome.setBorder(null);

                botaoHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    try {
                        InicioPaineis.this.frame.dispose();
                        new InicioPaineis();
                        new SysRate().janela().setVisible(false);

                    } catch (SQLException ex) {
                        System.err.println("erro botaoHome:"+ex.getMessage());
                    }


                }
                });
                
                cabecalho.add(nomeUsuario);
                cabecalho.add(botaoLoginLogout);
                cabecalho.add(botaoHome);            

                add(cabecalho, BorderLayout.NORTH);   
                //FIM CABEÇALHO
                
                JPanel painelProf = new JPanel(new GridBagLayout());
                painelProf.setBackground(Color.ORANGE);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weighty = 0;
                add(new JScrollPane(painelProf));
                
                
                List<Professor> ListaProf = new ArrayList();
                ProfessorDao pdao = new ProfessorDao();
                try {
                    ListaProf = pdao.listarProfessor();
                    while (ListaProf.size() > painelProf.getComponentCount()) {        
                        CriacaoPainel pane = new CriacaoPainel();
                        int insertAt = Math.max(0, painelProf.getComponentCount() - 1);                    
                        gbc.gridwidth = GridBagConstraints.REMAINDER;                    
                        painelProf.add(pane, gbc, insertAt);
                        painelProf.revalidate();
                        painelProf.repaint();
                }
                } catch (SQLException ex) {
                    Logger.getLogger(InicioPaineis.class.getName()).log(Level.SEVERE, null, ex);
                } 
        }
    }       
                
        public class CriacaoPainel extends JPanel {
            
            ProfessorDao pdao = new ProfessorDao();
            Professor p = new Professor(); 
            CursoDao cdao = new CursoDao();
            Curso c = new Curso();
            NotasDao ndao = new NotasDao();
            Notas n = new Notas();
            DisciplinaDao ddao = new DisciplinaDao();
            Disciplina d = new Disciplina();
            
            public CriacaoPainel() throws SQLException {                                
                setPreferredSize(new Dimension(900, 120));
                setLayout(null);
                
                //IMAGEM PROF
                ImageIcon imagemProf = new ImageIcon(getClass().getResource("icons_prof.png"));
                JLabel imagemP = new JLabel(imagemProf);
                imagemP.setBounds(20, 3, 100, 100);
                imagemP.setBackground(Color.BLACK);
                add(imagemP);
                
                //FONTES
                Font fonteNomeProf = new Font("", Font.BOLD, 18);
                Font fonteNomeCurso = new Font("",Font.PLAIN, 12);
                Font fonteDisciplinas = new Font("", Font.PLAIN,12);
                Font fonteNotas = new Font("",Font.BOLD,12);
                
                //NOME PROF
                JLabel nomeProf = new JLabel("NOME PROFESSOR");
                nomeProf.setBounds(140, 20, 700, 20);
                nomeProf.setFont(fonteNomeProf);
                add(nomeProf);
                nomeProf.setText(pdao.buscarNomeProfessor(i));
                
                //setProfID(i);
                
                //NOME CURSO
                JLabel cursoProf = new JLabel("CURSO");
                cursoProf.setBounds(140, 50, 400, 20);
                cursoProf.setFont(fonteNomeCurso);
                add(cursoProf);
                cursoProf.setText(cdao.buscarNomeCurso(pdao.buscarNomeProfessor(i)));
                
                //NOME DISCIPLINAS
                JLabel disciplinas = new JLabel("DISCIPLINAS:");
                disciplinas.setBounds(140, 80,180,20);
                disciplinas.setFont(fonteDisciplinas);
                add(disciplinas);                
                
                JLabel NomeDisciplinas = new JLabel("disciplina1" + ", " + "disciplina2");
                NomeDisciplinas.setBounds(220, 80, 300, 20);
                NomeDisciplinas.setFont(fonteDisciplinas);
                add(NomeDisciplinas);
                
                List<Disciplina> ListaDisc = Arrays.asList();
                ListaDisc = ddao.buscarDisciplinaProf(i);

                DefaultTableModel tabelaDisciplinas = new DefaultTableModel();
                JTable tabela = new JTable(tabelaDisciplinas);
                tabelaDisciplinas.addColumn("Disciplina");
                tabelaDisciplinas.getDataVector().clear();// limpa a tabela

                if (!ListaDisc.isEmpty()) { //verifica se a lista não esta vaxia
                    for (Disciplina d : ListaDisc) { //percorre minha lista
                        tabelaDisciplinas.addRow(new Object[]{d.getDisciplina()});
                    }
                }

                ArrayList list = new ArrayList();
                for (int i = 0; i < tabela.getModel().getRowCount(); i++) {
                    list.add(tabela.getModel().getValueAt(i, 0)); //get the all row values at column index 0
                }

                NomeDisciplinas.setText(String.join(", ", list));//o método de buscraDisciplina é feito por professorID
                
                //AVALIAR
                JButton avaliar = new JButton("Avaliar");
                avaliar.setBounds(32, 97, 80, 18);
                avaliar.setBackground(Color.DARK_GRAY);
                avaliar.setForeground(Color.white);
                add(avaliar);
                
                //PAINEL CONTENDO AS NOTAS
                JPanel PainelNotas = new JPanel();
                PainelNotas.setLayout(null);
                PainelNotas.setBounds(590, 10, 300, 100);
                PainelNotas.setBackground(Color.GRAY);
//                PainelNotas.setForeground(Color.black);
                
                JLabel didatica = new JLabel("Didática");
                didatica.setBounds(0,0,200,20);
                didatica.setFont(fonteNotas);
                PainelNotas.add(didatica);
                JLabel didaticaNota = new JLabel();
                didaticaNota.setBounds(200, 0, 50, 20);
                PainelNotas.add(didaticaNota);
                didaticaNota.setText((ndao.buscarMediaDidatica(i)));
                
                JLabel qualidadeMaterial = new JLabel("Qualidade do Material");
                qualidadeMaterial.setBounds(0,20,200,20);
                qualidadeMaterial.setFont(fonteNotas);
                PainelNotas.add(qualidadeMaterial);
                JLabel qualidadeMaterialNota = new JLabel();
                qualidadeMaterialNota.setBounds(200, 20, 50, 20);
                PainelNotas.add(qualidadeMaterialNota);
                qualidadeMaterialNota.setText((ndao.buscarMediaQualidadeMaterial(i)));

                
                JLabel qualidadeCorrecao = new JLabel("Qualidade da Correção");
                qualidadeCorrecao.setBounds(0,40,200,20);
                qualidadeCorrecao.setFont(fonteNotas);
                PainelNotas.add(qualidadeCorrecao);
                JLabel qualidadeCorrecaoNota = new JLabel();
                qualidadeCorrecaoNota.setBounds(200, 40, 50, 20);
                PainelNotas.add(qualidadeCorrecaoNota);
                qualidadeCorrecaoNota.setText((ndao.buscarMediaQualidadeCorrecao(i)));

                
                JLabel receptividade = new JLabel("Receptividade");
                receptividade.setBounds(0,60,200,20);
                receptividade.setFont(fonteNotas);
                PainelNotas.add(receptividade);
                JLabel receptividadeNota = new JLabel();
                receptividadeNota.setBounds(200, 60, 50, 20);
                PainelNotas.add(receptividadeNota);
                receptividadeNota.setText((ndao.buscarMediaReceptividade(i)));

                
                JLabel respeito = new JLabel("Respeito");
                respeito.setBounds(0,80,200,20);
                respeito.setFont(fonteNotas);
                PainelNotas.add(respeito);
                JLabel respeitoNota = new JLabel();
                respeitoNota.setBounds(200, 80, 50, 20);
                PainelNotas.add(respeitoNota);
                respeitoNota.setText((ndao.buscarMediaRespeito(i)));
                
                add(PainelNotas);
                
                
                
                //FIM DO PAINEL CONTENDO AS NOTAS
                
//                Usuario usuario = new Usuario();
                
                avaliar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        System.out.println(nomeProf.getText());
                        try {
                            System.out.println(String.valueOf(pdao.buscarNomeProfessorRetornaID(nomeProf.getText())));
                            setProfID(pdao.buscarNomeProfessorRetornaID(nomeProf.getText()));
                            if (Validacao.getValidaPermissao() == true){
                                JOptionPane.showMessageDialog(null, "Administradores não podem fazer avaliações","Atenção",1);
                                }
                            else if (Validacao.getValidaOnline() == true) {
                                new PerfilResumo();                        
                            }
                            else if (Validacao.getValidaOnline() == false){
                                new LoginCadastro();                         
                            } 
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(InicioPaineis.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                        frame.setVisible(false);
//                        new PerfilResumo();
                        }
                    
                    
                });
                
                setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
                i = i + 1;

            }
        }

    /**
     * @return the profID
     */
    public static int getProfID() {
        return profID;
    }

    /**
     * @param aProfID the profID to set
     */
    public static void setProfID(int aProfID) {
        profID = aProfID;
    }

    /**
     * @return the profID
     */
    
          
 }
