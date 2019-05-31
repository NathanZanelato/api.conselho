package org.conselhotutelar.modelos.entidades;

import org.conselhotutelar.modelos.ValueObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "OCORRENCIAS")
@SequenceGenerator(name = "OCORRENCIAS_SEQ", sequenceName = "OCORRENCIAS_SEQ", allocationSize = 1)
public class Ocorrencias implements ValueObject {

    @Id
    @Column(name = "ID_OCORRENCIA")
    @GeneratedValue(generator = "OCORRENCIAS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @NotNull(message = "{Ocorrencias.conselheira.NotNull}")
    @JoinColumn(name = "ID_CONSELHEIRA", nullable = false)
    private Conselheiras conselheira;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{Ocorrencias.dhRegistro.NotNull}")
    @Column(name = "DH_REGISTRO", nullable = false)
    private Date dhRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{Ocorrencias.dhOcorrencia.NotNull}")
    @Column(name = "DH_OCORRENCIA", nullable = false)
    private Date dhOcorrencia;

    @Size(min = 3, max = 5000, message = "{Ocorrencias.descricao.Size}")
    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @ManyToOne
    @NotNull(message = "{Ocorrencias.crianca.NotNull}")
    @JoinColumn(name = "ID_CRIANCA", nullable = false)
    private Criancas crianca;

    @Size(min = 3, max = 100, message = "{Ocorrencias.responsavel.Size}")
    @Column(name = "RESPONSAVEL", length = 100, nullable = false)
    private String responsavel;

    @Column(name = "PROCEDENCIA_DENUNCIA")
    private Integer procedenciaDenuncia;

    @Column(name = "AGENTE_VIOLADOR")
    private Integer agenteViolador;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDhOcorrencia() {
        return dhOcorrencia;
    }

    public Integer getProcedenciaDenuncia() {
        return procedenciaDenuncia;
    }

    public Integer getAgenteViolador() {
        return agenteViolador;
    }

    public void setDhOcorrencia(Date dhOcorrencia) {
        this.dhOcorrencia = dhOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Criancas getCrianca() {
        return crianca;
    }

    public void setCrianca(Criancas crianca) {
        this.crianca = crianca;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setProcedenciaDenuncia(Integer procedenciaDenuncia) {
        this.procedenciaDenuncia = procedenciaDenuncia;
    }

    public void setAgenteViolador(Integer agenteViolador) {
        this.agenteViolador = agenteViolador;
    }

}
