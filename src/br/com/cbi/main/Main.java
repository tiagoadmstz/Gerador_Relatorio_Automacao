/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.main;

import br.com.cbi.frames.Form_Splash;
import br.com.cbi.util.Utilidades;

/**
 *
 * @author tiago.teixeira
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Utilidades.mudaLookAndFeel(Utilidades.WINDOWS);
        Form_Splash splashForm = new Form_Splash();
        splashForm.setVisible(true);
    }
    
}
