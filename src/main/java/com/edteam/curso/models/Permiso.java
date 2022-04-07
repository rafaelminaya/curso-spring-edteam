package com.edteam.curso.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
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
@Table(name = "permisos")
@NoArgsConstructor
@AllArgsConstructor
public class Permiso  extends BaseEntity{
    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    /**
     * Role
     */

    /*
    - @JsonIdentityReference(alwaysAsId = true) : Esto es para que no nos traiga el objeto sino únicamente el ID.
    - @JsonProperty("role_id") : Indicamos el nombre que tendrá en "json" esta propiedad ya que mostraremos solo el ID, no toda el objeto.
    - @ManyToOne(): Esta será la estrategia de conexión. Un/Muchos permisos tendrán un role.
    - fetch = FetchType.LAZY: "LAZY" cuando consultemos a la base de datos por permisos, no nos traerá toda el contenido de roles, sino que guardará solo la referencia, es decir, el ID.
      De esta forma evitamos un loop en que cuando traigamos un "role", este nos trae permisos y este nos traiga roles no nos sobrecarguemos.
    - @JoinColumn :  Nombre que tendrá en la columna de la BD, en este caso "role_id".
    */

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //Esto también para que nos traiga solamente el id. Y la propiedad a la que hará referencia es al "id".
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @Getter @Setter
    private Role role;

    //Función "toString" solo para el atributo "nombre", creado con el mismo IDE. Ya que (fetch = FetchType.LAZY) no nos permite usar "@EqualsAndHashCode" de Lombok.
    @Override
    public String toString() {
        return "Permiso{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
    //Funciones "equals" y "hashCode" solo para el atributo "nombre", creado con el mismo IDE. Ya que (fetch = FetchType.LAZY) no nos permite usar "@EqualsAndHashCode" de Lombok.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permiso permiso = (Permiso) o;
        return Objects.equals(nombre, permiso.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
