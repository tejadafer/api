
package com.marven.Spring.dto;
import java.io.Serializable;
import lombok.*;  // el asterisco es para importar geter seter y constructores
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PacienteDto implements Serializable {
    @NotBlank // instalar dependencia Spring Boot Starter Validation en pom
    private String nombre;
    @NotBlank 
    private String dni;
    @NotNull 
    private int habitacion;   
    
}
