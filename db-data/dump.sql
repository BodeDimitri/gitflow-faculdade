CREATE TABLE Intersecoes (
    id_intersecao INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    localizacao_geografica POINT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    UNIQUE KEY (nome, cidade, estado)
);

CREATE TABLE Servicos_Emergencia (
    id_servico_emergencia INT PRIMARY KEY AUTO_INCREMENT,
    tipo ENUM('Policia', 'Bombeiros', 'Ambulancia') NOT NULL,
    contato VARCHAR(100) NOT NULL,
    localizacao_geografica POINT NOT NULL
);

CREATE TABLE Semaforos (
    id_semaforo INT PRIMARY KEY AUTO_INCREMENT,
    id_intersecao INT NOT NULL,
    status ENUM('Verde', 'Amarelo', 'Vermelho') NOT NULL,
    ultima_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_intersecao) REFERENCES Intersecoes(id_intersecao)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Condicoes_Climaticas (
    id_condicao INT PRIMARY KEY AUTO_INCREMENT,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    localizacao_geografica POINT NOT NULL,
    temperatura DECIMAL(5,2),
    umidade INT CHECK(umidade >= 0 AND umidade <= 100),
    precipitacao DECIMAL(5,2) DEFAULT 0.00,
    velocidade_vento DECIMAL(5,2)
);

CREATE TABLE Fluxo_Trafego (
    id_fluxo INT PRIMARY KEY AUTO_INCREMENT,
    id_intersecao INT NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    veiculos_contagem INT NOT NULL CHECK(veiculos_contagem >= 0),
    sentido ENUM('Norte', 'Sul', 'Leste', 'Oeste') NOT NULL,
    FOREIGN KEY (id_intersecao) REFERENCES Intersecoes(id_intersecao)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Rotas (
    id_rota INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    distancia_km DECIMAL(6,2) NOT NULL CHECK(distancia_km >= 0),
    tempo_estimado_min INT NOT NULL CHECK(tempo_estimado_min >= 0),
    UNIQUE KEY (nome)
);

CREATE TABLE Padroes_Trajeto (
    id_padrao INT PRIMARY KEY AUTO_INCREMENT,
    id_rota INT NOT NULL,
    descricao TEXT NOT NULL,
    frequencia INT NOT NULL CHECK(frequencia >= 0),
    pico_horario_inicio TIME,
    pico_horario_fim TIME,
    FOREIGN KEY (id_rota) REFERENCES Rotas(id_rota)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Acidentes (
    id_acidente INT PRIMARY KEY AUTO_INCREMENT,
    id_intersecao INT NULL,
    id_rota INT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao TEXT,
    severidade ENUM('Baixa', 'Moderada', 'Alta') NOT NULL,
    status ENUM('Reportado', 'Resolvido') DEFAULT 'Reportado',
    FOREIGN KEY (id_intersecao) REFERENCES Intersecoes(id_intersecao)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (id_rota) REFERENCES Rotas(id_rota)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE Notificacoes (
    id_notificacao INT PRIMARY KEY AUTO_INCREMENT,
    id_acidente INT NOT NULL,
    id_servico_emergencia INT NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    mensagem TEXT NOT NULL,
    status ENUM('Enviada', 'Recebida', 'Em Progresso', 'Concluida') DEFAULT 'Enviada',
    FOREIGN KEY (id_acidente) REFERENCES Acidentes(id_acidente)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_servico_emergencia) REFERENCES Servicos_Emergencia(id_servico_emergencia)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
