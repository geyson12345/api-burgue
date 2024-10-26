package br.com.api.burge.apiatacadaotopburgue.repositories;

import br.com.api.burge.apiatacadaotopburgue.domain.lanche.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancheRepository extends JpaRepository<Lanche, Integer> {}
