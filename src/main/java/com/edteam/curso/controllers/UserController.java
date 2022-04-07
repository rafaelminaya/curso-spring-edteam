package com.edteam.curso.controllers;

import com.edteam.curso.models.User;
import com.edteam.curso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    /*
    - permisoService: Es la variable que representa la capa de servicios, cuya clase está ubicada enel package "services",
     Esta es necesaria, ya que de poner toda la lógica en el controller se hace insostenible en el tiempo.
    - @Autowired : Me carga el objeto automáticamente, funciona similar a un singleton. Acá hacemos la inyección de dependencias.
    */
    @Autowired
    UserService userService;

    //Trae todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User> getAll(){
        return userService.getAll();
    }

    //Trae un usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User get(@PathVariable long id){
        return userService.get(id);
    }

    //Registra un usuario
    @RequestMapping(value = "/", method = RequestMethod.POST)
    User register(@RequestBody User user){
        return userService.register(user);
    }

    //Modifica usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    User update(@RequestBody User user){
        return userService.update(user);
    }

    //elimina un usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable long id){
        userService.delete(id);
    }
}
