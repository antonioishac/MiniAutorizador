package br.com.miniautorizador.domain.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_card")
public class CardEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String numeroCartao;

    @Column(name = "card_balance")
    private BigDecimal valor;

    @Column(name = "password")
    private String senha;
}
