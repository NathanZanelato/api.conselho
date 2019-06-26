package org.conselhotutelar.resources.relatorios;

import org.conselhotutelar.modelos.DynamicDto;
import org.conselhotutelar.repositorios.OcorrenciasRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.logging.Logger;

@Path("ocorrencias/relatorio_ocorrencias")
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciasRelatorioResource {

    @Inject
    private OcorrenciasRepository repository;

    @POST
    public Response executarRelatorio(DynamicDto dto){
        Date dtInicial = dto.getDate("dtInicial");
        Date dtFinal = dto.getDate("dtFinal");
        String sexoDaCrianca = dto.getString("sexoDaCrianca");
        Logger.getLogger(getClass().getSimpleName()).info("dtInicial = " + dtInicial);
        Logger.getLogger(getClass().getSimpleName()).info("dtFinal = " + dtFinal);
        Logger.getLogger(getClass().getSimpleName()).info("sexoDaCrianca = " + sexoDaCrianca);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

}
