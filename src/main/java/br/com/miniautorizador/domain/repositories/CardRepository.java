package br.com.miniautorizador.domain.repositories;

import br.com.miniautorizador.domain.repositories.entities.CardEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CardEntity> findByCardNumber(final String cardNumber);

    Optional<CardEntity> findByCardNumberAndPassword(final String cardNumber, final String password);

    @Query("SELECT c FROM CardEntity c WHERE c.cardNumber = :cardNumber")
    Optional<CardEntity> findCardByBalance(@Param("cardNumber") final String cardNumber);

    @Modifying
    @Query(value = "update CardEntity c set c.cardBalance = (c.cardBalance - :newValue) where cardNumber = :cardNumber")
    void updateCardBalance(
            @Param("newValue") BigDecimal newValue,
            @Param("cardNumber") String cardNumber);

}
