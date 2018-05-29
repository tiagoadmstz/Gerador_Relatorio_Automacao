/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author tiago.teixeira
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "cad_filtro_report")
@Indexes({
    @Index(name = "idx_id", columnNames = {"id"})
    ,
    @Index(name = "idx_nome", columnNames = {"nome"})
})
@NamedQueries({
    @NamedQuery(name = "filtro.findAll", query = "SELECT ft FROM Filtro AS ft")
    ,
    @NamedQuery(name = "filtro.findById", query = "SELECT ft FROM Filtro AS ft WHERE ft.id = :paramId")
    ,
    @NamedQuery(name = "filtro.findByNome", query = "SELECT ft FROM Filtro AS ft WHERE ft.nome = :paramNome")
})
@SequenceGenerator(name = "seq_filtro", sequenceName = "filtro_seq", allocationSize = 1, initialValue = 1)
public class Filtro implements Serializable {

    private static final long serialVersionUID = 2940652468912326875L;

    private Long id;
    private String nome;
    private String valor;
    private String tipo;
    private int version;

    public Filtro() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_filtro")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome", length = 80)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "valor", length = 100)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Column(name = "tipo", length = 50)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + Objects.hashCode(this.valor);
        hash = 71 * hash + Objects.hashCode(this.tipo);
        hash = 71 * hash + this.version;
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
        final Filtro other = (Filtro) obj;
        if (this.version != other.version) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filtro{" + "id=" + id + ", nome=" + nome + ", valor=" + valor + ", tipo=" + tipo + ", version=" + version + '}';
    }

}
