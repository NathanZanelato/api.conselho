package org.conselhotutelar.repositorios.indicadores;

import org.conselhotutelar.enums.Mes;
import org.conselhotutelar.enums.Sexo;
import org.conselhotutelar.modelos.DynamicDto;
import org.conselhotutelar.utilitarios.RelatoriosUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OcorrenciasIndicadoresRepository {

    @Inject
    private EntityManager em;

    public List<DynamicDto> buildResultPorSexoNoAno(Integer ano) {

        if (ano == null || ano <= 0)
            return new ArrayList<>();

        List<DynamicDto> dados = getDadosPorSexoNoAno(ano);

        int quantidade;
        List<DynamicDto> resultList = new ArrayList<>();
        for (int mes = 1; mes < 13; mes++) {
            for (Sexo s : Sexo.values()) {
                quantidade  = 0;
                for (DynamicDto dto : dados) {
                    if (dto.getString("mes").equals(mes + ".0") &&
                        dto.getString("sexo").equals(s.getValue())) {
                        quantidade = dto.getInt("quantidade");
                        break;
                    }
                }
                resultList.add(DynamicDto.build()
                        .with("ano", ano)
                        .with("mes", Mes.byValue(mes))
                        .with("sexo", s.getValue())
                        .with("quantidade", quantidade));
            }
        }

        return resultList;
    }

    public List<DynamicDto> buildResultRecorrenciasNosUltimosMeses(Integer qtdMeses) {
        if (qtdMeses == null || qtdMeses.compareTo(0) <= 0)
            return new ArrayList<>();

        return getDadosRecorrenciasUltimosMeses(qtdMeses);
    }

    public List<DynamicDto> buildResultRecorrenciasNoMes(Integer ano, Integer mes) {
        if (ano == null || ano.compareTo(0) <= 0 || mes == null || mes.compareTo(0) <= 0)
            return new ArrayList<>();

        List<DynamicDto> dados = getDadosRecorrenciasNoMes(ano, mes);

        if (dados.isEmpty()) {
            dados.add(DynamicDto.build()
                    .with("totalOcorrencias", 0)
                    .with("qtdCriancas", 0));
        }
        return dados;
    }

    private List<DynamicDto> getDadosPorSexoNoAno(Integer ano) {

        StringBuilder sql = new StringBuilder();
        sql.append("select extract(month from o.dh_ocorrencia) as mes, c.sexo, count(*) ");
        sql.append("from ocorrencias o ");
        sql.append("join criancas c on (c.id_crianca = o.id_crianca) ");
        sql.append("where extract(year from dh_ocorrencia) = :ano ");
        sql.append("group by extract(year from o.dh_ocorrencia), extract(month from o.dh_ocorrencia), c.sexo ");
        sql.append("order by 1 asc, 2 asc");

        Query query = em.createNativeQuery(sql.toString()).setParameter("ano", ano);
        List<Object[]> resultList = query.getResultList();
        List<DynamicDto> dados = new ArrayList<>();
        for (Object[] row : resultList) {
            dados.add(DynamicDto.build()
                    .with("mes", row[0])
                    .with("sexo", row[1])
                    .with("quantidade", row[2]));
        }
        return dados;
    }

    private List<DynamicDto> getDadosRecorrenciasUltimosMeses(Integer qtdMeses) {

        StringBuilder sql = new StringBuilder();
        sql.append("select count(id_crianca), total_ocorrencias from (");
        sql.append("       select id_crianca, count(*) as total_ocorrencias ");
        sql.append("         from ocorrencias where dh_ocorrencia >= :dataInicial ");
        sql.append("        group by id_crianca) as tmp " );
        sql.append("group by total_ocorrencias order by 2");

        Query query = em.createNativeQuery(sql.toString()).setParameter("dataInicial",
                RelatoriosUtils.getDataAMesesAtras(qtdMeses));
        List<Object[]> resultList = query.getResultList();
        List<DynamicDto> dados = new ArrayList<>();
        for (Object[] row : resultList) {
            dados.add(DynamicDto.build()
                    .with("totalOcorrencias", row[1])
                    .with("qtdCriancas", row[0]));
        }
        return dados;
    }


    private List<DynamicDto> getDadosRecorrenciasNoMes(Integer ano, Integer mes) {

        StringBuilder sql = new StringBuilder();
        sql.append("select count(id_crianca), total_ocorrencias from (");
        sql.append("       select id_crianca, count(*) as total_ocorrencias ");
        sql.append("         from ocorrencias where extract(year from dh_ocorrencia) = :ano and extract(month from dh_ocorrencia) = :mes  ");
        sql.append("        group by id_crianca) as tmp " );
        sql.append("group by total_ocorrencias order by 2");

        Query query = em.createNativeQuery(sql.toString()).setParameter("ano", ano).setParameter("mes", mes);
        List<Object[]> resultList = query.getResultList();
        List<DynamicDto> dados = new ArrayList<>();
        for (Object[] row : resultList) {
            dados.add(DynamicDto.build()
                    .with("totalOcorrencias", row[1])
                    .with("qtdCriancas", row[0]));
        }
        return dados;
    }

}
