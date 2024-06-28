package br.com.miniautorizador.domain.repositories;

import br.com.miniautorizador.domain.repositories.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
