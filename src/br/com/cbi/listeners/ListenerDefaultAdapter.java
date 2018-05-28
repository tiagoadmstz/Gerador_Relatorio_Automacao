/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.util.MessageFactory;
import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Tiago
 * @param <T>
 */
public abstract class ListenerDefaultAdapter<T> implements Serializable, ActionListener {

    private static final long serialVersionUID = 5169752221295205754L;
    protected final T form;

    public ListenerDefaultAdapter(T form) {
        this.form = form;
    }

    protected abstract void initComponents();

    protected abstract void attachListener();

    protected void addModel() {
    }

    protected void carregarPaineis() {
    }

    protected void carregarListas() {
    }

    protected void novo() {
        
    }

    protected void cancelar() {

    }

    protected void salvar(){
        
    }
    
    protected void editar() {

    }

    protected void alterar() {

    }

    protected void deletar() {

    }

    protected void fechar() {
        JFrame f = (JFrame) this.form;
        if (MessageFactory.getMsgApp(MessageFactory.FECHAR_FRAME, f)) {
            f.dispose();
        }
    }

    protected void fecharSistema() {
        JFrame f = (JFrame) this.form;
        if (MessageFactory.getMsgApp(MessageFactory.FECHAR_SISTEMA, f)) {
            System.exit(0);
        }
    }

    protected void fecharEsc(JMenuItem itemFechar) {
        itemFechar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
