package org.conselhotutelar.repositorios;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.conselhotutelar.modelos.entidades.Conselheiras;
import org.conselhotutelar.utilitarios.AbstractGenericCrud;
import org.conselhotutelar.utilitarios.GenericRepository;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ConselheirasRepository extends AbstractGenericCrud<Conselheiras> {

    @Inject
    private GenericRepository<Conselheiras> repository;

    @Override
    public GenericRepository<Conselheiras> getRepository() {
        return repository;
    }
}
