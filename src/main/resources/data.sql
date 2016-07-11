
INSERT INTO shop.product(name, description, price) VALUES ('book', 'fantasy book', 33.22);

INSERT INTO shop.category(name, description) VALUES ('computers', 'computers description');
INSERT INTO shop.category(parent_category_id, name, description) VALUES (1, 'laptop', 'laptops description');