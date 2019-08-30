package org.conselhotutelar.repositorios;

import org.conselhotutelar.enums.AgenteViolador;
import org.conselhotutelar.enums.PersistenceAction;
import org.conselhotutelar.enums.ProcedenciaDenuncia;
import org.conselhotutelar.modelos.entidades.Atendimentos;
import org.conselhotutelar.modelos.entidades.Ocorrencias;
import org.conselhotutelar.utilitarios.AbstractGenericCrud;
import org.conselhotutelar.utilitarios.BusinessException;
import org.conselhotutelar.utilitarios.GenericRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.conselhotutelar.enums.PersistenceAction.INSERT;
import static org.conselhotutelar.enums.PersistenceAction.UPDATE;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OcorrenciasRepository extends AbstractGenericCrud<Ocorrencias> {

    @Inject
    private GenericRepository<Ocorrencias> repository;

    @Override
    public GenericRepository<Ocorrencias> getRepository() {
        return repository;
    }

    public List<Ocorrencias> getHistoricoOcorrenciasCrianca(Long idCrianca) {
        if (idCrianca == null) { return new ArrayList<>(); }
        return repository.getByCriteria("o.crianca.id = " + idCrianca + " order by dh_ocorrencia desc");
    }

    @Override
    public void applyBusinessRules(Ocorrencias ocorrencia, PersistenceAction action) {

        if (action.equals(INSERT) || action.equals(UPDATE)) {

            try {
                ProcedenciaDenuncia.byValue(ocorrencia.getProcedenciaDenuncia());
            } catch (IllegalArgumentException e) {
                throw new BusinessException(e.getMessage());
            }
            try {
                AgenteViolador.byValue(ocorrencia.getAgenteViolador());
            } catch (IllegalArgumentException e) {
                throw new BusinessException(e.getMessage());
            }

        }

        super.applyBusinessRules(ocorrencia, action);
    }
}
