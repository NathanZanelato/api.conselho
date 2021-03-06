package org.conselhotutelar.resources;

import org.conselhotutelar.modelos.entidades.Criancas;
import org.conselhotutelar.repositorios.CriancasRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("criancas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CriancasResource extends AbstractCrudResource<Criancas> {

    @Inject
    private CriancasRepository repository;

    @Override
    public CriancasRepository getRepository() {
        return repository;
    }
}
