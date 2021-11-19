package br.com.sysrate.tela;

import br.com.sysrate.dao.TurmaDao;
import br.com.sysrate.entidade.Turma;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    private JScrollPane scrollInicio;
    
    private JFrame janela;
    
    public Inicio() throws SQLException {
        
       janela = new SysRate().janela();
        
        janela.setVisible(true);
        
        int i =0;
        
        JPanel painelProfessor = new JPanel(new GridBagLayout());
        painelProfessor.setLayout(null);
        painelProfessor.setBounds(0,i, 700, 100);
        painelProfessor.setVisible(true);
        painelProfessor.setBackground(Color.red);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0;
        
        scrollInicio = new JScrollPane();
        scrollInicio.setBounds(80, 80, 840, 520);
        scrollInicio.setForeground(Color.ORANGE);
        scrollInicio.setMinimumSize(new Dimension(840,520));
        scrollInicio.setMaximumSize(new Dimension(840,520));
        scrollInicio.setPreferredSize(new Dimension(840,520));
        scrollInicio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollInicio.setAutoscrolls(true);
        scrollInicio.setVisible(true);
        
        
        
        
        
        
        
        List<Turma> listaTurma = new ArrayList();
        TurmaDao tDao = new TurmaDao();
        
        listaTurma = tDao.listarTurmaInner();
        
        for (Turma t : listaTurma) {
            
            if (t.getVisivelTurma() == true) {
                
                int insertAt = Math.max(0, painelProfessor.getComponentCount() - 1);                                
                scrollInicio.add(painelProfessor, gbc, insertAt);
                scrollInicio.revalidate();
                scrollInicio.repaint();
                scrollInicio.getViewportBorderBounds();
        
            i += 100;
            
            
            }
        }
        
        janela.getContentPane().add(scrollInicio);
    }
        
        
        
        
        
        
        

        
    
    
    public JLabel label(String nome, int x, int y, int l, int a, int f, Color fontColor){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, l, a);
        label.setFont(new Font("null", Font.PLAIN, f));
        label.setForeground(fontColor);
        
        
        return label;
    }
        
}
