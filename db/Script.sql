CREATE TABLE dados.tb_conector (
	id_conector bigserial NOT NULL,
	id_contrato bigint NOT NULL,
	id_externo bigint NOT NULL,
	nome_externo varchar NULL,
	"label" varchar NULL,
	descricao varchar NULL,
	ativo boolean NOT NULL,
	conectado boolean NOT NULL,
	dh_exclusao timestamp without time zone NULL,
	CONSTRAINT tb_conector_pk PRIMARY KEY (id_conector)
);
GRANT TRIGGER, SELECT, DELETE, INSERT, TRUNCATE, UPDATE, REFERENCES ON TABLE dados.tb_conector TO postgres;


CREATE TABLE dados.tb_dispositivo (
	id_dispositivo bigserial NOT NULL,
	id_conector bigint NOT NULL,
	id_externo bigint NOT NULL,
	nome_externo varchar NULL,
	"label" varchar NULL,
	descricao varchar NULL,
	ativo boolean NOT NULL,
	conectado boolean NOT NULL,
	localizacao_latitude varchar NULL,
	localizacao_longitude varchar NULL,
	tempo_leitura bigint NULL,
	exibir boolean NULL,
	iniciar_automaticamente boolean NULL,
	dh_exclusao timestamp without time zone NULL,
	CONSTRAINT tb_dispositivo_pk PRIMARY KEY (id_dispositivo),
	CONSTRAINT tb_dispositivo_fk FOREIGN KEY (id_conector) REFERENCES dados.tb_conector(id_conector)
);
GRANT TRIGGER, SELECT, DELETE, INSERT, TRUNCATE, UPDATE, REFERENCES ON TABLE dados.tb_dispositivo TO postgres;


CREATE TABLE dados.tb_dado_dispositivo (
	id_dado_dispositivo bigserial NOT NULL,
	id_dispositivo bigint NOT NULL,
	id_externo bigint NOT NULL,
	nome_externo varchar NULL,
	"label" varchar NULL,
	descricao varchar NULL,
	ativo boolean NOT NULL,
	tamanho_memoria bigint NULL,
	tipo_memoria varchar NOT NULL,
	endereco_memoria bigint NULL,
	tipo_formato_valor varchar NOT NULL,
	id_valor_corrente bigint NULL,
	exibir boolean NULL,
	dh_exclusao timestamp without time zone NULL,
	CONSTRAINT tb_dado_dispositivo_pk PRIMARY KEY (id_dado_dispositivo),
	CONSTRAINT tb_dado_dispositivo_fk FOREIGN KEY (id_dispositivo) REFERENCES dados.tb_dispositivo(id_dispositivo)
);
GRANT TRIGGER, SELECT, DELETE, INSERT, TRUNCATE, UPDATE, REFERENCES ON TABLE dados.tb_dado_dispositivo TO postgres;


CREATE TABLE dados.tb_valor_dado (
	id_valor_dado bigserial NOT NULL,
	id_dado_dispositivo bigint NOT NULL,
	id_externo bigint NOT NULL,
	valor varchar NOT NULL,
	dh_leitura timestamp without time zone NOT NULL,
	dh_cricacao timestamp without time zone NOT NULL,
	CONSTRAINT tb_valor_dado_pk PRIMARY KEY (id_valor_dado),
	CONSTRAINT tb_valor_dado_un UNIQUE (id_externo),
	CONSTRAINT tb_valor_dado_fk FOREIGN KEY (id_dado_dispositivo) REFERENCES dados.tb_dado_dispositivo(id_dado_dispositivo)
);
GRANT TRIGGER, SELECT, DELETE, INSERT, TRUNCATE, UPDATE, REFERENCES ON TABLE dados.tb_valor_dado TO postgres;
