-- 1. Inserindo Categorias (IDs fixos mantidos para relacionamento)
INSERT INTO categorias (id, nome, ativo)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Computadores & Hardware', true),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'Periféricos', true),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'Móveis & Escritório', true),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 'Eletrônicos & Gadgets', true);


-- 2. Inserindo Produtos (Usando gen_random_uuid() do Postgres)
-- Produtos: Computadores & Hardware
INSERT INTO produtos (id, nome, preco, quantidade, categoria_id)
VALUES (gen_random_uuid(), 'Notebook Gamer Pro', 7500.00, 10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       (gen_random_uuid(), 'SSD NVMe 1TB', 650.00, 40, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       (gen_random_uuid(), 'Placa de Vídeo RTX 4070', 4500.00, 8, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       (gen_random_uuid(), 'Memória RAM DDR5 16GB', 520.00, 30, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       (gen_random_uuid(), 'Processador Core i7 13ª Gen', 2100.00, 18, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
       (gen_random_uuid(), 'Gabinete Mid-Tower Vidro', 380.00, 22, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Produtos: Periféricos
INSERT INTO produtos (id, nome, preco, quantidade, categoria_id)
VALUES (gen_random_uuid(), 'Mouse Óptico Sem Fio', 120.50, 50, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'Teclado Mecânico RGB', 350.00, 30, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'Monitor Ultrawide 34"', 2800.75, 15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'Headset Gamer 7.1', 499.90, 25, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'Mousepad Gamer Extra Grande', 90.00, 60, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'Webcam Full HD 1080p', 230.00, 35, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'Filtro de Linha 8 Tomadas', 75.00, 100, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
       (gen_random_uuid(), 'HD Externo 2TB USB 3.0', 399.00, 28, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');

-- Produtos: Móveis & Escritório
INSERT INTO produtos (id, nome, preco, quantidade, categoria_id)
VALUES (gen_random_uuid(), 'Cadeira Gamer Ergonômica', 1800.00, 12, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33');

-- Produtos: Eletrônicos & Gadgets
INSERT INTO produtos (id, nome, preco, quantidade, categoria_id)
VALUES (gen_random_uuid(), 'Smartphone Top de Linha', 3200.00, 20, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
       (gen_random_uuid(), 'Smartwatch Pro 5', 1300.00, 15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
       (gen_random_uuid(), 'Caixa de Som Bluetooth', 280.00, 40, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
       (gen_random_uuid(), 'Tablet 10 Polegadas', 950.00, 10, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
       (gen_random_uuid(), 'Roteador Wi-Fi 6', 450.00, 15, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a44');


-- 3. Inserindo Usuário
INSERT INTO usuarios (id, login, senha)
VALUES (1, 'joao.victor@proton.me', '$2a$12$LmVAr6uqaLks5XZy5l04M.ShClWsEBqr/8SbFbcg7JFFOi8P26M4a');

-- Ajusta a sequence do ID do usuario para não dar erro no próximo insert manual
SELECT setval('usuarios_id_seq', (SELECT MAX(id) FROM usuarios));