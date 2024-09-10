package com.senac.conectadoacoesback.dto;

import com.senac.conectadoacoesback.domain.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {
    private Long id;
    private String cnpj;
    private String nome;
    private String email;
    private String descricao;
    private String telefone;
    private Endereco endereco;
}