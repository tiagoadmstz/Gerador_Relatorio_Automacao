/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodel;

import br.com.cbi.entities.Filtro;
import java.util.List;

/**
 *
 * @author tiago.teixeira
 */
public final class TableModel_Filtros extends TableModelDefaultAdapter<Filtro> {

    private static final long serialVersionUID = -8104928841906356738L;
    private final String[] columnName = new String[]{"Filtro", "Valor"};

    public TableModel_Filtros() {
        setColmunName(columnName);
    }

    public TableModel_Filtros(List<Filtro> lista) {
        super(lista);
        setColmunName(columnName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Filtro filtro = (Filtro) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return filtro.getNome();
            case 1:
                return filtro.getValor();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Filtro filtro = (Filtro) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                filtro.setNome(aValue.toString());
                break;
            case 1:
                filtro.setValor(aValue.toString());
                break;
        }
    }

}
