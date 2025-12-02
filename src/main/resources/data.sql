-- 1. PRIMEIRO: Criamos as Categorias com IDs fixos (para podermos usar nos produtos)
-- Categoria: Computadores & Hardware (ID final ...1111)
INSERT INTO CATEGORIAS (id, nome, ativo)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Computadores & Hardware', true);

-- Categoria: Periféricos (ID final ...2222)
INSERT INTO CATEGORIAS (id, nome, ativo)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'Periféricos', true);

-- Categoria: Móveis & Escritório (ID final ...3333)
INSERT INTO CATEGORIAS (id, nome, ativo)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'Móveis & Escritório', true);

-- Categoria: Eletrônicos & Gadgets (ID final ...4444)
INSERT INTO CATEGORIAS (id, nome, ativo)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 'Eletrônicos & Gadgets', true);


-- 2. SEGUNDO: Criamos os Produtos referenciando os IDs acima na coluna categoria_id

-- Produtos ligados a 'Computadores & Hardware' (...1111)
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Notebook Gamer Pro', 7500.00, 10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'SSD NVMe 1TB', 650.00, 40, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Placa de Vídeo RTX 4070', 4500.00, 8, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Memória RAM DDR5 16GB', 520.00, 30, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Processador Core i7 13ª Gen', 2100.00, 18, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Gabinete Mid-Tower Vidro', 380.00, 22, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Produtos ligados a 'Periféricos' (...2222)
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Mouse Óptico Sem Fio', 120.50, 50, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Teclado Mecânico RGB', 350.00, 30, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Monitor Ultrawide 34"', 2800.75, 15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Headset Gamer 7.1', 499.90, 25, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Mousepad Gamer Extra Grande', 90.00, 60, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Webcam Full HD 1080p', 230.00, 35, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Filtro de Linha 8 Tomadas', 75.00, 100, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'HD Externo 2TB USB 3.0', 399.00, 28, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');

-- Produtos ligados a 'Móveis & Escritório' (...3333)
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Cadeira Gamer Ergonômica', 1800.00, 12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33');

-- Produtos ligados a 'Eletrônicos & Gadgets' (...4444)
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Smartphone Top de Linha', 3200.00, 20, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Smartwatch Pro 5', 1300.00, 15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Caixa de Som Bluetooth', 280.00, 40, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Tablet 10 Polegadas', 950.00, 10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44');
INSERT INTO PRODUTOS (id, nome, preco, quantidade, categoria_id)
VALUES (RANDOM_UUID(), 'Roteador Wi-Fi 6', 450.00, 15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44');


-- 3. TERCEIRO: Mantivemos seu usuário inalterado
INSERT INTO USUARIOS (id, login, senha)
VALUES (1, 'joao.victor@proton.me', '$2a$12$LmVAr6uqaLks5XZy5l04M.ShClWsEBqr/8SbFbcg7JFFOi8P26M4a');