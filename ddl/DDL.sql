use fpms;
#  DROP TABLE IF EXISTS product, shop, price, shop_category, product_category,
#      products_categories, shops_categories, products_prices_shops;

CREATE TABLE `product`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(80)     NOT NULL,
    `description` VARCHAR(25)     NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

CREATE TABLE `shop`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(80)     NOT NULL,
    `address` VARCHAR(80)     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;


CREATE TABLE `category_of_product`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30)     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

CREATE TABLE `category_of_shop`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30)     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;



CREATE TABLE product_category
(
    `product_id`  BIGINT UNSIGNED NOT NULL,
    `category_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`product_id`, `category_id`),
    CONSTRAINT `Constr_product_category_product_fk`
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `Constr_product_category_category_fk`
        FOREIGN KEY (`category_id`) REFERENCES `category_of_product` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

CREATE TABLE shop_category
(
    `shop_id`     BIGINT UNSIGNED NOT NULL,
    `category_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`shop_id`, `category_id`),
    CONSTRAINT `Constr_shopscategories_shops_fk`
        FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `Constr_shopscategories_category_fk`
        FOREIGN KEY (`category_id`) REFERENCES `category_of_shop` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;


CREATE TABLE product_price_shop
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT UNSIGNED NOT NULL,
    `shop_id`    BIGINT UNSIGNED NOT NULL,
    `valuePrice` DECIMAL(8, 2),
    `datePrice`  DATE,
    PRIMARY KEY (`id`, `product_id`, `shop_id`),
    CONSTRAINT `Constr_product_price_shop_product_fk`
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `Constr_product_price_shop_shop_fk`
        FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;