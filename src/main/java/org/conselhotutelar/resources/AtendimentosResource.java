package org.conselhotutelar.resources;

import org.conselhotutelar.enums.DireitoViolado;
import org.conselhotutelar.enums.MedidaAplicada;
import org.conselhotutelar.modelos.entidades.Atendimentos;
import org.conselhotutelar.repositorios.AtendimentosRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("medidasAplicadas")
    public Response getMedidasAplicadas() {
        return Response.ok(MedidaAplicada.builderAsDtoList()).status(Response.Status.OK).build();
    }

    @GET
    @Path("direitosViolados")
    public Response getAgentesVioladores() {
        return Response.ok(DireitoViolado.builderAsDtoList()).status(Response.Status.OK).build();
    }
}
