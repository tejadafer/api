
package com.marven.Spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {
    
     @GetMapping  // muestra en la pag porque por default ejecuta get
        public String hola(){
            return "hola mundo";
        }
        @GetMapping("/hola/{nom}")  // por la URL del navegador le mandamos lo que va a mostrar
        public String hola1(@PathVariable String nom){
            return "Hola Mundo " + nom;
        }
        @GetMapping("/hola")  /*Asi se llama...//localhost:8080/hola?nombre=Fer*/
        public String hola3(@RequestParam String nombre){
            return "Hola Mundo3 " + nombre;
        }
    
}
