package br.com.api.burge.apiatacadaotopburgue.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ListaIngredientesDTO(

        int id,
        String nome,
        BigDecimal preco

) {
}


