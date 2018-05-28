/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodel;

import br.com.cbi.entities.Atendimento;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class TableModel_Relatorios extends TableModelDefaultAdapter<Atendimento> {

    private static final long serialVersionUID = -8607497136923881454L;
    private final String[] columnsName = new String[]{"Id", "Nº Relatório", "Data", "Cliente", "Produto", "Nº Série"};

    public TableModel_Relatorios() {
        setColmunName(columnsName);
    }

    public TableModel_Relatorios(List<Atendimento> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Atendimento atendimento = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return atendimento.getId();
            case 1:
                return atendimento.getNumeroRelatorio();
            case 2:
                return atendimento.getDataAtendimento();
            case 3:
                return atendimento.getClienteFinal();
            case 4:
                return atendimento.getProduto();
            case 5:
                return atendimento.getNumeroSerie();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Atendimento atendimento = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                atendimento.setId(aValue != null ? Long.parseLong(aValue.toString()) : null);
                break;
            case 1:
                atendimento.setNumeroRelatorio(aValue.toString());
                break;
            case 2:
                atendimento.setDataAtendimento(aValue != null ? LocalDateTime.parse(aValue.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
                break;
            case 3:
                atendimento.setClienteFinal(aValue.toString());
                break;
            case 4:
                atendimento.setProduto(aValue.toString());
                break;
            case 5:
                atendimento.setNumeroSerie(aValue.toString());
                break;
        }
    }

}
