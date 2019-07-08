package org.conselhotutelar.resources.relatorios;

import org.conselhotutelar.enums.RelType;
import org.conselhotutelar.modelos.DynamicDto;
import org.conselhotutelar.repositorios.relatorios.OcorrenciasRelatoriosRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("ocorrencias/relatorio_ocorrencias")
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciasRelatorioResource {

    @Inject
    private OcorrenciasRelatoriosRepository repository;

    @POST
    @Produces("application/pdf")
    public Response executarRelatorio(DynamicDto dto) {

        repository.validateFilters(dto);

        try {

            Response.ResponseBuilder response = Response.ok(repository.buildReport(dto));
            String fileOutput = repository.fileName + "." + RelType.PDF.getValue();
            return response.type("application/pdf").header("Content-Disposition", "filename=" + fileOutput).build();

        } catch (Exception e) {
            Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, e.getMessage());
        }


        return Response.ok().entity("{\"message\" : \"Não foi possível recuperar os dados\"}").build();
    }


}
