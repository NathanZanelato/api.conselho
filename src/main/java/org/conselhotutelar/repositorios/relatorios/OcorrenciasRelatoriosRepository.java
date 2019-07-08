package org.conselhotutelar.repositorios.relatorios;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.properties.ResourcePropertiesMetadataReader;
import org.conselhotutelar.enums.RelType;
import org.conselhotutelar.modelos.DynamicDto;
import org.conselhotutelar.modelos.entidades.Ocorrencias;
import org.conselhotutelar.utilitarios.BusinessException;
import org.conselhotutelar.utilitarios.RelatoriosUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OcorrenciasRelatoriosRepository {

    @Inject
    private EntityManager em;
    public final String fileName = "relatorio-de-ocorrencias";
    private static final String jasperFile = "report/rel-ocorrencias.jasper";

    public void validateFilters(DynamicDto dto) {
        Date dtInicial = dto.getDate("dtInicial");
        Date dtFinal = dto.getDate("dtFinal");
        String sexoDaCrianca = dto.getString("sexoDaCrianca");

        if (dtInicial == null || dtFinal == null) {
            throw new BusinessException("Datas inicial e final devem ser informadas");
        }

        if (sexoDaCrianca != null && !"MFO".contains(sexoDaCrianca)) {
            throw new BusinessException("Sexo da criança deve ser 'M - masculino', 'F- feminino' ou 'O- outro'");
        }

        String filtro = String.format("{\"dtInicial\":\"%s\", \"dtFinal\":\"%s\", \"sexoDaCrianca\":\"%s\"}",
                dtInicial, dtFinal, sexoDaCrianca == null || sexoDaCrianca.isEmpty() ? "null" : sexoDaCrianca);

        Logger.getLogger(getClass().getSimpleName()).info(filtro);
    }

    public FileInputStream buildReport(DynamicDto dto) throws FileNotFoundException, JRException {

        Date dtInicial = dto.getDate("dtInicial");
        Date dtFinal = dto.getDate("dtFinal");
        String sexoDaCrianca = dto.getString("sexoDaCrianca");
        List<DynamicDto> dados = new ArrayList<>();

        HashMap<String, Object> parametros = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        parametros.put("pDhRelatorio", new Date());
        parametros.put("pDtInicial", sdf.format(dtInicial));
        parametros.put("pDtFinal", sdf.format(dtFinal));
        parametros.put("pSexo", sexoDaCrianca);
        DynamicDto row;
        for (Ocorrencias ocorrencia : getDados(dtInicial, dtFinal, sexoDaCrianca)) {
            row = DynamicDto.build().with("idOcorrencia", ocorrencia.getId())
                    .with("dhOcorrencia", ocorrencia.getDhOcorrencia())
                    .with("nomeConselheira", ocorrencia.getConselheira().getNome())
                    .with("nomeCrianca", ocorrencia.getCrianca().getNome())
                    .with("sexoDaCrianca", ocorrencia.getCrianca().getSexo())
                    .with("descricao", ocorrencia.getDescricao())
            ;
            dados.add(row);
        }

        // Stream com o .jasper
        InputStream relJasper = getJasperFile();

        // Datasource para o relatório criado a partir de uma lista de Beans
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);

        JasperPrint impressao;
        impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
        final byte[] file = JasperExportManager.exportReportToPdf(impressao);

        return new FileInputStream(RelatoriosUtils.geraPDFEmDisco(fileName, RelType.PDF, file));

    }

    private List<Ocorrencias> getDados(Date dtInicial, Date dtFinal, String sexoDaCrianca) {

        TypedQuery<Ocorrencias> q = em.createQuery("Select o from Ocorrencias o where o.dhOcorrencia Between :dtInicial and :dtFinal", Ocorrencias.class)
                .setParameter("dtInicial", dtInicial, TemporalType.DATE).setParameter("dtFinal", dtFinal, TemporalType.DATE);

        if (sexoDaCrianca != null && "MFO".contains(sexoDaCrianca)) {
            return q.getResultList().stream()
                    .filter(o -> o.getCrianca().getSexo().equals(sexoDaCrianca)).collect(Collectors.toList());
        }
        return q.getResultList();
    }

    private InputStream getJasperFile() {
        return ResourcePropertiesMetadataReader.class.getClassLoader().getResourceAsStream(jasperFile);
    }

}
