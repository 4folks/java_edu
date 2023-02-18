INSERT INTO category (name) VALUES ('meat');
INSERT INTO category (name) VALUES ('dairy');
INSERT INTO category (name) VALUES ('vegetables');
INSERT INTO category (name) VALUES ('confectionery');

INSERT INTO groups (name) VALUES ('children');
INSERT INTO groups (name) VALUES ('adult');

INSERT INTO person (name, monthly_budget, balance, groups_id) VALUES ('igor', 300, 250, 1);
INSERT INTO person (name, monthly_budget, balance, groups_id) VALUES ('john', 200, 200, 1);
INSERT INTO person (name, monthly_budget, balance, groups_id) VALUES ('tom', 3000, 2300, 2);

INSERT INTO goods (name, cost, category_id) VALUES ('cheese', 50, 2);
INSERT INTO goods (name, cost, category_id) VALUES ('potato', 5, 3);
INSERT INTO goods (name, cost, category_id) VALUES ('cake', 10, 4);

INSERT INTO purchase (label, person_id, goods_id, item_count, p_timestamp) VALUES ('morning', 1, 1, 4, '2023-12-01');
INSERT INTO purchase (label, person_id, goods_id, item_count, p_timestamp) VALUES ('morning', 1, 2, 5, '2023-12-01');
INSERT INTO purchase (label, person_id, goods_id, item_count, p_timestamp) VALUES ('morning', 1, 3, 2, '2023-12-01');
INSERT INTO purchase (label, person_id, goods_id, item_count, p_timestamp) VALUES ('morning', 1, 1, 4, '2023-12-01');

INSERT INTO purchase (label, person_id, goods_id, item_count, p_timestamp) VALUES ('day', 1, 1, 4, '2023-12-01');




