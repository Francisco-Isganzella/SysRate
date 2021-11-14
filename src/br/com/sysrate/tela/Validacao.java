/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import br.com.sysrate.dao.UsuarioDao;
import br.com.sysrate.entidade.Usuario;
import java.sql.SQLException;

/**
 *
 * @author franc
 */
public class Validacao {
    
    
    public static Boolean validaVazio(String a){
        
        if (a.equals("") || a.equals(null)) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public static Boolean validaMatricula(String matricula)throws SQLException{
        UsuarioDao uDao = new UsuarioDao();
        Usuario u = new Usuario();
        u = uDao.pesquisarPorMatricula(matricula);
        if (u == null) {
            // matricula não existe, usuario pode cadastrar
            return false;
        } else {
            // matricula existe, usuario deve cadastrar outra matricula
            return true;
        }
    }
}
