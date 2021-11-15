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
    private static Boolean validaOnline = false;
    private static String validaNome = "";
    private static String validaMatr="";
    private static Integer validaID=0;
    
    
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
    
    /*public static Boolean validaOnline2(String matricula) throws SQLException{
    UsuarioDao uDao = new UsuarioDao();
    Usuario u = new Usuario();
    
    u = uDao.pesquisarPorMatricula(matricula);
    if (u.getAtivoOnline() == true) {
    return true;
    } else{
    return false;
    }
    }*/
    

    /**
     * @return the validaOnline
     */
    public static Boolean getValidaOnline() {
        return validaOnline;
    }

    /**
     * @param aValidaOnline the validaOnline to set
     */
    public static void setValidaOnline(Boolean aValidaOnline) {
        validaOnline = aValidaOnline;
    }

    /**
     * @return the validaNome
     */
    public static String getValidaNome() {
        return validaNome;
    }

    /**
     * @param aValidaNome the validaNome to set
     */
    public static void setValidaNome(String aValidaNome) {
        validaNome = aValidaNome;
    }

    /**
     * @return the validaMatr
     */
    public static String getValidaMatr() {
        return validaMatr;
    }

    /**
     * @param aValidaMatr the validaMatr to set
     */
    public static void setValidaMatr(String aValidaMatr) {
        validaMatr = aValidaMatr;
    }

    /**
     * @return the validaID
     */
    public static Integer getValidaID() {
        return validaID;
    }

    /**
     * @param aValidaID the validaID to set
     */
    public static void setValidaID(Integer aValidaID) {
        validaID = aValidaID;
    }


    
    
}
