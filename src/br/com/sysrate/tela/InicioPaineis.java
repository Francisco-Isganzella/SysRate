package br.com.sysrate.tela;

import br.com.sysrate.dao.DisciplinaDao;
import br.com.sysrate.entidade.Usuario;
import br.com.sysrate.dao.ProfessorDao;
import br.com.sysrate.dao.TurmaDao;
import br.com.sysrate.entidade.Disciplina;
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
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    
    public Integer i = 1;
    
    public InicioPaineis() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                JFrame frame = new JFrame("SysRate");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new PainelPrincipal());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);                 
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

                ImageIcon imagemBotaoHome = new ImageIcon(getClass().getResource("botaoHome.png"));

                JButton botaoHome = new JButton(imagemBotaoHome);
                botaoHome.setBounds(20, 0, 30, 30);
                botaoHome.setBackground(Color.LIGHT_GRAY);
                botaoHome.setBorder(null);

                cabecalho.add(nomeUsuario);
                cabecalho.add(botaoLoginLogout);
                cabecalho.add(botaoHome);            

                add(cabecalho, BorderLayout.NORTH);   
                
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
            DisciplinaDao ddao = new DisciplinaDao();
            Disciplina d = new Disciplina();
            
            public CriacaoPainel() throws SQLException {
                                
                setPreferredSize(new Dimension(900, 120));
                setLayout(null);
                
                ImageIcon imagemProf = new ImageIcon(getClass().getResource("icons_prof.png"));
                JLabel imagemP = new JLabel(imagemProf);
                imagemP.setBounds(20, 3, 100, 100);
                imagemP.setBackground(Color.BLACK);
                add(imagemP);
                
                Font fonteNomeProf = new Font("", Font.BOLD, 18);
                Font fonteNomeCurso = new Font("",Font.PLAIN, 12);
                Font fonteDisciplinas = new Font("", Font.PLAIN,12);
                Font fonteNotas = new Font("",Font.PLAIN,12);
                
                JLabel nomeProf = new JLabel("NOME PROFESSOR");
                nomeProf.setBounds(140, 20, 700, 20);
                nomeProf.setFont(fonteNomeProf);
                add(nomeProf);
                nomeProf.setText(pdao.buscarNomeProfessor(i));

                JLabel cursoProf = new JLabel("CURSO");
                cursoProf.setBounds(140, 50, 400, 20);
                cursoProf.setFont(fonteNomeCurso);
                add(cursoProf);
                //curoProf.setText("substituir com nome do curso do banco, fazer isto na ação do botão");
                
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
                
                if (!ListaDisc.isEmpty()) { //verifica se a lista não esta vaxia
                    for (Disciplina d : ListaDisc) { //percorre minha lista
                        tabelaDisciplinas.addRow(new Object[]{d.getDisciplina()});
                    }
                }
                System.out.println(tabelaDisciplinas.getValueAt(i, 0));

                NomeDisciplinas.setText("");//o método de buscraDisciplina é feito por professorID
                i = i + 1;
                                                
                JButton avaliar = new JButton("Avaliar");
                avaliar.setBounds(32, 97, 80, 18);
                avaliar.setBackground(Color.ORANGE);
                add(avaliar);
                
                //PAINEL CONTENDO AS NOTAS
                JPanel PainelNotas = new JPanel();
                PainelNotas.setLayout(null);
                PainelNotas.setBounds(590, 10, 300, 100);
                PainelNotas.setBackground(Color.DARK_GRAY);
                
                JLabel didatica = new JLabel("Didática");
                didatica.setBounds(0,0,200,20);
                didatica.setFont(fonteNotas);
                PainelNotas.add(didatica);
                
                JLabel qualidadeMaterial = new JLabel("Qualidade do Material");
                qualidadeMaterial.setBounds(0,20,200,20);
                qualidadeMaterial.setFont(fonteNotas);
                PainelNotas.add(qualidadeMaterial);
                
                JLabel qualidadeCorrecao = new JLabel("Qualidade da Correção");
                qualidadeCorrecao.setBounds(0,40,200,20);
                qualidadeCorrecao.setFont(fonteNotas);
                PainelNotas.add(qualidadeCorrecao);
                
                JLabel receptividade = new JLabel("Receptividade");
                receptividade.setBounds(0,60,200,20);
                receptividade.setFont(fonteNotas);
                PainelNotas.add(receptividade);
                
                JLabel respeito = new JLabel("Respeito");
                respeito.setBounds(0,80,200,20);
                respeito.setFont(fonteNotas);
                PainelNotas.add(respeito);
                
                add(PainelNotas);
                
                //FIM DO PAINEL CONTENDO AS NOTAS
                
                Usuario usuarioU = new Usuario();
                
                avaliar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        /*if (usuarioU.getAtivoOnline().equals(true)) {
                            new PerfilResumo();                            
                        }
                        else{
                            new LoginCadastro();
                        }*/
                        new PerfilResumo();
                        }                
                    
                });
                
                setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

            }
        }
          
 }
