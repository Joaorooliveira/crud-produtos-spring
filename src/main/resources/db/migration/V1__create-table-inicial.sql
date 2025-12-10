CREATE TABLE categorias (
                            id UUID NOT NULL,
                            ativo BOOLEAN,
                            nome VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
);

CREATE TABLE usuarios (
                          id BIGSERIAL NOT NULL, --
                          login VARCHAR(255) NOT NULL,
                          senha VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id)
);

CREATE TABLE produtos (
                          id UUID NOT NULL,
                          nome VARCHAR(255) NOT NULL,
                          preco NUMERIC(38, 2) NOT NULL,
                          quantidade INTEGER NOT NULL,
                          categoria_id UUID,
                          PRIMARY KEY (id)
);

-- Adiciona a constraint de unicidade no nome da categoria
ALTER TABLE IF EXISTS categorias
    ADD CONSTRAINT uk_nome_categoria UNIQUE (nome);

-- Cria a chave estrangeira (Produto aponta para Categoria)
ALTER TABLE IF EXISTS produtos
    ADD CONSTRAINT fk_produtos_categorias
    FOREIGN KEY (categoria_id)
    REFERENCES categorias;