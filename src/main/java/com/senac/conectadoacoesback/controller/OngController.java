package com.senac.conectadoacoesback.controller;

import com.senac.conectadoacoesback.dto.OngDTO;
import com.senac.conectadoacoesback.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService ongService;

    @GetMapping
    public List<OngDTO> getAllOngs() {
        return ongService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OngDTO> getOngById(@PathVariable String id) {
        Optional<OngDTO> ong = ongService.findById(id);
        return ong.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OngDTO createOng(@RequestBody OngDTO ongDTO) {
        return ongService.save(ongDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OngDTO> updateOng(@PathVariable String id, @RequestBody OngDTO ongDetails) {
        Optional<OngDTO> ong = ongService.findById(id);
        if (ong.isPresent()) {
            OngDTO updatedOng = ong.get();
            updatedOng.setCnpj(ongDetails.getCnpj());
            updatedOng.setNome(ongDetails.getNome());
            updatedOng.setEmail(ongDetails.getEmail());
            updatedOng.setDescricao(ongDetails.getDescricao());
            updatedOng.setTelefone(ongDetails.getTelefone());
            updatedOng.setTipoDoacao(ongDetails.getTipoDoacao());
            updatedOng.setEndereco(ongDetails.getEndereco());
            return ResponseEntity.ok(ongService.save(updatedOng));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable String id) {
        if (ongService.findById(id).isPresent()) {
            ongService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}