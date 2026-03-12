create database corinthians;

use corinthians;

CREATE TABLE torcedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    idade INT,
    data_nascimento DATE,
    tipo_plano VARCHAR(50),
    aceita_termos BOOLEAN,
    setor_estadio VARCHAR(100)
);

select * from torcedores;