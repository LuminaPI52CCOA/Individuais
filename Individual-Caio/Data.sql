drop database ProjetoBiblioteca;

create database ProjetoBiblioteca;
use ProjetoBiblioteca;

create table cursos(
id int primary key auto_increment,
nome varchar(100),
turno varchar(30),
tempo int 
); 

insert into cursos(nome, turno, tempo)
values ("Ciencias da Computação","Matutino",4000),
("Analise e Desenvolvimento de Sistemas","Vespertino",2500),
("Sistemas da Informação","Noturno",4000);

create table cargos( 
id int primary key auto_increment,
nome varchar(100)
);

insert into cargos(nome)
values ("Gerente"),
	   ("Bibliotecario");

create table categoria(
id int primary key auto_increment,
tipo varchar(50)
);

insert into categoria (tipo)
 values ('Ficção'),
('Romance'),
('Fantasia'),
('Ficção Científica'),
('Terror'),
('Suspense'),
('Mistério'),
('Drama'),
('Aventura'),
('Biografia'),
('Autobiografia'),
('História'),
('Autoajuda'),
('Negócios'),
('Educação'),
('Infantil'),
('Juvenil'),
('Religião'),
('Filosofia'),
('Tecnologia'),
('Saúde'),
('Psicologia'),
('Poesia'),
('HQ / Quadrinhos');


create table livros(
id int primary key auto_increment,
titulos varchar(300),
autor varchar(100),
fkCategoria int,
foreign key (fkCategoria) references categoria(id)
);


create table aluno(
ra int primary key auto_increment,
nome varchar(100),	
email varchar(300),
senha varchar(500), 
cpf char(11), 
sexo char(1), 
nomeSocial varchar(300), 
nacionalidade char(2), 
dtNasc date,
fkcurso int,
foreign key (fkcurso) references cursos(id)
);

create table funcionario (
id int primary key auto_increment,
nome varchar(100) ,
email varchar(300) unique,
senha varchar(500),
cpf char(11) unique,
sexo char(1),
nomeSocial varchar(300) default null,
nacionalidade char(2),
dtNasc date,
turno varchar(30),
metaMensal decimal(10,2) default 0.00,
fkcargo int,
foreign key (fkcargo) references cargos(id)
);



select*from livros;	
select*from cursos;
select*from categoria;
select * from funcionario;
select*from aluno;
select*from cargos;
select nome from aluno where email = 'giovanna.canesso@bibliotech.com';