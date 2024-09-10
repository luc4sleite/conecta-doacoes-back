package com.senac.conectadoacoesback.controller;

import com.senac.conectadoacoesback.dto.EmpresaDTO;
import com.senac.conectadoacoesback.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<EmpresaDTO> getAllEmpresas() {
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable String id) {
        Optional<EmpresaDTO> empresa = empresaService.findById(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpresaDTO createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        return empresaService.save(empresaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable String id, @RequestBody EmpresaDTO empresaDetails) {
        Optional<EmpresaDTO> empresa = empresaService.findById(id);
        if (empresa.isPresent()) {
            EmpresaDTO updatedEmpresa = empresa.get();
            updatedEmpresa.setCnpj(empresaDetails.getCnpj());
            updatedEmpresa.setNome(empresaDetails.getNome());
            updatedEmpresa.setEmail(empresaDetails.getEmail());
            updatedEmpresa.setDescricao(empresaDetails.getDescricao());
            updatedEmpresa.setTelefone(empresaDetails.getTelefone());
            updatedEmpresa.setEndereco(empresaDetails.getEndereco());
            return ResponseEntity.ok(empresaService.save(updatedEmpresa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable String id) {
        if (empresaService.findById(id).isPresent()) {
            empresaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}