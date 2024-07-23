DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand           VARCHAR(20) NOT NULL,
    category        VARCHAR(5)  NOT NULL,
    price           INT         NOT NULL,
    created_at      DATETIME    NOT NULL,
    updated_at      DATETIME    NOT NULL
);

CREATE INDEX idx_brand ON product(brand);
CREATE INDEX idx_category ON product(category);