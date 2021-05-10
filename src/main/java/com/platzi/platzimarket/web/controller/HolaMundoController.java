package com.platzi.platzimarket.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")  // controlador
public class HolaMundoController {

    @GetMapping("/hola")  // vista-metodo-ruta
    public String saludar (){
        return "Nunca pares de aprender";
    }
}
