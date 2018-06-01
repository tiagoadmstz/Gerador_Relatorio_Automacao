/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.beans.PesquisaDefaultForm;
import br.com.cbi.dal.EntityManagerHelper;
import br.com.cbi.entities.Atendimento;
import br.com.cbi.entities.Jornada;
import br.com.cbi.entities.Peca;
import br.com.cbi.frames.Form_Atendimento;
import br.com.cbi.msg.MessageFactory;
import br.com.cbi.tablemodel.TableModel_Jornada;
import br.com.cbi.tablemodel.TableModel_Pecas;
import br.com.cbi.tablemodel.TableModel_Pesquisa_Atendimento;
import br.com.cbi.util.JasperUtil;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class Listener_Atendimento extends ListenerPatternAdapter<Form_Atendimento> {

    private static final long serialVersionUID = 8658902882695357107L;
    private TableModel_Pecas modelPecas;
    private TableModel_Jornada modelJornada;
    private EntityManagerHelper emh;
    private Atendimento atendimento;

    public Listener_Atendimento(Form_Atendimento form) {
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
        form.getListMenus().ifPresent(lista -> lista.forEach(comp -> ((AbstractButton) comp).addActionListener(this)));
        form.getListButtons().ifPresent(lista -> lista.forEach(bt -> bt.addActionListener(this)));
        fecharESC(form.getMenuBarCbiDefault().getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        switch (event.getActionCommand()) {
            case "addPeca":
                modelPecas.addObject(new Peca());
                break;
            case "removePeca":
                removePeca();
                break;
            case "addJornada":
                modelJornada.addObject(new Jornada());
                break;
            case "removeJornada":
                removeJornada();
                break;
        }
    }

    @Override
    protected void addModel() {
        modelPecas = new TableModel_Pecas();
        modelJornada = new TableModel_Jornada();
        form.getTbPecas().setModel(modelPecas);
        setColumnSize(form.getTbPecas(), 80, 400, 80);
        form.getTbJornada().setModel(modelJornada);
        setColumnSize(form.getTbJornada(), 100, 150, 150, 150, 150, 150, 150, 150, 150, 150);
    }

    @Override
    protected void carregarListas() {

    }

    @Override
    protected void salvar() {
        salvar(atendimento, emh);
    }

    @Override
    protected void alterar() {
        alterar(atendimento, emh);
    }

    @Override
    protected void deletar() {
        deletar(atendimento, emh);
    }

    @Override
    public void copiarObject(Object object) {
        atendimento.copiar((Atendimento) object);
        form.setObject(atendimento);
    }

    @Override
    protected void pesquisa() {
        TableModel_Pesquisa_Atendimento model = new TableModel_Pesquisa_Atendimento();
        model.addLista((List<Atendimento>) emh.getObjectListNamedQuery(Atendimento.class, "atendimento.findAll", null, null, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU));
        PesquisaDefaultForm pesquisa = pesquisar("Pesquisa de Atendimentos", model, null, this, 80, 100, 100, 250, 250, 250);
        pesquisa.setVisible(true);
    }

    @Override
    public void imprimir() {
        int result = MessageFactory.getPrintMessage(MessageFactory.IMPRIMIR, form);
        Map<String, Object> filters = result == 0 && atendimento.getId() != null
                ? JasperUtil.getReportFilter("ID", atendimento.getId().toString(), JasperUtil.NUMBER)
                : new HashMap();

        if (result != 2) {
            InputStream report = getClass().getResourceAsStream("/br/com/cbi/reports/rel_ATENDIMENTO_CAMPO.jasper");
            JasperUtil.imprimirRelatorio(emh.getConnection(EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU),
                    "Relatório de Atendimento", form.getIconImage(), filters, report);
        }
    }

    protected void removePeca() {
        if (form.getTbPecas().getSelectedRow() != -1) {
            modelPecas.removeObject(form.getTbPecas().getSelectedRow());
        }
    }

    protected void removeJornada() {
        if (form.getTbJornada().getSelectedRow() != -1) {
            modelJornada.removeObject(form.getTbJornada().getSelectedRow());
        }
    }
}
