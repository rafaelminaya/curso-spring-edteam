package com.edteam.curso.dao.imp;

import com.edteam.curso.dao.PermisoDao;
import com.edteam.curso.models.Permiso;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
- @Transactional :
* Esta anotación se añade en cada función y también a la clase.
* Esto se aplica en cada transacción/consulta que vaya a la base de datos.
- @Repository : Para que pueda acceder a la BD, ya que hace referencia al repositorio de la base de datos. Esto se aplica a la clase.
*/
@Transactional
@Repository
public class PermisoDaoImp implements PermisoDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Permiso> getAll() {
        String hql = "FROM Permiso as p";
        return (List<Permiso>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Permiso get(long id) {
        return entityManager.find(Permiso.class, id);
    }

    @Transactional
    @Override
    public Permiso register(Permiso permiso) {
        entityManager.merge(permiso);
        return permiso;
    }

    @Transactional
    @Override
    public Permiso update(Permiso permiso) {
        entityManager.merge(permiso);
        return permiso;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Permiso permiso = get(id);
        entityManager.remove(permiso);
    }
}
