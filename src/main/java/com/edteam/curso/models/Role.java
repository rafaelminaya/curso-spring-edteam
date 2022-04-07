package com.edteam.curso.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
- @Entity: Anotación para indicar que esta clase será una entidad de base de datos.
- @Table(name = "role"): Nombre que esta clase tomará en la tabla de la base de datos.
- Estas dos anotaciones "@ToString" y "@EqualsAndHashCode" solo funcionan cuando usamos un "fetch = FetchType.EAGER" en las relaciones de tablas.
  De necesitar usar fetch = FetchType.LAZY", no podemos usar las anotaciones mencionadas.
- @ToString: Anotación de Lombok, para generar la función "toString()" luego de compilar el código
- @EqualsAndHashCode: Anotación de Lombok, para generar las funciones "equals()" y "hashCode()".
- @NoArgsConstructor: Anotación de Lombok, para generar un constructor sin argumentos.
- @AllArgsConstructor: Anotación de Lombok, para generar un constructor con todos los argumentos de la clase.
 */

@Entity
@Table(name = "role")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    @Column(name = "nombre")
    @Getter
    @Setter
    private String nombre;

    /*
    - Los roles tienen varios permisos, así que agregamos lo sgte.
    - De esta forma podremos controlar la parte de permisos.
    - @OneToMany : Para indicar que un role tendrá varios permisos.
    - cascade = CascadeType.ALL : Para casca le vamos a indicar que traiga todos los resultados.
    - fetch = FetchType.EAGER : Tipo de estrategia que traiga toda el contenido de los objetos, no solamente que me traiga la referencia.
    - mappedBy = "role" : Hacemos que el que mapee toda esto sea el "role" de usuario. Este es el atributo "private Role role;" del la clase "Permiso.java".
    */

    /**
     * permisos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    @Getter @Setter
    //Set<> : Tipo de dato similar a "List". Usamos "Set" funciona mucho mejor al momento de comparar en el listado dos permisos diferentes al momento de traer los datos
    private Set<Permiso> permisos;

}
