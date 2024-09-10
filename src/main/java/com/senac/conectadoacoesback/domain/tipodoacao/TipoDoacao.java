package com.senac.conectadoacoesback.domain.tipodoacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_doacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoDoacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
