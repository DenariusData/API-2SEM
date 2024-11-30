DROP DATABASE API;

CREATE DATABASE API;

USE API;

CREATE TABLE GRUPO (
    GRUPO_ID INT AUTO_INCREMENT,
    GRUPO_NOME VARCHAR(20) NOT NULL UNIQUE,
    REPOS_LINK VARCHAR(255) NOT NULL,
	PONTOS_SPRINT INT,
    PRIMARY KEY (GRUPO_ID)
);

CREATE TABLE ALUNO (
    ALUNO_RA BIGINT NOT NULL,                 
    ALUNO_EMAIL VARCHAR(50) NOT NULL UNIQUE,   
    ALUNO_NOME VARCHAR(100) NOT NULL,
    ALUNO_SENHA VARCHAR(30),
    FOTO LONGBLOB,
    GRUPO_ID INT,
    PRIMARY KEY (ALUNO_RA),
    FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO(GRUPO_ID)
);

CREATE TABLE PROFESSOR (
    PROF_EMAIL VARCHAR(30) NOT NULL UNIQUE,  
    PROF_SENHA VARCHAR(30) NOT NULL,
    PRIMARY KEY (PROF_EMAIL)
);

CREATE TABLE CRITERIOS (
    CRITERIO_ID INT AUTO_INCREMENT,            
    CRITERIO_NOME VARCHAR(50) NOT NULL,
    CRITERIO_DESCRICAO VARCHAR(600),
    CRITERIO_ATIVO BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (CRITERIO_ID)
);

CREATE TABLE SPRINT (
    SPRINT_ID INT AUTO_INCREMENT,
    SPRINT_NUM INT NOT NULL,
    SPRINT_DATA_INICIO DATE NOT NULL,
    SPRINT_DATA_FIM DATE NOT NULL,
    CONSTRAINT CK_SPRINT CHECK (SPRINT_NUM IN (1,2,3,4)),
    PRIMARY KEY (SPRINT_ID)
);

CREATE TABLE AVALIACAO (
    AVALIACAO_ID INT AUTO_INCREMENT,
    AVALIADO_ALUNO_RA BIGINT NOT NULL,
    AVALIADOR_ALUNO_RA BIGINT NOT NULL,
    CRITERIO_ID INT NOT NULL,
    NOTA DECIMAL(3,1) NOT NULL,
    SPRINT_ID INT NOT NULL,
    AVALIACAO_DATA DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (AVALIACAO_ID),
    FOREIGN KEY (AVALIADO_ALUNO_RA) REFERENCES ALUNO(ALUNO_RA),
    FOREIGN KEY (AVALIADOR_ALUNO_RA) REFERENCES ALUNO(ALUNO_RA),
    FOREIGN KEY (CRITERIO_ID) REFERENCES CRITERIOS(CRITERIO_ID),
    FOREIGN KEY (SPRINT_ID) REFERENCES SPRINT(SPRINT_ID)
);

CREATE TABLE CALENDARIO (
    CALENDARIO_ID INT AUTO_INCREMENT,
    SEMESTRE VARCHAR(10) NOT NULL,
    DATA_INICIO DATE NOT NULL,
    DATA_FIM DATE NOT NULL,
    PRIMARY KEY (CALENDARIO_ID)
);

-- Inserção na tabela GRUPO
INSERT INTO GRUPO (GRUPO_NOME, REPOS_LINK, PONTOS_SPRINT) VALUES
('Grupo Alpha', 'https://github.com/alpha', 100),
('Grupo Beta', 'https://github.com/beta', 120),
('Grupo Gamma', 'https://github.com/gamma', 95);

-- Inserção na tabela ALUNO
INSERT INTO ALUNO (ALUNO_RA, ALUNO_EMAIL, ALUNO_NOME, ALUNO_SENHA, GRUPO_ID) VALUES
(1234567890123, 'joao@email.com', 'João Silva', 'senha123', 1),
(1234567890124, 'maria@email.com', 'Maria Oliveira', 'senha456', 1),
(1234567890125, 'pedro@email.com', 'Pedro Santos', 'senha789', 2),
(1234567890126, 'lucas@email.com', 'Lucas Lima', 'senha321', 2),
(1234567890127, 'juliana@email.com', 'Juliana Alves', 'senha654', 3),
(1234567890128, 'ana@email.com', 'Ana Souza', 'senha987', 3);

-- Inserção na tabela PROFESSOR
INSERT INTO PROFESSOR (PROF_EMAIL, PROF_SENHA) VALUES
('prof1@email.com', 'prof123'),
('prof2@email.com', 'prof456');

-- Inserção na tabela CRITERIOS
INSERT INTO CRITERIOS (CRITERIO_NOME, CRITERIO_DESCRICAO) VALUES
('Qualidade do Código', 'Avaliação da clareza e manutenção do código.'),
('Pontualidade', 'Entrega das atividades dentro do prazo.'),
('Trabalho em Equipe', 'Colaboração e comunicação com a equipe.');

-- Inserção na tabela SPRINT
INSERT INTO SPRINT (SPRINT_NUM, SPRINT_DATA_INICIO, SPRINT_DATA_FIM) VALUES
(1, '2024-01-10', '2024-01-17'),
(2, '2024-01-18', '2024-01-25'),
(3, '2024-01-26', '2024-02-02');

-- Inserção na tabela AVALIACAO
INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, SPRINT_ID) VALUES
(1234567890123, 1234567890124, 1, 3.0, 1), -- João avaliado por Maria
(1234567890123, 1234567890125, 2, 2.5, 1), -- João avaliado por Pedro
(1234567890124, 1234567890123, 3, 4.0, 1), -- Maria avaliada por João
(1234567890124, 1234567890125, 1, 2.0, 1), -- Maria avaliada por Pedro
(1234567890125, 1234567890123, 2, 3.5, 1), -- Pedro avaliado por João
(1234567890126, 1234567890127, 3, 1.5, 2), -- Lucas avaliado por Juliana
(1234567890127, 1234567890128, 1, 2.0, 2), -- Juliana avaliada por Ana
(1234567890128, 1234567890126, 2, 4.0, 2); -- Ana avaliada por Lucas

-- Inserção na tabela CALENDARIO
INSERT INTO CALENDARIO (SEMESTRE, DATA_INICIO, DATA_FIM) VALUES
('2024.1', '2024-01-08', '2024-06-30'),
('2024.2', '2024-07-01', '2024-12-15');

USE API;
SELECT * FROM ALUNO;
SELECT * FROM PROFESSOR;
SELECT * FROM GRUPO;
SELECT * FROM CRITERIOS;