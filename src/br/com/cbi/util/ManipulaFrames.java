/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import br.com.cbi.annotations.MapFrameField;
import br.com.cbi.beans.JTextFieldCBI;
import br.com.cbi.tablemodel.TableModelDefaultAdapter;
import java.awt.Component;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tiago.teixeira
 * @param <T>
 */
public abstract class ManipulaFrames<T> extends JFrame implements Serializable {

    private static final long serialVersionUID = -3961370694826014470L;
    public static final int NOVO = 0, CANCELAR = 1, DELETAR = 1, ALTERAR = 2, SALVAR = 3, EDITAR = 4, IMPRIMIR = 5;

    public Optional<List<JPanel>> getListPaineis() {
        return Optional.ofNullable(null);
    }

    public Optional<List<JComponent>> getListMenus() {
        return Optional.ofNullable(null);
    }

    public void novo() {
        getListPaineis().ifPresent(lista -> lista.forEach(pl -> enableDisableComponentJFrame(NOVO, pl.getComponents())));
        getListMenus().ifPresent(lista -> lista.forEach(comp -> operacaoEnableOrder(NOVO, comp)));
    }

    public void cancelar() {
        getListPaineis().ifPresent(lista -> lista.forEach(pl -> enableDisableComponentJFrame(CANCELAR, pl.getComponents())));
        getListMenus().ifPresent(lista -> lista.forEach(comp -> operacaoEnableOrder(CANCELAR, comp)));
    }

    public void fechar() {
        if (MessageFactory.getMsgApp(MessageFactory.FECHAR_FRAME, this)) {
            this.dispose();
        }
    }

    public void fecharSistema() {
        if (MessageFactory.getMsgApp(MessageFactory.FECHAR_SISTEMA, this)) {
            System.exit(0);
        }
    }

    protected void setImageIcon() {
        setIconImage(new ImageIcon(getClass().getResource("/br/com/cbi/img/iconeCBI.gif")).getImage());
    }

    /**
     * Método de manipulação de formulários para ativar e desativar componentes
     * de interface
     *
     * @param codigoOperacao constante indicando a operacao
     * @param components lista de componentes a ser manipulados
     */
    public void enableDisableComponentJFrame(int codigoOperacao, Component[] components) {

        Map<Class, Integer> map = new HashMap();
        Boolean[][] opcoes = new Boolean[][]{
            {true, false, false, false, true}
        };

        map.put(JTextField.class, 0);
        map.put(JTextFieldCBI.class, 0);
        map.put(JComboBox.class, 0);
        map.put(JScrollPane.class, 0);
        map.put(JTextArea.class, 0);
        map.put(JTable.class, 0);
        map.put(JButton.class, 0);
        map.put(JCheckBox.class, 0);
        map.put(JRadioButton.class, 0);
        map.put(JSlider.class, 0);
        map.put(JPasswordField.class, 0);

        for (Component cp : components) {
            if (map.get(cp.getClass()) != null) {
                if (cp instanceof JScrollPane) {
                    JScrollPane sp = (JScrollPane) cp;
                    Component cp2 = sp.getViewport().getComponent(0);
                    cp2.setEnabled(opcoes[map.get(cp2.getClass())][codigoOperacao]);
                } else {
                    cp.setEnabled(opcoes[map.get(cp.getClass())][codigoOperacao]);
                }
            }
        }

        limparCampos(codigoOperacao, components);
    }

    /**
     * Este método faz a ativação ou inativação de botões ou itens de menu para
     * cada tipo de operacao do sistema.
     *
     * @param codigoOperacao É o código da operação do enumerador OPERACAO
     * @param componentes lista de botoes ou itens de menu
     */
    public void operacaoEnableOrder(int codigoOperacao, JComponent... componentes) {
        Map<String, Integer> mapEnable = new HashMap();
        mapEnable.put("Novo", 0);
        mapEnable.put("Cancelar", 1);
        mapEnable.put("Alterar", 2);
        mapEnable.put("Salvar", 2);
        mapEnable.put("Editar", 3);
        mapEnable.put("Fechar", 4);
        mapEnable.put("Imprimir", 5);
        mapEnable.put("Deletar", 6);
        mapEnable.put("Pesquisar", 7);
        //"Novo", "Cancelar", "Alterar", "Salvar", "Editar", "Fechar", "Imprimir", "Deletar"
        Boolean[][] opcoes = new Boolean[][]{
            {false, true, true, false, false, false, false, false}, //novo
            {true, false, false, false, false, true, true, true}, //cancelar e deletar
            {true, false, false, true, true, true, true, true}, //alterar
            {true, false, false, true, true, true, true, true}, //salvar
            {false, true, true, false, false, false, false, false} //editar
        };

        for (JComponent cp : componentes) {
            if (cp instanceof JMenuItem) {
                JMenuItem item = (JMenuItem) cp;
                item.setEnabled(opcoes[codigoOperacao][mapEnable.get(item.getText())]);
                if (Objects.equals("Alterar", item.getText()) && (codigoOperacao == 0 || codigoOperacao == 2)) {
                    item.setText("Salvar");
                    item.setActionCommand("salvar");
                } else if (Objects.equals("Salvar", item.getText()) && (codigoOperacao == 3 || codigoOperacao == 4)) {
                    item.setText("Alterar");
                    item.setActionCommand("alterar");
                }
            } else if (cp instanceof JButton) {
                JButton bt = (JButton) cp;
                bt.setEnabled(opcoes[codigoOperacao][mapEnable.get(bt.getText())]);
                if (Objects.equals("Alterar", bt.getText()) && (codigoOperacao == 0 || codigoOperacao == 2)) {
                    bt.setText("Salvar");
                    bt.setActionCommand("salvar");
                } else if (Objects.equals("Salvar", bt.getText()) && (codigoOperacao == 3 || codigoOperacao == 4)) {
                    bt.setText("Alterar");
                    bt.setActionCommand("alterar");
                }
            }
        }

    }

