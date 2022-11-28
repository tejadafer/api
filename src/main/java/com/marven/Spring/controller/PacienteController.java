
package com.marven.Spring.controller;


import com.marven.Spring.entity.Paciente;
import com.marven.Spring.dto.PacienteDto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marven.Spring.service.PacienteService;
//import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/apk")
//@CrossOrigin("http://localhost:4200") //esto se coloca para que la api escuche y se conecte por ese puerto
//al final se comenta porque agregamos los cors en la app principal 
public class PacienteController {
    @Autowired  //inyecto el servicio en controller
    private PacienteService pacienteserv; //variable del tipo servicio
    //trae todo los registros de la BD
    @GetMapping("/vertodos") //el endpoint para llamarla va ser /apk/vertodos
    private ResponseEntity<List<Paciente>> list(){ //al poner resposeentity nos permite retornar un estado y la lista
       List<Paciente> paci=pacienteserv.list(); 
       return ResponseEntity.status(HttpStatus.OK).body(paci);
       //return ResponseEntity.ok(pacientes); otra forma de hacerlo   
    }
    //traer un libro por id
    @GetMapping("/ver/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable int id){
        if (!pacienteserv.exitsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//build vendria ser como vacio 
        }
        Paciente paci=pacienteserv.getById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(paci);
    }
    // borramos un registro
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){
        if (!pacienteserv.exitsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        pacienteserv.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //crear un libro usando DTO
    @PostMapping("/crear")
    public ResponseEntity<?> create (@RequestBody PacienteDto dtopaciente){
       if (StringUtils.isBlank(dtopaciente.getNombre())){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       } else if (pacienteserv.exitsByNombre(dtopaciente.getNombre())){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       Paciente paci = new Paciente(dtopaciente.getNombre(), dtopaciente.getDni(),
               dtopaciente.getHabitacion()); //los datos vienen del dto
       pacienteserv.save(paci);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // editar
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PacienteDto dtopaciente){
        if (!pacienteserv.exitsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          } else if (StringUtils.isBlank(dtopaciente.getNombre())){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else if(pacienteserv.exitsByNombre(dtopaciente.getNombre()) &&
                      pacienteserv.getByNombre(dtopaciente.getNombre()).get().getId() != id){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  
            }//esto ultimo es para no tener 2 pacientes con el mismo nombre
        Paciente paci = pacienteserv.getById(id).get(); // obtengo el paciente de ese id
        paci.setNombre(dtopaciente.getNombre());
        paci.setDni(dtopaciente.getDni());
        paci.setHabitacion(dtopaciente.getHabitacion());
        pacienteserv.save(paci);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
}
