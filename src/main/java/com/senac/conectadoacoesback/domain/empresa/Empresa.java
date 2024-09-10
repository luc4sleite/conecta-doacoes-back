package com.senac.conectadoacoesback.domain.empresa;

import com.senac.conectadoacoesback.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cnpj;
    private String nome;
    @Column(unique = true)
    private String email;
    private String descricao;
    private String telefone;
    @OneToOne
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;
}
