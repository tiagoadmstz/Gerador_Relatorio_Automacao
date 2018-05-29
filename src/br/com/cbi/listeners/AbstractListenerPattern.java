/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.annotations.MapFrameField;
import br.com.cbi.beans.JTextFieldCBI;
import br.com.cbi.beans.PesquisaDefaultForm;
import br.com.cbi.dal.EntityManagerHelper;
import br.com.cbi.msg.MessageFactory;
import br.com.cbi.tablemodel.DefaultCBIHeaderRenderer;
import br.com.cbi.tablemodel.TableModelDefaultAdapter;
import br.com.cbi.util.CastFactory;
import br.com.cbi.util.ControleInstancias;
import br.com.cbi.util.Datas;
import br.com.cbi.util.ManipulaBean;
import br.com.cbi.util.ManipulaFrames;
import br.com.cbi.util.ScrollPaneUtil;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyListener;

/**
 *
 * @author tiago.teixeira
 * @param <T>
 */
public abstract class AbstractListenerPattern<T> implements ActionListener, FocusListener, MouseListener, CaretListener, ItemListener, KeyListener {

    protected final T form;
    public final int SALVAR = 1, ALTERAR = 2, EXCLUIR = 3;

    public AbstractListenerPattern(T form) {
        this.form = form;
    }

    protected abstract void initComponents();

    protected void attachListener() {
        ((ManipulaFrames) form).getListMenus().ifPresent(list -> list.forEach(comp -> ((AbstractButton) comp).addActionListener(this)));
    }

    protected abstract void addModel();

