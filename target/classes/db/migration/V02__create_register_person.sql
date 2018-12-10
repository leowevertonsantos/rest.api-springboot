CREATE TABLE person(
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	street VARCHAR(200),
	number VARCHAR(30),
	complement VARCHAR(50),
	neighborhood VARCHAR(100),
	zip_code VARCHAR(30),
	city VARCHAR(150),
	state VARCHAR(200),
	status BOOLEAN NOT NULL	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, status ) VALUES ('Léo', 'Rua Teste', '277', '', 'Spring', '08977-530', 'Valinhos', 'São Paulo', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, status ) VALUES ('João', 'Rua Spring', '277', '', 'Spring', '08347-530', 'Ubatuba', 'São Paulo', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, status ) VALUES ('Maria', 'Rua Java', '277', '', 'Spring', '08977-556', 'São Bernardo', 'São Paulo', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, status ) VALUES ('Manoel', 'Rua Teste', '277', '', 'Spring', '12377-530', 'Ubatuba', 'São Paulo', true);
INSERT INTO person (name, street, number, complement, neighborhood, zip_code, city, state, status ) VALUES ('Cecilia', 'Rua Teste', '277', '', 'Spring', '08457-530', 'Valinhos', 'São Paulo', true);