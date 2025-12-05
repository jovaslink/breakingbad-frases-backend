package com.ejemplo.breakingbad.repositorio;

import com.ejemplo.breakingbad.modelo.Frase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraseRepositorio extends JpaRepository<Frase, Long> {
}
