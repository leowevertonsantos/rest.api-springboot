CREATE TABLE category(
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) VALUES ('Recreation');
INSERT INTO category (name) VALUES ('Food');
INSERT INTO category (name) VALUES ('SuperMarket');
INSERT INTO category (name) VALUES ('Drugstore');
INSERT INTO category (name) VALUES ('Others');
