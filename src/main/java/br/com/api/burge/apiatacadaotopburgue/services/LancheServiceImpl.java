package br.com.api.burge.apiatacadaotopburgue.services;

import br.com.api.burge.apiatacadaotopburgue.domain.associativa.LancheIngrediente;
import br.com.api.burge.apiatacadaotopburgue.domain.ingrediente.Ingredientes;
import br.com.api.burge.apiatacadaotopburgue.domain.lanche.Lanche;
import br.com.api.burge.apiatacadaotopburgue.exceptions.ObjectNotFoundException;
import br.com.api.burge.apiatacadaotopburgue.repositories.IngredienteRepository;

import br.com.api.burge.apiatacadaotopburgue.repositories.LancheRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class LancheServiceImpl {

    @Autowired
    private LancheRepository lancheRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;


    @Transactional
    public Lanche criarLanche(Lanche lanche) {

        List<LancheIngrediente> novosIngredientes = new ArrayList<>();

        for (LancheIngrediente lancheIngrediente : lanche.getIngredientes()) {
            // Recupere o ingrediente do banco
            Ingredientes ingrediente = ingredienteRepository.findById(lancheIngrediente.getIngrediente().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Ingrediente não encontrado"));

            // Estabelecer a relação com o lanche
            lancheIngrediente.setIngrediente(ingrediente);
            lancheIngrediente.setLanche(lanche);

            // Adiciona à lista temporária
            novosIngredientes.add(lancheIngrediente);
        }

        // Adiciona todos os novos ingredientes ao lanche
        lanche.setIngredientes(novosIngredientes);

        // Calcular o total dos preços dos ingredientes
        BigDecimal totalPreco = calcularPrecoTotal(lanche.getIngredientes());
        lanche.setTotal(totalPreco); // Definindo o total no lanche

        // Salvar o lanche no banco
        return lancheRepository.save(lanche);


    }


    private BigDecimal calcularPrecoTotal(List<LancheIngrediente> ingredientes) {
        BigDecimal total = ingredientes.stream()
                .map(ingrediente -> {
                    BigDecimal preco = ingrediente.getIngrediente().getPreco();
                    return (preco != null ? preco : BigDecimal.ZERO);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }









}







