CREATE TABLE pessoa(
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
ativo BOOLEAN NOT NULL,
logradouro VARCHAR(30) NOT NULL,
numero VARCHAR(5) NOT NULL,
complemento VARCHAR(15) NOT NULL,
bairro VARCHAR(15) NOT NULL,
cep VARCHAR(9) NOT NULL,
cidade VARCHAR(15) NOT NULL,
estado VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;