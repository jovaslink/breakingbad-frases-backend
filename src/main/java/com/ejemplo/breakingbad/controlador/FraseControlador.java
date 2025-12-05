package com.ejemplo.breakingbad.controlador;

import com.ejemplo.breakingbad.modelo.Frase;
import com.ejemplo.breakingbad.servicio.FraseServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frases")
@CrossOrigin
public class FraseControlador {

    private final FraseServicio servicio;

    public FraseControlador(FraseServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/aleatoria")
    public Frase obtenerAleatoria() {
        return servicio.obtenerFraseAleatoria();
    }
}
