CREATE TABLE launch(
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(200) NOT NULL,
	expiration_date DATE NOT NULL,
	payment_date DATE,
	currency_value DECIMAL(20,2) NOT NULL,
	type VARCHAR(30) NOT NULL,
	category_code BIGINT(20) NOT NULL,
	person_code BIGINT(20) NOT NULL,
	observation VARCHAR(100),
	
	FOREIGN KEY (category_code) REFERENCES category(code),
	FOREIGN KEY (person_code) REFERENCES person(code)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'REVENUE', 1, 1);
INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('Top Club', '2017-06-10', null, 120, null, 'REVENUE', 3, 3);
INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'REVENUE', 3, 4);
INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'REVENUE', 4, 2);
INSERT INTO launch (description, expiration_date, payment_date, currency_value, observation, type, category_code, person_code) values ('Bahamas', '2017-06-10', null, 500, null, 'REVENUE', 1, 3);
