package org.conselhotutelar.repositorios;

import org.conselhotutelar.enums.PersistenceAction;
import org.conselhotutelar.enums.Sexo;
import org.conselhotutelar.enums.SimNao;
import org.conselhotutelar.modelos.entidades.Criancas;
import org.conselhotutelar.utilitarios.AbstractGenericCrud;
import org.conselhotutelar.utilitarios.BusinessException;
import org.conselhotutelar.utilitarios.GenericRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.TimeZone;

import static org.conselhotutelar.enums.PersistenceAction.INSERT;
import static org.conselhotutelar.enums.PersistenceAction.UPDATE;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CriancasRepository extends AbstractGenericCrud<Criancas> {

    @Inject
    private GenericRepository<Criancas> repository;

    @Override
    public GenericRepository<Criancas> getRepository() {
        return repository;
    }

    @Override
    public void applyBusinessRules(Criancas crianca, PersistenceAction action) {

        if (action.equals(INSERT) || action.equals(UPDATE)) {
            Calendar calendario = Calendar.getInstance(TimeZone.getTimeZone("UTC-3"));
            calendario.set(Calendar.YEAR, calendario.get(Calendar.YEAR) - 18);
            if (crianca.getDtNascimento() != null && crianca.getDtNascimento().before(calendario.getTime())) {
                throw new BusinessException("O adolescente informado tem mais que 18 anos.");
            }

            try {
                Sexo.byValue(crianca.getSexo());
            } catch (IllegalArgumentException e) {
                throw new BusinessException(e.getMessage());
            }
            try {
                SimNao.byValue(crianca.getDeficiente());
            } catch (IllegalArgumentException e) {
                throw new BusinessException("Selecione a opção 'S - Sim' ou 'N - Não' para o campo deficiente.");
            }

        }

        super.applyBusinessRules(crianca, action);
    }
}
