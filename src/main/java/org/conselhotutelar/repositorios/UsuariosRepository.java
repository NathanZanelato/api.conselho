package org.conselhotutelar.repositorios;

import org.conselhotutelar.modelos.entidades.Usuarios;
import org.conselhotutelar.utilitarios.AbstractGenericCrud;
import org.conselhotutelar.utilitarios.GenericRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuariosRepository extends AbstractGenericCrud<Usuarios> {

    @Inject
    private GenericRepository<Usuarios> repository;

    @Override
    public GenericRepository<Usuarios> getRepository() {
        return repository;
    }

    public Usuarios findByUsernameAndPassword(String username, String password) {
        List<Usuarios> list = repository.getByCriteria("username = '".concat(username).concat("'").concat(" and password = '").concat(password).concat("'"));
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
