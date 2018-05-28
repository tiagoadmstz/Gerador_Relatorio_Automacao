package br.com.cbi.tablemodel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tiago D. Teixeira
 */
public class DefaultCBIRenderer extends DefaultTableCellRenderer {

    private final Color gray = new Color(225, 225, 225);
    private final Color white = new Color(255, 255, 255);
    private int column = 0;

    public DefaultCBIRenderer() {
    }

    public void setColumnLink(int column) {
        this.column = column;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            renderer.setBackground(Color.blue);
            renderer.setForeground(white);
        } else if (row % 2 != 0 && column != this.column) {
            renderer.setForeground(Color.black);
            renderer.setBackground(gray);
        } else if (row % 2 == 0 && column != this.column) {
            renderer.setForeground(Color.black);
            renderer.setBackground(white);
        } else if (row % 2 != 0 && column == this.column) {
            renderer.setForeground(Color.blue);
            renderer.setBackground(gray);
        } else if (row % 2 == 0 && column == this.column) {
            renderer.setForeground(Color.blue);
            renderer.setBackground(white);
        }
        return renderer;
    }

}
