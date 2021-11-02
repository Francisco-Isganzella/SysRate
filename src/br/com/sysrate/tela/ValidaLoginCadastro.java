/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

/**
 *
 * @author migue
 */
public class ValidaLoginCadastro {
    
    public static boolean setValidaMatricula (String matricula){
        boolean result = false;
        if (matricula!=" ") {
            result = true;
        }
        return result;
    }
    
    public static boolean setValidaSenha (String senha){
        boolean result = false;
        if (senha !=" ") {
            result = true;
        }
        return result;
    }
    
}
