package com.ejemplo.breakingbad.servicio;

import com.ejemplo.breakingbad.modelo.Frase;
import com.ejemplo.breakingbad.repositorio.FraseRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FraseServicio {

    private final FraseRepositorio repo;
    private final Random random = new Random();

    public FraseServicio(FraseRepositorio repo) {
        this.repo = repo;
    }

    public Frase obtenerFraseAleatoria() {
        List<Frase> frases = repo.findAll();
        if (frases.isEmpty()) return null;
        return frases.get(random.nextInt(frases.size()));
    }
}
