--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 12.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.almonds;
DROP TABLE IF EXISTS public.password_reset_token;


CREATE TABLE IF NOT EXISTS  public.users (
    deleted boolean DEFAULT false,
    id uuid DEFAULT public.uuid_generate_v1() NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone,
    name character varying(32),
    email character varying(32),
    password character varying(128),
    PRIMARY KEY (id)
);
ALTER TABLE public.users OWNER TO  demo;


CREATE TABLE IF NOT EXISTS  public.almonds (
    deleted boolean DEFAULT false,
    id uuid DEFAULT public.uuid_generate_v1() NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone,
    index character varying(32) unique NOT NULL,
    index_one_hot jsonb unique NOT NULL ,
    title character varying(32) unique NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE public.almonds OWNER TO  demo;


CREATE TABLE IF NOT EXISTS public.password_reset_token (
    deleted BOOLEAN DEFAULT false,
    id uuid DEFAULT public.uuid_generate_v1(),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP DEFAULT NULL,
    user_id uuid NOT NULL,
    token varchar(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id)
);
ALTER TABLE public.password_reset_token OWNER TO  demo;

CREATE TABLE if NOT EXISTS public.oauth_access_token (
  token_id VARCHAR(255),
  token bytea,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication bytea,
  refresh_token VARCHAR(255)
);


CREATE TABLE if NOT EXISTS public.oauth_refresh_token (
  token_id VARCHAR(255),
  token bytea,
  authentication bytea
);

--> Insert Almonds types with their index and represenation
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (0,'[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','tardy nonpareil');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (1,'[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','fra gulio grade');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (2,'[0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','atocha');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (3,'[0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','picantil');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (4,'[0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','pri morsky');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (5,'[0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','peerless');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (6,'[0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]','moncaio');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (7,'[0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0]','marta');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (8,'[0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0]','ferralise');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (9,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]','garrigaes');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (10,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0]','masbovera');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (11,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0]','ai');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (12,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0]','planeta');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (13,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0]','texas');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (14,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0]','philys');
INSERT INTO public.almonds(index,index_one_hot,title) VALUES (15,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]','desmayo lagueto');
