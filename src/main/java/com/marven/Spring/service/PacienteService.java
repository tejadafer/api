
package com.marven.Spring.service;

import com.marven.Spring.entity.Paciente;
import com.marven.Spring.repository.IPacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired   // inyectamos el repositorio en Service
    IPacienteRepository pacienteRepo; //variable del tipo repositorio
    // ahora creamos los metodos para el CRUD
    // ACA VAN LOS VERVOS DE LA BASE DE DATOS MY SQL
    //Create
    public void save(Paciente paci){
        pacienteRepo.save(paci);
    }
    
    //Read
    public List<Paciente> list(){
        return pacienteRepo.findAll();
        }
    public Optional<Paciente> getById(int id){
            return pacienteRepo.findById(id);
    }
    public Optional<Paciente> getByNombre(String nombre){
        return pacienteRepo.findByNombre(nombre);
    }
    public Optional<Paciente> getByDni(String dni){
        return pacienteRepo.findByDni(dni);
    }
    public Optional<Paciente> getByHabitacion(int habitacion){
        return pacienteRepo.findByHabitacion(habitacion);
    }
    //update no se hace ... utuliza el save
    
    //delete
    public void delete(int id){
        pacienteRepo.deleteById(id); // aca se puede devolver una exeption pero en nuestro ejemplo
    }                                // lo vamos a manejar desde http status en la capa controladora
    public boolean exitsById(int id){
        return pacienteRepo.existsById(id);
    }
    public boolean exitsByNombre(String nombre){
        return pacienteRepo.existsByNombre(nombre);
   }
}
