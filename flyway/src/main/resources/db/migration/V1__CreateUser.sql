create TABLE user(
  id INTEGER NOT NULL ,
  username VARCHAR(64) NOT NULL ,
  PRIMARY KEY (id)
);

INSERT INTO user(id, username) VALUES (1, 'admin');