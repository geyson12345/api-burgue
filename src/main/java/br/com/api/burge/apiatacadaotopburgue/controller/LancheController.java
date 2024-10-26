package br.com.api.burge.apiatacadaotopburgue.controller;

import br.com.api.burge.apiatacadaotopburgue.domain.associativa.LancheIngrediente;
import br.com.api.burge.apiatacadaotopburgue.domain.ingrediente.Ingredientes;
import br.com.api.burge.apiatacadaotopburgue.domain.lanche.Lanche;
import br.com.api.burge.apiatacadaotopburgue.dto.LancheDTO;
import br.com.api.burge.apiatacadaotopburgue.dto.LancheIngredienteDTO;
import br.com.api.burge.apiatacadaotopburgue.dto.ListaIngredientesDTO;
import br.com.api.burge.apiatacadaotopburgue.services.IngredienteServiceImpl;
import br.com.api.burge.apiatacadaotopburgue.services.LancheServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/lanches")
public class LancheController {

    @Autowired
    private LancheServiceImpl lancheService;

    @Autowired
    private IngredienteServiceImpl ingredienteService;

    @Autowired
    private ModelMapper mapper;


    @PostMapping("/personalizado")
    public ResponseEntity<Lanche> criarLanche(@RequestBody LancheDTO lancheDTO) {

        // Criar uma nova instância de Lanche
        Lanche lanche = new Lanche();
        lanche.setNome(lancheDTO.nome());

        List<LancheIngrediente> ingredientes = new ArrayList<>();
        for (LancheIngredienteDTO lancheIngredienteDTO : lancheDTO.ingredientes()) {
            Integer ingredienteId = lancheIngredienteDTO.ingredienteId(); // Obter o ID do ingrediente

            // Usando o serviço para buscar o ingrediente
            Ingredientes ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);

            // Criar e configurar a instância de LancheIngrediente
            LancheIngrediente lancheIngrediente = new LancheIngrediente();
            lancheIngrediente.setIngrediente(ingrediente);
            lancheIngrediente.setLanche(lanche);
            ingredientes.add(lancheIngrediente);
        }

        lanche.setIngredientes(ingredientes);

        // Calcule o total e salve
        Lanche novoLanche = lancheService.criarLanche(lanche);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLanche);


    }


    @GetMapping("/listaringrediente")
    public ResponseEntity<List<ListaIngredientesDTO>> listarIngredientes(){
    return ResponseEntity.ok().body(ingredienteService.listarTodosIngredientes()
                .stream().map(x -> mapper.map(x, ListaIngredientesDTO.class)).collect(Collectors.toList()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ListaIngredientesDTO> atualizarIngrediente(
            @PathVariable Integer id,
            @Valid @RequestBody ListaIngredientesDTO ingredienteDTO) {

        ListaIngredientesDTO atualizado = ingredienteService.atualizarIngrediente(id, ingredienteDTO);

        return ResponseEntity.ok(atualizado);
    }



}


