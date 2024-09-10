package com.senac.conectadoacoesback.repository;

import com.senac.conectadoacoesback.domain.ong.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, String> {
}