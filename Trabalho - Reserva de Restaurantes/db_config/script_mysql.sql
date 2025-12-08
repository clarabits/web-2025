-- ===========================================
-- Criar banco de dados
-- ===========================================
DROP DATABASE IF EXISTS devweb;
CREATE DATABASE devweb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE devweb;

-- ===========================================
-- Estrutura das tabelas
-- ===========================================

CREATE TABLE mesa (
    numero INT NOT NULL,
    capacidade INT NOT NULL,
    disponivel TINYINT(1) NOT NULL,
    PRIMARY KEY (numero)
);

CREATE TABLE reserva (
    id INT NOT NULL AUTO_INCREMENT,
    numeromesa INT NOT NULL,
    nomecliente VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'ATIVA',
    PRIMARY KEY (id)
);

-- ===========================================
-- Dados iniciais
-- ===========================================

INSERT INTO mesa (numero, capacidade, disponivel) VALUES
(8, 4, 1),
(9, 2, 1),
(10, 10, 1),
(7, 6, 1),
(1, 2, 1),
(2, 4, 1),
(3, 4, 1),
(4, 6, 1),
(5, 2, 1),
(6, 8, 1);

-- Tabela reserva começa vazia
-- ===========================================
