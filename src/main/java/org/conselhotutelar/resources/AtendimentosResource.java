package org.conselhotutelar.resources;

import org.conselhotutelar.modelos.entidades.Atendimentos;
import org.conselhotutelar.repositorios.AtendimentosRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("atendimentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtendimentosResource extends AbstractCrudResource<Atendimentos> {

    @Inject
    private AtendimentosRepository repository;

    @Override
    public AtendimentosRepository getRepository() {
        return repository;
    }
}
