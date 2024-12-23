DROP DATABASE API;

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

CREATE TABLE PONTOS_SPRINT (
    PONTOS_INICIAIS INT,
    PONTOS_ATUAIS INT,
    SPRINT_ID INT NOT NULL,
    GRUPO_ID INT NOT NULL,
    DATA_ATRIBUICAO DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(SPRINT_ID, GRUPO_ID),
    FOREIGN KEY (SPRINT_ID) REFERENCES SPRINT(SPRINT_ID),
    FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO(GRUPO_ID)
);

-- Grupos
INSERT INTO GRUPO (GRUPO_NOME, REPOS_LINK)
VALUES 
('Equipe Alpha', 'https://github.com/equipe-alpha'),
('Equipe Beta', 'https://github.com/equipe-beta');

-- Alunos
INSERT INTO ALUNO (ALUNO_RA, ALUNO_EMAIL, ALUNO_NOME, ALUNO_SENHA, GRUPO_ID)
VALUES
(12345678901, 'joao.alpha@email.com', 'João Silva', 'senha123', 1),
(12345678902, 'maria.alpha@email.com', 'Maria Oliveira', 'senha123', 1),
(12345678903, 'carlos.beta@email.com', 'Carlos Santos', 'senha123', 2),
(12345678904, 'ana.beta@email.com', 'Ana Pereira', 'senha123', 2),
(12345678905, 'lucas@email.com', 'Lucas Fernandes', 'senha123', NULL),
(12345678906, 'juliana@email.com', 'Juliana Souza', 'senha123', NULL),
(12345678907, 'ricardo@email.com', 'Ricardo Lima', 'senha123', NULL);

-- Professor
INSERT INTO PROFESSOR (PROF_EMAIL, PROF_SENHA)
VALUES 
('professor@email.com', 'senha123');

-- Critérios
INSERT INTO CRITERIOS (CRITERIO_NOME, CRITERIO_DESCRICAO)
VALUES
('Participação', 'Nível de participação do aluno nas atividades.'),
('Colaboração', 'Nível de colaboração com os membros do grupo.'),
('Entrega', 'Pontualidade e qualidade nas entregas de tarefas.');

-- Sprints
INSERT INTO SPRINT (SPRINT_NUM, SPRINT_DATA_INICIO, SPRINT_DATA_FIM)
VALUES
(1, '2024-10-01', '2024-10-14'), -- Sprint 1
(2, '2024-10-15', '2024-10-28'), -- Sprint 2
(3, '2024-11-01', '2024-11-14'); -- Sprint 3

-- Avaliações Sprint 1
INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, SPRINT_ID)
VALUES
(12345678901, 12345678902, 1, 2, 1), -- Maria avaliou João
(12345678902, 12345678901, 2, 3, 1), -- João avaliou Maria
(12345678903, 12345678904, 3, 1, 1), -- Ana avaliou Carlos
(12345678904, 12345678903, 1, 2, 1); -- Carlos avaliou Ana

-- Avaliações Sprint 2
INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, SPRINT_ID)
VALUES
(12345678901, 12345678902, 1, 3, 2), -- Maria avaliou João
(12345678902, 12345678901, 2, 2, 2), -- João avaliou Maria
(12345678903, 12345678904, 3, 2, 2), -- Ana avaliou Carlos
(12345678904, 12345678903, 1, 1, 2); -- Carlos avaliou Ana

-- Avaliações Sprint 3
INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, SPRINT_ID)
VALUES
(12345678901, 12345678902, 2, 1, 3), -- Maria avaliou João
(12345678902, 12345678901, 3, 2, 3), -- João avaliou Maria
(12345678903, 12345678904, 1, 3, 3), -- Ana avaliou Carlos
(12345678904, 12345678903, 2, 3, 3); -- Carlos avaliou Ana


-- Pontos Sprint 1
INSERT INTO PONTOS_SPRINT (PONTOS_INICIAIS, PONTOS_ATUAIS, SPRINT_ID, GRUPO_ID, DATA_ATRIBUICAO)
VALUES
(100, 95, 1, 1, '2024-11-01 10:00:00'), -- Grupo Alpha
(100, 97, 1, 2, '2024-11-01 10:00:00'); -- Grupo Beta

-- Pontos Sprint 2
INSERT INTO PONTOS_SPRINT (PONTOS_INICIAIS, PONTOS_ATUAIS, SPRINT_ID, GRUPO_ID, DATA_ATRIBUICAO)
VALUES
(95, 90, 2, 1, '2024-11-15 10:00:00'), -- Grupo Alpha
(97, 94, 2, 2, '2024-11-15 10:00:00'); -- Grupo Beta

-- Pontos Sprint 3
INSERT INTO PONTOS_SPRINT (PONTOS_INICIAIS, PONTOS_ATUAIS, SPRINT_ID, GRUPO_ID, DATA_ATRIBUICAO)
VALUES
(90, 87, 3, 1, '2024-11-15 10:00:00'), -- Grupo Alpha
(94, 88, 3, 2, '2024-11-15 10:00:00'); -- Grupo Beta



USE API;
SELECT * FROM ALUNO;
SELECT * FROM PROFESSOR;
SELECT * FROM GRUPO;
SELECT * FROM CRITERIOS;
SELECT * FROM SPRINT;
SELECT * FROM PONTOS_SPRINT;

SELECT * FROM AVALIACAO;

SELECT * FROM ALUNO WHERE GRUPO_ID IS NULL
