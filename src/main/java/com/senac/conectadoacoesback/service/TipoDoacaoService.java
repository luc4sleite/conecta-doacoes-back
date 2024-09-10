package com.senac.conectadoacoesback.service;

import com.senac.conectadoacoesback.domain.tipodoacao.TipoDoacao;
import com.senac.conectadoacoesback.dto.TipoDoacaoDTO;
import com.senac.conectadoacoesback.repository.TipoDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoDoacaoService {

    @Autowired
    private TipoDoacaoRepository tipoDoacaoRepository;

    public List<TipoDoacaoDTO> findAll() {
        return tipoDoacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoDoacaoDTO> findById(Long id) {
        return tipoDoacaoRepository.findById(id).map(this::convertToDTO);
    }

    public TipoDoacaoDTO save(TipoDoacaoDTO tipoDoacaoDTO) {
        TipoDoacao tipoDoacao = convertToEntity(tipoDoacaoDTO);
        return convertToDTO(tipoDoacaoRepository.save(tipoDoacao));
    }

    public void deleteById(Long id) {
        tipoDoacaoRepository.deleteById(id);
    }

    private TipoDoacaoDTO convertToDTO(TipoDoacao tipoDoacao) {
        return new TipoDoacaoDTO(tipoDoacao.getId(), tipoDoacao.getNome());
    }

    private TipoDoacao convertToEntity(TipoDoacaoDTO tipoDoacaoDTO) {
        return new TipoDoacao(tipoDoacaoDTO.getId(), tipoDoacaoDTO.getNome());
    }
}