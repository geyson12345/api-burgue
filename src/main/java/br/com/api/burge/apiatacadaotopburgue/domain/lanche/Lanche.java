package br.com.api.burge.apiatacadaotopburgue.domain.lanche;


import br.com.api.burge.apiatacadaotopburgue.domain.associativa.LancheIngrediente;
import br.com.api.burge.apiatacadaotopburgue.domain.ingrediente.Ingredientes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "lanche")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lanche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total; // Novo campo para armazenar o total


    @OneToMany(mappedBy = "lanche", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LancheIngrediente> ingredientes = new ArrayList<>();


















}
