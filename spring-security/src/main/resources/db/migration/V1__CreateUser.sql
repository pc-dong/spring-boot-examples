create TABLE user(
  id BIGINT NOT NULL ,
  username VARCHAR(128) NOT NULL ,
  password VARCHAR(128) NOT NULL ,
  name VARCHAR(128) NOT NULL ,
  PRIMARY KEY (id)
);

INSERT INTO user(id, username, password, name) VALUES (1, 'admin', '{bcrypt}$2a$10$KD7/fT6MjgDATYpQnGp9BuKUMV18RUo.S6Cf7jm7i2HBs10MRRuSy', '系统管理员');
INSERT INTO user(id, username, password, name) VALUES (2, 'user', 'password', '普通用户');