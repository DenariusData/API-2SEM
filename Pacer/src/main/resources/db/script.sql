CREATE DATABASE API;

USE API;

CREATE TABLE ALUNO (
    ALUNO_RA BIGINT NOT NULL,                 
    ALUNO_EMAIL VARCHAR(30) NOT NULL UNIQUE,   
    ALUNO_NOME VARCHAR(100) NOT NULL,
    ALUNO_SENHA VARCHAR(30) NOT NULL,
	FOTO LONGBLOB,
    PRIMARY KEY (ALUNO_RA)                    
);

CREATE TABLE GRUPO (
    GRUPO_ID INT AUTO_INCREMENT,
    GRUPO_NOME VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (GRUPO_ID)
);

CREATE TABLE PROFESSOR (
	PROF_ID INT AUTO_INCREMENT,
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

CREATE TABLE PROF_MATERIA (
    MATERIA_ID INT,
    PROF_ID INT,               -- Chave composta para permitir que um professor possa lecionar várias matérias
    PRIMARY KEY (PROF_ID, MATERIA_ID),
    FOREIGN KEY (PROF_ID) REFERENCES PROFESSOR(PROF_ID),
    FOREIGN KEY (MATERIA_ID) REFERENCES MATERIA(MATERIA_ID)
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

CREATE TABLE ALUNO_GRUPO (
    ALUNO_RA BIGINT,                          
    GRUPO_ID INT,                            
    PRIMARY KEY (ALUNO_RA, GRUPO_ID),         -- Chave composta para garantir que cada aluno seja único em um grupo
    FOREIGN KEY (ALUNO_RA) REFERENCES ALUNO(ALUNO_RA), 
    FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO(GRUPO_ID)  
);

-- Inserindo alunos
INSERT INTO ALUNO (ALUNO_RA, ALUNO_EMAIL, ALUNO_NOME, ALUNO_SENHA)
VALUES (1144197338157, 'Joao.silva@fatec.sp.gov.br', 'João Silva', 'senha123'),
       (1144197338987, 'Maria.santos@fatec.sp.gov.br', 'Maria Santos', 'senha456');

-- Inserindo grupos
INSERT INTO GRUPO (GRUPO_NOME)
VALUES ('Denarius Data'), ('Programers');

-- Inserindo professores (deixe o `PROF_ID` ser gerado automaticamente)
INSERT INTO PROFESSOR (PROF_EMAIL, PROF_SENHA)
VALUES ('Emerson.silva@fatec.sp.gov.br', 'senha789'),
       ('Cinthia.costa@fatec.sp.gov.br', 'senha321');

-- Inserindo matérias (deixe o `MATERIA_ID` ser gerado automaticamente)
INSERT INTO MATERIA (MATERIA_NOME, MATERIA_SEMESTRE)
VALUES ('Banco de Dados', 2),
       ('Algoritmo', 5);

-- Associando professores a matérias (verifique se os IDs inseridos para `PROF_ID` e `MATERIA_ID` existem)
INSERT INTO PROF_MATERIA (MATERIA_ID, PROF_ID)
VALUES (1, 1), -- Banco de Dados com Emerson
       (2, 2); -- Algoritmo com Cinthia

-- Inserindo critérios de avaliação
INSERT INTO CRITERIOS (CRITERIO_ID,CRITERIO_NOME, CRITERIO_DESCRICAO)
VALUES ('1','Proatividade', 'É uma característica que envolve antecipar problemas...'),
       ('2','Autonomia', 'É a capacidade de agir e tomar decisões de forma independente...');
       

-- Inserindo avaliações (usando uma data fixa)
INSERT INTO AVALIACAO (CRITERIO_ID,AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, NOTA, AVALIACAO_DATA)
VALUES (1,1144197338157, 1144197338987, 2.0, '2024-10-16'),
       (2,1144197338987, 1144197338157, 2.2, '2024-10-16');

-- Inserindo sprints
INSERT INTO SPRINT (SPRINT, SPRINT_SEMESTRE, SPRINT_DATA_INICIO, SPRINT_DATA_FIM)
VALUES (1, 2, '2024-03-01', '2024-03-31'),
       (2, 5, '2024-04-01', '2024-04-30');

-- Inserindo calendário
INSERT INTO CALENDARIO (SEMESTRE, SPRINT_ID, DATA_INICIO, DATA_FIM)
VALUES ('2024-2', 1, '2024-03-01', '2024-03-31'),
       ('2024-5', 2, '2024-04-01', '2024-04-30');

-- Associando alunos a grupos
INSERT INTO ALUNO_GRUPO (ALUNO_RA, GRUPO_ID)
VALUES (1144197338157, 1),
       (1144197338987, 2);
