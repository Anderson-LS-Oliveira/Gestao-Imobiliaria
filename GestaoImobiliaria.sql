CREATE DATABASE IF NOT EXISTS imobiliaria;
USE imobiliaria;

CREATE TABLE IF NOT EXISTS Imovel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL,
    quartos INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255),
    telefone VARCHAR(20),
    cpf CHAR(11) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Contrato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    idImovel INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    dataInicio DATE,
    dataFim DATE,
    FOREIGN KEY (idCliente) REFERENCES Cliente(id),
    FOREIGN KEY (idImovel) REFERENCES Imovel(id)
);
