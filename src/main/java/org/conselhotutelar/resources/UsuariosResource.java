package org.conselhotutelar.resources;

import org.conselhotutelar.modelos.entidades.Usuarios;
import org.conselhotutelar.repositorios.UsuariosRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosResource extends AbstractCrudResource<Usuarios> {

    @Inject
    private UsuariosRepository repository;

    @Override
    public UsuariosRepository getRepository() {
        return repository;
    }
}
