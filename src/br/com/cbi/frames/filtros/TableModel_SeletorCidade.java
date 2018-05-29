/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.frames.filtros;

import br.com.cbi.entities.Cidade;
import br.com.cbi.tablemodel.TableModelDefaultAdapter;
import java.util.List;

/**
 *
 * @author tiago.teixeira
 */
public final class TableModel_SeletorCidade extends TableModelDefaultAdapter<Cidade> {

    private static final long serialVersionUID = -8762166198565581821L;
    private final String[] columnName = new String[]{"Código", "Cidade", "Estado"};

    public TableModel_SeletorCidade() {
        setColmunName(columnName);
    }

    public TableModel_SeletorCidade(List<Cidade> lista) {
        super(lista);
        setColmunName(columnName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade cdd = (Cidade) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cdd.getId();
            case 1:
                return cdd.getNome();
            case 2:
                return cdd.getEstado();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cidade cdd = (Cidade) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                cdd.setId((Number) aValue);
                break;
            case 1:
                cdd.setNome(aValue.toString());
                break;
            case 2:
                cdd.setEstado(aValue.toString());
                break;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Number.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
        }
        return Object.class;
    }

}
