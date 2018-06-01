/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import br.com.cbi.annotations.MapFrameField;
import br.com.cbi.beans.JTextFieldCBI;
import br.com.cbi.msg.MessageFactory;
import br.com.cbi.tablemodel.TableModelDefaultAdapter;
import java.awt.Component;
import java.awt.Image;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Column;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
 * @author tiago.teixeira
 */
public abstract class ManipulaFrames extends JFrame {

    private static final long serialVersionUID = 5932987037949674860L;
    public static int NOVO = 0, CANCELAR = 1, SALVAR = 3, EDITAR = 4, FECHAR = 1, IMPRIMIR = 5, DELETAR = 1, ALTERAR = 2;
    private final Image image = new ImageIcon(getClass().getResource("/br/com/cbi/img/iconeCBI.gif")).getImage();

    public Optional<List<JPanel>> getListPaineis() {
        return Optional.empty();
    }

    public Optional<List<JComponent>> getListMenus() {
        return Optional.empty();
    }

    public void novo() {
        getListPaineis().get().forEach(pl -> enableDisableComponentJFrame(ManipulaFrames.NOVO, pl.getComponents()));
        operacaoEnableOrder(ManipulaFrames.NOVO, getListMenus().get());
    }

    public void cancelar() {
        getListPaineis().get().forEach(pl -> enableDisableComponentJFrame(ManipulaFrames.CANCELAR, pl.getComponents()));
        operacaoEnableOrder(ManipulaFrames.CANCELAR, getListMenus().get());
    }

    public void editar() {
        getListPaineis().get().forEach(pl -> enableDisableComponentJFrame(ManipulaFrames.EDITAR, pl.getComponents()));
        operacaoEnableOrder(ManipulaFrames.EDITAR, getListMenus().get());
    }

    public void setMenuEditarOpcoes() {
        operacaoEnableOrder(ManipulaFrames.SALVAR, getListMenus().get());
    }

