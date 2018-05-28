/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.beans;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author tiago.teixeira
 */
public class MenuBarCbiDefault extends JMenuBar {

    private static final long serialVersionUID = -817017516809273586L;
    private JMenu menuArquivo;
    private JMenuItem itemNovo;
    private JMenuItem itemEditar;
    private JMenuItem itemAlterar;
    private JMenuItem itemCancelar;
    private JMenuItem itemFechar;
    private JMenuItem itemImprimir;
    private JMenuItem itemDeletar;
    private JMenuItem itemPesquisar;
    private final List<JComponent> itens = new ArrayList();
    private final Cursor defCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    public MenuBarCbiDefault() {
        super();
        construirMenus();
    }

    private void construirMenus() {
        this.removeAll();

        menuArquivo = new JMenu("Arquivo");
        itemNovo = new JMenuItem("Novo");
        itemNovo.setActionCommand("novo");
        itemEditar = new JMenuItem("Editar");
        itemEditar.setActionCommand("editar");
        itemEditar.setEnabled(false);
        itemAlterar = new JMenuItem("Alterar");
        itemAlterar.setActionCommand("alterar");
        itemAlterar.setEnabled(false);
        itemCancelar = new JMenuItem("Cancelar");
        itemCancelar.setActionCommand("cancelar");
        itemCancelar.setEnabled(false);
        itemFechar = new JMenuItem("Fechar");
        itemFechar.setActionCommand("fechar");
        itemImprimir = new JMenuItem("Imprimir");
        itemImprimir.setActionCommand("imprimir");
        itemDeletar = new JMenuItem("Deletar");
        itemDeletar.setActionCommand("imprimir");
        itemDeletar.setEnabled(false);
        itemPesquisar = new JMenuItem("Pesquisar");
        itemPesquisar.setActionCommand("pesquisar");

        menuArquivo.setCursor(defCursor);
        menuArquivo.add(itemNovo);
        menuArquivo.add(itemEditar);
        menuArquivo.add(itemAlterar);
        menuArquivo.add(itemCancelar);
        menuArquivo.add(itemImprimir);
        menuArquivo.add(itemDeletar);
        menuArquivo.add(itemPesquisar);
        menuArquivo.add(new JSeparator());
        menuArquivo.add(itemFechar);

        itens.add(itemNovo);
        itens.add(itemCancelar);
        itens.add(itemAlterar);
        itens.add(itemEditar);
        itens.add(itemFechar);
        itens.add(itemImprimir);
        itens.add(itemDeletar);
        itens.add(itemPesquisar);

        itens.forEach(it -> {
            it.setCursor(defCursor);
        });

        this.add(menuArquivo);
    }

    public List<JComponent> getListMenuItens() {
        return itens;
    }

    /**
     * Este método adiciona itens diferentes a lista de menus vinculandos as
     * ações as que possuem os mesmo nomes e actionCommands.
     *
     * @param comp Componente que será adicionado;
     */
    public void addItem(JComponent comp) {
        itens.add(comp);
    }

    public JMenu getMenuArquivo() {
        return menuArquivo;
    }

    public JMenuItem getItemNovo() {
        return itemNovo;
    }

    public JMenuItem getItemEditar() {
        return itemEditar;
    }

    public JMenuItem getItemAlterar() {
        return itemAlterar;
    }

    public JMenuItem getItemCancelar() {
        return itemCancelar;
    }

    public JMenuItem getItemFechar() {
        return itemFechar;
    }

    public JMenuItem getItemImprimir() {
        return itemImprimir;
    }

    public JMenuItem getItemDeletar() {
        return itemDeletar;
    }

    public JMenuItem getItemPesquisar() {
        return itemPesquisar;
    }

}
