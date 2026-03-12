-- drop database reviewbox;
create database reviewbox;
use reviewbox;

create table filme(
	id_filme int not null primary key auto_increment,
	titulo_filme varchar(100) not null,
    genero_filme varchar(100) not null,
    class_ind int not null
);

create table usuario(
	id_usuario int not null PRIMARY KEY auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    data_nasc date not null,
    senha varchar(8) not null
);

create table avaliacao(
	id_avaliacao int not null primary key auto_increment,
    nota double not null,
    favorito boolean not null,
    descricao varchar(255),
    fk_filme int not null,
    fk_usuario int not null,
    constraint fk_filme foreign key(fk_filme) references filme(id_filme),
    constraint fk_usuario foreign key (fk_usuario) references usuario(id_usuario)
);

select *from usuario;
select *from filme;
select *from avaliacao;

INSERT INTO filme (titulo_filme, genero_filme, class_ind) VALUES 
('Interstellar', 'Ficção Científica', '12'),
('O Poderoso Chefão', 'Crime/Drama', '14'),
('O Rei Leão', 'Animação', '0'),
('Batman: O Cavaleiro das Trevas', 'Ação', '12'),
('Fragmentado', 'Suspense/Terror', '14');