package com.edteam.curso.dao.imp;

import com.edteam.curso.dao.UserDao;
import com.edteam.curso.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public void register(User user) {
        //merge() : Función para guardar o actualizar.
        entityManager.merge(user);
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

    @Override
    public User login(User dto) {
        //variable que indica si el usuario se autenticó, false por defecto
        boolean isAutheticated = false;

        //buscamos el usuario que coincida con el email
        String hql = "FROM User as u WHERE u.password is not null and u.email = :email";

        //ejecutamos la query guardando el resultado en la variable "result" el cual puede tiener varios resultados.
        //Enviamos el valor del ":email" proveniente de la consulta, por medio de la funcíón  ".setParameter()"
        List<User> result = entityManager.createQuery(hql.toString()).setParameter("email", dto.getEmail()).getResultList();

        //Retornamos un valor "null" en caso no encuentre el usuario con el email.
        if(result.size() == 0){
            return null;
        }

        //Guardamos el primer registro del usuario con la función "get(0)" propia del tipo de dato "List".
        User user = result.get(0);
        //cambiamos a true el estado de que se autenticó el usuario.
        isAutheticated = true;

        //Verificamos que haya una contraseña del usuario que se está recibiendo como argumento.

        if(!dto.getPassword().isEmpty()){
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            //Comparamos las dos contraseñas. Es decir, el hash del usuario de la BD con el hash del usuario recibido por argumento.
            isAutheticated = argon2.verify(user.getPassword(), dto.getPassword());
        }

        //Devolvemos el usuario y null en caso que no.
        if(isAutheticated){
            return user;
        }
        return null;
    }
}
