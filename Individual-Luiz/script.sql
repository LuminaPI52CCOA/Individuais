create database Aventura;

use Aventura;

-- -----------------------------------------------------
-- Tabela usuario
-- -----------------------------------------------------
create table usuario (
  idusuario int primary key auto_increment,
  nome varchar(45) not null,
  sobrenome varchar(45) not null,
  dtNascimento date not null,
  email varchar(45) not null,
  senha varchar(45) not null,
  clube varchar(45) not null,
  dtCriacao timestamp default current_timestamp
  );

-- -----------------------------------------------------
-- Tabela questionario
-- -----------------------------------------------------
create table questionario (
  idquestionario int primary key
);


-- -----------------------------------------------------
-- Tabela questionario_usuario
-- -----------------------------------------------------
create table questionario_usuario (
  fkquestionario int not null,
  fkusuario int not null,
  resultado int not null,
  primary key (fkquestionario, fkusuario),
  constraint fkquestionario foreign key (fkquestionario) references questionario (idquestionario),
  constraint fkusuario foreign key (fkusuario) references usuario (idusuario)
);


-- -----------------------------------------------------
-- Tabela questoes
-- -----------------------------------------------------
create table  questoes (
  idquestoes int not null,
  fkquestionario int not null,
  primary key (idquestoes, fkquestionario),
  constraint fkquestionario_questao foreign key (fkquestionario) references questionario (idquestionario)
);
