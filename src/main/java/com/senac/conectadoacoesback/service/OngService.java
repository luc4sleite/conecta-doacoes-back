package com.senac.conectadoacoesback.service;

import com.senac.conectadoacoesback.domain.endereco.Endereco;
import com.senac.conectadoacoesback.domain.ong.Ong;
import com.senac.conectadoacoesback.dto.OngDTO;
import com.senac.conectadoacoesback.repository.EnderecoRepository;
import com.senac.conectadoacoesback.repository.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OngService {

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<OngDTO> findAll() {
        return ongRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OngDTO> findById(String id) {
        return ongRepository.findById(id).map(this::convertToDTO);
    }

    public OngDTO save(OngDTO ongDTO) {
        Endereco endereco = ongDTO.getEndereco();
        if (endereco != null) {
            endereco = enderecoRepository.save(endereco);
            ongDTO.setEndereco(endereco);
        }

        // Converter OngDTO para Ong
        Ong ong = new Ong();
        ong.setCnpj(ongDTO.getCnpj());
        ong.setNome(ongDTO.getNome());
        ong.setEmail(ongDTO.getEmail());
        ong.setDescricao(ongDTO.getDescricao());
        ong.setTelefone(ongDTO.getTelefone());
        ong.setTipoDoacao(ongDTO.getTipoDoacao());
        ong.setEndereco(ongDTO.getEndereco());

        // Salvar Ong
        Ong savedOng = ongRepository.save(ong);

        // Converter Ong para OngDTO
        OngDTO savedOngDTO = new OngDTO();
        savedOngDTO.setId(savedOng.getId());
        savedOngDTO.setCnpj(savedOng.getCnpj());
        savedOngDTO.setNome(savedOng.getNome());
        savedOngDTO.setEmail(savedOng.getEmail());
        savedOngDTO.setDescricao(savedOng.getDescricao());
        savedOngDTO.setTelefone(savedOng.getTelefone());
        savedOngDTO.setTipoDoacao(savedOng.getTipoDoacao());
        savedOngDTO.setEndereco(savedOng.getEndereco());

        return savedOngDTO;
    }

    public void deleteById(String id) {
        ongRepository.deleteById(id);
    }

    private OngDTO convertToDTO(Ong ong) {
        return new OngDTO(ong.getId(), ong.getCnpj(), ong.getNome(), ong.getEmail(), ong.getDescricao(), ong.getTelefone(), ong.getTipoDoacao(), ong.getEndereco());
    }

    private Ong convertToEntity(OngDTO ongDTO) {
        return new Ong(ongDTO.getId(), ongDTO.getCnpj(), ongDTO.getNome(), ongDTO.getEmail(), ongDTO.getDescricao(), ongDTO.getTelefone(), ongDTO.getTipoDoacao(), ongDTO.getEndereco());
    }
}