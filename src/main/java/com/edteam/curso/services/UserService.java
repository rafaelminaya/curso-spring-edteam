package com.edteam.curso.services;

import com.edteam.curso.dao.UserDao;
import com.edteam.curso.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

//@Service : Anotación para indicar que esta clase funcionará como un servicio.
@Service
public class UserService {
    /*
    - Aca haremos la inyección de dependencias.
    - @Autowired : Me carga el objeto automáticamente, funciona similar a un singleton.
    - UserDao userDao : Sirve para cargar el objeto automáticamente. Ya que si uso esta variable "userDao" no lo instanciará de nuevo.
     Sino que comparte este mismo objeto haciendo que se cumpla la inyección de dependencias.
     - userDao : De esta forma ya tendré cargado el dao y solo lo llamaré en cada una de las instancias (funciones abajo)
    */
    @Autowired
    UserDao userDao;

    public List<User> getAll(){
        return userDao.getAll();
    }

    public User get(@PathVariable long id){
        return  userDao.get(id);
    }

    public void register(@RequestBody User user){
        String hash = generarHash(user.getPassword());
        user.setPassword(hash);
        userDao.register(user);
    }

    public User update(User user){
        return  userDao.update(user);
    }

    public void delete(@PathVariable long id){
        userDao.delete(id);
    }

    //Función que genera el hash a partir del password que recibe.
    private String generarHash(String password){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //1 : Este primer argumento, indica la cantidad de iteraciones. Mientras más iteraciones más seguro pero gasta más procesos
        return argon2.hash(1, 1024 * 1, 1, password);
    }
    public User login(User user){
        return userDao.login(user);
    }

}
