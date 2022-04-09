package com.edteam.curso.controllers;

import com.edteam.curso.models.User;
import com.edteam.curso.services.UserService;
import com.edteam.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    /*
    - permisoService: Es la variable que representa la capa de servicios, cuya clase está ubicada enel package "services",
     Esta es necesaria, ya que de poner toda la lógica en el controller se hace insostenible en el tiempo.
    - @Autowired : Me carga el objeto automáticamente, funciona similar a un singleton. Acá hacemos la inyección de dependencias.
    - private JWTUtil jwtUtil; : Hacemos la inyección de dependencias también para la clase "JWTUil" que hemos creado.
    */
    @Autowired
    UserService userService;

    @Autowired
    private JWTUtil jwtUtil;
    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User> getAll(@RequestHeader(value = "Authorization") String token){
        if (!validarToken(token)){
            return null;
        }
        return userService.getAll();
    }
    */
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
    void register(@RequestBody User user){
        userService.register(user);
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

    //Inicio de sesion, necesita email y contraseña
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    //Map<String, Object> : El String por el tipo de dato cadena de caracteres, que será el "token" y el segundo, es Object por el objeto de tipo "User".
    Map<String, Object> login(@RequestBody User dto){
        User user = userService.login(dto);

        Map<String, Object> result = new HashMap<>();
        if(user != null){
            //Devolvemos un token si el usuario inicia sesion correctamente
            //jwtUtil : Variable obtenida por la inyección de dependencias.
            String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
            result.put("token", token);
            result.put("user", user);
        }
        return result;
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
}
