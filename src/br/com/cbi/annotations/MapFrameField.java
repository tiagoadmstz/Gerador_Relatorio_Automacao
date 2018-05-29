/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para mapeamento de campos de formulários utilizados por classes com atributos simples
 * 
 * @author tiago.teixeira
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapFrameField {
    
    /**
     * O tipo do campo que será utilizado dentro da classe que o possui
     * 
     * @return Classe
     */
    public Class typeParameter(); 
    /**
     * O nome do campo que será utilizado
     * 
     * @return 
     */
    public String referencedField();
    
}
