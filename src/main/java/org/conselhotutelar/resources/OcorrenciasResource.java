package org.conselhotutelar.resources;

import org.conselhotutelar.modelos.entidades.Ocorrencias;
import org.conselhotutelar.repositorios.OcorrenciasRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ocorrencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciasResource extends AbstractCrudResource<Ocorrencias> {

    @Inject
    private OcorrenciasRepository repository;

    @Override
    public OcorrenciasRepository getRepository() {
        return repository;
    }
}
