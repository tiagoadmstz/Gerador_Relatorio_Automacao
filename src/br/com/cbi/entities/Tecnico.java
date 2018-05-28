/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import br.com.cbi.util.ManipulaBean;
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
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author Tiago D. Teixeira
 */
@Entity
@Access(AccessType.PROPERTY)
@Table (name = "TECNICOS")
@Indexes(value = {
    @Index(schema = "ROOT", name = "idx_id", columnNames = {"id"})
})
@NamedQueries(value = {
    @NamedQuery(name = "tecnico.findAll", query = "SELECT tec FROM Tecnico AS tec")
})
@SequenceGenerator(name = "tec_seq", sequenceName = "seq_tec", initialValue = 1, allocationSize = 1)
public class Tecnico extends ManipulaBean<Tecnico> implements Serializable {

    private static final long serialVersionUID = 4849557875996594826L;
    
    private Long id;
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tec_seq")
    @Column (name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME_TECNICO", nullable = true, length = 255)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Tecnico other = (Tecnico) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tecnico{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
