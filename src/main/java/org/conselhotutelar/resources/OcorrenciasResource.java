package org.conselhotutelar.resources;

import org.conselhotutelar.enums.AgenteViolador;
import org.conselhotutelar.enums.ProcedenciaDenuncia;
import org.conselhotutelar.modelos.entidades.Ocorrencias;
import org.conselhotutelar.repositorios.OcorrenciasRepository;
import org.conselhotutelar.rest.AbstractCrudResource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status;

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

    @GET
    @Path("procedenciasDenuncias")
    public Response getProcedenciasDenuncia() {
        return Response.ok(ProcedenciaDenuncia.builderAsDtoList()).status(Status.OK).build();
    }

    @GET
    @Path("agentesVioladores")
    public Response getAgentesVioladores() {
        return Response.ok(AgenteViolador.builderAsDtoList()).status(Status.OK).build();
    }

}
