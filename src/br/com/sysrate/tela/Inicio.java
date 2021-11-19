package br.com.sysrate.tela;

import br.com.sysrate.dao.NotasDao;
import br.com.sysrate.dao.ProfessorDao;
import br.com.sysrate.dao.TurmaDao;
import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Notas;
import br.com.sysrate.entidade.Professor;
import br.com.sysrate.entidade.Turma;
import br.com.sysrate.entidade.Usuario;
import static br.com.sysrate.tela.InicioPaineis.setProfID;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Thiago
 */
public class Inicio {
    private static int profID = 0;
    private JFrame janela;
    private JPanel painelJanela;   
    private JPanel painelScroll;
    int i = 0;
    private JScrollPane scrollInicio;
    
    public Inicio() throws SQLException {
        
        janela = new SysRate().janela();
        janela.setVisible(true);
        janela.revalidate();
        janela.repaint();
        
        
        painelJanela = new JPanel();
        painelJanela.setLayout(new BorderLayout());
        painelJanela.setBounds(0, 30, 985, 580);
        painelJanela.setBackground(Color.orange);
        janela.getContentPane().add(painelJanela);
                
        painelScroll = new JPanel();
        painelScroll.setPreferredSize(new Dimension(900,i));
        painelScroll.setBackground(Color.orange);
        painelScroll.setVisible(true);
        
        scrollInicio = new JScrollPane(painelScroll);
        scrollInicio.setVisible(true);
        scrollInicio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        painelJanela.add(scrollInicio);
        //painelJanela.setVisible(true);
        
        //de dentro pra fora:
        //painelScroll -> scrollInicio -> painelJanela -> janela
        //adicionar os paineis os profs no painelScroll
        
        List<Turma> listaTurma = new ArrayList();        
        TurmaDao tDao = new TurmaDao();{
            listaTurma = tDao.listarTurmaInner();
            for (Turma t : listaTurma) {
                if (t.getVisivelTurma() == true && t.getVisivelProfessor()==true) {
                    System.out.println(t.getNomeProfessor());
                    
                    NotasDao ndao = new NotasDao();
                    Notas n = new Notas();
                    
                    painelScroll.add(painelProfessor(t.getNomeProfessor(), t.getCurso(), t.getDisciplina(),
                            ndao.buscarMediaDidatica(t.getProfessorID()),
                            ndao.buscarMediaQualidadeMaterial(t.getProfessorID()), 
                            ndao.buscarMediaQualidadeCorrecao(t.getProfessorID()), 
                            ndao.buscarMediaReceptividade(t.getProfessorID()), 
                            ndao.buscarMediaRespeito(t.getProfessorID())));
                    
                    i += 125;
                    painelScroll.setPreferredSize(new Dimension(900,i));
                }
            }
        }
    }
    public JPanel painelProfessor (String nomeProf, String cursoProf, String disciplina, String notaDidatica,
        String notaQualidadeMaterial, String notaQualidadeCorrecao,
        String notaReceptividade, String notaRespeito) {//mudar String disciplina para List<> caso o prof ter mais de uma disciplina
        
        JPanel painelProfessor = new JPanel();
        painelProfessor.setLayout(null);
        painelProfessor.setPreferredSize(new Dimension(900,120));
        painelProfessor.setBackground(Color.GRAY);

        //IMAGEM PROF
        //ImageIcon imagemProf = new ImageIcon(getClass().getResource("icons_prof.png"));
        ImageIcon imagemProf = new ImageIcon(getClass().getResource("profSidney80x80.png"));
        JLabel imagemP = new JLabel(imagemProf);
        imagemP.setBounds(20, 3, 100, 100);
        imagemP.setBackground(Color.BLACK);

        //FONTES
        Font fonteNomeProf = new Font("", Font.BOLD, 18);
        Font fonteNomeCurso = new Font("",Font.PLAIN, 12);
        Font fonteDisciplinas = new Font("", Font.PLAIN,12);
        Font fonteNotas = new Font("",Font.BOLD,12);

        //NOME PROF
        JLabel labelNomeProf = new JLabel(nomeProf);
        labelNomeProf.setBounds(140, 20, 700, 25);
        labelNomeProf.setFont(fonteNomeProf);

        //NOME CURSO
        JLabel curso = new JLabel("CURSO: ");
        curso.setBounds(140, 50, 180, 20);
        curso.setFont(fonteDisciplinas);
        JLabel nomeCursoProf = new JLabel(cursoProf);
        nomeCursoProf.setBounds(220, 50, 300, 20);
        nomeCursoProf.setFont(fonteNomeCurso);

        //NOME DISCIPLINAS
        JLabel disciplinas = new JLabel("DISCIPLINA:");
        disciplinas.setBounds(140, 80,180,20);
        disciplinas.setFont(fonteDisciplinas);
        JLabel NomeDisciplinas = new JLabel(disciplina);
        NomeDisciplinas.setBounds(220, 80, 300, 20);
        NomeDisciplinas.setFont(fonteDisciplinas);                 
        
        //AVALIAR
        JButton avaliar = new JButton("Avaliar");
        avaliar.setBounds(32, 97, 80, 18);
        avaliar.setBackground(Color.DARK_GRAY);
        avaliar.setForeground(Color.white);
        
        //PAINEL CONTENDO AS NOTAS
        JPanel PainelNotas = new JPanel();
        PainelNotas.setLayout(null);
        PainelNotas.setBounds(630, 10, 300, 100);
        PainelNotas.setBackground(Color.GRAY);
//      PainelNotas.setForeground(Color.black);
        //NOTA DIDÁTICA
        JLabel didatica = new JLabel("Didática");
        didatica.setBounds(10,0,200,20);
        didatica.setFont(fonteNotas);
        PainelNotas.add(didatica);
        JLabel didaticaNota = new JLabel(notaDidatica);
        didaticaNota.setBounds(200, 0, 50, 20);
        PainelNotas.add(didaticaNota);
        //NOTA QUALIDADE DO MATERIAL
        JLabel qualidadeMaterial = new JLabel("Qualidade do Material");
        qualidadeMaterial.setBounds(10,20,200,20);
        qualidadeMaterial.setFont(fonteNotas);
        PainelNotas.add(qualidadeMaterial);
        JLabel qualidadeMaterialNota = new JLabel(notaQualidadeMaterial);
        qualidadeMaterialNota.setBounds(200, 20, 50, 20);
        PainelNotas.add(qualidadeMaterialNota);
        //NOTA QUALIDADE DA CORREÇÃO
        JLabel qualidadeCorrecao = new JLabel("Qualidade da Correção");
        qualidadeCorrecao.setBounds(10,40,200,20);
        qualidadeCorrecao.setFont(fonteNotas);
        PainelNotas.add(qualidadeCorrecao);
        JLabel qualidadeCorrecaoNota = new JLabel(notaQualidadeCorrecao);
        qualidadeCorrecaoNota.setBounds(200, 40, 50, 20);
        PainelNotas.add(qualidadeCorrecaoNota);
        //NOTA RECEPTIVIDADE
        JLabel receptividade = new JLabel("Receptividade");
        receptividade.setBounds(10,60,200,20);
        receptividade.setFont(fonteNotas);
        PainelNotas.add(receptividade);
        JLabel receptividadeNota = new JLabel(notaReceptividade);
        receptividadeNota.setBounds(200, 60, 50, 20);
        PainelNotas.add(receptividadeNota);
        //NOTA RESPEITO
        JLabel respeito = new JLabel("Respeito");
        respeito.setBounds(10,80,200,20);
        respeito.setFont(fonteNotas);
        PainelNotas.add(respeito);
        JLabel respeitoNota = new JLabel(notaRespeito);
        respeitoNota.setBounds(200, 80, 50, 20);
        PainelNotas.add(respeitoNota);
        
        painelProfessor.add(imagemP);
        painelProfessor.add(labelNomeProf);
        painelProfessor.add(curso);
        painelProfessor.add(nomeCursoProf);
        painelProfessor.add(disciplinas);
        painelProfessor.add(NomeDisciplinas);
        painelProfessor.add(avaliar);
        painelProfessor.add(PainelNotas);
        PainelNotas.add(didatica);
        PainelNotas.add(didaticaNota);
        PainelNotas.add(qualidadeMaterial);
        PainelNotas.add(qualidadeMaterialNota);
        PainelNotas.add(qualidadeCorrecao);
        PainelNotas.add(qualidadeCorrecaoNota);
        PainelNotas.add(receptividade);
        PainelNotas.add(receptividadeNota);
        PainelNotas.add(respeito);
        PainelNotas.add(respeitoNota);
        
        avaliar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(labelNomeProf.getText());
            try {
                ProfessorDao pdao = new ProfessorDao();
                //Professor p = new Professor();
                System.out.println(String.valueOf(pdao.buscarNomeProfessorRetornaID(labelNomeProf.getText())));
                setProfID(pdao.buscarNomeProfessorRetornaID(labelNomeProf.getText()));
                Usuario usu = new Usuario();
                UsuarioDao usuDao = new UsuarioDao();
                usu = usuDao.pesquisarPorMatricula(Validacao.getValidaMatr());                      
                if (usu != null && Validacao.getValidaPermissao() == true){
                    JOptionPane.showMessageDialog(null, "Administradores não podem fazer avaliações!","Atenção",2);
                    new Inicio();
                    }
                else if (Validacao.getValidaOnline() == true && Validacao.getValidaPermissao() == false) {
                    new PerfilResumo();                        
                }
                else if (Validacao.getValidaOnline() == false && Validacao.getValidaPermissao() == false){
                    new LoginCadastro();                         
                }                            
            } catch (SQLException ex) {
                System.err.println("Erro botão avaliar: " + ex.getMessage());
            }                        
            janela.setVisible(false);
//          new PerfilResumo();
            }
        });
                
                 
        
        return painelProfessor;
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
    
    
}
        