package com.senac.conectadoacoesback.service;

import com.senac.conectadoacoesback.domain.empresa.Empresa;
import com.senac.conectadoacoesback.domain.endereco.Endereco;
import com.senac.conectadoacoesback.dto.EmpresaDTO;
import com.senac.conectadoacoesback.repository.EmpresaRepository;
import com.senac.conectadoacoesback.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EmpresaDTO> findAll() {
        return empresaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmpresaDTO> findById(String id) {
        return empresaRepository.findById(id).map(this::convertToDTO);
    }

    public EmpresaDTO save(EmpresaDTO empresaDTO) {
        Endereco endereco = empresaDTO.getEndereco();
        if (endereco != null) {
            endereco = enderecoRepository.save(endereco);
            empresaDTO.setEndereco(endereco);
        }

        // Salvar Empresa
        Empresa savedEmpresa = empresaRepository.save(convertToEntity(empresaDTO));

        return convertToDTO(savedEmpresa);
    }

    public void deleteById(String id) {
        empresaRepository.deleteById(id);
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        return new EmpresaDTO(empresa.getId(), empresa.getCnpj(), empresa.getNome(), empresa.getEmail(), empresa.getDescricao(), empresa.getTelefone(), empresa.getEndereco());
    }

    private Empresa convertToEntity(EmpresaDTO empresaDTO) {
        return new Empresa(empresaDTO.getId(), empresaDTO.getCnpj(), empresaDTO.getNome(), empresaDTO.getEmail(), empresaDTO.getDescricao(), empresaDTO.getTelefone(), empresaDTO.getEndereco());
    }
}