/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.tablemodel;

import br.com.cbi.entities.Jornada;
import br.com.cbi.util.Datas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Tiago D. Teixeira
 */
public final class TableModel_Jornada extends TableModelDefaultAdapter<Jornada> {

    private static final long serialVersionUID = -8261766930662347552L;
    private final String[] columnsName = new String[]{
        "Data", "Deslocamento Início", "Deslocamento Fim", "Trabalho Início", "Trabalho Fim",
        "Refeição Início", "Refeição Fim", "Deslocamento Início", "Deslocamento Fim", "Diárias"
    };

    public TableModel_Jornada() {
        setColmunName(columnsName);
    }

    public TableModel_Jornada(List<Jornada> lista) {
        super(lista);
        setColmunName(columnsName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jornada jornada = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return jornada.getDataJornada() != null ? jornada.getDataJornada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
            case 1:
                return jornada.getDeslocamento_in();
            case 2:
                return jornada.getDeslocamento_out();
            case 3:
                return jornada.getTrabalho_in();
            case 4:
                return jornada.getTrabalho_out();
            case 5:
                return jornada.getRefeicao_in();
            case 6:
                return jornada.getRefeicao_out();
            case 7:
                return jornada.getDeslocamentoSaida_in();
            case 8:
                return jornada.getDeslocamentoSaida_out();
            case 9:
                return jornada.getDiarias();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Jornada jornada = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                jornada.setDataJornada(aValue != null
                        ? LocalDate.parse(Datas.getDateString(aValue.toString(), Datas.VERIFICAR_DATA), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : null);
                break;
            case 1:
                jornada.setDeslocamento_in(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 2:
                jornada.setDeslocamento_out(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 3:
                jornada.setTrabalho_in(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 4:
                jornada.setTrabalho_out(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 5:
                jornada.setRefeicao_in(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 6:
                jornada.setRefeicao_out(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 7:
                jornada.setDeslocamentoSaida_in(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 8:
                jornada.setDeslocamentoSaida_out(Datas.getHour(aValue.toString(), Datas.VERIFICAR_HORA));
                break;
            case 9:
                jornada.setDiarias(aValue.toString());
                break;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
