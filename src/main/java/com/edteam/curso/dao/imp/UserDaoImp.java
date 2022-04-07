package com.edteam.curso.dao.imp;

import com.edteam.curso.dao.UserDao;
import com.edteam.curso.models.User;
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
public class UserDaoImp implements UserDao {
    /*
    - @PersistenceContext : Anotación para persistir en la base de datos.
    - EntityManager : Clase para manejar la base de datos.
     */
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<User> getAll() {
        /*
        - Esta es una query de hibernate
        - User : Hace referencia a la clase de java, no a la base de datos directamente.
        - hql : Denominado así porque sería un "hibernate query language".
        - (List<User>)  : Parsea el resultado obtenido en tipo de dato "list" del tipo "User".
        - getResultList() : Agarra toda esta sentencia obtenida y la transforma en un listado.
        */
        String hql = "FROM User as u";
        return (List<User>) entityManager.createQuery(hql).getResultList();
    }
    @Transactional
    @Override
    public User get(long id) {
        //User.class : Clase que buscará según el "id" enviado en el segundo argumento.
        return entityManager.find(User.class, id);
    }

    //Si quisiseramos que envíe un email para una verificación del usuario o similar,
    // deberíamos hacerlo dentro de la capa de "services" por regla de negocio standard.
    @Transactional
    @Override
    public User register(User user) {
        //merge() : Función para guardar o actualizar.
        entityManager.merge(user);
        return user;
    }
    @Transactional
    @Override
    public User update(User user) {
        //merge() :  Función para guardar o actualizar.
        entityManager.merge(user);
        return  user;
    }

    @Transactional
    @Override
    public void delete(long id) {
        /*
        - get() : Función creada más arriba para obtener un usuario.
        - remove() : Función hibernate para eliminar en la BD, se envía de parámetro un Objeto.
         Por esto hacemos el paso previo de buscar el usuario y enviar el objeto encontrado.
        */
        User user = get(id);

        entityManager.remove(user);
    }
}
