/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.main.Form_Main;
import br.com.cbi.tablemodel.TableModel_Jornada;
import br.com.cbi.tablemodel.TableModel_Pecas;
import br.com.cbi.util.ManipulaFrames;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class Listener_Main extends ListenerDefaultAdapter<Form_Main> {

    private static final long serialVersionUID = 8658902882695357107L;
    private TableModel_Pecas modelPecas;
    private TableModel_Jornada modelJornada;

    public Listener_Main(Form_Main form) {
        super(form);
        initComponents();
    }

    @Override
    protected void initComponents() {
        attachListener();
        addModel();
    }

    @Override
    protected void attachListener() {
        form.getMenuBarCbiDefault().getListMenuItens().stream().forEach(m -> {
            ((JMenuItem) m).addActionListener(this);
        });
        fecharEsc(form.getMenuBarCbiDefault().getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (event.getActionCommand()) {
            case "novo":
                novo();
                break;
            case "cancelar":
                cancelar();
                break;
            case "fechar":
                fecharSistema();
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

    @Override
    protected void novo() {
        form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
            form.enableDisableComponentJFrame(ManipulaFrames.NOVO, comp);
            form.limparCampos(ManipulaFrames.NOVO, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.NOVO, form.getMenuBarCbiDefault().getListMenuItens());
    }

    @Override
    protected void cancelar() {
        form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
            form.enableDisableComponentJFrame(ManipulaFrames.CANCELAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.CANCELAR, form.getMenuBarCbiDefault().getListMenuItens());
    }

    @Override
    protected void salvar() {
        form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
            form.enableDisableComponentJFrame(ManipulaFrames.SALVAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.SALVAR, form.getMenuBarCbiDefault().getListMenuItens());
    }

    @Override
    protected void alterar() {
        form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
            form.enableDisableComponentJFrame(ManipulaFrames.ALTERAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.ALTERAR, form.getMenuBarCbiDefault().getListMenuItens());
    }

    @Override
    protected void editar() {
        form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
            form.enableDisableComponentJFrame(ManipulaFrames.EDITAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.EDITAR, form.getMenuBarCbiDefault().getListMenuItens());
    }

    @Override
    protected void deletar() {
        form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
            form.enableDisableComponentJFrame(ManipulaFrames.DELETAR, comp);
            form.operacaoEnableOrder(ManipulaFrames.DELETAR, form.getMenuBarCbiDefault().getListMenuItens());
        });
    }

}
