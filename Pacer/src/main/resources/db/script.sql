CREATE DATABASE API;

USE API;

CREATE TABLE GRUPO (
    GRUPO_ID INT AUTO_INCREMENT,
    GRUPO_NOME VARCHAR(20) NOT NULL UNIQUE,
    REPOS_LINK VARCHAR(255) NOT NULL,
    PRIMARY KEY (GRUPO_ID)
);

CREATE TABLE ALUNO (
    ALUNO_RA BIGINT NOT NULL,                 
    ALUNO_EMAIL VARCHAR(30) NOT NULL UNIQUE,   
    ALUNO_NOME VARCHAR(100) NOT NULL,
    ALUNO_SENHA VARCHAR(30) NOT NULL,
	FOTO LONGBLOB,
    GRUPO_ID INT,
    PRIMARY KEY (ALUNO_RA),
    FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO(GRUPO_ID)  
);

CREATE TABLE PROFESSOR (
	PROF_ID INT AUTO_INCREMENT,
	PROF_NOME VARCHAR(100) NOT NULL,
    PROF_EMAIL VARCHAR(30) NOT NULL UNIQUE,  
    PROF_SENHA VARCHAR(30) NOT NULL,
    FOTO LONGBLOB,
    PRIMARY KEY (PROF_ID)
);

CREATE TABLE MATERIA (
	MATERIA_ID INT AUTO_INCREMENT,
    MATERIA_NOME VARCHAR(30) NOT NULL,
    MATERIA_SEMESTRE INT NOT NULL,
    PRIMARY KEY (MATERIA_ID)
);


CREATE TABLE CRITERIOS (
    CRITERIO_ID INT AUTO_INCREMENT,            
    CRITERIO_NOME VARCHAR(50) NOT NULL,
    CRITERIO_DESCRICAO VARCHAR(600),                            -- Descrição do critério (opcional)
    CRITERIO_ATIVO BOOLEAN DEFAULT TRUE,                -- Define se o critério está ativo para uso
    PRIMARY KEY (CRITERIO_ID)                  
);

CREATE TABLE AVALIACAO (
    AVALIACAO_ID INT AUTO_INCREMENT,          
	AVALIADO_ALUNO_RA BIGINT NOT NULL,                      
    AVALIADOR_ALUNO_RA BIGINT NOT NULL,
    CRITERIO_ID INT NOT NULL,                         
    NOTA DECIMAL(3,1) NOT NULL,      -- podendo inserir um numero inteiro somente e um numero após a virgula        
    AVALIACAO_DATA DATETIME NOT NULL,             
    PRIMARY KEY (AVALIACAO_ID),               
    FOREIGN KEY (AVALIADO_ALUNO_RA) REFERENCES ALUNO(ALUNO_RA), -- Chave estrangeira referenciando o aluno avaliador
    FOREIGN KEY (AVALIADOR_ALUNO_RA) REFERENCES ALUNO(ALUNO_RA),  -- Chave estrangeira referenciando o aluno avaliado
    FOREIGN KEY (CRITERIO_ID) REFERENCES CRITERIOS(CRITERIO_ID) -- Chave estrangeira referenciando o critério
);

CREATE TABLE SPRINT (
    SPRINT_ID INT AUTO_INCREMENT,            -- Identificador único da Sprint
    SPRINT INT NOT NULL,        -- Nome da Sprint (por exemplo, "Sprint 1", "Sprint 2")
    SPRINT_SEMESTRE INT NOT NULL,
    SPRINT_DATA_INICIO DATE NOT NULL,               
    SPRINT_DATA_FIM DATE NOT NULL,  
    CONSTRAINT CK_SPRINT CHECK (SPRINT IN (1,2,3,4)), -- deixar inserir somente 1,2,3,4
    PRIMARY KEY (SPRINT_ID)                 
);

CREATE TABLE CALENDARIO (
    CALENDARIO_ID INT AUTO_INCREMENT,          
    SEMESTRE VARCHAR(10) NOT NULL,             -- Semestre e ano (ex: "2024-1" para primeiro semestre de 2024)
    SPRINT_ID INT,                             -- ID da Sprint (associado a uma entrada na tabela SPRINT)
    DATA_INICIO DATE NOT NULL,                 
    DATA_FIM DATE NOT NULL,                    
    PRIMARY KEY (CALENDARIO_ID),             
    FOREIGN KEY (SPRINT_ID) REFERENCES SPRINT(SPRINT_ID) 
);

-- Tabela GRUPO
INSERT INTO GRUPO (GRUPO_NOME, REPOS_LINK) VALUES 
('Grupo Alpha', 'https://github.com/grupo-alpha'),
('Grupo Beta', 'https://github.com/grupo-beta'),
('Grupo Gama', 'https://github.com/grupo-gama');

-- Tabela ALUNO
INSERT INTO ALUNO (ALUNO_RA, ALUNO_EMAIL, ALUNO_NOME, ALUNO_SENHA, FOTO, GRUPO_ID) VALUES 
(12345678901, 'aluno1@email.com', 'Alice Silva', 'senha123', NULL, 1),
(12345678902, 'aluno2@email.com', 'Bruno Santos', 'senha456', NULL, 2),
(12345678903, 'aluno3@email.com', 'Carla Costa', 'senha789', NULL, 3);

-- Tabela PROFESSOR
INSERT INTO PROFESSOR (PROF_NOME, PROF_EMAIL, PROF_SENHA, FOTO) VALUES 
('Professor Xavier', 'xavier@email.com', 'prof123', NULL),
('Dra. Grey', 'grey@email.com', 'prof456', NULL),
('Logan Wolverine', 'logan@email.com', 'prof789', NULL);

-- Tabela MATERIA
INSERT INTO MATERIA (MATERIA_NOME, MATERIA_SEMESTRE) VALUES 
('Matemática', 1),
('Física', 2),
('Programação', 3);

-- Tabela CRITERIOS
INSERT INTO CRITERIOS (CRITERIO_NOME, CRITERIO_DESCRICAO, CRITERIO_ATIVO) VALUES 
('Pontualidade', 'Avaliação sobre a pontualidade do aluno nas entregas', TRUE),
('Trabalho em equipe', 'Capacidade de trabalhar bem em equipe', TRUE),
('Qualidade do código', 'Avaliação da qualidade do código produzido', TRUE);

-- Tabela AVALIACAO
INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, AVALIACAO_DATA) VALUES 
(12345678901, 12345678902, 1, 9.5, '2024-10-01 10:00:00'),
(12345678902, 12345678903, 2, 8.0, '2024-10-02 12:30:00'),
(12345678903, 12345678901, 3, 7.5, '2024-10-03 14:15:00');

-- Tabela SPRINT
INSERT INTO SPRINT (SPRINT, SPRINT_SEMESTRE, SPRINT_DATA_INICIO, SPRINT_DATA_FIM) VALUES 
(1, 2024, '2024-02-01', '2024-02-28'),
(2, 2024, '2024-03-01', '2024-03-31'),
(3, 2024, '2024-04-01', '2024-04-30');

-- Tabela CALENDARIO
INSERT INTO CALENDARIO (SEMESTRE, SPRINT_ID, DATA_INICIO, DATA_FIM) VALUES 
('2024-1', 1, '2024-02-01', '2024-02-28'),
('2024-1', 2, '2024-03-01', '2024-03-31'),
('2024-1', 3, '2024-04-01', '2024-04-30');

