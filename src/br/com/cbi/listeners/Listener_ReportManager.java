/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.dal.EntityManagerHelper;
import br.com.cbi.entities.Cidade;
import br.com.cbi.entities.Filtro;
import br.com.cbi.entities.Report_Group;
import br.com.cbi.entities.SresReport;
import br.com.cbi.frames.Form_ReportManager;
import br.com.cbi.frames.filtros.SeletorAno;
import br.com.cbi.frames.filtros.SeletorDataCorte;
import br.com.cbi.frames.filtros.SeletorDatas;
import br.com.cbi.frames.filtros.SeletorDias;
import br.com.cbi.frames.filtros.SeletorHorario;
import br.com.cbi.frames.filtros.SeletorMeses;
import br.com.cbi.frames.filtros.SeletorLista;
import br.com.cbi.frames.filtros.TableModel_SeletorCidade;
import br.com.cbi.msg.MessageFactory;
import br.com.cbi.tablemodel.TableModelDefaultAdapter;
import br.com.cbi.tablemodel.TableModel_Filtros;
import br.com.cbi.tablemodel.TableModel_Report;
import br.com.cbi.tablemodel.TableModel_ReportGroup;
import br.com.cbi.util.ControleInstancias;
import br.com.cbi.util.JasperUtil;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;

/**
 *
 * @author tiago.teixeira
 */
public final class Listener_ReportManager extends ListenerPatternAdapter<Form_ReportManager> {

    private List<SresReport> reports;
    private List<Report_Group> groups;
    private EntityManagerHelper emh;
    private TableModel_ReportGroup groupModel;
    private TableModel_Report reportModel;
    private TableModel_Filtros filtrosModel;
    private final Map<String, Object> mapParam = new HashMap();

    public Listener_ReportManager(Form_ReportManager form) {
        super(form);
        initComponents();
    }

    @Override
    protected void initComponents() {
        emh = new EntityManagerHelper();
        reports = new ArrayList();
        attachListener();
        carregarListas();
        addModel();
    }

