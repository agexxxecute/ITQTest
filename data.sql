DROP TABLE IF EXISTS order_details;

DROP TABLE IF EXISTS orders;

CREATE TABLE orders(
    order_id bigserial primary key,
    number varchar(13) not null,
    price decimal not null,
    date date not null,
    recipient varchar(50) not null,
    delivery_address varchar(75) not null,
    payment_type varchar(4) not null,
    delivery_type varchar(13) not null
);

CREATE TABLE order_details(
    order_details_id bigserial primary key,
    article bigint not null,
    product_name varchar(50) not null,
    quantity int not null default (1),
    price decimal not null,
    orders_id bigint not null,
    foreign key(orders_id) references orders(order_id)
);

INSERT INTO orders (number, price, date, recipient, delivery_address, payment_type, delivery_type) VALUES
('1111120240101', '100', '2024-01-01', 'Иванов И.И.', 'Москва, Мансуровский переулок, 9', 'CASH', 'SELF_DELIVERY'),
('9876520240603', '50', '2024-06-03', 'Петров А.А.', 'Санкт-Петербург, Думская, 3', 'CARD', 'DOOR_DELIVERY'),
('5465220241212', '500', '2024-12-12', 'Сидоров Р.Д.', 'Самара, Ленина, 52', 'CARD', 'SELF_DELIVERY');

INSERT INTO order_details (article, product_name, quantity, price, orders_id) VALUES
(87894, 'Ноутбук', 1, 200, 3),
(8884, 'Клавиатура', 2, 50, 1),
(64541, 'Стереосистема', 1, 300, 3);


