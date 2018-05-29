/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.EntityManagerHelper;
import br.com.cbi.entities.Atendimento;
import br.com.cbi.main.Form_Main;
import br.com.cbi.tablemodel.TableModel_Jornada;
import br.com.cbi.tablemodel.TableModel_Pecas;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class Listener_Main extends ListenerDefaultAdapter<Form_Main> {

    private static final long serialVersionUID = 8658902882695357107L;
    private TableModel_Pecas modelPecas;
    private TableModel_Jornada modelJornada;
    private EntityManagerHelper<Atendimento> emh;
    private Atendimento atendimento;

    public Listener_Main(Form_Main form) {
        super(form);
        initComponents();
    }

    @Override
    protected void initComponents() {
        atendimento = new Atendimento();
        emh = new EntityManagerHelper();
        attachListener();
        addModel();
    }

    @Override
    protected void attachListener() {
        form.getListMenus().ifPresent(lista -> lista.forEach(comp -> ((JMenuItem) comp).addActionListener(this)));
        fecharEsc(form.getMenuBarCbiDefault().getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        switch (event.getActionCommand()) {
            case "salvar":
                form.salvar(atendimento, emh);
                break;
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    protected void addModel() {
        modelPecas = new TableModel_Pecas();
        modelJornada = new TableModel_Jornada();
        form.getTbPecas().setModel(modelPecas);
        form.getTbJornada().setModel(modelJornada);
    }

    @Override
    protected void carregarListas() {
        
    }

}