    @Override
    public void attachListener() {
        form.getTbGrupos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    try {
                        reportModel.setLista(reports.stream().filter(rl -> rl.getGrupo().getGrupo().equals(((Report_Group) groupModel.getObject(form.getTbGrupos().getSelectedRow())).getGrupo())).collect(Collectors.toList()));
                        filtrosModel.setLista(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Erro ao tentar gerar lista", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        form.getTbReports().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    filtrosModel.setLista(((SresReport) reportModel.getObject(form.getTbReports().getSelectedRow())).getFiltro());
                }
            }
        });
        form.getTbFiltros().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    editar();
                    form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        form.getBtEditarFiltro().addActionListener(this);
        form.getBtImprimir().addActionListener(this);
        form.getItemFechar().addActionListener(this);
        fecharESC(form.getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            switch (e.getActionCommand()) {
                case "fechar":
                    form.fechar();
                case "editar":
                    editar();
                    break;
                case "imprimir":
                    imprimir();
                    break;
                default:
                    MessageFactory.getAppMessage(MessageFactory.EM_DESENVOLVIMENTO, form);
            }
        } catch (Exception ex) {
            form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            if (!ex.getMessage().equals("")) {
                MessageFactory.getExceptionMessage(ex.getMessage(), form);
            }
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void carregarListas() {
        reports = (List<SresReport>) emh.getObjectListNamedQuery(SresReport.class, "report.findAll", null, null, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
        groups = (List<Report_Group>) emh.getObjectListNamedQuery(Report_Group.class, "grupo.findAll", null, null, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
    }

    @Override
    public void addModel() {
        groupModel = new TableModel_ReportGroup(groups);
        reportModel = new TableModel_Report();
        filtrosModel = new TableModel_Filtros();
        form.getTbGrupos().setModel(groupModel);
        form.getTbReports().setModel(reportModel);
        form.getTbFiltros().setModel(filtrosModel);
    }

    @Override
    public void editar() {
        try {
            Filtro filtro = (Filtro) filtrosModel.getObject(form.getTbFiltros().getSelectedRow());
            switch (filtro.getTipo()) {
                case "data":
                    SeletorDatas datas = (SeletorDatas) ControleInstancias.getInstance(SeletorDatas.class.getName());
                    datas.getBtConfirmar().addActionListener((ActionEvent e) -> {
                        try {
                            if (datas.validarData()) {
                                filtro.setValor(datas.getTxtDataInicial().getText() + " à " + datas.getTxtDataFinal().getText());
                                mapParam.put("DataInicial", new Timestamp(datas.getInitDateTimeMillis()));
                                mapParam.put("DataFinal", new Timestamp(datas.getEndDateTimeMillis()));
                                filtrosModel.fireTableDataChanged();
                                datas.dispose();
                            } else {
                                JOptionPane.showMessageDialog(datas, "Datas inválidas", "Erro ao informar datas", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    datas.setVisible(true);
                    break;
                case "dias":
                    SeletorDias dias = (SeletorDias) ControleInstancias.getInstance(SeletorDias.class.getName());
                    dias.getBtConfirmar().addActionListener(event -> {
                        try {
                            filtro.setValor(dias.getDiaInicial() + " a " + dias.getDiaFinal());
                            mapParam.put("DIA_INICIAL", dias.getDiaInicial());
                            mapParam.put("DIA_FINAL", dias.getDiaFinal());
                            filtrosModel.fireTableDataChanged();
                            dias.dispose();
                        } catch (Exception ex) {
                            MessageFactory.getExceptionMessage(ex.getMessage(), dias);
                        }
                    });
                    dias.setVisible(true);
                    break;
                case "mes":
                    SeletorMeses mes = (SeletorMeses) ControleInstancias.getInstance(SeletorMeses.class.getName());
                    mes.getBtConfirmar().addActionListener(event -> {
                        try {
                            filtro.setValor(mes.getMesInicial() + " a " + mes.getMesFinal());
                            mapParam.put("MES_INICIAL", mes.getMesInicial());
                            mapParam.put("MES_FINAL", mes.getMesFinal());
                            filtrosModel.fireTableDataChanged();
                            mes.dispose();
                        } catch (Exception ex) {
                            MessageFactory.getExceptionMessage(ex.getMessage(), mes);
                        }
                    });
                    mes.setVisible(true);
                    break;
                case "ano":
                    SeletorAno ano = (SeletorAno) ControleInstancias.getInstance(SeletorAno.class.getName());
                    ano.getBtConfirmar().addActionListener(event -> {
                        try {
                            filtro.setValor(ano.getAno());
                            mapParam.put("ANO", ano.getAno());
                            filtrosModel.fireTableDataChanged();
                            ano.dispose();
                        } catch (Exception ex) {
                            MessageFactory.getExceptionMessage(ex.getMessage(), ano);
                        }
                    });
                    ano.setVisible(true);
                    break;
                case "cidade":
                    List<Cidade> listaCid = (List<Cidade>) emh.getObjectListNamedQuery(Cidade.class, "Cidade.findAll", null, null, EntityManagerHelper.PERSISTENCE_UNIT.ORACLE11G_PU);
                    mountPesquisa(listaCid, new TableModel_SeletorCidade(), filtro);
                    break;
                case "dataCorte":
                    SeletorDataCorte dataCorte = (SeletorDataCorte) ControleInstancias.getInstance(SeletorDataCorte.class.getName());
                    dataCorte.getBtConfirmar().addActionListener(event -> {
                        filtro.setValor(dataCorte.getDataCorte());
                        mapParam.put("DATA_CORTE", dataCorte.getDataCorte());
                        filtrosModel.fireTableDataChanged();
                        dataCorte.dispose();
                    });
                    dataCorte.setVisible(true);
                    break;
                case "horas":
                    SeletorHorario horario = (SeletorHorario) ControleInstancias.getInstance(SeletorHorario.class.getName());
                    horario.getBtConfirmar().addActionListener(event -> {
                        filtro.setValor(horario.getHoraInicial() + " à " + horario.getHoraFinal());
                        mapParam.put("HORA_INICIO", horario.getHoraInicial());
                        mapParam.put("HORA_FIM", horario.getHoraFinal());
                        filtrosModel.fireTableDataChanged();
                        horario.dispose();
                    });
                    horario.setVisible(true);
                    break;
                case "boolean":
                    filtro.setValor(filtro.getValor().equals("SIM") ? "NÃO" : "SIM");
                    mapParam.put("GRAFICO", filtro.getValor().equals("SIM") ? "true" : "false");
                    filtrosModel.fireTableDataChanged();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(form, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void imprimir() {
        try {
            if (filtrosModel.getRowCount() > 0 && !mapParam.isEmpty()) {
                SresReport report = (SresReport) reportModel.getObject(form.getTbReports().getSelectedRow());
                //System.out.println(report.getPath());
                this.getReportPath(report);
                //System.out.println(report.getPath());
                InputStream input = getClass().getResourceAsStream(report.getPath());
                mapParam.put("SUBREPORT_DIR", getClass().getResource("/br/com/sres/reports/"));
                JasperUtil.imprimirRelatorio(emh.getConnection(EntityManagerHelper.PERSISTENCE_UNIT.ORACLE11G_PU), report.getNome(), form.getIconImage(), mapParam, input);
                emh.closeAll();
            } else if (filtrosModel.getRowCount() == 0) {
                SresReport report = (SresReport) reportModel.getObject(form.getTbReports().getSelectedRow());
                this.getReportPath(report);
                InputStream input = getClass().getResourceAsStream(report.getPath());
                mapParam.put("SUBREPORT_DIR", getClass().getResource("/br/com/sres/reports/"));
                JasperUtil.imprimirRelatorio(emh.getConnection(EntityManagerHelper.PERSISTENCE_UNIT.ORACLE11G_PU), report.getNome(), form.getIconImage(), mapParam, input);
                emh.closeAll();
            } else {
                JOptionPane.showMessageDialog(form, "Não há parâmetros para geração do relatório!", "Parâmetros não informados", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(form, "Não foi possível imprimir este ralatório!", "Erro ao tentar imprimir", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mountPesquisa(List<?> lista, TableModelDefaultAdapter model, Filtro filtro) {
        SeletorLista prestadores = (SeletorLista) ControleInstancias.getInstance(SeletorLista.class.getName());
        prestadores.setParameters(lista, model, 50, 200);
        prestadores.getTbPrestador().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    RowSorter sorter = prestadores.getTbPrestador().getRowSorter();
                    Object object = model.getObject(sorter.convertRowIndexToModel(prestadores.getTbPrestador().getSelectedRow()));
                    if (object instanceof Cidade) {
                        Cidade cid = (Cidade) object;
                        filtro.setValor(cid.getNome());
                        mapParam.put("CIDADE", Integer.parseInt(cid.getId().toString()));
                    }
                    filtrosModel.fireTableDataChanged();
                    prestadores.dispose();
                }
            }
        });
        prestadores.setVisible(true);
    }

    private void getReportPath(SresReport report) {
        if (report.getPath().contains("rel_RESUMO_PROCEDIMENTOS_PROFISSIONAIS")) {
            report.setPath("/br/com/sres/reports/rel_RESUMO_PROCEDIMENTOS_PROFISSIONAIS");
            report.getFiltro().stream().filter((filtro) -> (filtro.getNome().equals("Departamento"))).forEachOrdered((Filtro filtro) -> {
                report.setPath(report.getPath().replace(".jasper", ""));
                if (filtro.getValor().contains("Ginecologia")) {
                    report.setPath(report.getPath() + "_GINECOLOGIA.jasper");
                } else {
                    report.setPath(report.getPath() + "_" + filtro.getValor().toUpperCase().replaceAll("\\s", "_") + ".jasper");
                }
            });
        }
    }

}
