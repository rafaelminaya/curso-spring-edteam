package com.edteam.curso.controllers;

import com.edteam.curso.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    //trae todos los usuarios
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User>  getAll(){

        List <User> list = new ArrayList<>();
        User user = new User();
        user.setNombre("Lucas");
        user.setApellido("Moy");
        list.add(user);

        return list; //esto retorna un json
    }

    //trae un usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User get(@PathVariable long id){

        User user = new User();
        user.setNombre("Lucas");
        user.setApellido("Moy");

        return user; //esto retorna un json
    }

    //registrar usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    User register(@RequestBody User user){
        //TODO: registrarlo en base de datos
        return user; //esto retorna un json
    }

    //modificar usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    User update(@RequestBody User user){
        //TODO: actualizarlo en base de datos
        return user; //esto retorna un json
    }
    //elimina un usuario
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User delete(@PathVariable long id){

        User user = new User();
        user.setNombre("Lucas");
        user.setApellido("Moy");

        return user; //esto retorna un json
    }

}
