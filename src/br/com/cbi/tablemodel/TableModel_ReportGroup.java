/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodel;

import br.com.cbi.entities.Report_Group;
import java.util.List;

/**
 *
 * @author tiago.teixeira
 */
public final class TableModel_ReportGroup extends TableModelDefaultAdapter<Report_Group> {
    
    private static final long serialVersionUID = 4987539438886251051L;
    private final String[] columnsName = new String[]{"Grupo de Relatórios"};

    public TableModel_ReportGroup() {
        setColmunName(columnsName);
    }

    public TableModel_ReportGroup(List<Report_Group> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Report_Group report = (Report_Group) lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return report.getGrupo();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Report_Group report = (Report_Group) lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                report.setGrupo(aValue.toString());
                break;
        }
    }
    
}
