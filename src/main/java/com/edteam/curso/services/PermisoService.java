package com.edteam.curso.services;

import com.edteam.curso.dao.PermisoDao;
import com.edteam.curso.models.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Service : Anotación para indicar que esta clase funcionará como un servicio.
@Service
public class PermisoService {
    @Autowired
    PermisoDao permisoDao;
    public List<Permiso> getAll(){
        return permisoDao.getAll();
    }

    public Permiso get(@PathVariable long id){
        return  permisoDao.get(id);
    }

    public Permiso register(@RequestBody Permiso permiso){
        return permisoDao.register(permiso);
    }

    public Permiso update(Permiso permiso){
        return  permisoDao.update(permiso);
    }

    public void delete(@PathVariable long id){
        permisoDao.delete(id);
    }
}
