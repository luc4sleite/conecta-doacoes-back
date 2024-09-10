package com.senac.conectadoacoesback.service;

import com.senac.conectadoacoesback.domain.endereco.Endereco;
import com.senac.conectadoacoesback.dto.EnderecoDTO;
import com.senac.conectadoacoesback.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> findAll() {
        return enderecoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnderecoDTO> findById(Long id) {
        return enderecoRepository.findById(id).map(this::convertToDTO);
    }

    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        Endereco endereco = convertToEntity(enderecoDTO);
        return convertToDTO(enderecoRepository.save(endereco));
    }

    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }

    private EnderecoDTO convertToDTO(Endereco endereco) {
        return new EnderecoDTO(endereco.getId(), endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(),
                endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getPais());
    }

    private Endereco convertToEntity(EnderecoDTO enderecoDTO) {
        return new Endereco(enderecoDTO.getId(), enderecoDTO.getCep(), enderecoDTO.getLogradouro(), enderecoDTO.getNumero(),
                enderecoDTO.getComplemento(), enderecoDTO.getBairro(), enderecoDTO.getCidade(), enderecoDTO.getEstado(), enderecoDTO.getPais());
    }
}