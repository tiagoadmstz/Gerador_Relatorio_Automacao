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
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author tiago.teixeira
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "cad_grupo_relatorio")
@Indexes({
    @Index(name = "idx_grupo", columnNames = {"grupo"})
})
@NamedQueries({
    @NamedQuery(name = "grupo.findAll", query = "SELECT gp FROM Report_Group AS gp"),
    @NamedQuery(name = "grupo.findById", query = "SELECT gp FROM Report_Group AS gp WHERE gp.id = :paramId"),
    @NamedQuery(name = "grupo.findByName", query = "SELECT gp FROM Report_Group AS gp WHERE gp.grupo = :paramNome")
})
public class Report_Group implements Serializable {

    private static final long serialVersionUID = 5539142025489258099L;
    private Long id;
    private String grupo;

    public Report_Group() {
    }

    public Report_Group(Long id, String grupo) {
        this.id = id;
        this.grupo = grupo;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "grupo", length = 80)
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.grupo);
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
        final Report_Group other = (Report_Group) obj;
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report_Group{" + "id=" + id + ", grupo=" + grupo + '}';
    }
    
}
