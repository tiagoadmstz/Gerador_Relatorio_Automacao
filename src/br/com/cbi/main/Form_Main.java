/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.main;

import br.com.cbi.annotations.MapFrameField;
import br.com.cbi.beans.MenuBarCbiDefault;
import br.com.cbi.dal.AlgoritimosDB;
import br.com.cbi.entities.Atendimento;
import br.com.cbi.listeners.Listener_Main;
import br.com.cbi.util.ManipulaFrames;
import br.com.cbi.util.Utilidades;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tiago
 */
public final class Form_Main extends ManipulaFrames {

    private static final long serialVersionUID = -7054545364563752986L;
    private final Listener_Main listener;
    private Atendimento atendimento = new Atendimento();

    /**
     * Creates new form MainForm
     */
    public Form_Main() {
        Utilidades.mudaLookAndFeel(3);
        setImageIcon();
        initComponents();
        listener = new Listener_Main(this);
        //startDB();
    }

    public void startDB() {
        AlgoritimosDB.algoritimosDB();
        AlgoritimosDB.iniciarServidor();
    }

    @Override
    public Optional<List<JPanel>> getListPaineis(){
        return Optional.ofNullable(Arrays.asList(painel_Atendimento, painel_Defeito, painel_Pecas, painel_Jornada));
    }

    @Override
    public Optional<List<JComponent>> getListMenus() {
        return Optional.ofNullable(menuBarCbiDefault.getListMenuItens());
    }
    
    public MenuBarCbiDefault getMenuBarCbiDefault() {
        return menuBarCbiDefault;
    }

    @MapFrameField(referencedField = "clienteFinal", typeReference = String.class)
    public JTextField getTxtCliente() {
        return txtCliente;
    }

    @MapFrameField(referencedField = "nomeAutorizada", typeReference = String.class)
    public JComboBox<String> getCbAutorizada() {
        return cbAutorizada;
    }

    @MapFrameField(referencedField = "condicoesFuncionamento", typeReference = String.class)
    public JComboBox<String> getCbCondicaoFuncionamento() {
        return cbCondicaoFuncionamento;
    }

    @MapFrameField(referencedField = "nomeTecnico", typeReference = String.class)
    public JComboBox<String> getCbTecnico() {
        return cbTecnico;
    }

    @MapFrameField(referencedField = "tipoAtendimento", typeReference = String.class)
    public JComboBox<String> getCbTipoAtendimento() {
        return cbTipoAtendimento;
    }

    @MapFrameField(referencedField = "defeitoReclamado", typeReference = String.class)
    public JTextArea getTaDefeitoReclamado() {
        return taDefeitoReclamado;
    }

    @MapFrameField(referencedField = "bairro", typeReference = String.class)
    public JTextField getTxtBairro() {
        return txtBairro;
    }

    @MapFrameField(referencedField = "celular", typeReference = String.class)
    public JTextField getTxtCelular() {
        return txtCelular;
    }

    @MapFrameField(referencedField = "cidade", typeReference = String.class)
    public JTextField getTxtCidade() {
        return txtCidade;
    }

    //@MapFrameField(referencedField = "dataAtendimento", typeReference = LocalDateTime.class)
    public JTextField getTxtData() {
        return txtData;
    }

    @MapFrameField(referencedField = "email", typeReference = String.class)
    public JTextField getTxtEmail() {
        return txtEmail;
    }

    @MapFrameField(referencedField = "endereco", typeReference = String.class)
    public JTextField getTxtEndereco() {
        return txtEndereco;
    }

    @MapFrameField(referencedField = "equipamento", typeReference = String.class)
    public JTextField getTxtEquipamento() {
        return txtEquipamento;
    }

    @MapFrameField(referencedField = "estado", typeReference = String.class)
    public JTextField getTxtEstado() {
        return txtEstado;
    }

    //@MapFrameField(referencedField = "id", typeReference = Long.class)
    public JTextField getTxtId() {
        return txtId;
    }

    @MapFrameField(referencedField = "kmRodado", typeReference = String.class)
    public JTextField getTxtKmRodado() {
        return txtKmRodado;
    }

    @MapFrameField(referencedField = "localAtendimento", typeReference = String.class)
    public JTextField getTxtLocal() {
        return txtLocal;
    }

    @MapFrameField(referencedField = "nomeCliente", typeReference = String.class)
    public JTextField getTxtNomeCliente() {
        return txtNomeCliente;
    }

    @MapFrameField(referencedField = "numero", typeReference = String.class)
    public JTextField getTxtNumero() {
        return txtNumero;
    }

    @MapFrameField(referencedField = "numeroRelatorio", typeReference = String.class)
    public JTextField getTxtNumeroRelaorio() {
        return txtNumeroRelaorio;
    }

