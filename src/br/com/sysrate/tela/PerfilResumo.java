/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import br.com.sysrate.dao.CursoDao;
import br.com.sysrate.dao.DisciplinaDao;
import br.com.sysrate.dao.NotasDao;
import br.com.sysrate.dao.ProfessorDao;
import br.com.sysrate.entidade.Curso;
import br.com.sysrate.entidade.Disciplina;
import br.com.sysrate.entidade.Notas;
import br.com.sysrate.entidade.Professor;
import br.com.sysrate.entidade.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author migue
 */
public class PerfilResumo {
    
    private JFrame janela;
    
    private JPanel panelUP;
    
    private JPanel panelDOWN;
    
    public PerfilResumo (){
    
        janela = new SysRate().janela();
        janela.setVisible(true);
        
        ImageIcon imagemFotoPerfil = new ImageIcon(getClass().getResource("icons_prof.png"));
        
        JButton buttonSalvar;
        JButton buttonCancelar;
        
        JRadioButton radioDidatica1;
        JRadioButton radioDidatica2;
        JRadioButton radioDidatica3;
        JRadioButton radioDidatica4;
        JRadioButton radioDidatica5;
        
        JRadioButton radioQualidadeMaterial1;
        JRadioButton radioQualidadeMaterial2;
        JRadioButton radioQualidadeMaterial3;
        JRadioButton radioQualidadeMaterial4;
        JRadioButton radioQualidadeMaterial5;
        
        JRadioButton radioQualidadeCorrecao1;
        JRadioButton radioQualidadeCorrecao2;
        JRadioButton radioQualidadeCorrecao3;
        JRadioButton radioQualidadeCorrecao4;
        JRadioButton radioQualidadeCorrecao5;
        
        JRadioButton radioReceptividade1;
        JRadioButton radioReceptividade2;
        JRadioButton radioReceptividade3;
        JRadioButton radioReceptividade4;
        JRadioButton radioReceptividade5;

        JRadioButton radioRespeito1;
        JRadioButton radioRespeito2;
        JRadioButton radioRespeito3;
        JRadioButton radioRespeito4;
        JRadioButton radioRespeito5;
        
        
        
        JLabel labelFotoPerfil = new JLabel(imagemFotoPerfil);
        labelFotoPerfil.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
        labelFotoPerfil.setBackground(Color.BLACK);
        labelFotoPerfil.setBounds(70, 35, 160, 160);
        
        JLabel labelNomeProfessor = new JLabel("Professor: + Método Atributo Nome, Tabela Professor");
        labelNomeProfessor.setBounds(300, 45, 400, 40);
        labelNomeProfessor.setForeground(Color.BLACK);
        labelNomeProfessor.setFont(new Font(null, Font.BOLD, 30) {
        });
        
        JLabel labelNomeDisciplina = new JLabel("Disciplina: + Método Atributo Nome, Tabela Disciplina");
        labelNomeDisciplina.setBounds(300, 120, 400, 30);
        labelNomeDisciplina.setForeground(Color.BLACK);
        labelNomeDisciplina.setFont(new Font(null, Font.PLAIN, 20) {
        });
        
        JLabel labelNomeCurso = new JLabel("Curso: + Método Atributo Nome, Tabela Curso");
        labelNomeCurso.setBounds(300, 160, 400, 30);
        labelNomeCurso.setForeground(Color.BLACK);
        labelNomeCurso.setFont(new Font(null, Font.PLAIN, 20) {
        });
        
        
        
        
        panelUP = new JPanel();
        panelUP.setLayout(null);
        panelUP = painel(0, 30, 1000, 230, "");
        panelUP.setBackground(Color.WHITE);
        panelUP.add(labelNomeProfessor);
        panelUP.add(labelFotoPerfil);
        panelUP.add(labelNomeDisciplina);
        panelUP.add(labelNomeCurso);      
        
        panelDOWN = new JPanel();
        panelDOWN.setLayout(null);
        panelDOWN = painel(0, 275, 1000, 375, "");
        panelDOWN.setBackground(Color.GRAY);
        panelDOWN.add(labelTitulo("DIDÁTICA", 40, 50, 200, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("QUALIDADE DO MATERIAL", 40, 100, 300, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("QUALIDADE CORREÇÃO", 40, 150, 300, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("RECEPTIVIDADE", 40, 200, 300, 30, 20, Color.BLACK));
        panelDOWN.add(labelTitulo("RESPEITO", 40, 250, 300, 30, 20, Color.BLACK));
        panelDOWN.add(label("MUITO RUIM", 335, 10, 120, 20, 20, Color.LIGHT_GRAY));
        panelDOWN.add(label("RUIM", 495, 10, 120, 20, 20, Color.LIGHT_GRAY));
        panelDOWN.add(label("MÉDIO", 610, 10, 120, 20, 20, Color.LIGHT_GRAY));
        panelDOWN.add(label("BOM", 740, 10, 120, 20, 20, Color.LIGHT_GRAY));
        panelDOWN.add(label("MUITO BOM", 835, 10, 120, 20, 20, Color.LIGHT_GRAY));
        
        panelDOWN.add(radioDidatica1 = new Radio(390, 55));
        panelDOWN.add(radioDidatica2 = new Radio(510, 55));
        panelDOWN.add(radioDidatica3 = new Radio(630, 55));
        panelDOWN.add(radioDidatica4 = new Radio(750, 55));
        panelDOWN.add(radioDidatica5 = new Radio(870, 55));
        
        panelDOWN.add(radioQualidadeMaterial1 = new Radio(390, 105));
        panelDOWN.add(radioQualidadeMaterial2 = new Radio(510, 105));
        panelDOWN.add(radioQualidadeMaterial3 = new Radio(630, 105));
        panelDOWN.add(radioQualidadeMaterial4 = new Radio(750, 105));
        panelDOWN.add(radioQualidadeMaterial5 = new Radio(870, 105));
        
        panelDOWN.add(radioQualidadeCorrecao1 = new Radio(390, 155));
        panelDOWN.add(radioQualidadeCorrecao2 = new Radio(510, 155));
        panelDOWN.add(radioQualidadeCorrecao3 = new Radio(630, 155));
        panelDOWN.add(radioQualidadeCorrecao4 = new Radio(750, 155));
        panelDOWN.add(radioQualidadeCorrecao5 = new Radio(870, 155));
        
        panelDOWN.add(radioReceptividade1 = new Radio(390, 205));
        panelDOWN.add(radioReceptividade2 = new Radio(510, 205));
        panelDOWN.add(radioReceptividade3 = new Radio(630, 205));
        panelDOWN.add(radioReceptividade4 = new Radio(750, 205));
        panelDOWN.add(radioReceptividade5 = new Radio(870, 205));
        
        panelDOWN.add(radioRespeito1 = new Radio(390, 255));
        panelDOWN.add(radioRespeito2 = new Radio(510, 255));
        panelDOWN.add(radioRespeito3 = new Radio(630, 255));
        panelDOWN.add(radioRespeito4 = new Radio(750, 255));
        panelDOWN.add(radioRespeito5 = new Radio(870, 255));
        
        panelDOWN.add(buttonSalvar = new Button(630, 295, 100, 25, "Salvar", Color.DARK_GRAY, Color.WHITE));
        panelDOWN.add(buttonCancelar = new Button(750, 295, 100, 25, "Cancelar", Color.LIGHT_GRAY, Color.WHITE));
        
        ButtonGroup didatica = new ButtonGroup();
        didatica.add(radioDidatica1);
        didatica.add(radioDidatica2);
        didatica.add(radioDidatica3);
        didatica.add(radioDidatica4);
        didatica.add(radioDidatica5);
        
        ButtonGroup qualidadeMaterial = new ButtonGroup();
        qualidadeMaterial.add(radioQualidadeMaterial1);
        qualidadeMaterial.add(radioQualidadeMaterial2);
        qualidadeMaterial.add(radioQualidadeMaterial3);
        qualidadeMaterial.add(radioQualidadeMaterial4);
        qualidadeMaterial.add(radioQualidadeMaterial5);
        
        ButtonGroup qualidadeCorrecao = new ButtonGroup();
        qualidadeCorrecao.add(radioQualidadeCorrecao1);
        qualidadeCorrecao.add(radioQualidadeCorrecao2);
        qualidadeCorrecao.add(radioQualidadeCorrecao3);
        qualidadeCorrecao.add(radioQualidadeCorrecao4);
        qualidadeCorrecao.add(radioQualidadeCorrecao5);
        
        ButtonGroup receptividade = new ButtonGroup();
        receptividade.add(radioReceptividade1);
        receptividade.add(radioReceptividade2);
        receptividade.add(radioReceptividade3);
        receptividade.add(radioReceptividade4);
        receptividade.add(radioReceptividade5);
        
        ButtonGroup respeito = new ButtonGroup();
        respeito.add(radioRespeito1);
        respeito.add(radioRespeito2);
        respeito.add(radioRespeito3);
        respeito.add(radioRespeito4);
        respeito.add(radioRespeito5);
        
        
        Professor p = new Professor();
        ProfessorDao pDao = new ProfessorDao();
        
        Disciplina d = new Disciplina();
        DisciplinaDao dDao = new DisciplinaDao();
        
        Curso c = new Curso();
        CursoDao cDao = new CursoDao();
        
        
        try {
            c = cDao.pesquisarPorId(2);
            p = pDao.pesquisarPorId(1);
            d = dDao.pesquisarPorId(1);
            
        } catch (SQLException ex) {
            System.err.println("Erro pesquisar por ID: "+ex.getMessage());
        }
        
        labelNomeProfessor.setText(p.getNomeProfessor());
        labelNomeDisciplina.setText(d.getDisciplina());
        labelNomeCurso.setText(c.getCurso());
        
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //fazer validação para cada usuario avaliar somente uma vez cada professor
                
                
                Notas n = new Notas();
                NotasDao nDao = new NotasDao();
                n.setProfessorID(1);
                try {
                    
                if (!(radioDidatica1.isSelected() || radioDidatica2.isSelected() || radioDidatica3.isSelected() || radioDidatica4.isSelected() || radioDidatica5.isSelected())){
                    JOptionPane.showMessageDialog(null, "Você deve atribuir ao menos uma nota a cada tópico!","Erro Avaliação",0);
                    System.err.println("O Usuário deve atribuir ao menos uma nota a cada tópico");
                     
                } else if(!(radioQualidadeMaterial1.isSelected() || radioQualidadeMaterial2.isSelected() || radioQualidadeMaterial3.isSelected() || radioQualidadeMaterial4.isSelected() || radioQualidadeMaterial5.isSelected())){        
                    JOptionPane.showMessageDialog(null, "Você deve atribuir ao menos uma nota a cada tópico!","Erro Avaliação",0);
                    System.err.println("O Usuário deve atribuir ao menos uma nota a cada tópico");
                    
                } else if (!(radioReceptividade1.isSelected() || radioReceptividade2.isSelected() || radioReceptividade3.isSelected() || radioReceptividade4.isSelected() || radioReceptividade5.isSelected())){
                    JOptionPane.showMessageDialog(null, "Você deve atribuir ao menos uma nota a cada tópico!","Erro Avaliação",0);
                    System.err.println("O Usuário deve atribuir ao menos uma nota a cada tópico");
                        
                        
                } else if (!(radioRespeito1.isSelected() || radioRespeito2.isSelected() || radioRespeito3.isSelected() || radioRespeito4.isSelected() || radioRespeito5.isSelected())){        
                    JOptionPane.showMessageDialog(null, "Você deve atribuir ao menos uma nota a cada tópico!","Erro Avaliação",0);
                    System.err.println("O Usuário deve atribuir ao menos uma nota a cada tópico");
                            
                }else {
                    
                    int didatica = NotaRecebida.notaRecebida(radioDidatica1, radioDidatica2, radioDidatica3, radioDidatica4, radioDidatica5);
                    n.setDidatica(didatica);
                    int qualidadeMaterial = NotaRecebida.notaRecebida(radioQualidadeMaterial1, radioQualidadeMaterial2, radioQualidadeMaterial3, radioQualidadeMaterial4, radioQualidadeMaterial5);
                    n.setQualidadeMaterial(qualidadeMaterial);
                
                    int qualidadeCorrecao = NotaRecebida.notaRecebida(radioQualidadeCorrecao1, radioQualidadeCorrecao2, radioQualidadeCorrecao3, radioQualidadeCorrecao4, radioQualidadeCorrecao5);
                    n.setQualidadeCorrecao(qualidadeCorrecao);
                
                    int receptividade = NotaRecebida.notaRecebida(radioReceptividade1, radioReceptividade2, radioReceptividade3, radioReceptividade4, radioReceptividade5);
                    n.setReceptividade(receptividade);
                
                    int respeito = NotaRecebida.notaRecebida(radioRespeito1, radioRespeito2, radioRespeito3, radioRespeito4, radioRespeito5);
                    n.setRespeito(respeito);
                
                
                    nDao.salvar(n);
                    JOptionPane.showMessageDialog(null, "Avaliação realizada com sucesso!!!","Avaliação",1);
                                System.out.println("Avaliação realizada com sucesso!!!");
                    janela.setVisible(false);
                    new InicioPaineis();
                }
                    
                } catch (SQLException x) {
                    System.err.println("Erro ao salvar notas: "+ x.getMessage());
                }
                
            }
        });
        
        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                didatica.clearSelection();
                qualidadeMaterial.clearSelection();
                qualidadeCorrecao.clearSelection();
                receptividade.clearSelection();
                respeito.clearSelection();
            }
        });
        
        janela.getContentPane().add(panelUP);
        janela.getContentPane().add(panelDOWN);
        
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
    
    public JLabel labelTitulo (String nome, int x, int y, int l, int a, int f, Color fontColor){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.BOLD, f));
        label.setForeground(fontColor);
        
        return label;
    }
    
    //public JLabel labelEstrelaCinza 
    
    //public JLabel labelEstrelaAmarela 
    
}
    
