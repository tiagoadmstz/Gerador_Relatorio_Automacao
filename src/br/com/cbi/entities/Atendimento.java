/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.cbi.converters.LocalDateTimeConverter;
import br.com.cbi.util.ManipulaBean;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author Tiago D. Teixeira
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "ATENDIMENTOS")
@Indexes(value = {
    @Index(schema = "ROOT", name = "idx_id", columnNames = {"id"})
})
@NamedQueries(value = {
    @NamedQuery(name = "atendimento.findAll", query = "SELECT at FROM Atendimento AS at")
})
@SequenceGenerator(name = "at_seq", sequenceName = "seq_at", initialValue = 1, allocationSize = 1)
public class Atendimento extends ManipulaBean<Atendimento> implements Serializable {

    private static final long serialVersionUID = -6492303563811529902L;

    private Long id;
    private String numeroRelatorio;
    private String nomeAutorizada;
    private String clienteFinal;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String email;
    private String telefone;
    private String celular;
    private String produto;
    private String numeroPW;
    private String equipamento;
    private String numeroSerie;
    private String kmRodado;
    private String defeitoReclamado;
    private String localAtendimento;
    private LocalDateTime dataAtendimento;
    private String nomeCliente;
    private String nomeTecnico;
    private String condicoesFuncionamento;
    private String conteudoRelatorio;
    private List<Jornada> jornada;
    private List<Peca> pecas;

