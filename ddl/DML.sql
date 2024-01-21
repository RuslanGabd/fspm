use fpms;
INSERT INTO product (`name`, `description`)
values ('Apple', ''),
       ('Chicken Breast', ''),
       ('Bread', ''),
       ('Spinach', ''),
       ('Yogurt', ''),
       ('Pasta', '');
TRUNCATE TABLE product;

INSERT INTO category_of_product (`name`)
values ('Meat'),
       ('Fruit'),
       ('Bakery'),
       ('Vegetables'),
       ('Dairy'),
       ('Grains');

INSERT INTO product_category (`product_id`, `category_id`)
values ('2', '1'),
       ('1', '2'),
       ('3', '3'),
       ('4', '4'),
       ('5', '5'),
       ('6', '6');

INSERT INTO shop(`name`, `address`)
values ('Fresh Mart', '123 Main St'),
       ('Meaty Delights', '456 Oak Ave'),
       ('Baker''s Haven', '789 Elm Blvd'),
       ('Green Grocers ', '234 Pine Ln');
INSERT INTO category_of_shop(`name`)
values ('Produce Market'),
       ('Mini Market'),
       ('Dairy Shop'),
       ('Hyper Market');

INSERT INTO shop_category(`shop_id`, `category_id`)
values ('1', '1'),
       ('2', '2'),
       ('3', '3'),
       ('4', '4');

TRUNCATE TABLE product;
select *
from product pr
         join product_category pc on pr.id = pc.product_id
         join category_of_product cp on pc.category_id = cp.id;

INSERT INTO product_price_shop(product_id, shop_id, valuePrice, datePrice)
    value (1, 1, 13.00, '2023-05-21'),
    (1, 2, 15.00, '2023-05-21'),
    (1, 1, 14.00, '2023-07-21'),
    (1, 2, 16.00, '2023-07-21'),
    (1, 1, 13.00, '2023-09-01'),
    (1, 2, 16.00, '2023-09-01'),
#
    (2, 1, 23.00, '2023-05-21'),
    (2, 2, 25.00, '2023-05-21'),
    (2, 1, 24.00, '2023-07-21'),
    (2, 2, 26.00, '2023-07-21'),
    (2, 1, 23.00, '2023-09-01'),
    (2, 2, 26.00, '2023-09-01'),
#
    (3, 1, 23.00, '2023-05-21'),
    (3, 2, 23.00, '2023-05-21'),
    (3, 1, 25.00, '2023-07-21'),
    (3, 2, 24.00, '2023-07-21'),
    (3, 1, 13.00, '2023-09-01'),
    (3, 2, 26.00, '2023-09-01');

select pps1_0.id,
       pps1_0.datePrice,
       pps1_0.product_id,
       p1_0.id,
       p1_0.description,
       p1_0.name,
       pps1_0.shop_id,
       s1_0.id,
       s1_0.address,
       s1_0.name,
       pps1_0.valuePrice
from product_price_shop pps1_0
         join
     product p1_0
     on p1_0.id = pps1_0.product_id
         join
     shop s1_0
     on s1_0.id = pps1_0.shop_id
where pps1_0.id = 1

select p1_0.id,
       p1_0.description,
       p1_0.name
from product p1_0
         join
     product_price_shop pps1_0
     on p1_0.id = pps1_0.product_id