/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

/**
 *
 * @author Tiago
 */
public enum REGEX {
    
    COMECACOM(0), CONTEM(1), IGUAL(2), DIFERENTE(3), TERMINACOM(4), NAOCONTEM(5);
    
    private int value;

    private REGEX(int value) {
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
