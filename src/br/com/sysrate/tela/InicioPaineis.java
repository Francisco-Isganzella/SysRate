package br.com.sysrate.tela;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Thiago
 */
public class InicioPaineis {
    
    //public JFrame janela;
    
    public InicioPaineis() throws SQLException{
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                //janela = new SysRate().janela();
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

        public PainelPrincipal() {
            JButton btnAdd = new JButton("+");
            setLayout(new BorderLayout());
            JPanel buttons = new JPanel(new FlowLayout());
            buttons.add(btnAdd, FlowLayout.LEFT);
            add(buttons, BorderLayout.WEST);
            
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
            //painelProf.add(new JPanel(), gbc);

            add(new JScrollPane(painelProf));

            btnAdd.addActionListener(new ActionListener() { //no caso iria ser o botão de adição de Prof no cadastro
                @Override
                public void actionPerformed(ActionEvent e) {
                    CriacaoPainel pane = new CriacaoPainel();
                    int insertAt = Math.max(0, painelProf.getComponentCount() - 1);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    //gbc.fill = GridBagConstraints.HORIZONTAL;
                    //gbc.weightx = 0;
                    //gbc.weighty = 0;    
                    painelProf.add(pane, gbc, insertAt);
                    painelProf.revalidate();
                    painelProf.repaint();
                }
            });

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1000, 650);
        } 
    }
        public static class CriacaoPainel extends JPanel {

            public CriacaoPainel() {
                setPreferredSize(new Dimension(900, 120));
                setLayout(null);
                
                ImageIcon imagemProf = new ImageIcon(getClass().getResource("icons_prof.png"));
                JLabel imagemP = new JLabel(imagemProf);
                imagemP.setBounds(20, 20, 100, 100);
                imagemP.setBackground(Color.BLACK);
                add(imagemP);
                
                Font fonteNomeProf = new Font("", Font.BOLD, 18);
                Font fonteNomeCurso = new Font("",Font.PLAIN, 12);
                Font fonteDisciplinas = new Font("", Font.PLAIN,12);
                
                JLabel nomeProf = new JLabel("NOME PROFESSOR");
                nomeProf.setBounds(140, 20, 700, 20);
                nomeProf.setFont(fonteNomeProf);
                add(nomeProf);
                //nomeProf.setText("substituir com nome dos professores do banco, fazer isto na ação do botão");
                
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
                
                JPanel avaliacao = new JPanel();
                avaliacao.setBounds(720, 20, 100, 20);
                avaliacao.setBackground(Color.BLACK);
                add(avaliacao);
                
                /*JPanel avaliacao = new JPanel();
                avaliacao.setBounds(720, 60, 100, 20);
                avaliacao.setBackground(Color.black);
                add(avaliacao);*/
                
                JButton avaliar = new JButton("Avaliar");
                avaliar.setBounds(720, 80, 100, 20);
                avaliar.setBackground(Color.ORANGE);
                add(avaliar);
                
                JFrame janelaAvaliacao = new JFrame();
                
                avaliar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        try {
                            new LoginCadastro();
                            
                            //add(janelaAvaliacao);
                            //return janelaAvaliacao;
                        } catch (SQLException ex) {
                            System.err.println("Erro botao avaliar: "+ex.getMessage());;
                        }
                        
                        }
                                            
                    
                });
//                setLayout(new GridBagLayout());                                          
//                GridBagConstraints gbc = new GridBagConstraints();
//                gbc.ipady = 0;       //reset to default
//                gbc.weighty = 1.0;   //request any extra vertical space
//                gbc.anchor = GridBagConstraints.LINE_START; //bottom of space
//                gbc.insets = new Insets(0, 5, 0, 0);  //top padding
//                gbc.gridx = 0;       
//                gbc.gridwidth = 0;   
//                gbc.gridy = 1;       
//                ImageIcon imagemProf = new ImageIcon(getClass().getResource("Unknown_person.png"));
//                JLabel image = new JLabel(imagemProf);
//                add(image, gbc);
//                JLabel nomeProf = new JLabel("Juvenildo Silva");
//                add(image, gbc);

// não deu certo ainda!!!!!!!!!!! !IJ JR!()!J ()F)ISAJF)IAJ
                
                setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

            }
        }
        
        
 }
