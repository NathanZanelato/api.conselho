package org.conselhotutelar.modelos.entidades;

import org.conselhotutelar.modelos.ValueObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "ATENDIMENTOS")
@SequenceGenerator(name = "ATENDIMENTOS_SEQ", sequenceName = "ATENDIMENTOS_SEQ", allocationSize = 1)
public class Atendimentos implements ValueObject {

    @Id
    @Column(name = "ID_ATENDIMENTO")
    @GeneratedValue(generator = "ATENDIMENTOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @NotNull(message = "{Atendimentos.ocorrencia.NotNull}")
    @JoinColumn(name = "ID_OCORRENCIA", nullable = false)
    private Ocorrencias ocorrencia;

    @ManyToOne
    @NotNull(message = "{Ocorrencias.conselheira.NotNull}")
    @JoinColumn(name = "ID_CONSELHEIRA", nullable = false)
    private Conselheiras conselheira;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{Atendimentos.dhRegistro.NotNull}")
    @Column(name = "DH_REGISTRO", nullable = false)
    private Date dhRegistro;

    @Size(min = 3, max = 5000, message = "{Atendimentos.relato.Size}")
    @NotNull(message = "{Atendimentos.relato.NotNull}")
    @Column(name = "RELATO", length = 100, nullable = false)
    private String relato;

    @Column(name = "MEDIDA_APLICADA")
    private Integer medidaAplicada;

    @Column(name = "VIOLACAO_DIREITO")
    private Integer violacaoDireito;

    @NotNull(message = "{Atendimentos.possuiAgendamento.NotNull}")
    @Size(max = 1, message = "{Atendimentos.possuiAgendamento.Size}")
    @Column(name = "POSSUI_AGENDAMENTO", length = 1, nullable = false)
    private String possuiAgendamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DH_ATENDIMENTO")
    private Date dhAtendimento;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ocorrencias getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencias ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Conselheiras getConselheira() {
        return conselheira;
    }

    public void setConselheira(Conselheiras conselheira) {
        this.conselheira = conselheira;
    }

    public Date getDhRegistro() {
        return dhRegistro;
    }

    public void setDhRegistro(Date dhRegistro) {
        this.dhRegistro = dhRegistro;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public Integer getMedidaAplicada() {
        return medidaAplicada;
    }

    public void setMedidaAplicada(Integer medidaAplicada) {
        this.medidaAplicada = medidaAplicada;
    }

    public Integer getViolacaoDireito() {
        return violacaoDireito;
    }

    public void setViolacaoDireito(Integer violacaoDireito) {
        this.violacaoDireito = violacaoDireito;
    }

    public String getPossuiAgendamento() {
        return possuiAgendamento;
    }

    public void setPossuiAgendamento(String possuiAgendamento) {
        this.possuiAgendamento = possuiAgendamento;
    }

    public Date getDhAtendimento() {
        return dhAtendimento;
    }

    public void setDhAtendimento(Date dhAtendimento) {
        this.dhAtendimento = dhAtendimento;
    }
}
