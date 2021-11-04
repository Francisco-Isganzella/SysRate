/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sysrate.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public JFrame janela;
    
    public InicioPaineis() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                //janela = new SysRate().janela();
                JFrame frame = new JFrame("Testing");
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
            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttons.add(btnAdd);
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
            painelProf.add(new JPanel(), gbc);

            add(new JScrollPane(painelProf));

            btnAdd.addActionListener(new ActionListener() { //no caso iria ser o botão de adição de Prof no cadastro
                @Override
                public void actionPerformed(ActionEvent e) {
                    CriacaoPainel pane = new CriacaoPainel();
                    int insertAt = Math.max(0, painelProf.getComponentCount() - 1);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 0;
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
            setPreferredSize(new Dimension(700, 250));
            setLayout(new GridBagLayout());
            setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

        }
    }
    }
