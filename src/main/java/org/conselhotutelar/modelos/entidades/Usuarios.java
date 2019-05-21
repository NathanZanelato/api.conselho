package org.conselhotutelar.modelos.entidades;

import org.conselhotutelar.modelos.ValueObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIOS", uniqueConstraints = @UniqueConstraint(columnNames = "username", name = "username_uk"))
@SequenceGenerator(name = "USUARIOS_SEQ", sequenceName = "USUARIOS_SEQ", allocationSize = 1)
public class Usuarios implements ValueObject {

    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    @GeneratedValue(generator = "USUARIOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 2, max = 20, message = "{Usuarios.nome.Size}")
    @NotNull(message = "{Usuarios.nome.NotNull}")
    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    @Size(min = 6, max = 8, message = "{Usuarios.password.Size}")
    @NotNull(message = "{Usuarios.password.NotNull}")
    @Column(name = "password", length = 25)
    private String password;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
