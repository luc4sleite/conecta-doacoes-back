package com.senac.conectadoacoesback.controller;

import com.senac.conectadoacoesback.dto.EnderecoDTO;
import com.senac.conectadoacoesback.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> getAllEnderecos() {
        return enderecoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id) {
        Optional<EnderecoDTO> endereco = enderecoService.findById(id);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnderecoDTO createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        return enderecoService.save(enderecoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDetails) {
        Optional<EnderecoDTO> endereco = enderecoService.findById(id);
        if (endereco.isPresent()) {
            EnderecoDTO updatedEndereco = endereco.get();
            updatedEndereco.setCep(enderecoDetails.getCep());
            updatedEndereco.setLogradouro(enderecoDetails.getLogradouro());
            updatedEndereco.setNumero(enderecoDetails.getNumero());
            updatedEndereco.setComplemento(enderecoDetails.getComplemento());
            updatedEndereco.setBairro(enderecoDetails.getBairro());
            updatedEndereco.setCidade(enderecoDetails.getCidade());
            updatedEndereco.setEstado(enderecoDetails.getEstado());
            updatedEndereco.setPais(enderecoDetails.getPais());
            return ResponseEntity.ok(enderecoService.save(updatedEndereco));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        if (enderecoService.findById(id).isPresent()) {
            enderecoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}