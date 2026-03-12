CREATE DATABASE projetoPW;
USE projetoPW;


CREATE TABLE raridade (
    id INT PRIMARY KEY AUTO_INCREMENT,
    raridade VARCHAR(45)
);

INSERT INTO raridade (raridade) 
VALUES ('Super Raro'), ('Ultra Raro'), ('Secret Rare'), ('Holográfica');


CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    idade TINYINT,
    data_cadastro DATE,
    raridade VARCHAR(50), 
    tipo VARCHAR(11),
    deck_favorito VARCHAR(100),
    comentario TEXT
);


INSERT INTO usuario (nome, idade, data_cadastro, raridade, tipo, deck_favorito, comentario) 
VALUES 
    ('Yugi Muto', 16, CURDATE(), 'Holográfica', 'Duelo', 'Dark Magician', 'Acredito no coração das cartas!'),
    ('Seto Kaiba', 16, CURDATE(), 'Secret Rare', 'Duelo', 'Blue-Eyes', 'Quando você pensa na derrota, você já perdeu'),
    ('Joey Wheeler', 16, CURDATE(), 'Super Raro', 'Duelo', 'Red-Eyes', 'Eu ativo o Mago do Tempo! Agora tudo depende da sorte!');


SELECT * FROM usuario;