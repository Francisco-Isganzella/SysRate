/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.tela;

import javax.swing.JRadioButton;

/**
 *
 * @author migue
 */
public class NotaRecebida {
        public static int notaRecebida (JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, JRadioButton e){
        int i = 0;
        if (a.isSelected()) {
            i=1;
        } else if(b.isSelected()){
            i=2;
        } else if(c.isSelected()){
            i=3;
        } else if(d.isSelected()){
            i=4;
        } else if(e.isSelected()){
            i=5;
        }
        return i;
    }
}
