/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.frames.Form_Atendimento;
import br.com.cbi.frames.Form_Main;
import br.com.cbi.frames.Form_ReportManager;
import br.com.cbi.msg.MessageFactory;
import br.com.cbi.util.ControleInstancias;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

/**
 *
 * @author tiago.teixeira
 */
public final class Listener_Main extends ListenerPatternAdapter<Form_Main> {

    public Listener_Main(Form_Main form) {
        super(form);
        initComponents();
    }

    @Override
    protected void initComponents() {
        attachListener();
        fecharESC(form.getItemFechar());
    }

    @Override
    public void attachListener() {
        form.getMenuList().stream().forEach(item -> {
            item.addActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (e.getActionCommand()) {
            case "logout":
                break;
            case "fechar":
                form.fecharSistema();
                break;
            case "atendimento":
                Form_Atendimento atendimento = (Form_Atendimento) ControleInstancias.getInstance(Form_Atendimento.class.getName());
                atendimento.setVisible(true);
                break;
            case "reportManager":
                openReportManager();
                break;
            default:
                MessageFactory.getAppMessage(MessageFactory.EM_DESENVOLVIMENTO, form);
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void openReportManager() {
        Form_ReportManager rm = (Form_ReportManager) ControleInstancias.getInstance(Form_ReportManager.class.getName());
        rm.setVisible(true);
    }

}
