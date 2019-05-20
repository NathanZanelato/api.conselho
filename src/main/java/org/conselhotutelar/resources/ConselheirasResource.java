package org.conselhotutelar.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.conselhotutelar.modelos.entidades.Conselheiras;
import org.conselhotutelar.repositorios.ConselheirasRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

@Path("conselheiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConselheirasResource extends AbstractCrudResource<Conselheiras> {

    @Inject private ConselheirasRepository repository;

    @Override
    public ConselheirasRepository getRepository() {
        return repository;
    }
}
