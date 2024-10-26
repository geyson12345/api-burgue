package br.com.api.burge.apiatacadaotopburgue.domain.ingrediente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Table(name = "ingredientes")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;



}



