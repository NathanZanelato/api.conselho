package org.conselhotutelar.repositorios;

import org.conselhotutelar.enums.DireitoViolado;
import org.conselhotutelar.enums.MedidaAplicada;
import org.conselhotutelar.enums.PersistenceAction;
import org.conselhotutelar.enums.SimNao;
import org.conselhotutelar.modelos.entidades.Atendimentos;
import org.conselhotutelar.utilitarios.AbstractGenericCrud;
import org.conselhotutelar.utilitarios.BusinessException;
import org.conselhotutelar.utilitarios.GenericRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import static org.conselhotutelar.enums.PersistenceAction.INSERT;
import static org.conselhotutelar.enums.PersistenceAction.UPDATE;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AtendimentosRepository extends AbstractGenericCrud<Atendimentos> {

    @Inject
    private GenericRepository<Atendimentos> repository;

    @Override
    public GenericRepository<Atendimentos> getRepository() {
        return repository;
    }

    @Override
    public void applyBusinessRules(Atendimentos atendimento, PersistenceAction action) {

        if (action.equals(INSERT) || action.equals(UPDATE)) {

            try {
                if (atendimento.getMedidaAplicada() != null) {
                    MedidaAplicada.byValue(atendimento.getMedidaAplicada());
                }
            } catch (IllegalArgumentException e) {
                throw new BusinessException(e.getMessage());
            }

            try {
                if (atendimento.getViolacaoDireito() != null) {
                    DireitoViolado.byValue(atendimento.getViolacaoDireito());
                }
            } catch (IllegalArgumentException e) {
                throw new BusinessException(e.getMessage());
            }

            try {
                if (atendimento.getPossuiAgendamento() != null) {
                    SimNao possuiAgendamento = SimNao.byValue(atendimento.getPossuiAgendamento());
                    if (SimNao.SIM.equals(possuiAgendamento) && atendimento.getDhAtendimento() == null) {
                        throw new BusinessException("A data e hora de agendamento do atendimento não pode ser em branco.");
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new BusinessException(e.getMessage());
            }
        }

        super.applyBusinessRules(atendimento, action);
    }
}
