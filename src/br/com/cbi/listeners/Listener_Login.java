/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.frames.Form_Login;
import br.com.cbi.frames.Form_Main;
import br.com.cbi.util.ControleInstancias;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago.teixeira
 */
public final class Listener_Login extends ListenerPatternAdapter<Form_Login> implements ActionListener, Serializable {

    private static final long serialVersionUID = -6735813835735026392L;

    public Listener_Login(Form_Login form) {
        super(form);
        initComponents();
    }

    @Override
    protected void initComponents() {
        attachListener();
    }

    @Override
    protected void attachListener() {
        form.getBtLogin().addActionListener(this);
        form.getBtSenha().addActionListener(this);
        form.getItemFechar().addActionListener(this);
    }

    private void openMainForm(boolean abrir) {
        if (abrir) {
            Form_Main formMain = (Form_Main) ControleInstancias.getInstance(Form_Main.class.getName());
            formMain.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (e.getActionCommand()) {
            case "login":
                if(sessionLogin(form.getTxtUsuario().getText(), String.valueOf(form.getTxtSenha().getPassword()))){
                    openMainForm(true);
                    form.dispose();
                } else {
                    JOptionPane.showMessageDialog(form, "Não foi possível iniciar a sessão, contate o administrador do sistema", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "cancelar":
                form.fecharSistema();
                break;
            case "fechar":
                form.fecharSistema();
                break;
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private boolean sessionLogin(String login, String senha) {
        try {
            //byte[] dados = Utilidades.encryptMsg((login + "," + senha + ",S34M3D"));
            return login.equals("admin") && senha.equals("admin");
        } catch (Exception ex) {
            Logger.getLogger(Listener_Login.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
