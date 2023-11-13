create database projetobd;
use projetobd;
create table pessoa(
id int primary key auto_increment,
nome varchar(50) not null,
email varchar(255) not null,
endereco varchar(255) not null
);
show tables;
describe pessoa;
select * from pessoa;
insert into pessoa (nome, email, endereco) values ("Lucas", "viado@email.com", "Piroruri");

	