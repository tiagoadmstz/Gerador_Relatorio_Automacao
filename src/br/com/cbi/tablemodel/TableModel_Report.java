/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodel;

import br.com.cbi.entities.SresReport;
import java.util.List;

/**
 *
 * @author tiago.teixeira
 */
public final class TableModel_Report extends TableModelDefaultAdapter<SresReport> {

    private static final long serialVersionUID = -2521268252808690658L;
    private final String[] columnsName = new String[]{"Relatórios"};

    public TableModel_Report() {
        setColmunName(columnsName);
    }

    public TableModel_Report(List<SresReport> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SresReport report = (SresReport) lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return report.getNome();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SresReport report = (SresReport) lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                report.setNome(aValue.toString());
                break;
        }
    }
    
}
