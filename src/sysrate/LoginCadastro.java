/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysrate;


import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author migue
 */
public class LoginCadastro {
    
    private JFrame janela;

    
    /**
     *
     */
    
    
    private JPanel panelCadastro;
    private JPanel panelLogin;
    
    public LoginCadastro (){
        
        janela = new SysRate().janela();
        
        JTextField fieldNomeCompletoCadastro;
        JTextField fieldMatriculaCadastro;
        JPasswordField fieldSenhaCadastro;
        JPasswordField fieldConfirmacaoSenhaCadastro;
        JButton buttonCadastrarse;
        
        JTextField fieldMatriculaLogin;
        JPasswordField fieldSenhaLogin;
        JButton buttonEntrar;
        JButton buttonCancelar;
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        panelCadastro = new JPanel();
        panelCadastro.setLayout(null);
        panelCadastro = painel(20, 150, 460, 320, "");
        panelCadastro.setBackground(Color.LIGHT_GRAY);
        panelCadastro.add(label("Primeiro Acesso?", 150, 20, 160, 20, 20, Color.BLACK));
        panelCadastro.add(label("Nome Completo", 50, 80, 120, 20, 16, Color.BLACK));
        panelCadastro.add(fieldNomeCompletoCadastro = new Field(190, 80, 200, 20));
        panelCadastro.add(label("Matrícula", 70, 130, 100, 20, 16, Color.BLACK));
        panelCadastro.add(fieldMatriculaCadastro = new Field(190, 130, 200, 20));
        panelCadastro.add(label("Senha", 80, 180, 120, 20, 16, Color.BLACK));
        panelCadastro.add(fieldSenhaCadastro = new PasswordField(190, 180, 200, 20));
        panelCadastro.add(label("Confirme a Senha", 40, 230, 140, 20, 16, Color.BLACK));
        panelCadastro.add(fieldConfirmacaoSenhaCadastro = new PasswordField(190, 230, 200, 20));
        panelCadastro.add(buttonCadastrarse = new Button(170, 270, 120, 30, "Cadastrar-se", Color.GRAY, Color.WHITE));
       
        panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin = painel(500, 0, 500, 650, "");
        panelLogin.setBackground(Color.DARK_GRAY);
        panelLogin.add(label("Login", 210, 200, 100, 45, 35, Color.WHITE));
        panelLogin.add(label("Matrícula", 120, 280, 100, 20, 16, Color.WHITE));
        panelLogin.add(fieldMatriculaLogin = new Field(210, 280, 160, 20));
        panelLogin.add(label("Senha", 130, 330, 100, 20, 16, Color.WHITE));
        panelLogin.add(fieldSenhaLogin = new PasswordField(210, 330, 160, 20));
        panelLogin.add(buttonEntrar = new Button(160, 380, 80, 30, "Entrar", Color.GRAY, Color.WHITE));
        panelLogin.add(buttonCancelar = new Button(270, 380, 90, 30, "Cancelar", Color.GRAY, Color.WHITE));
        
        janela.getContentPane().add(panelCadastro);
        janela.getContentPane().add(panelLogin);
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
    

}
