package com.edteam.curso.models;

import javax.persistence.*;
import java.util.Date;

//@MappedSuperclass : De esta forma quedará como una clase madre. Es decir, no se considerará para crearse como tabla en la BD.
@MappedSuperclass
public class BaseEntity {
    /*
    - UUID : Esto es una alterntiva para un id muy gigante
    */

    /*
    - @Id : Indica que es la PK de la BD.
    - @GeneratedValue : Indica que es un valor general.
    - GenerationType.IDENTITY : Significa que en la BD será identity, auto incremental.
    - updatable = false : Significa que no se va modificar.
    - nullable = false : Significa que no será nulo.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    /*
    - Atributo para guardar la fecha de creación del usuario.
    - columnDefinition = "DATETIME" : De esta forma especificamos el tipo de dato en la base de datos. Pudimos usar esto en los demás atributos.
    - updatable = false : Indicamos que no será modificable.
    - nullable = false : Indicamos que nunca será nulo.
    */
    @Column(columnDefinition = "DATETIME", updatable = false, nullable = false)
    protected Date createdDate;

    //Atributo para guardar la fecha de modificación del usuario
    @Column(columnDefinition = "DATETIME", updatable = false, nullable = false)
    protected Date updatedDate;
    /*
    - @PrePersist : Indica que antes de persistir/crear algo va a realizar lo sgte.
    - Esta función verificará que si la fecha a crear es nula, le asignará una nueva fecha.
    - También cada vez que persiste esta función, añadirá una nueva fecha al atributo "updatedDate".
    */
    @PrePersist
    protected  void onCreate(){
        updatedDate = new Date();
        if(createdDate == null){
            createdDate = new Date();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}



