INSERT INTO role(name)
VALUES ('operator');
INSERT INTO role(name)
VALUES ('user');
INSERT INTO role(name)
VALUES ('administrator');
INSERT INTO rules(name)
VALUES ('public');
INSERT INTO rules(name)
VALUES ('private');
INSERT INTO users(name, role_id)
VALUES ('Alex', 1);
INSERT INTO users(name, role_id)
VALUES ('Viktor', 2);
INSERT INTO rolerules (role_id, rules_id)
VALUES (1, 1);
INSERT INTO category(name)
VALUES ('ordinary');
INSERT INTO category(name)
VALUES ('VIP');
INSERT INTO state(name)
VALUES ('in progress');
INSERT INTO state(name)
VALUES ('closed');
INSERT INTO item(name, users_id, category_id, state_id)
VALUES ('First_Item', 1, 1, 1);
INSERT INTO item(name, users_id, category_id, state_id)
VALUES ('Second_Item', 2, 2, 2);
INSERT INTO comments(comment, item_id)
VALUES ('closed', 1);
INSERT INTO attachs(name, item_id)
VALUES ('attachment', 1);
