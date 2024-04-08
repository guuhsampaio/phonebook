CREATE TABLE desafio.contato (
        contato_id SERIAL PRIMARY KEY,
        contato_nome VARCHAR(100),
        contato_email VARCHAR(255),
        contato_celular VARCHAR(11),
        contato_telefone VARCHAR(10),
        contato_sn_favorito CHAR(1),
        contato_sn_ativo CHAR(1),
        contato_sn_deletado CHAR(1),
        contato_dh_ins TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        contato_dh_upd TIMESTAMP WITHOUT TIME ZONE,
        contato_dh_del TIMESTAMP WITHOUT TIME ZONE
);