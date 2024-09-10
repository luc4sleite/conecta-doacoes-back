package com.senac.conectadoacoesback.repository;

import com.senac.conectadoacoesback.domain.tipodoacao.TipoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDoacaoRepository extends JpaRepository<TipoDoacao, Long> {
}