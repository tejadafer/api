
package com.marven.Spring.repository;

import com.marven.Spring.entity.Paciente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // los repositorios son todas las interfases que voy a ocupar
public interface IPacienteRepository extends JpaRepository<Paciente, Integer>{
//Jpa repository recibe una class y el tipo de dato del ID
// Repositorio contiene los metodos que maneja los CRUD
// ademas podemos crear metodos propios personalizados
// estos metodos se implementan en la capa servicios    
public Optional<Paciente> findByNombre(String nombre); //cuando devolvemos un objeto tenemos riesgo de que no lo encuentre al agregar optional devuelve un null
public Optional<Paciente> findByDni(String dni);
public Optional<Paciente> findByHabitacion(int habitacion);
// un ejemplo para usar un operador and or between y poder hacer una consulta mas compleja
//findByNombreAndDni(String nombre, String dni); use el and

public boolean existsByNombre(String nombre);
}
