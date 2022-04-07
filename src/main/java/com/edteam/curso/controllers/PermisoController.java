package com.edteam.curso.controllers;

import com.edteam.curso.models.Permiso;
import com.edteam.curso.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permiso")
public class PermisoController {
    /*
    - permisoService: Es la variable que representa la capa de servicios, cuya clase está ubicada enel package "services",
     Esta es necesaria, ya que de poner toda la lógica en el controller se hace insostenible en el tiempo.
    - @Autowired : Me carga el objeto automáticamente, funciona similar a un singleton. Acá hacemos la inyección de dependencias.
    */
    @Autowired
    PermisoService permisoService;

    //Trae todos los permisos
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Permiso> getAll(){
        return permisoService.getAll();
    }

    //Trae un permiso
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Permiso get(@PathVariable long id){
        return permisoService.get(id);
    }

    //Registra un permiso
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Permiso register(@RequestBody Permiso permiso){
        return permisoService.register(permiso);
    }

    //Modifica un permisos
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Permiso update(@RequestBody Permiso permiso){
        return permisoService.update(permiso);
    }

    //Elimina un permiso
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable long id){
        permisoService.delete(id);
    }
}
