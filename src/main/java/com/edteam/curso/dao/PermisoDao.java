package com.edteam.curso.dao;

import com.edteam.curso.models.Permiso;

import java.util.List;

public interface PermisoDao {
    List<Permiso> getAll();
    Permiso get(long id);
    Permiso register(Permiso permiso);
    Permiso update(Permiso permiso);
    void delete(long id);
}
