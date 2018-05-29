/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.main.Form_Main;
import br.com.cbi.tablemodel.TableModel_Jornada;
import br.com.cbi.tablemodel.TableModel_Pecas;
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
        form.getListMenus().ifPresent(lista -> lista.forEach(comp -> ((JMenuItem) comp).addActionListener(this)));
        fecharEsc(form.getMenuBarCbiDefault().getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
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
    protected void salvar() {
        /*form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
        form.enableDisableComponentJFrame(ManipulaFrames.SALVAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.SALVAR, form.getMenuBarCbiDefault().getListMenuItens());*/
    }

    @Override
    protected void alterar() {
        /*form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
        form.enableDisableComponentJFrame(ManipulaFrames.ALTERAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.ALTERAR, form.getMenuBarCbiDefault().getListMenuItens());*/
    }

    @Override
    protected void editar() {
        /*form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
        form.enableDisableComponentJFrame(ManipulaFrames.EDITAR, comp);
        });
        form.operacaoEnableOrder(ManipulaFrames.EDITAR, form.getMenuBarCbiDefault().getListMenuItens());*/
    }

    @Override
    protected void deletar() {
        /*form.getListPaineis().stream().map(JPanel::getComponents).forEach(comp -> {
        form.enableDisableComponentJFrame(ManipulaFrames.DELETAR, comp);
        form.operacaoEnableOrder(ManipulaFrames.DELETAR, form.getMenuBarCbiDefault().getListMenuItens());
        });*/
    }

}
