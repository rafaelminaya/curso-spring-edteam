package com.edteam.curso.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "user")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    //@JsonIgnore : Permite que al traer el json del usuario no se muestre la contraseña.
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Solo escritura
    @Column(name = "password")
    @Getter @Setter
    private String password;

    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    @Column(name = "apellido")
    @Getter @Setter
    private String apellido;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "telefono")
    @Getter @Setter
    private String telefono;

    @Column(name = "fecha_nac")
    @Getter @Setter
    private Date fechaNac;

    //@JsonProperty()Propiedad opcional, defino el tipo de acceso que va a tener el json,
    //access = JsonProperty.Access.READ_ONLY: En este caso el acceso será que solo se puede leer con "READ_ONLY". La otra opción es "WRITE_ONLY".
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    //Esta será la estrategia de conexión, de muchos a uno. Es decir, un/muchos usuarios tiene un solo rol.
    //Con "FetchType.EAGER" indico que siempre me traiga el contenido. La otra opción es "FetchType.LAZY", para este caso necesitaría controlar cuando va a traer algo de json y cuando no.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id") //Esta será el nombre de la columna que se asociará en la tabla
    @Getter @Setter
    private Role role; //En vez de llamar al atributo "role_id" de tipo int, creamos la variable "role" de tipo de dato "Role".


}
