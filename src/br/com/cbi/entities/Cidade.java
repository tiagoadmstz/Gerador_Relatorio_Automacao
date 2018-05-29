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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tiago.teixeira
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "CAD_CIDADE", schema = "PLANO")
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "FROM Cidade AS cdd ORDER BY cdd.nome ASC"),
    @NamedQuery(name = "Cidade.find", query = "SELECT cdd FROM Cidade AS cdd WHERE cdd.nome = :paramNome")
})
public class Cidade implements Serializable {

    private static final long serialVersionUID = 405019835580603249L;
    private Number id;
    private String nome;
    private String estado;
    private String codigoAns;
    private Number codigoIbge;

    public Cidade() {
    }

    @Id
    @Column(name = "CIDADE")
    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    @Column(name = "NOME", length = 40)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "ESTADO", length = 2)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "CODIGO_ANS", length = 7)
    public String getCodigoAns() {
        return codigoAns;
    }

    public void setCodigoAns(String codigoAns) {
        this.codigoAns = codigoAns;
    }

    @Column(name = "CODIGO_IBGE")
    public Number getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Number codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.estado);
        hash = 43 * hash + Objects.hashCode(this.codigoAns);
        hash = 43 * hash + Objects.hashCode(this.codigoIbge);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.codigoAns, other.codigoAns)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codigoIbge, other.codigoIbge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cidade{" + "id=" + id + ", nome=" + nome + ", estado=" + estado + ", codigoAns=" + codigoAns + ", codigoIbge=" + codigoIbge + '}';
    }

}