    public boolean validaCampos(ManipulaBean object) {
        try {
            for (Method mo : object.getClass().getDeclaredMethods()) {
                if (mo.isAnnotationPresent(Column.class)) {
                    Column column = mo.getAnnotation(Column.class);
                    if (!column.nullable()) {
                        return mo.invoke(object) != null && !mo.invoke(object).equals("");
                    } else {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
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
            {true, false, false, false, true, false, false, true}, //cancelar e deletar
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

    protected void setImageIcon() {
        setIconImage(image);
    }

    public void fechar() {
        if (MessageFactory.getQuestionMessage(MessageFactory.FECHAR, this)) {
            this.dispose();
        }
    }

    public void fecharSistema() {
        if (MessageFactory.getQuestionMessage(MessageFactory.FECHAR_SISTEMA, this)) {
            System.exit(0);
        }
    }

    public void getObject(ManipulaBean object) {
        setDados(object);
    }

    public void setObject(ManipulaBean object) {
        getDados(object);
    }

    protected void setDados(ManipulaBean object) {
        try {
            for (Method mf : getClass().getDeclaredMethods()) {
                if (mf.isAnnotationPresent(MapFrameField.class)) {
                    MapFrameField map = mf.getAnnotation(MapFrameField.class);
                    Method mo = object.getClass().getDeclaredMethod("set".concat(map.referencedField().replaceFirst("\\w", map.referencedField().substring(0, 1).toUpperCase())), map.typeParameter());

                    if (mf.getReturnType() == JTextField.class || mf.getReturnType() == JTextFieldCBI.class || mf.getReturnType() == JTextArea.class) {
                        if (!mf.invoke(this).getClass().getMethod("getText").invoke(mf.invoke(this)).equals("")) {
                            mo.invoke(object, CastFactory.cast(mf.invoke(this).getClass().getMethod("getText").invoke(mf.invoke(this)), map.typeParameter()));
                        }
                    } else if (mf.getReturnType() == JComboBox.class) {
                        if (!mf.invoke(this).getClass().getMethod("getSelectedIndex").invoke(mf.invoke(this)).equals(-1)) {
                            mo.invoke(object, CastFactory.cast(mf.invoke(this).getClass().getMethod("getSelectedItem").invoke(mf.invoke(this)), map.typeParameter()));
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getDados(ManipulaBean object) {
        try {
            for (Method mf : getClass().getDeclaredMethods()) {
                if (mf.isAnnotationPresent(MapFrameField.class)) {
                    MapFrameField map = mf.getAnnotation(MapFrameField.class);
                    Method mo = object.getClass().getDeclaredMethod("get".concat(map.referencedField().replaceFirst("\\w", map.referencedField().substring(0, 1).toUpperCase())));

                    if (mo.invoke(object) != null) {
                        if (mf.getReturnType() == JTextField.class || mf.getReturnType() == JTextFieldCBI.class || mf.getReturnType() == JTextArea.class) {
                            if(mo.getReturnType() == LocalDate.class){
                                mf.invoke(this).getClass().getMethod("setText", String.class).invoke(mf.invoke(this), ((LocalDate) CastFactory.cast(mo.invoke(object), map.typeParameter())).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            } else {
                                mf.invoke(this).getClass().getMethod("setText", String.class).invoke(mf.invoke(this), CastFactory.cast(mo.invoke(object), map.typeParameter()).toString());
                            }
                        } else if (mf.getReturnType() == JComboBox.class) {

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setValuesFieldsForm(JFrame form, Object obj, Method method, Method setMethod, MapFrameField map) throws Exception {
        //verifica o tipo de retorno do getter
        if (method.getReturnType() == JTextFieldCBI.class | method.getReturnType() == JTextField.class | method.getReturnType() == JTextArea.class) {
            //pega o método set referenciado na variável sm e invoca o método getText do TextField
            setMethod.invoke(obj, CastFactory.cast((method.invoke(form).getClass().getMethod("getText")).invoke(method.invoke(form)), map.typeParameter()));
        } else if (method.getReturnType() == JPasswordField.class) {
            setMethod.invoke(obj, CastFactory.cast(String.valueOf(method.invoke(form).getClass().getMethod("getPassword").invoke(method.invoke(form))), map.typeParameter()));
        } else if (method.getReturnType() == JComboBox.class) {
            String metodo = null;
            if (map.typeParameter() == String.class) {
                metodo = "getSelectedItem";
            } else if (map.typeParameter() == Integer.class) {
                metodo = "getSelectedIndex";
            }
            setMethod.invoke(obj, (method.invoke(form).getClass().getMethod(metodo)).invoke(method.invoke(form)));
        } else if (method.getReturnType() == JCheckBox.class) {
            setMethod.invoke(obj, (method.invoke(form).getClass().getMethod("isSelected")).invoke(method.invoke(form)));
        } else if (method.getReturnType() == ButtonGroup.class) {
            Enumeration<AbstractButton> e = (Enumeration<AbstractButton>) (method.invoke(form).getClass().getMethod("getElements").invoke(method.invoke(form)));
            while (e.hasMoreElements()) {
                AbstractButton ab = e.nextElement();
                if (ab.isSelected()) {
                    setMethod.invoke(obj, ab.getText());
                    break;
                }
            }
        } else if (method.getReturnType() == JTable.class) {
            TableModelDefaultAdapter model = (TableModelDefaultAdapter) method.invoke(form).getClass().getMethod("getModel").invoke(method.invoke(form));
            setMethod.invoke(obj, model.clonar());
        }
    }

    private void getValuesFieldsForm(JFrame form, Object obj, Method method, Method getMethod, MapFrameField map) {
        try {
            if (method.getReturnType() == JTextFieldCBI.class | method.getReturnType() == JTextField.class | method.getReturnType() == JTextArea.class) {
                method.invoke(form).getClass().getMethod("setText", String.class).invoke(method.invoke(form), CastFactory.cast(getMethod.invoke(obj), String.class));
            } else if (method.getReturnType() == JPasswordField.class) {
                method.invoke(form).getClass().getMethod("setText", String.class).invoke(method.invoke(form), CastFactory.cast(getMethod.invoke(obj), String.class));
            } else if (method.getReturnType() == JComboBox.class) {
                if (map.typeParameter() == String.class) {
                    method.invoke(form).getClass().getMethod("setSelectedItem", Object.class).invoke(method.invoke(form), CastFactory.cast(getMethod.invoke(obj), Object.class));
                } else if (map.typeParameter() == Integer.class) {
                    method.invoke(form).getClass().getMethod("setSelectedIndex", int.class).invoke(method.invoke(form), getMethod.invoke(obj));
                }
            } else if (method.getReturnType() == JCheckBox.class) {
                method.invoke(form).getClass().getMethod("setSelected", map.typeParameter()).invoke(method.invoke(form), getMethod.invoke(obj));
            } else if (method.getReturnType() == ButtonGroup.class) {
                Enumeration<AbstractButton> e = (Enumeration<AbstractButton>) (method.invoke(form).getClass().getMethod("getElements").invoke(method.invoke(form)));
                while (e.hasMoreElements()) {
                    AbstractButton ab = e.nextElement();
                    if (Objects.equals(getMethod.invoke(obj), ab.getText())) {
                        ab.setSelected(true);
                    } else {
                        ab.setSelected(false);
                    }
                }
            } else if (method.getReturnType() == JTable.class) {
                TableModelDefaultAdapter model = (TableModelDefaultAdapter) method.invoke(form).getClass().getMethod("getModel").invoke(method.invoke(form));
                model.deletarLista();
                model.addLista((List<?>) getMethod.invoke(obj));
            }
        } catch (Exception e) {
        }
    }
}
