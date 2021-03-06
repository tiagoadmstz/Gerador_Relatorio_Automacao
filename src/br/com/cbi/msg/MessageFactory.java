/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.msg;

import java.awt.Component;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago.teixeira
 */
public class MessageFactory implements Serializable {

    private static final long serialVersionUID = -7391602748339106858L;
    public static final int FECHAR = 0, FECHAR_SISTEMA = 1, SALVAR = 2, ALTERAR = 3, CANCELAR = 4, DELETAR = 5, IMPRIMIR = 6,
            EM_DESENVOLVIMENTO = 0, PREENCHIMENTO_OBRIGATORIO = 1;

    public static boolean getQuestionMessage(int msg, Component parent) {
        switch (msg) {
            case FECHAR:
                return JOptionPane.showConfirmDialog(parent, "Deseja realmente fechar o formulário?", "Fechar formulário", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
            case FECHAR_SISTEMA:
                return JOptionPane.showConfirmDialog(parent, "Deseja realmente fechar o sistema?", "Fechar sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
            case SALVAR:
                return JOptionPane.showConfirmDialog(parent, "Deseja realmente salvar este registro?", "Salvar registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
            case ALTERAR:
                return JOptionPane.showConfirmDialog(parent, "Deseja realmente alterar este registro?", "Salvar registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
            case CANCELAR:
                return JOptionPane.showConfirmDialog(parent, "Deseja realmente cancelar esta ação?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
            case DELETAR:
                return JOptionPane.showConfirmDialog(parent, "Deseja realmente deletar este registro?", "Deletar registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
        }
        return false;
    }

    public static int getPrintMessage(int msg, Component parent) {
        switch (msg) {
            case IMPRIMIR:
                return JOptionPane.showConfirmDialog(parent, "Imprimir somente registro atual?", "Imprimir relatório", JOptionPane.YES_NO_CANCEL_OPTION);
            default:
                return 2;
        }
    }

    public static void getPersistenceMessage(int msg, boolean sucess, Component parent) {
        switch (msg) {
            case SALVAR:
                if (sucess) {
                    JOptionPane.showMessageDialog(parent, "Registro salvo com sucesso!", "Registro salvo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parent, "Erro ao tentar salvar o registro!", "Erro ao salvar", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case ALTERAR:
                if (sucess) {
                    JOptionPane.showMessageDialog(parent, "Registro alterado com sucesso!", "Registro alterado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parent, "Erro ao tentar alterar o registro!", "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case DELETAR:
                if (sucess) {
                    JOptionPane.showMessageDialog(parent, "Registro deletado com sucesso!", "Registro deletado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parent, "Erro ao tentar deletar o registro!", "Erro ao deletar", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    public static void getAppMessage(int msg, Component parent) {
        switch (msg) {
            case EM_DESENVOLVIMENTO:
                JOptionPane.showMessageDialog(parent, "Calma! Sem pressa! Ainda está em desenvolvimento", "Em Desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
                break;
            case PREENCHIMENTO_OBRIGATORIO:
                JOptionPane.showMessageDialog(parent, "Item obrigatório não preenchido", "Erro de validação", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    public static void getExceptionMessage(String msg, Component parent) {
        JOptionPane.showMessageDialog(parent, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
