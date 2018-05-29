/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiago
 */
public class MessageFactory {

    public static int FECHAR_FRAME = 0, FECHAR_SISTEMA = 1, SALVAR = 2;

    public static boolean getMsgApp(int msg, Component parent) {
        switch (msg) {
            case 0:
                return (JOptionPane.showConfirmDialog(parent, "Deseja realmente fechar o formulário?", "Fechar Formulário", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) == 0;
            case 1:
                return (JOptionPane.showConfirmDialog(parent, "Deseja realmente encerrar o sistema?", "Encerrar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) == 0;
            case 2:
                return (JOptionPane.showConfirmDialog(parent, "Deseja realmente salvar o registro?", "Salvar registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) == 0;
        }
        return false;
    }

    public static void getPersistenceMsg(int msg, boolean sucess, Component parent){
        switch(msg){
            case 2:
                if(sucess){
                    JOptionPane.showMessageDialog(parent, "Registro salvo com sucesso!", "Registro salvo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parent, "Erro ao tentar salvar o registro", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
    
}