    /**
     * Este método faz a ativação ou inativação de botões ou itens de menu para
     * cada tipo de operacao do sistema.
     *
     * @param codigoOperacao É o código da operação do enumerador OPERACAO
     * @param componentes lista de botoes ou itens de menu
     */
    public void operacaoEnableOrder(int codigoOperacao, List<JComponent> componentes) {
        if (componentes != null) {
            operacaoEnableOrder(codigoOperacao, componentes.toArray(new JComponent[componentes.size()]));
        }
    }

    /**
     * Este método limpa todos os campos presentes em um formulário
     *
     * @param codigoOperacao
     * @param componentes
     */
    public void limparCampos(int codigoOperacao, Component[] componentes) {
        if (codigoOperacao != EDITAR && codigoOperacao != SALVAR && codigoOperacao != ALTERAR) {
            for (Component component : componentes) {
                if (component instanceof JFormattedTextField) {
                    JFormattedTextField field = (JFormattedTextField) component;
                    field.setText("");
                } else if (component instanceof JTextField) {
                    JTextField field = (JTextField) component;
                    field.setText("");
                } else if (component instanceof JTextArea) {
                    JTextArea area = (JTextArea) component;
                    area.setText("");
                } else if (component instanceof JScrollPane) {
                    JScrollPane scroll = (JScrollPane) component;
                    for (Component comp : scroll.getViewport().getComponents()) {
                        if (comp instanceof JTextArea) {
                            JTextArea areaTemp = (JTextArea) comp;
                            areaTemp.setText("");
                        } else if (comp instanceof JTable) {
                            JTable tb = (JTable) comp;
                            TableModelDefaultAdapter model = (TableModelDefaultAdapter) tb.getModel();
                            model.deletarLista();
                        }
                    }
                } else if (component instanceof JComboBox) {
                    JComboBox combo = (JComboBox) component;
                    if (combo.getItemCount() > 0) {
                        combo.setSelectedIndex(0);
                    }
                } else if (component instanceof JCheckBox) {
                    JCheckBox ck = (JCheckBox) component;
                    ck.setSelected(false);
                } else if (component instanceof JSlider) {
                    JSlider sd = (JSlider) component;
                    sd.setValue(0);
                }
            }
        }
    }

    /**
     * Este método inseri os dados do objecto no frame
     * 
     * @param object 
     */
    public void setObject(T object) {
        getDados(object);
    }

    /**
     * Este método retorna o objecto que o frame representa
     * 
     * @param object 
     */
    public void getObject(T object) {
        setDados(object);
    }

    protected void setDados(T object) {
        try {
            for (Method mf : getClass().getDeclaredMethods()) {
                if (mf.isAnnotationPresent(MapFrameField.class)) {
                    MapFrameField map = mf.getAnnotation(MapFrameField.class);
                    Method mo = object.getClass().getMethod("set".concat(map.referencedField().replaceFirst("\\w", map.referencedField().substring(0, 1).toUpperCase())), map.typeReference());

                    if (mf.getReturnType() == JTextField.class || mf.getReturnType() == JTextFieldCBI.class) {
                        mo.invoke(object, CastFactory.cast(mf.invoke(this).getClass().getMethod("getText").invoke(mf.invoke(this)), map.typeReference()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getDados(T object) {
        try {
            for (Method mf : getClass().getDeclaredMethods()) {
                if (mf.isAnnotationPresent(MapFrameField.class)) {
                    MapFrameField map = mf.getAnnotation(MapFrameField.class);
                    Method mo = object.getClass().getMethod("get".concat(map.referencedField().replaceFirst("\\w", map.referencedField().substring(0, 1).toUpperCase())));

                    if (mf.getReturnType() == JTextField.class || mf.getReturnType() == JTextFieldCBI.class) {
                        mf.invoke(this).getClass().getMethod("setText", String.class).invoke(mf.invoke(this), CastFactory.cast(mo.invoke(object), map.typeReference()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
