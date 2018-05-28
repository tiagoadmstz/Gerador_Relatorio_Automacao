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
@Access(AccessType.PROPERTY)
@Table(name = "JORNADAS")
@Indexes(value = {
    @Index(schema = "ROOT", name = "idx_id", columnNames = {"id"})
})
@NamedQueries(value = {
    @NamedQuery(name = "jornada.findAll", query = "SELECT jd FROM Jornada AS jd")
})
@SequenceGenerator(name = "jd_seq", sequenceName = "seq_jd", initialValue = 1, allocationSize = 1)
public class Jornada extends ManipulaBean<Jornada> implements Serializable {

    private static final long serialVersionUID = -3697876686009244273L;

    private Long id;
    private LocalDateTime dataJornada;
    private String deslocamento_in;
    private String deslocamento_out;
    private String trabalho_in;
    private String trabalho_out;
    private String refeicao_in;
    private String refeicao_out;
    private String deslocamentoSaida_in;
    private String deslocamentoSaida_out;
    private String diarias;
    private String relatorioJornada;
    private Atendimento atendimento;

    public Jornada() {
    }

    @Id
    @GeneratedValue(generator = "jd_seq", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_JORNADA", nullable = true)
    public LocalDateTime getDataJornada() {
        return dataJornada;
    }

    public void setDataJornada(LocalDateTime dataJornada) {
        this.dataJornada = dataJornada;
    }

    @Column(name = "DESLOCAMENTO_IN", nullable = true, length = 10)
    public String getDeslocamento_in() {
        return deslocamento_in;
    }

    public void setDeslocamento_in(String deslocamento_in) {
        this.deslocamento_in = deslocamento_in;
    }

    @Column(name = "DESLOCAMENTO_OUT", nullable = true, length = 10)
    public String getDeslocamento_out() {
        return deslocamento_out;
    }

    public void setDeslocamento_out(String deslocamento_out) {
        this.deslocamento_out = deslocamento_out;
    }

    @Column(name = "TRABALHO_IN", nullable = true, length = 10)
    public String getTrabalho_in() {
        return trabalho_in;
    }

    public void setTrabalho_in(String trabalho_in) {
        this.trabalho_in = trabalho_in;
    }

    @Column(name = "TRABALHO_OUT", nullable = true, length = 10)
    public String getTrabalho_out() {
        return trabalho_out;
    }

    public void setTrabalho_out(String trabalho_out) {
        this.trabalho_out = trabalho_out;
    }

    @Column(name = "REFEICAO_IN", nullable = true, length = 10)
    public String getRefeicao_in() {
        return refeicao_in;
    }

    public void setRefeicao_in(String refeicao_in) {
        this.refeicao_in = refeicao_in;
    }

    @Column(name = "REFEICAO_OUT", nullable = true, length = 10)
    public String getRefeicao_out() {
        return refeicao_out;
    }

    public void setRefeicao_out(String refeicao_out) {
        this.refeicao_out = refeicao_out;
    }

    @Column(name = "DESLOCAMENTO_SAIDA_IN", nullable = true, length = 10)
    public String getDeslocamentoSaida_in() {
        return deslocamentoSaida_in;
    }

    public void setDeslocamentoSaida_in(String deslocamentoSaida_in) {
        this.deslocamentoSaida_in = deslocamentoSaida_in;
    }

    @Column(name = "DESLOCAMENTO_SAIDA_OUT", nullable = true, length = 10)
    public String getDeslocamentoSaida_out() {
        return deslocamentoSaida_out;
    }

    public void setDeslocamentoSaida_out(String deslocamentoSaida_out) {
        this.deslocamentoSaida_out = deslocamentoSaida_out;
    }

    @Column(name = "DIARIAS", nullable = true, length = 10)
    public String getDiarias() {
        return diarias;
    }

    public void setDiarias(String diarias) {
        this.diarias = diarias;
    }

    @Column(name = "RELATORIO_JORNADA")
    public String getRelatorioJornada() {
        return relatorioJornada;
    }

    public void setRelatorioJornada(String relatorioJornada) {
        this.relatorioJornada = relatorioJornada;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Atendimento.class)
    @JoinColumn(name = "ATENDIMENTO", referencedColumnName = "ID")
    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.dataJornada);
        hash = 41 * hash + Objects.hashCode(this.deslocamento_in);
        hash = 41 * hash + Objects.hashCode(this.deslocamento_out);
        hash = 41 * hash + Objects.hashCode(this.trabalho_in);
        hash = 41 * hash + Objects.hashCode(this.trabalho_out);
        hash = 41 * hash + Objects.hashCode(this.refeicao_in);
        hash = 41 * hash + Objects.hashCode(this.refeicao_out);
        hash = 41 * hash + Objects.hashCode(this.deslocamentoSaida_in);
        hash = 41 * hash + Objects.hashCode(this.deslocamentoSaida_out);
        hash = 41 * hash + Objects.hashCode(this.diarias);
        hash = 41 * hash + Objects.hashCode(this.relatorioJornada);
        hash = 41 * hash + Objects.hashCode(this.atendimento);
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
        final Jornada other = (Jornada) obj;
        if (!Objects.equals(this.deslocamento_in, other.deslocamento_in)) {
            return false;
        }
        if (!Objects.equals(this.deslocamento_out, other.deslocamento_out)) {
            return false;
        }
        if (!Objects.equals(this.trabalho_in, other.trabalho_in)) {
            return false;
        }
        if (!Objects.equals(this.trabalho_out, other.trabalho_out)) {
            return false;
        }
        if (!Objects.equals(this.refeicao_in, other.refeicao_in)) {
            return false;
        }
        if (!Objects.equals(this.refeicao_out, other.refeicao_out)) {
            return false;
        }
        if (!Objects.equals(this.deslocamentoSaida_in, other.deslocamentoSaida_in)) {
            return false;
        }
        if (!Objects.equals(this.deslocamentoSaida_out, other.deslocamentoSaida_out)) {
            return false;
        }
        if (!Objects.equals(this.diarias, other.diarias)) {
            return false;
        }
        if (!Objects.equals(this.relatorioJornada, other.relatorioJornada)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataJornada, other.dataJornada)) {
            return false;
        }
        if (!Objects.equals(this.atendimento, other.atendimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jornada{" + "id=" + id + ", dataJornada=" + dataJornada + ", deslocamento_in=" + deslocamento_in + ", deslocamento_out=" + deslocamento_out + ", trabalho_in=" + trabalho_in + ", trabalho_out=" + trabalho_out + ", refeicao_in=" + refeicao_in + ", refeicao_out=" + refeicao_out + ", deslocamentoSaida_in=" + deslocamentoSaida_in + ", deslocamentoSaida_out=" + deslocamentoSaida_out + ", diarias=" + diarias + ", relatorioJornada=" + relatorioJornada + ", atendimento=" + atendimento + '}';
    }

}
