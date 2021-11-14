/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

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
}
