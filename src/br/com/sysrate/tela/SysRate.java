/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author franc
 */
public class SysRate {
    
    private JFrame janela;
    public JButton botaoHome = new JButton();
    
    public SysRate(){
   
        janela();
    }
    
    public JFrame janela(){
        janela = new JFrame("SysRate");
        janela.setSize(1000,650);
        janela.getContentPane().setBackground(Color.orange);
        janela.setVisible(false);
        janela.setLocationRelativeTo(null);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        
        
        JPanel cabecalho = new JPanel();
        cabecalho.setLayout(null);
        cabecalho.setBounds(0, 0, 1000, 30);
        cabecalho.setBackground(Color.LIGHT_GRAY);
        
        JLabel nomeUsuario = new JLabel(/*if usuario tiver logado settext(nomeUser) else settext(null)*/"");
        nomeUsuario.setBounds(710, 05, 120, 20);
        
        JButton botaoLoginLogout = new JButton(/*if usuario tiver logado settext("Logout") else settext(Login) */ "Login");
        botaoLoginLogout.setBounds(870, 05, 80, 20);
        botaoLoginLogout.setBackground(Color.DARK_GRAY);
        botaoLoginLogout.setForeground(Color.WHITE);
        botaoLoginLogout.setBorder(null);
        
        ImageIcon imagemBotaoHome = new ImageIcon(getClass().getResource("botaoHome.png"));
        
        botaoHome = new JButton(imagemBotaoHome);
        botaoHome.setBounds(20, 0, 30, 30);
        botaoHome.setBackground(Color.LIGHT_GRAY);
        botaoHome.setBorder(null);
        
       
        if (Validacao.getValidaOnline() == true) {
            botaoLoginLogout.setText("Logout");
            nomeUsuario.setText(Validacao.getValidaNome());
        } else {
            botaoLoginLogout.setText("Login");
        }
        
        
        botaoHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try {
                    janela.setVisible(false);
                    new Inicio();
                    
                } catch (SQLException ex) {
                    System.err.println("erro botaoHome:"+ex.getMessage());
                }
                    
                
            }
        });
        
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
                        
                        Validacao.setValidaPermissao(Boolean.FALSE);
                        
                        new Inicio();
                        janela.setVisible(false);
                        
                    } else {
                        janela.setVisible(false);
                        new LoginCadastro();
                        
                    }
                    
                    
                    
                } catch (SQLException x) {
                    System.err.println("Erro LoginLogout: "+x.getMessage());
                }
            }
        });
        
        cabecalho.add(nomeUsuario);
        cabecalho.add(botaoLoginLogout);
        cabecalho.add(botaoHome);
        
        janela.add(cabecalho);
        
        return janela;
    }
    
    public static void main(String[] args) throws SQLException{
        new Inicio();
    }
    
}