    public Atendimento() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "at_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NUMERO_RELATORIO", nullable = true, length = 100)
    public String getNumeroRelatorio() {
        return numeroRelatorio;
    }

    public void setNumeroRelatorio(String numeroRelatorio) {
        this.numeroRelatorio = numeroRelatorio;
    }

    @Column(name = "NOME_AUTORIZADA", nullable = true, length = 255)
    public String getNomeAutorizada() {
        return nomeAutorizada;
    }

    public void setNomeAutorizada(String nomeAutorizada) {
        this.nomeAutorizada = nomeAutorizada;
    }

    @Column(name = "CLIENTE_FINAL", nullable = true, length = 255)
    public String getClienteFinal() {
        return clienteFinal;
    }

    public void setClienteFinal(String clienteFinal) {
        this.clienteFinal = clienteFinal;
    }

    @Column(name = "ENDERECO", nullable = true, length = 255)
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Column(name = "NUMERO", nullable = true, length = 100)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "BAIRRO", nullable = true, length = 150)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "CIDADE", nullable = true, length = 100)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Column(name = "ESTADO", nullable = true, length = 100)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "EMAIL", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "TELEFONE", nullable = true, length = 100)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name = "CELULAR", nullable = true, length = 100)
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Column(name = "PRODUTO", nullable = true, length = 255)
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    @Column(name = "NUMERO_PW", nullable = true, length = 100)
    public String getNumeroPW() {
        return numeroPW;
    }

    public void setNumeroPW(String numeroPW) {
        this.numeroPW = numeroPW;
    }

    @Column(name = "EQUIPAMENTO", nullable = true, length = 255)
    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    @Column(name = "NUMERO_SERIE", nullable = true, length = 100)
    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Column(name = "KM_RODADO", nullable = true, length = 100)
    public String getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(String kmRodado) {
        this.kmRodado = kmRodado;
    }

    @Column(name = "DEFEITO_RECLAMADO")
    public String getDefeitoReclamado() {
        return defeitoReclamado;
    }

    public void setDefeitoReclamado(String defeitoReclamado) {
        this.defeitoReclamado = defeitoReclamado;
    }

    @Column(name = "LOCAL_ATENDIMENTO", nullable = true, length = 100)
    public String getLocalAtendimento() {
        return localAtendimento;
    }

    public void setLocalAtendimento(String localAtendimento) {
        this.localAtendimento = localAtendimento;
    }

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_ATENDIMENTO", nullable = true)
    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    @Column(name = "NOME_CLIENTE", nullable = true, length = 255)
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Column(name = "NOME_TECNICO", nullable = true, length = 255)
    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    @Column(name = "CONDICOES_FUNCIONAMENTO", nullable = true, length = 50)
    public String getCondicoesFuncionamento() {
        return condicoesFuncionamento;
    }

    public void setCondicoesFuncionamento(String condicoesFuncionamento) {
        this.condicoesFuncionamento = condicoesFuncionamento;
    }

    @Column(name = "CONTEUDO_RELATORIO")
    public String getConteudoRelatorio() {
        return conteudoRelatorio;
    }

    public void setConteudoRelatorio(String conteudoRelatorio) {
        this.conteudoRelatorio = conteudoRelatorio;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ATENDIMENTO")
    public List<Jornada> getJornada() {
        return jornada;
    }

    public void setJornada(List<Jornada> jornada) {
        this.jornada = jornada;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ATENDIMENTO")
    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.numeroRelatorio);
        hash = 11 * hash + Objects.hashCode(this.nomeAutorizada);
        hash = 11 * hash + Objects.hashCode(this.clienteFinal);
        hash = 11 * hash + Objects.hashCode(this.endereco);
        hash = 11 * hash + Objects.hashCode(this.numero);
        hash = 11 * hash + Objects.hashCode(this.bairro);
        hash = 11 * hash + Objects.hashCode(this.cidade);
        hash = 11 * hash + Objects.hashCode(this.estado);
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.telefone);
        hash = 11 * hash + Objects.hashCode(this.celular);
        hash = 11 * hash + Objects.hashCode(this.produto);
        hash = 11 * hash + Objects.hashCode(this.numeroPW);
        hash = 11 * hash + Objects.hashCode(this.equipamento);
        hash = 11 * hash + Objects.hashCode(this.numeroSerie);
        hash = 11 * hash + Objects.hashCode(this.kmRodado);
        hash = 11 * hash + Objects.hashCode(this.defeitoReclamado);
        hash = 11 * hash + Objects.hashCode(this.localAtendimento);
        hash = 11 * hash + Objects.hashCode(this.dataAtendimento);
        hash = 11 * hash + Objects.hashCode(this.nomeCliente);
        hash = 11 * hash + Objects.hashCode(this.nomeTecnico);
        hash = 11 * hash + Objects.hashCode(this.condicoesFuncionamento);
        hash = 11 * hash + Objects.hashCode(this.conteudoRelatorio);
        hash = 11 * hash + Objects.hashCode(this.jornada);
        hash = 11 * hash + Objects.hashCode(this.pecas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atendimento other = (Atendimento) obj;
        if (!Objects.equals(this.numeroRelatorio, other.numeroRelatorio)) {
            return false;
        }
        if (!Objects.equals(this.nomeAutorizada, other.nomeAutorizada)) {
            return false;
        }
        if (!Objects.equals(this.clienteFinal, other.clienteFinal)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.numeroPW, other.numeroPW)) {
            return false;
        }
        if (!Objects.equals(this.equipamento, other.equipamento)) {
            return false;
        }
        if (!Objects.equals(this.numeroSerie, other.numeroSerie)) {
            return false;
        }
        if (!Objects.equals(this.kmRodado, other.kmRodado)) {
            return false;
        }
        if (!Objects.equals(this.defeitoReclamado, other.defeitoReclamado)) {
            return false;
        }
        if (!Objects.equals(this.localAtendimento, other.localAtendimento)) {
            return false;
        }
        if (!Objects.equals(this.nomeCliente, other.nomeCliente)) {
            return false;
        }
        if (!Objects.equals(this.nomeTecnico, other.nomeTecnico)) {
            return false;
        }
        if (!Objects.equals(this.condicoesFuncionamento, other.condicoesFuncionamento)) {
            return false;
        }
        if (!Objects.equals(this.conteudoRelatorio, other.conteudoRelatorio)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataAtendimento, other.dataAtendimento)) {
            return false;
        }
        if (!Objects.equals(this.jornada, other.jornada)) {
            return false;
        }
        if (!Objects.equals(this.pecas, other.pecas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "id=" + id + ", numeroRelatorio=" + numeroRelatorio + ", nomeAutorizada=" + nomeAutorizada + ", clienteFinal=" + clienteFinal + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", email=" + email + ", telefone=" + telefone + ", celular=" + celular + ", produto=" + produto + ", numeroPW=" + numeroPW + ", equipamento=" + equipamento + ", numeroSerie=" + numeroSerie + ", kmRodado=" + kmRodado + ", defeitoReclamado=" + defeitoReclamado + ", localAtendimento=" + localAtendimento + ", dataAtendimento=" + dataAtendimento + ", nomeCliente=" + nomeCliente + ", nomeTecnico=" + nomeTecnico + ", condicoesFuncionamento=" + condicoesFuncionamento + ", conteudoRelatorio=" + conteudoRelatorio + ", jornada=" + jornada + ", pecas=" + pecas + '}';
    }

}
