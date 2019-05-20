package org.conselhotutelar.utilitarios;

import org.conselhotutelar.modelos.ValueObject;
import org.conselhotutelar.enums.PersistenceAction;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.Valid;
import java.util.List;

import static org.conselhotutelar.enums.PersistenceAction.*;

public abstract class AbstractGenericCrud<T extends ValueObject> {

    protected abstract GenericRepository<T> getRepository();

    public T findById(Long id) {
        return getRepository().find(id);
    }

    public List<T> findAll() {
        return getRepository().getAll();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T insert(@Valid T valueObject) throws BusinessException {
        applyBusinessRules(valueObject, INSERT);
        return getRepository().insert(valueObject);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T update(@Valid T valueObject) throws BusinessException {
        applyBusinessRules(valueObject, UPDATE);
        return getRepository().update(valueObject);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Long id) {
        applyBusinessRules(findById(id), DELETE);
        getRepository().delete(id);
    }

    public void applyBusinessRules(T valueObject, PersistenceAction action) {
    }

}
