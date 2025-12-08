-- ===========================================
-- CRIAÇÃO DO BANCO DE DADOS
-- ===========================================
DROP DATABASE IF EXISTS devweb;

CREATE DATABASE devweb
    WITH OWNER = postgres
    ENCODING 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TEMPLATE template0;

-- Conecta no banco recém-criado
\c devweb;

-- ===========================================
-- INÍCIO DA ESTRUTURA DO BANCO
-- ===========================================

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

SET default_tablespace = '';
SET default_table_access_method = heap;

--
-- Tabela: mesa
--
CREATE TABLE public.mesa (
    numero integer NOT NULL,
    capacidade integer NOT NULL,
    disponivel boolean NOT NULL
);

ALTER TABLE public.mesa OWNER TO postgres;

--
-- Tabela: reserva
--
CREATE TABLE public.reserva (
    id integer NOT NULL,
    numeromesa integer NOT NULL,
    nomecliente varchar(100) NOT NULL,
    data date NOT NULL,
    status varchar(20) DEFAULT 'ATIVA'
);

ALTER TABLE public.reserva OWNER TO postgres;

--
-- Sequence: reserva_id_seq
--
CREATE SEQUENCE public.reserva_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.reserva_id_seq OWNER TO postgres;

ALTER SEQUENCE public.reserva_id_seq
    OWNED BY public.reserva.id;

ALTER TABLE ONLY public.reserva
    ALTER COLUMN id SET DEFAULT nextval('public.reserva_id_seq'::regclass);

--
-- Dados da tabela mesa
--
COPY public.mesa (numero, capacidade, disponivel) FROM stdin;
8   4   t
9   2   t
10  10  t
7   6   t
1   2   t
2   4   t
3   4   t
4   6   t
5   2   t
6   8   t
\.

--
-- Dados da tabela reserva
--
COPY public.reserva (id, numeromesa, nomecliente, data, status) FROM stdin;
\.

--

--
-- Constraints
--
ALTER TABLE ONLY public.mesa
    ADD CONSTRAINT mesa_pkey PRIMARY KEY (numero);

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT reserva_pkey PRIMARY KEY (id);

-- ===========================================
-- FIM DO SCRIPT
-- ===========================================
