package org.conselhotutelar.modelos.entidades;

import org.conselhotutelar.modelos.ValueObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "CRIANCAS")
@SequenceGenerator(name = "CRIANCAS_SEQ", sequenceName = "CRIANCAS_SEQ", allocationSize = 1)
public class Criancas implements ValueObject {

    @Id
    @Column(name = "ID_CRIANCA")
    @GeneratedValue(generator = "CRIANCAS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "{Criancas.nome.NotNull}")
    @Size(min = 2, max = 100, message = "{Criancas.nome.Size}")
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @NotNull(message = "{Criancas.cpf.NotNull}")
    @Size(min = 11, max = 11, message = "{Criancas.cpf.Size}")
    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{Criancas.dtNascimento.NotNull}")
    @Column(name = "DT_NASCIMENTO", nullable = false)
    private Date dtNascimento;

    @NotNull(message = "{Criancas.telefone.NotNull}")
    @Size(max = 14, message = "{Criancas.telefone.Size}")
    @Column(name = "TELEFONE", length = 14, nullable = false)
    private String telefone;

    @NotNull(message = "{Criancas.deficiente.NotNull}")
    @Size(max = 1, message = "{Criancas.deficiente.Size}")
    @Column(name = "DEFICIENTE", length = 1, nullable = false)
    private String deficiente;

    @NotNull(message = "{Criancas.sexo.NotNull}")
    @Size(max = 1, message = "{Criancas.sexo.Size}")
    @Column(name = "SEXO", length = 1, nullable = false)
    private String sexo;

    @Size(min = 2, max = 100, message = "{Criancas.endereco.Size}")
    @Column(name = "ENDERECO", length = 100)
    private String endereco;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDeficiente() {
        return deficiente;
    }

    public void setDeficiente(String deficiente) {
        this.deficiente = deficiente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
