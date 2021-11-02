/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;


import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Usuario;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        JTextField fieldMatriculaCadastro;
        JPasswordField fieldSenhaCadastro;
        JPasswordField fieldConfirmacaoSenhaCadastro;
        JButton buttonCadastrarse;
        JButton buttonCancelarCadastro;
        
        JTextField fieldMatriculaLogin;
        JPasswordField fieldSenhaLogin;
        JButton buttonEntrar;
        JButton buttonCancelarLogin;
        
        JLabel labelErroCadastro = new JLabel();
        labelErroCadastro.setBounds(125, 250, 260, 20);
        labelErroCadastro.setForeground(Color.RED);
        
        
        JLabel labelErroLogin = new JLabel();
        labelErroLogin.setBounds(160, 420, 260, 20);
        labelErroLogin.setForeground(Color.RED);
        
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        panelCadastro = new JPanel();
        panelCadastro.setLayout(null);
        panelCadastro = painel(20, 180, 460, 280, "");
        panelCadastro.setBackground(Color.LIGHT_GRAY);
        panelCadastro.add(label("Primeiro Acesso?", 150, 20, 160, 20, 20, Color.BLACK));
        panelCadastro.add(label("Matrícula", 70, 70, 100, 20, 16, Color.BLACK));
        panelCadastro.add(fieldMatriculaCadastro = new Field(190, 70, 200, 20));
        panelCadastro.add(label("Senha", 80, 120, 120, 20, 16, Color.BLACK));
        panelCadastro.add(fieldSenhaCadastro = new PasswordField(190, 120, 200, 20));
        panelCadastro.add(label("Confirmação Senha", 40, 170, 140, 20, 16, Color.BLACK));
        panelCadastro.add(fieldConfirmacaoSenhaCadastro = new PasswordField(190, 170, 200, 20));
        panelCadastro.add(buttonCadastrarse = new Button(110, 215, 120, 30, "Cadastrar-se", Color.DARK_GRAY, Color.WHITE));
        panelCadastro.add(buttonCancelarCadastro = new Button(260, 215, 90, 30, "Cancelar", Color.GRAY, Color.WHITE));
        panelCadastro.add(labelErroCadastro);
       
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
        panelLogin.add(buttonCancelarLogin = new Button(270, 380, 90, 30, "Cancelar", Color.GRAY, Color.WHITE));
        panelLogin.add(labelErroLogin);
        
        Usuario usuario = new Usuario();
        
        UsuarioDao uDao = new UsuarioDao();
        
        buttonCadastrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                     if (fieldMatriculaCadastro.getText().equals("") || fieldMatriculaCadastro.getText().equals(null)){
                        System.err.println("ERRO CADASTRO: Campo Matrícula não pode ser vazio");
                        labelErroCadastro.setText("Campo Matrícula não pode ser vazio");
                         
                    }else if (fieldSenhaCadastro.getText().equals("") || fieldSenhaCadastro.getText().equals(null)){
                        System.err.println("ERRO CADASTRO: Campo Senha não pode ser vazio");
                        labelErroCadastro.setText("Campo Senha não pode ser vazio");
                        
                    }else if (fieldConfirmacaoSenhaCadastro.getText().equals("") || fieldConfirmacaoSenhaCadastro.getText().equals(null)){
                        System.err.println("ERRO CADASTRO: CCampo Confirmação Senha não pode ser vazio");
                        labelErroCadastro.setText("Campo Confirmação Senha não pode ser vazio");
                        
                    }else if (!(fieldSenhaCadastro.getText().equals(fieldConfirmacaoSenhaCadastro.getText()))) {
                        System.err.println("ERRO CADASTRO: Confirmação Senha deve ser igual a Senha");
                        labelErroCadastro.setText("Confirmação Senha deve ser igual a Senha");
                                            
                    } else {
                        usuario.setMatricula(fieldMatriculaCadastro.getText());
                    usuario.setSenha(fieldSenhaCadastro.getText());
                    usuario.setPermissao(Boolean.FALSE);
                    usuario.setAtivoOnline(Boolean.FALSE);
                          uDao.salvar(usuario);  
                          new Cadastro();
                    }
                } catch (Exception w) {
                    System.err.println("Erro botao Cadastrarse: "+w.getMessage());
                }
            }
        });
        
        buttonCancelarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fieldMatriculaCadastro.setText("");
                    fieldSenhaCadastro.setText("");
                    fieldConfirmacaoSenhaCadastro.setText("");
                    labelErroCadastro.setText("");
                    
                } catch (Exception x) {
                    System.err.println("Erro botao Cancelar Cadastro: "+x.getMessage());
                }
            }
        });
        
        buttonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    if (fieldMatriculaLogin.getText().equals("") || fieldMatriculaLogin.getText().equals(null)) {
                        System.err.println("ERRO LOGIN: Campo Matrícula não pode ser vazio ");
                        labelErroLogin.setText("Campo Matrícula não pode ser vazio");
                        
                    }else if (fieldSenhaLogin.getText().equals("") || fieldSenhaLogin.getText().equals(null)) {
                        System.err.println("ERRO CADASTRO: Campo Senha não pode ser vazio");
                        labelErroLogin.setText("Campo Senha não pode ser vazio");
                        
                    } else {
                        usuario.setMatricula(fieldMatriculaLogin.getText());
                    usuario.setSenha(fieldSenhaLogin.getText());
                    usuario.setPermissao(Boolean.FALSE);
                    usuario.setAtivoOnline(Boolean.FALSE);
                          uDao.salvar(usuario);
                          new Cadastro();
                    }
                } catch (Exception y) {
                    System.err.println("Erro botao Entrar"+y.getMessage());
                }
            }
        });
        
        buttonCancelarLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                   fieldMatriculaLogin.setText("");
                   fieldSenhaLogin.setText("");
                   labelErroLogin.setText("");
                   
                } catch (Exception z) {
                    System.err.println("Erro botao Cancelar Login"+z.getMessage());
                }
            }
        });
        
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
