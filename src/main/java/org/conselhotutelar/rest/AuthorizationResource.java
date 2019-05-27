package org.conselhotutelar.rest;

import io.jsonwebtoken.Jwts;
import org.conselhotutelar.modelos.entidades.Usuarios;
import org.conselhotutelar.repositorios.UsuariosRepository;
import org.conselhotutelar.utilitarios.BusinessException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.Serializable;

import static org.conselhotutelar.filters.AuthFilter.LOGIN_PATH;
import static org.conselhotutelar.filters.AuthFilter.AUTHORIZATION_KEY;

@Path("logar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorizationResource {

    @Inject
    UsuariosRepository repository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Context HttpServletRequest req) {
        return Response.ok(req.getScheme()
                .concat("://")
                .concat(req.getServerName())
                .concat(":")
                .concat(String.valueOf(req.getServerPort()))
                .concat(LOGIN_PATH))
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }

    @POST
    public Response logar(Usuarios usuario) {

        if (usuario == null || usuario.getUsername() == null || usuario.getPassword() == null) {
            throw new BusinessException("Informe o username e senha do usuário");
        }

        Usuarios usuarioCadastrado = repository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
        if (usuarioCadastrado == null) {
            //throw new BusinessException("Usuário e senha não cadastrados");
            return Response.status(Response.Status.OK).entity("{\"error\" : \"Usuário e senha não cadastrados\"}").build();
        }

        return Response.ok().entity(new Credentials(usuarioCadastrado)).build();
    }

    private static class Credentials implements Serializable {
        private Usuarios usuario;
        private String token;
        private Credentials(){}
        Credentials(Usuarios usuario) {
            usuario.setPassword(null); // limpa o password para não ficar o dado vulnerável no browser
            this.usuario = usuario;
            this.token = Jwts.builder().setSubject(usuario.getUsername()).setId(usuario.getId().toString()).signWith(AUTHORIZATION_KEY).compact();
        }
        public Usuarios getUsuario() {
            return usuario;
        }
        public void setUsuario(Usuarios usuario) {
            this.usuario = usuario;
        }
        public String getToken() {
            return token;
        }
    }
}
