package com.edteam.curso.services;

import com.edteam.curso.dao.UserDao;
import com.edteam.curso.models.User;
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

    public User register(@RequestBody User user){
        return userDao.register(user);
    }

    public User update(User user){
        return  userDao.update(user);
    }

    public void delete(@PathVariable long id){
        userDao.delete(id);
    }

}