    @MapFrameField(referencedField = "numeroPW", typeReference = String.class)
    public JTextField getTxtPW() {
        return txtPW;
    }

    @MapFrameField(referencedField = "produto", typeReference = String.class)
    public JTextField getTxtProduto() {
        return txtProduto;
    }

    @MapFrameField(referencedField = "numeroSerie", typeReference = String.class)
    public JTextField getTxtSerie() {
        return txtSerie;
    }

    @MapFrameField(referencedField = "telefone", typeReference = String.class)
    public JTextField getTxtTelefone() {
        return txtTelefone;
    }

    public JTable getTbJornada() {
        return tbJornada;
    }

    public JTable getTbPecas() {
        return tbPecas;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel_Guias = new javax.swing.JTabbedPane();
        painel_Atendimento = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        lbRelatorio = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbNomeAutorizada = new javax.swing.JLabel();
        lbClienteFinal = new javax.swing.JLabel();
        lbEndereco = new javax.swing.JLabel();
        lbN = new javax.swing.JLabel();
        lbBairro = new javax.swing.JLabel();
        lbCidade = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbTelefone = new javax.swing.JLabel();
        lbCelular = new javax.swing.JLabel();
        lbProduto = new javax.swing.JLabel();
        lbEquipamento = new javax.swing.JLabel();
        lbPw = new javax.swing.JLabel();
        lbNSerie = new javax.swing.JLabel();
        lbLocal = new javax.swing.JLabel();
        lbKmRodado = new javax.swing.JLabel();
        lbNomeCliente = new javax.swing.JLabel();
        lbTipoAtendimento = new javax.swing.JLabel();
        lbNomeTecnico = new javax.swing.JLabel();
        lbCondFuncionamento = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNumeroRelaorio = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        cbAutorizada = new javax.swing.JComboBox<>();
        txtCliente = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtProduto = new javax.swing.JTextField();
        txtEquipamento = new javax.swing.JTextField();
        txtPW = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtLocal = new javax.swing.JTextField();
        txtKmRodado = new javax.swing.JTextField();
        txtNomeCliente = new javax.swing.JTextField();
        cbTipoAtendimento = new javax.swing.JComboBox<>();
        cbTecnico = new javax.swing.JComboBox<>();
        cbCondicaoFuncionamento = new javax.swing.JComboBox<>();
        buttonsPanel = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        painel_Defeito = new javax.swing.JPanel();
        defeitoScrollPane = new javax.swing.JScrollPane();
        taDefeitoReclamado = new javax.swing.JTextArea();
        painel_Pecas = new javax.swing.JPanel();
        btPlus = new javax.swing.JButton();
        btNegativeButton = new javax.swing.JButton();
        pecasScrollPane = new javax.swing.JScrollPane();
        tbPecas = new javax.swing.JTable();
        painel_Jornada = new javax.swing.JPanel();
        btPlus2 = new javax.swing.JButton();
        btNegative2 = new javax.swing.JButton();
        jornadaScrollPane = new javax.swing.JScrollPane();
        tbJornada = new javax.swing.JTable();
        menuBarCbiDefault = new br.com.cbi.beans.MenuBarCbiDefault();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painel_Guias.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        painel_Guias.setToolTipText("");
        painel_Guias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbId.setText("ID");

        lbRelatorio.setText("Relatório Nº");

        lbData.setText("Data");

        lbNomeAutorizada.setText("Nome Autorizada");

        lbClienteFinal.setText("Cliente Final");

        lbEndereco.setText("Endereço");

        lbN.setText("Nº");

        lbBairro.setText("Bairro");

        lbCidade.setText("Cidade");

        lbEstado.setText("Estado");

        lbEmail.setText("E-mail");

        lbTelefone.setText("Telefone");

        lbCelular.setText("Celular");

        lbProduto.setText("Produto");

        lbEquipamento.setText("Equipamento");

        lbPw.setText("Nº PW");

        lbNSerie.setText("Nº Série");

        lbLocal.setText("Local");

        lbKmRodado.setText("Km Rodado");

        lbNomeCliente.setText("Nome do Cliente");

        lbTipoAtendimento.setText("Tipo de Atendimento");

        lbNomeTecnico.setText("Nome do Técnico");

        lbCondFuncionamento.setText("Cond. de Funcionamento");

        txtId.setBackground(new java.awt.Color(204, 255, 255));
        txtId.setEnabled(false);

        txtNumeroRelaorio.setEnabled(false);

        txtData.setEnabled(false);

        cbAutorizada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TESE COMERCIAL ELÉTRICA LTDA -EPP", "TESE RIBEIRÃO PRETO MOTORES ELÉTRICOS LTDA" }));
        cbAutorizada.setEnabled(false);

        txtCliente.setEnabled(false);

        txtEndereco.setEnabled(false);

        txtNumero.setEnabled(false);

        txtBairro.setEnabled(false);

        txtCidade.setEnabled(false);

        txtEstado.setEnabled(false);

        txtEmail.setEnabled(false);

        txtTelefone.setEnabled(false);

        txtCelular.setEnabled(false);

        txtProduto.setEnabled(false);

        txtEquipamento.setEnabled(false);

        txtPW.setEnabled(false);

        txtSerie.setEnabled(false);

        txtLocal.setEnabled(false);

        txtKmRodado.setEnabled(false);

        txtNomeCliente.setEnabled(false);

        cbTipoAtendimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asstec" }));
        cbTipoAtendimento.setEnabled(false);

        cbTecnico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fernando Henrique Ferreira" }));
        cbTecnico.setEnabled(false);

        cbCondicaoFuncionamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sem Condições", "Precário", "Com Pendências", "Pleno" }));
        cbCondicaoFuncionamento.setSelectedIndex(3);
        cbCondicaoFuncionamento.setEnabled(false);

        btNovo.setText("Novo");
        btNovo.setActionCommand("novo");
        btNovo.setPreferredSize(new java.awt.Dimension(75, 23));
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.setActionCommand("cancelar");
        btCancelar.setEnabled(false);

        btAlterar.setText("Alterar");
        btAlterar.setActionCommand("alterar");
        btAlterar.setEnabled(false);
        btAlterar.setPreferredSize(new java.awt.Dimension(75, 23));

        btFechar.setText("Fechar");
        btFechar.setActionCommand("fechar");
        btFechar.setPreferredSize(new java.awt.Dimension(75, 23));

        btImprimir.setText("Imprimir");
        btImprimir.setActionCommand("imprimir");
        btImprimir.setEnabled(false);
        btImprimir.setPreferredSize(new java.awt.Dimension(75, 23));

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelar)
                    .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painel_AtendimentoLayout = new javax.swing.GroupLayout(painel_Atendimento);
        painel_Atendimento.setLayout(painel_AtendimentoLayout);
        painel_AtendimentoLayout.setHorizontalGroup(
            painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbClienteFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbProduto)
                            .addComponent(lbPw, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliente)
                            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEndereco)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel_AtendimentoLayout.createSequentialGroup()
                                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTelefone)
                                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                            .addComponent(txtEstado)))
                                    .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                        .addComponent(lbCelular)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCelular))))
                            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPW, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEquipamento)
                                    .addComponent(txtSerie)))
                            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNomeAutorizada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAutorizada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNomeTecnico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(txtNomeCliente)
                            .addComponent(cbTecnico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCondFuncionamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbKmRodado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTipoAtendimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCondicaoFuncionamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTipoAtendimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKmRodado)))
                    .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroRelaorio, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painel_AtendimentoLayout.setVerticalGroup(
            painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_AtendimentoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRelatorio)
                    .addComponent(txtNumeroRelaorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbData)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNomeAutorizada)
                    .addComponent(cbAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbClienteFinal)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEndereco)
                    .addComponent(lbN)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBairro)
                    .addComponent(lbCidade)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEstado)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail)
                    .addComponent(lbTelefone)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCelular)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbProduto)
                    .addComponent(lbEquipamento)
                    .addComponent(txtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPw)
                    .addComponent(txtPW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNSerie)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLocal)
                    .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKmRodado)
                    .addComponent(txtKmRodado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeCliente)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoAtendimento)
                    .addComponent(cbTipoAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_AtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeTecnico)
                    .addComponent(cbTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCondFuncionamento)
                    .addComponent(cbCondicaoFuncionamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painel_Guias.addTab("Dados do Atendimento", painel_Atendimento);

        defeitoScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        defeitoScrollPane.setToolTipText("");

        taDefeitoReclamado.setColumns(10);
        taDefeitoReclamado.setLineWrap(true);
        taDefeitoReclamado.setRows(5);
        taDefeitoReclamado.setEnabled(false);
        defeitoScrollPane.setViewportView(taDefeitoReclamado);

        javax.swing.GroupLayout painel_DefeitoLayout = new javax.swing.GroupLayout(painel_Defeito);
        painel_Defeito.setLayout(painel_DefeitoLayout);
        painel_DefeitoLayout.setHorizontalGroup(
            painel_DefeitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(defeitoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        );
        painel_DefeitoLayout.setVerticalGroup(
            painel_DefeitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(defeitoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        painel_Guias.addTab("Defeito Reclamado", painel_Defeito);

        btPlus.setText("+");
        btPlus.setActionCommand("plus");

        btNegativeButton.setText("-");
        btNegativeButton.setActionCommand("negative");

        tbPecas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPecas.setEnabled(false);
        pecasScrollPane.setViewportView(tbPecas);

        javax.swing.GroupLayout painel_PecasLayout = new javax.swing.GroupLayout(painel_Pecas);
        painel_Pecas.setLayout(painel_PecasLayout);
        painel_PecasLayout.setHorizontalGroup(
            painel_PecasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_PecasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_PecasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pecasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addGroup(painel_PecasLayout.createSequentialGroup()
                        .addComponent(btPlus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNegativeButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painel_PecasLayout.setVerticalGroup(
            painel_PecasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_PecasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_PecasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPlus)
                    .addComponent(btNegativeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pecasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        painel_Guias.addTab("Peças Utilizadas", painel_Pecas);

        btPlus2.setText("+");
        btPlus2.setActionCommand("plus2");

        btNegative2.setText("-");
        btNegative2.setActionCommand("negative2");

        tbJornada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbJornada.setEnabled(false);
        jornadaScrollPane.setViewportView(tbJornada);

        javax.swing.GroupLayout painel_JornadaLayout = new javax.swing.GroupLayout(painel_Jornada);
        painel_Jornada.setLayout(painel_JornadaLayout);
        painel_JornadaLayout.setHorizontalGroup(
            painel_JornadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_JornadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_JornadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jornadaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addGroup(painel_JornadaLayout.createSequentialGroup()
                        .addComponent(btPlus2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNegative2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painel_JornadaLayout.setVerticalGroup(
            painel_JornadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_JornadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_JornadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPlus2)
                    .addComponent(btNegative2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jornadaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        painel_Guias.addTab("Jornada", painel_Jornada);

        setJMenuBar(menuBarCbiDefault);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painel_Guias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(painel_Guias, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        txtNumeroRelaorio.setText("01/STZ");
        txtData.setText("01/10/2017");
        txtCliente.setText("TIAGO DANIEL TEIXEIRA");
        txtEndereco.setText("RUA ANTONIO TETEU FURLANETO");
        txtNumero.setText("2109");
        txtBairro.setText("JD. PORTO SEGURO");
        txtCidade.setText("SERTÃOZINHO");
        txtEstado.setText("SÃO PAULO");
        txtEmail.setText("tiagoadmstz@yahoo.com.br");
        txtTelefone.setText("(16) 99295-2244");
        txtCelular.setText("(16) 99295-2244");
        txtProduto.setText("INVERSOR");
        txtEquipamento.setText("CFW090180T3848PSZ");
        txtPW.setText("123456789");
        txtSerie.setText("123456789");
        txtLocal.setText("US. BELA VISTA");
        txtKmRodado.setText("234");
        txtNomeCliente.setText("TIAGO");
        taDefeitoReclamado.setText("TESTE");
    }//GEN-LAST:event_btNovoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btNegative2;
    private javax.swing.JButton btNegativeButton;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPlus;
    private javax.swing.JButton btPlus2;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox<String> cbAutorizada;
    private javax.swing.JComboBox<String> cbCondicaoFuncionamento;
    private javax.swing.JComboBox<String> cbTecnico;
    private javax.swing.JComboBox<String> cbTipoAtendimento;
    private javax.swing.JScrollPane defeitoScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane jornadaScrollPane;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbClienteFinal;
    private javax.swing.JLabel lbCondFuncionamento;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbEquipamento;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbKmRodado;
    private javax.swing.JLabel lbLocal;
    private javax.swing.JLabel lbN;
    private javax.swing.JLabel lbNSerie;
    private javax.swing.JLabel lbNomeAutorizada;
    private javax.swing.JLabel lbNomeCliente;
    private javax.swing.JLabel lbNomeTecnico;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbPw;
    private javax.swing.JLabel lbRelatorio;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbTipoAtendimento;
    private br.com.cbi.beans.MenuBarCbiDefault menuBarCbiDefault;
    private javax.swing.JPanel painel_Atendimento;
    private javax.swing.JPanel painel_Defeito;
    private javax.swing.JTabbedPane painel_Guias;
    private javax.swing.JPanel painel_Jornada;
    private javax.swing.JPanel painel_Pecas;
    private javax.swing.JScrollPane pecasScrollPane;
    private javax.swing.JTextArea taDefeitoReclamado;
    private javax.swing.JTable tbJornada;
    private javax.swing.JTable tbPecas;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEquipamento;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKmRodado;
    private javax.swing.JTextField txtLocal;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroRelaorio;
    private javax.swing.JTextField txtPW;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
