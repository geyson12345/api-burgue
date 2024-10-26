package br.com.api.burge.apiatacadaotopburgue.domain.associativa;

import br.com.api.burge.apiatacadaotopburgue.domain.ingrediente.Ingredientes;
import br.com.api.burge.apiatacadaotopburgue.domain.lanche.Lanche;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "lancheingredientes")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LancheIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @ManyToOne
    @JoinColumn(name = "lanche_id", nullable = false)
    @JsonIgnore
    private Lanche lanche;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingredientes ingrediente;




}
