/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.beans.PesquisaDefaultForm;
import br.com.cbi.dal.EntityManagerHelper;
import br.com.cbi.entities.Atendimento;
import br.com.cbi.frames.Form_Atendimento;
import br.com.cbi.msg.MessageFactory;
import br.com.cbi.tablemodel.TableModel_Jornada;
import br.com.cbi.tablemodel.TableModel_Pecas;
import br.com.cbi.tablemodel.TableModel_Pesquisa_Atendimento;
import br.com.cbi.util.JasperUtil;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;

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
        form.getListMenus().ifPresent(lista -> lista.forEach(comp -> ((JMenuItem) comp).addActionListener(this)));
        fecharESC(form.getMenuBarCbiDefault().getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
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
        PesquisaDefaultForm pesquisa = pesquisar("Pesquisa de Meios de Locomoção", model, null, this);
        pesquisa.setVisible(true);
    }
    
    @Override
    public void imprimir() {
        Map<String, Object> filters = null;
        int result = MessageFactory.getPrintMessage(MessageFactory.IMPRIMIR, form);
        filters = result == 0 && atendimento.getId() != null ? 
                JasperUtil.getReportFilter("ID", atendimento.getId().toString(), JasperUtil.NUMBER) :
                new HashMap();
        
        if (result != 2) {
            InputStream report = getClass().getResourceAsStream("/br/com/sres/reports/rel_MEIO_LOCOMOCAO.jasper");
            JasperUtil.imprimirRelatorio(emh.getConnection(EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU),
                    "Relatório de Meios de Locomoção", form.getIconImage(), filters, report);
        }
    }
    
}
