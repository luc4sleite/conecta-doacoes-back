package com.senac.conectadoacoesback.domain.ong;

import com.senac.conectadoacoesback.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ongs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ong {
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
    @ElementCollection
    @CollectionTable(name = "ong_tipo_doacao", joinColumns = @JoinColumn(name = "ong_id"))
    @Column(name = "tipo_doacao")
    private List<String> tipoDoacao;
    @OneToOne
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;

}