    protected void fecharESC(JMenuItem menuItem) {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        menuItem.setAccelerator(escape);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((JFrame) form).setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (e.getActionCommand()) {
            case "novo":
                novo();
                break;
            case "cancelar":
                cancelar();
                break;
            case "editar":
                editar();
                break;
            case "salvar":
                salvar();
                break;
            case "alterar":
                alterar();
                break;
            case "imprimir":
                imprimir();
                break;
            case "deletar":
                deletar();
                break;
            case "pesquisar":
                pesquisa();
                break;
            case "fechar":
                ((ManipulaFrames) form).fechar();
                break;
        }
        ((JFrame) form).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    protected void novo() {
        ((ManipulaFrames) form).novo();
    }

    protected void cancelar() {
        ((ManipulaFrames) form).cancelar();
    }

    protected void salvar() {

    }

    protected void salvar(ManipulaBean object, EntityManagerHelper emh) {
        if (MessageFactory.getQuestionMessage(MessageFactory.SALVAR, ((ManipulaFrames) form))) {
            object.clear();
            ((ManipulaFrames) form).getObject(object);
            if (((ManipulaFrames) form).validaCampos(object)) {
                boolean result = emh.getOperation(EntityManagerHelper.OPERATION_TYPE.SAVE, object, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
                ((ManipulaFrames) form).getListPaineis().get().forEach(pl -> ((ManipulaFrames) form).enableDisableComponentJFrame(ManipulaFrames.SALVAR, pl.getComponents()));
                ((ManipulaFrames) form).operacaoEnableOrder(ManipulaFrames.SALVAR, ((ManipulaFrames) form).getListMenus().get());
                MessageFactory.getPersistenceMessage(MessageFactory.SALVAR, result, ((ManipulaFrames) form));
            } else {
                MessageFactory.getAppMessage(MessageFactory.PREENCHIMENTO_OBRIGATORIO, ((ManipulaFrames) form));
            }
        }
    }

    protected void alterar() {

    }

    protected void alterar(ManipulaBean object, EntityManagerHelper emh) {
        if (MessageFactory.getQuestionMessage(MessageFactory.ALTERAR, ((ManipulaFrames) form))) {
            ((ManipulaFrames) form).getObject(object);
            if (((ManipulaFrames) form).validaCampos(object)) {
                boolean result = emh.getOperation(EntityManagerHelper.OPERATION_TYPE.UPDATE, object, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
                ((ManipulaFrames) form).getListPaineis().get().forEach(pl -> ((ManipulaFrames) form).enableDisableComponentJFrame(ManipulaFrames.ALTERAR, pl.getComponents()));
                ((ManipulaFrames) form).operacaoEnableOrder(ManipulaFrames.ALTERAR, ((ManipulaFrames) form).getListMenus().get());
                MessageFactory.getPersistenceMessage(MessageFactory.ALTERAR, result, ((ManipulaFrames) form));
            } else {
                MessageFactory.getAppMessage(MessageFactory.PREENCHIMENTO_OBRIGATORIO, ((ManipulaFrames) form));
            }
        }
    }

    protected void editar() {
        ((ManipulaFrames) form).editar();
    }

    protected void deletar() {

    }

    protected void deletar(ManipulaBean object, EntityManagerHelper emh) {
        if (MessageFactory.getQuestionMessage(MessageFactory.DELETAR, ((ManipulaFrames) form))) {
            ((ManipulaFrames) form).getObject(object);
            boolean result = emh.getOperation(EntityManagerHelper.OPERATION_TYPE.DELETE, object, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU);
            ((ManipulaFrames) form).getListPaineis().get().forEach(pl -> ((ManipulaFrames) form).enableDisableComponentJFrame(ManipulaFrames.DELETAR, pl.getComponents()));
            ((ManipulaFrames) form).operacaoEnableOrder(ManipulaFrames.DELETAR, ((ManipulaFrames) form).getListMenus().get());
            MessageFactory.getPersistenceMessage(MessageFactory.DELETAR, result, ((ManipulaFrames) form));
        }
    }

    protected void imprimir() {
    }

    public abstract void copiarObject(Object object);
    
    public void setMenuEditarOpcoes(){
        ((ManipulaFrames) form).setMenuEditarOpcoes();
    }
    
    protected void carregarListas() {
    }

    /**
     * Pega um objeto enviado de outro formulário e inclui no objeto local
     *
     * @param object = Entidade controlada pelo painel
     */
    public abstract void setDados(Object object);

    /**
     * Pega os dados do formulário e coloca no modelo de objeto EX:
     * produto.setQuantidade(form.getTxtQuantidade.getText());
     */
    public abstract void setDados();

    /**
     * Este método utiliza recursos de refletion com annotations para recuperar
     * dados dos formulários dinâmicamente
     *
     * @param form Formulário que será trabalhado
     * @param obj Objeto que é representado pelo formulário
     */
    public void setDados(JFrame form, Object obj) {
        //pega todos os métodos presentes dentro do form
        for (Method method : form.getClass().getMethods()) {
            //verifica se o método está anotado
            if (method.isAnnotationPresent(MapFrameField.class)) {
                try {
                    //pega a classe de mapeamento
                    MapFrameField map = method.getAnnotation(MapFrameField.class);
                    //pega o metodo
                    Method setMethod = this.getMethods(obj, "set", map);
                    //adicona os valores nos campos do form
                    this.setValuesFieldsForm(form, obj, method, setMethod, map);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } //fim try catch
            } // fim if annotation
        } // fim for
    }

    private Method getMethods(Object obj, String prefix, MapFrameField map) throws Exception {
        Method method = null;
        //transforma o nome do campo no método set do campo
        String fieldMethod = map.referencedField().replaceFirst("^(\\w{1})", map.referencedField().substring(0, 1).toUpperCase());
        //pega o método set da classe alvo
        try {
            switch (prefix) {
                case "set":
                    method = obj.getClass().getMethod(prefix.concat(fieldMethod), map.typeParameter());
                    return method;
                case "get":
                    if (Objects.equals(Boolean.class, map.typeParameter()) | Objects.equals(boolean.class, map.typeParameter())) {
                        method = obj.getClass().getMethod("is".concat(map.referencedField()));
                        return method;
                    } else {
                        method = obj.getClass().getMethod(prefix.concat(map.referencedField()));
                        return method;
                    }
            }
        } catch (Exception ex) {
            if (Objects.equals(prefix, "set")) {
                method = obj.getClass().getMethod(prefix.concat(fieldMethod), CastFactory.castReference(map.typeParameter()));
            }
        }
        return method;
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

    /**
     * Pega os dados de um objeto e mostra nos campos do formulário EX:
     * form.getTxtQuantidade().setText(produto.getQuantidade());
     */
    public abstract void getDados();

    /**
     * Este método popula o formulário de forma dinamica utilizando recursos de
     * refletion com annotations
     *
     * @param form Formulário que será trabalhado
     * @param objeto Objeto representado pelo formulário
     */
    public void getDados(JFrame form, Object objeto) {
        for (Method mt : form.getClass().getMethods()) { //pega todos os metodos do formulário
            if (mt.isAnnotationPresent(MapFrameField.class)) { //verifica se são do tipo SETTER
                MapFrameField map = mt.getAnnotation(MapFrameField.class); //recupera referencia da anotação
                try {
                    //pega os métodos
                    Method getMethod = this.getMethods(objeto, "get", map);
                    //pega o método set referenciado na variável sm e invoca o método getText do TextField
                    this.getValuesFieldsForm(form, objeto, mt, getMethod, map);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public abstract void setEnableButtons(int codFunction);

    protected void pesquisa() {

    }

    /**
     * Este método retorna um formulário de pesquisa padrão simplificado para
     * pesquisa rápidas
     *
     * @param titulo Título do formulário que será mostrado para o usuário
     * @param model Modelo da tabela contendo os dados de pesquisa
     * @param modelSolicitante Modelo da tabela onde os dados serão inseridos
     * caso exista, caso não informe 'null'
     * @param listenerSolicitante Listener do formulário solicitante onde serão
     * inseridos os dados da pesquisa, caso não informe 'null'
     * @param tamanho
     * @return Retorna um formulário de pesquisa simplificada. Será necessário
     * fazer o setVisible
     */
    public PesquisaDefaultForm pesquisar(String titulo, TableModelDefaultAdapter model, TableModelDefaultAdapter modelSolicitante, AbstractListenerPattern listenerSolicitante, int... tamanho) {
        PesquisaDefaultForm pesquisa = (PesquisaDefaultForm) ControleInstancias.getInstance(PesquisaDefaultForm.class.getName());
        if (pesquisa == null) {
            pesquisa = new PesquisaDefaultForm(titulo, model, listenerSolicitante, modelSolicitante, tamanho);
            ControleInstancias.setControleInstancias(PesquisaDefaultForm.class.getName(), pesquisa);
        }
        return pesquisa;
    }

    public void setEnableButtons(int codigoOperacao, List<JComponent> componentes) {
        ((ManipulaFrames) form).operacaoEnableOrder(codigoOperacao, componentes);
    }

    public void setScrollPanelConfig(JScrollPane scrollPane) {
        ScrollPaneUtil.scrollPanelConfigurator(scrollPane);
    }

    public void setColumnDesign(JTable table, DefaultTableCellRenderer tableRenderer) {
        //rederização das colunas
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnClass(i) != Boolean.class) {
                table.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
            }
        }
    }

    public void setColumnFilter(JTable table, TableRowSorter rowSorter) {
        int columns = table.getModel().getColumnCount();
        for (int i = 0; i < columns; i++) {
            rowSorter.setSortable(i, false);
            table.getColumnModel().getColumn(i).setHeaderRenderer(new DefaultCBIHeaderRenderer(rowSorter));
        }
    }

    public void addColumnComboBox(JTable table, int colunmIndex, JComboBox comboBox) {
        table.getColumnModel().getColumn(colunmIndex).setCellEditor(new DefaultCellEditor(comboBox));
    }

    public void setColumnSize(JTable table, int... tamanho) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(tamanho[i]);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        OnChangeListener.EventListener(e);
    }

    /**
     * Este método é utilizado como padrão para campos de data e hora protegendo
     * o campo contra entradas inválidas e formatando a data e a hora informados
     * pelo usuário para um formato padrão, para utilizar este método basta
     * colocar o nome do campo de data ou hora e adicionar um FocusListener
     * passar super.focusLost(e) na sobreescrita do método se existir, caso
     * contrário será utilizado o método padrão.
     *
     * @param e FocusEvent passado pelo campo
     */
    @Override
    public void focusLost(FocusEvent e) {
        OnChangeListener.EventListener(e);
        if (OnChangeListener.getChangeEvent() == true) {
            if (e.getSource() instanceof JTextField || e.getSource() instanceof JTextFieldCBI) {
                JTextField textField = (JTextField) e.getComponent();
                if (!"".equals(textField.getText())) {
                    switch (textField.getName()) {
                        case "data":
                            textField.setText(Datas.getDateString(textField.getText(), 1));
                            break;
                        case "hora":
                            textField.setText(Datas.getHour(textField.getText(), 1));
                            break;
                        case "valor":
                            MaskFormatter mask = new MaskFormatter();
                            String text = textField.getText().replace(",", ".");
                            if (!text.contains(".")) {
                                text = text.concat(".00");
                            } else if (text.contains(".")) {
                                String sufixo = text.substring(text.indexOf(".") + 1, text.length());
                                if (sufixo.length() < 2) {
                                    text = text.concat(sufixo.length() == 1 ? "0" : "00");
                                } else if (sufixo.length() > 2) {
                                    text = text.replace("." + sufixo, "." + sufixo.substring(0, 2));
                                }
                            }
                            text = text.contains("R$") ? text : "R$ ".concat(text);
                            textField.setText(text);
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
