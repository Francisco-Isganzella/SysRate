/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import br.com.sysrate.dao.CursoDao;
import br.com.sysrate.dao.DisciplinaDao;
import br.com.sysrate.entidade.Curso;
import br.com.sysrate.entidade.Disciplina;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author franc
 */
public class Combo{
    
    public JComboBox ComboCurso (int x, int y, int l, int a) throws SQLException{
        List<Curso> lista_cursos = new ArrayList();
        CursoDao cDaoL = new CursoDao();
        lista_cursos = cDaoL.listarCurso();
        JComboBox combo = new JComboBox();
        for (Curso c : lista_cursos) {
            if (c.getVisivelCurso()== true) {
                combo.addItem(c.getCurso());
            }
        }       
        combo.setBounds(x, y, l, a);
        return combo;
    }
    
    public JComboBox ComboDisciplina(int x, int y, int l, int a) throws SQLException{
        List<Disciplina> lista_disciplinas = new ArrayList();
        DisciplinaDao dDaoL = new DisciplinaDao();
        lista_disciplinas = dDaoL.listarDisciplina();
        JComboBox combo = new JComboBox();
        for (Disciplina d : lista_disciplinas) {
            if (d.getVisivelDisciplina()== true) {
            combo.addItem(d.getDisciplina());
            }
        }
        combo.setBounds(x, y, l, a);
        return combo;
    }
    
}
