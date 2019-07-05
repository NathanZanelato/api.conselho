package org.conselhotutelar.resources.relatorios;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.properties.ResourcePropertiesMetadataReader;
import org.conselhotutelar.modelos.DynamicDto;
import org.conselhotutelar.modelos.entidades.Ocorrencias;
import org.conselhotutelar.utilitarios.BusinessException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Path("ocorrencias/relatorio_ocorrencias")
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciasRelatorioResource {

    private final String outputType = "pdf";
    private final String fileName = "relatorio-de-ocorrencias";
    private final String fileOutput = "/home/daniel/" + fileName + "." + outputType;

    @Inject
    private EntityManager em;

    @POST
    @Produces("application/pdf")
    public Response executarRelatorio(DynamicDto dto) {

        validateFilters(dto);
        Date dtInicial = dto.getDate("dtInicial");
        Date dtFinal = dto.getDate("dtFinal");
        String sexoDaCrianca = dto.getString("sexoDaCrianca");
        List<DynamicDto> dados = new ArrayList<>();

        try {
            HashMap parametros = new HashMap();
            DynamicDto row;
            for (Ocorrencias ocorrencia : getDados(dtInicial, dtFinal, sexoDaCrianca)) {
                row = DynamicDto.build().with("idOcorrencia", ocorrencia.getId())
                        .with("dhOcorrencia", ocorrencia.getDhOcorrencia()
                        ).with("nomeConselheira", ocorrencia.getConselheira().getNome());
                dados.add(row);
            }

            // Stream com o .jasper
            InputStream relJasper = getJasperFile();

            // Datasource para o relatório criado a partir de uma lista de Beans
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);

            JasperPrint impressao;
            impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
            //JasperViewer viewer = new JasperViewer(impressao, true);
            //viewer.setVisible(true);
            final byte[] file = JasperExportManager.exportReportToPdf(impressao);
            System.out.println("PDF gerado...");

            FileInputStream fileInputStream = new FileInputStream(geraPDFEmDisco(file));
            javax.ws.rs.core.Response.ResponseBuilder response = javax.ws.rs.core.Response.ok(fileInputStream);

            return response.type("application/pdf").header("Content-Disposition", "filename="+fileOutput).build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok().entity("{\"message\" : \"Não foi possível recuperar os dados\"}").build();
    }

    private File geraPDFEmDisco(byte[] data) {
        OutputStream out = null;
        try {
            File file = new File(fileOutput);
            //File file = File.createTempFile(fileName, "." + outputType);
            out = new FileOutputStream(file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.write(data);
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void validateFilters(DynamicDto dto) {
        Date dtInicial = dto.getDate("dtInicial");
        Date dtFinal = dto.getDate("dtFinal");
        String sexoDaCrianca = dto.getString("sexoDaCrianca");

        if (dtInicial == null || dtFinal == null) {
            throw new BusinessException("Datas inicial e final devem ser informadas");
        }

        if (sexoDaCrianca != null && !"MFO".contains(sexoDaCrianca)) {
            throw new BusinessException("Sexo da criança deve ser 'M - masculino', 'F- feminino' ou 'O- outro'");
        }

        Logger.getLogger(getClass().getSimpleName()).info("dtInicial = " + dtInicial);
        Logger.getLogger(getClass().getSimpleName()).info("dtFinal = " + dtFinal);
        Logger.getLogger(getClass().getSimpleName()).info("sexoDaCrianca = " + sexoDaCrianca);
    }

    private List<Ocorrencias> getDados(Date dtInicial, Date dtFinal, String sexoDaCrianca) {
        TypedQuery<Ocorrencias> query = em.createNamedQuery("OcorrenciasPorPeriodo", Ocorrencias.class)
                .setParameter("dtInicial", dtInicial, TemporalType.DATE).setParameter("dtFinal", dtFinal, TemporalType.DATE);
        return query.getResultList();
    }

    private InputStream getJasperFile() {
        return ResourcePropertiesMetadataReader.class.getClassLoader()
                .getResourceAsStream("report/rel-ocorrencias.jasper");
    }

}
