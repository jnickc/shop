/**
  PGSql script, creates shop schema with product, category and product_category entities
 */


DROP SCHEMA shop CASCADE;
CREATE SCHEMA shop;

SET search_path = shop, pg_catalog;

CREATE SEQUENCE product_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

CREATE SEQUENCE category_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

CREATE TABLE product (
  product_id  INTEGER PRIMARY KEY DEFAULT nextval('product_id_seq'),
  name        VARCHAR(100),
  description VARCHAR(2000),
  price       FLOAT,
  currency    VARCHAR(20)
);

CREATE TABLE category (

  category_id        INTEGER PRIMARY KEY DEFAULT nextval('category_id_seq'),
  parent_category_id INTEGER ,
  name               VARCHAR(100),
  description        VARCHAR(2000)
);

CREATE TABLE product_category (

  product_id  INTEGER NOT NULL,
  category_id INTEGER NOT NULL
);

ALTER TABLE ONLY product_category
  ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE ONLY product_category
  ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (category_id);


