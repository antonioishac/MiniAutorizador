CREATE TABLE tb_card (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `card_number` varchar(20) NOT NULL,
  `card_balance` decimal(18,2) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT uc_card UNIQUE (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;