package br.com.api.burge.apiatacadaotopburgue.repositories;

import br.com.api.burge.apiatacadaotopburgue.domain.ingrediente.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingredientes,Integer> {}
