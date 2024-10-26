package br.com.api.burge.apiatacadaotopburgue.services;

import br.com.api.burge.apiatacadaotopburgue.domain.ingrediente.Ingredientes;
import br.com.api.burge.apiatacadaotopburgue.dto.ListaIngredientesDTO;
import br.com.api.burge.apiatacadaotopburgue.exceptions.ObjectNotFoundException;
import br.com.api.burge.apiatacadaotopburgue.repositories.IngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteServiceImpl {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private ModelMapper mapper;



    public Ingredientes buscarIngredientePorId(Integer id) {
        // Verifique se o ID é nulo
        if (id == null) {
            throw new ObjectNotFoundException("O ID do ingrediente não pode ser nulo");
        }

        // Buscando ingrediente no repositório
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Ingrediente não encontrado"));
    }

    public List<ListaIngredientesDTO> listarTodosIngredientes() {
        return ingredienteRepository.findAll()
                .stream()
                .map(ingrediente -> new ListaIngredientesDTO(
                        ingrediente.getId(),
                        ingrediente.getNome(),
                        ingrediente.getPreco()
                ))
                .collect(Collectors.toList());
    }



    public ListaIngredientesDTO atualizarIngrediente(Integer id, ListaIngredientesDTO ingredienteDTO) {
        Ingredientes ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Ingrediente não encontrado"));

        if (ingredienteDTO.nome() != null) {
            ingrediente.setNome(ingredienteDTO.nome());
        }

        ingrediente.setPreco(ingredienteDTO.preco());

        Ingredientes atualizado = ingredienteRepository.save(ingrediente);

        return new ListaIngredientesDTO(atualizado.getId(), atualizado.getNome(), atualizado.getPreco());
    }



}
