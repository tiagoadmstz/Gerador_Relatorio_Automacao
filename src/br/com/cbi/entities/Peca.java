/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.cbi.util.ManipulaBean;
import br.com.cbi.converters.BigDecimalConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author Tiago D. Teixeira
 */
@Entity
@Table(name = "PECAS_UTILIZADAS")
@Access(AccessType.PROPERTY)
@Indexes(value = {
    @Index(schema = "ROOT", name = "idx_id", columnNames = {"id"})
})
@NamedQueries(value = {
    @NamedQuery(name = "pecas.findAll", query = "SELECT pc FROM Peca AS pc")
})
@SequenceGenerator(name = "pc_seq", sequenceName = "seq_pc", initialValue = 1, allocationSize = 1)
public class Peca extends ManipulaBean<Peca> implements Serializable {

    private static final long serialVersionUID = 880534130340879110L;
    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal quantidade;
    private Atendimento atendimento;

    public Peca() {
    }

    public Peca(Long id, String codigo, String descricao, BigDecimal quantidade, Atendimento atendimento) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.atendimento = atendimento;
    }

    @Id
    @GeneratedValue(generator = "pc_seq", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "codigo", nullable = true, length = 255)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "descricao", nullable = true, length = 255)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Convert(converter = BigDecimalConverter.class)
    @Column(name = "quantidade", nullable = true, length = 10)
    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atendimento", referencedColumnName = "id")
    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.codigo);
        hash = 11 * hash + Objects.hashCode(this.descricao);
        hash = 11 * hash + Objects.hashCode(this.quantidade);
        hash = 11 * hash + Objects.hashCode(this.atendimento);
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
        final Peca other = (Peca) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.atendimento, other.atendimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Peca{" + "id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", quantidade=" + quantidade + ", atendimento=" + atendimento + '}';
    }

}
