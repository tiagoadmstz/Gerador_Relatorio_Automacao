/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodel;

import br.com.cbi.entities.Peca;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class TableModel_Pecas extends TableModelDefaultAdapter<Peca> {

    private static final long serialVersionUID = 93195137560743155L;
    private final String[] columnsName = new String[]{"Código", "Descrição", "Quantidade"};

    public TableModel_Pecas() {
        setColmunName(columnsName);
    }

    public TableModel_Pecas(List<Peca> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Peca peca = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return peca.getCodigo();
            case 1:
                return peca.getDescricao();
            case 2:
                return peca.getQuantidade();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Peca peca = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                peca.setCodigo(aValue.toString());
                break;
            case 1:
                peca.setDescricao(aValue.toString());
                break;
            case 2:
                peca.setQuantidade(aValue != null ? new BigDecimal(aValue.toString()) : null);
                break;
        }

    }

}
