package br.com.api.burge.apiatacadaotopburgue.dto;


import java.util.List;

public record LancheDTO(

        String nome,
        List<LancheIngredienteDTO> ingredientes



) {
}
