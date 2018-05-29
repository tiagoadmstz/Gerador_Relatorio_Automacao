/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "cad_relatorios")
@Indexes(value = {
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_sigla", columnNames = {"sigla"}),
    @Index(name = "idx_grupo", columnNames = {"grupo"}),
    @Index(name = "idx_nome", columnNames = {"nome"}),
    @Index(name = "idx_report", columnNames = {"sigla", "grupo", "nome"})
})
@NamedQueries(value = {
    @NamedQuery(name = "report.findAll", query = "SELECT rp FROM SresReport AS rp"),
    @NamedQuery(name = "report.findBySigla", query = "SELECT rp FROM SresReport AS rp WHERE rp.sigla = :paramSigla"),
    @NamedQuery(name = "report.findByGrupo", query = "SELECT rp FROM SresReport AS rp WHERE rp.grupo = :paramGrupo"),
    @NamedQuery(name = "report.findByNome", query = "SELECT rp FROM SresReport AS rp WHERE rp.nome = :paramNome")
})
@SequenceGenerator(name = "seq_report", sequenceName = "report_seq", allocationSize = 1, initialValue = 1)
public class SresReport implements Serializable {

    private static final long serialVersionUID = 6251801190469885360L;

    private Long id;
    private String sigla;
    private String nome;
    private Report_Group grupo;
    private List<Filtro> filtro;
    private String path;
    private Boolean ativo;
    private int version;

    public SresReport() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "sigla", length = 10)
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Column(name = "nome", length = 255)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Report_Group.class)
    public Report_Group getGrupo() {
        return grupo;
    }

    public void setGrupo(Report_Group grupo) {
        this.grupo = grupo;
    }

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Filtro.class)
    public List<Filtro> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Filtro> filtro) {
        this.filtro = filtro;
    }

    @Column(name = "path", columnDefinition = "long varchar")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "ativo", columnDefinition = "boolean")
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.sigla);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.grupo);
        hash = 89 * hash + Objects.hashCode(this.filtro);
        hash = 89 * hash + Objects.hashCode(this.path);
        hash = 89 * hash + Objects.hashCode(this.ativo);
        hash = 89 * hash + this.version;
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
        final SresReport other = (SresReport) obj;
        if (this.version != other.version) {
            return false;
        }
        if (!Objects.equals(this.sigla, other.sigla)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.filtro, other.filtro)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SresReport{" + "id=" + id + ", sigla=" + sigla + ", nome=" + nome + ", grupo=" + grupo + ", filtro=" + filtro + ", path=" + path + ", ativo=" + ativo + ", version=" + version + '}';
    }

}
