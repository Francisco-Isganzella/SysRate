package br.com.sysrate.tela;

import br.com.sysrate.dao.TurmaDao;
import br.com.sysrate.entidade.Turma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Thiago
 */
public class Inicio {
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
        painelJanela.setBounds(20, 50, 950, 540);
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
                if (t.getVisivelTurma() == true) {
                    System.out.println(t.getNomeProfessor());
                    
                    
                    painelScroll.add(painelProfessor(t.getNomeProfessor(), t.getCurso(), t.getDisciplina()));
                    
                    i += 113;
                    painelScroll.setPreferredSize(new Dimension(900,i));
                }
            }
        }
    }
    public JPanel painelProfessor (String nomeProf, String cursoProf, String disciplina) {//mudar String disciplina para List<>
        JPanel painelProfessor = new JPanel();
        painelProfessor.setLayout(null);
        painelProfessor.setPreferredSize(new Dimension(900,100));
        painelProfessor.setBackground(Color.GRAY);

        //IMAGEM PROF
        ImageIcon imagemProf = new ImageIcon(getClass().getResource("icons_prof.png"));
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
        labelNomeProf.setBounds(140, 20, 700, 20);
        labelNomeProf.setFont(fonteNomeProf);

        //NOME CURSO
        JLabel labelCursoProf = new JLabel(cursoProf);
        labelCursoProf.setBounds(140, 50, 400, 20);
        labelCursoProf.setFont(fonteNomeCurso);
        //cursoProf.setText(t.getCurso());

        //NOME DISCIPLINAS
        JLabel disciplinas = new JLabel("DISCIPLINAS");
        disciplinas.setBounds(140, 80,180,20);
        disciplinas.setFont(fonteDisciplinas);
        JLabel NomeDisciplinas = new JLabel(disciplina);
        NomeDisciplinas.setBounds(220, 80, 300, 20);
        NomeDisciplinas.setFont(fonteDisciplinas);                 
        //NomeDisciplinas.setText(t.getDisciplina());

        painelProfessor.add(imagemP);
        painelProfessor.add(labelNomeProf);
        painelProfessor.add(labelCursoProf);
        painelProfessor.add(disciplinas);
        painelProfessor.add(NomeDisciplinas);

        return painelProfessor;
    }  
    
    
}
        