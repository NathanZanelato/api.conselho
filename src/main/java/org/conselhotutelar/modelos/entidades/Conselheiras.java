package org.conselhotutelar.modelos.entidades;

import org.conselhotutelar.modelos.ValueObject;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONSELHEIRAS")
@SequenceGenerator(name = "CONSELHEIRAS_SEQ", sequenceName = "CONSELHEIRAS_SEQ", allocationSize = 1)
public class Conselheiras implements ValueObject {

    @Id
    @Column(name = "ID_CONSELHEIRA")
    @GeneratedValue(generator = "CONSELHEIRAS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 2, max = 100, message = "{Conselheiras.nome.Size}")
    @NotNull(message = "{Conselheiras.nome.NotNull}")
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Size(min = 11, max = 11, message = "{Conselheiras.cpf.Size}")
    @NotNull(message = "{Conselheiras.cpf.NotNull}")
    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{Conselheiras.dtNascimento.NotNull}")
    @Column(name = "DT_NASCIMENTO", nullable = false)
    private Date dtNascimento;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{Conselheiras.dtPosse.NotNull}")
    @Column(name = "DT_POSSE", nullable = false)
    private Date dtPosse;

	@NotNull(message = "{Conselheiras.matricula.NotNull}")
    @Size(max = 20, message = "{Conselheiras.matricula.Size}")
    @Column(name = "MATRICULA", length = 20, nullable = false)
    private String matricula;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Date getDtPosse() {
        return dtPosse;
    }

    public void setDtPosse(Date dtPosse) {
        this.dtPosse = dtPosse;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
