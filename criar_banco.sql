-- Script SQL para criar o banco de dados e tabelas
-- Execute este script no MySQL antes de rodar a aplicação

-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS sistema_cadastro;
USE sistema_cadastro;

-- Tabela de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    sexo VARCHAR(20),
    uf VARCHAR(2),
    newsletter TINYINT DEFAULT 0
);

-- Tabela de produtos
CREATE TABLE IF NOT EXISTS produtos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2),
    categoria VARCHAR(50),
    tipo VARCHAR(50),
    disponivel TINYINT DEFAULT 0
);

-- Tabela de administradores
CREATE TABLE IF NOT EXISTS administradores (
    id INT PRIMARY KEY,
    senha VARCHAR(50) NOT NULL
);

-- Inserir administrador padrão
INSERT INTO administradores (id, senha) VALUES (1, '1234')
ON DUPLICATE KEY UPDATE senha = '1234';

