package org.conselhotutelar.resources.indicadores;

import org.conselhotutelar.repositorios.indicadores.OcorrenciasIndicadoresRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("ocorrencias/indicadores")
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciasIndicadoresResource {

    @Inject
    private OcorrenciasIndicadoresRepository repository;

    @GET
    @Path("sexo/{ano}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response executarIndicadorPorSexoNoAno(@PathParam("ano") Integer ano) {

        try {

            Response.ResponseBuilder response = Response.ok(repository.buildResultPorSexoNoAno(ano));
            return response.type(MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, e.getMessage());
        }

        return Response.ok().entity("{\"message\" : \"Não foi possível recuperar os dados\"}").build();
    }


    @GET
    @Path("recorrencia/{qtdMeses}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response executarIndicadorPorRecorrenciaNosUltimosMeses(@PathParam("qtdMeses") Integer mes) {
        try {

            Response.ResponseBuilder response = Response.ok(repository.buildResultRecorrenciasNosUltimosMeses(mes));
            return response.type(MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, e.getMessage());
        }

        return Response.ok().entity("{\"message\" : \"Não foi possível recuperar os dados\"}").build();
    }
}
