package org.conselhotutelar.utilitarios;

import org.conselhotutelar.modelos.ValueObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public class GenericRepository<T extends ValueObject> {

    private final EntityManager em;
    private Class<T> valueObjectType;

    public GenericRepository(EntityManager em, Class<T> valueObjectType) {
        this.em = em;
        this.valueObjectType = valueObjectType;
    }

    public T find(Long id) {
        T valueObject = em.find(valueObjectType, id);
        if (valueObject == null) {
            throw new EntityNotFoundException();
        }
        return valueObject;
    }

    public List<T> getAll() {
        return em.createQuery("select o from ".concat(valueObjectType.getName()).concat(" o"), valueObjectType).getResultList();
    }

    public List<T> getByCriteria(String criteria) {
        return em.createQuery("select o from ".concat(valueObjectType.getName()).concat(" o WHERE ").concat(criteria), valueObjectType).getResultList();
    }

    public boolean existsByCriteria(String criteria) {
        return em.createQuery("select count(o)>0 from ".concat(valueObjectType.getName()).concat(" o WHERE ").concat(criteria), Boolean.class).getSingleResult();
    }

    public T insert(T valueObject) {
        em.persist(valueObject);
        return valueObject;
    }

    public T update(T valueObject) {
        find(valueObject.getId()); // find para validar a existencia do objeto persistido
        return em.merge(valueObject);
    }

    public void delete(Long id) {
        T valueObject = find(id); // find para validar a existencia do objeto persistido e recupera-lo
        em.remove(valueObject);
    }
}
