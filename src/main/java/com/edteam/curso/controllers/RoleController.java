package com.edteam.curso.controllers;

import com.edteam.curso.models.Role;
import com.edteam.curso.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    /*
    - permisoService: Es la variable que representa la capa de servicios, cuya clase está ubicada enel package "services",
     Esta es necesaria, ya que de poner toda la lógica en el controller se hace insostenible en el tiempo.
    - @Autowired : Me carga el objeto automáticamente, funciona similar a un singleton. Acá hacemos la inyección de dependencias.
    */
    @Autowired
    RoleService roleService;

    //Trae todos los roles
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Role> getAll(){
        return roleService.getAll();
    }

    //Trae un role
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Role get(@PathVariable long id){
        return roleService.get(id);
    }

    //Registrar un role
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Role register(@RequestBody Role role){
        return roleService.register(role);
    }

    //Modificar un role
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Role update(@RequestBody Role role){
        return roleService.update(role);
    }

    //Elimina un role
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable long id){
        roleService.delete(id);
    }
}
