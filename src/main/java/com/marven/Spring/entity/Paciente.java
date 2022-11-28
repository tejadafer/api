
package com.marven.Spring.entity;

import java.io.Serializable; // me pedia implementar esto
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity     //lleva la instancia jpa y sirve para convertir la clase en tabla
@Table(name = "pacientes")    // con esto le damos un nombre a la tabla futura BD
@Getter //gracias a Lombok no tenemos que codificar geter y seter y constructores
@Setter
@NoArgsConstructor //constructor vacio
// la anotation @Data crea todo los seter geter costructores iqual toStrin todo 
//pero en nuestro ejercicio no sirve porque el ID lo vamos a generar automaticamente no con el costructor
public class Paciente  implements Serializable{
   @Id   // esto es el mapeo con JPA
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id; // el id lo vamos a generar automaticamente
   @Column(name = "nombre")
   @NotBlank
    private String nombre;
   @Column(name = "dni")
   @NotBlank
    private String dni;
   @Column(name = "habitacion")
   @NotNull
    private int habitacion;

    public Paciente(String nombre, String dni, int habitacion) {
        this.nombre = nombre;
        this.dni = dni;
        this.habitacion = habitacion;
    }
       
}
