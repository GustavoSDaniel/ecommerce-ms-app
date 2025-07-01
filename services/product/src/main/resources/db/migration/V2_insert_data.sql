INSERT INTO category (id, name, description) VALUES
                                                 (nextval('category_seq'), 'Eletrônicos', 'Dispositivos eletrônicos e acessórios em geral.'),
                                                 (nextval('category_seq'), 'Livros', 'Livros de diversos gêneros literários e técnicos.'),
                                                 (nextval('category_seq'), 'Moda', 'Roupas, calçados e acessórios para todas as estações.'),
                                                 (nextval('category_seq'), 'Alimentos', 'Produtos alimentícios, bebidas e mercearia.'),
                                                 (nextval('category_seq'), 'Automotivo', 'Peças, acessórios e produtos para veículos.');


INSERT INTO product (id, name, description, available_quantity, price, id_category) VALUES
                                                                                        (nextval('product_seq'), 'Smartwatch Fit Pro', 'Relógio inteligente com monitor de saúde e GPS.', 200.0, 850.00, (SELECT id FROM category WHERE name = 'Eletrônicos')),
                                                                                        (nextval('product_seq'), 'A Arte da Guerra', 'Clássico livro de estratégia militar de Sun Tzu.', 500.0, 35.90, (SELECT id FROM category WHERE name = 'Livros')),
                                                                                        (nextval('product_seq'), 'Jaqueta de Couro Sintético', 'Jaqueta moderna e versátil, ideal para o dia a dia.', 150.0, 299.00, (SELECT id FROM category WHERE name = 'Moda')),
                                                                                        (nextval('product_seq'), 'Azeite Extra Virgem 500ml', 'Azeite de oliva de alta qualidade, prensado a frio.', 300.0, 45.00, (SELECT id FROM category WHERE name = 'Alimentos')),
                                                                                        (nextval('product_seq'), 'Kit Limpeza Automotiva', 'Conjunto completo para lavagem e polimento de veículos.', 100.0, 89.90, (SELECT id FROM category WHERE name = 'Automotivo'));

