--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2024-05-13 16:21:31

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 17331)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17330)
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 214
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- TOC entry 221 (class 1259 OID 17374)
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.log (
    id integer NOT NULL,
    order_id integer NOT NULL,
    amount numeric(10,2) NOT NULL
);


ALTER TABLE public.log OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17373)
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.log_id_seq OWNER TO postgres;

--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 220
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;


--
-- TOC entry 219 (class 1259 OID 17355)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    client_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    total_amount numeric(10,2) NOT NULL,
    CONSTRAINT orders_quantity_check CHECK ((quantity > 0))
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17354)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 218
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 222 (class 1259 OID 17385)
-- Name: orderslogview; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.orderslogview AS
 SELECT l.id,
    o.client_id,
    o.product_id,
    o.quantity,
    o.total_amount,
    l.order_id,
    l.amount
   FROM (public.orders o
     JOIN public.log l ON ((o.id = l.order_id)));


ALTER TABLE public.orderslogview OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17338)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(10,2) NOT NULL,
    stock integer NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (0)::numeric)),
    CONSTRAINT product_stockquantity_check CHECK ((stock >= 0))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17337)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 216
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 3192 (class 2604 OID 17334)
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- TOC entry 3195 (class 2604 OID 17377)
-- Name: log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- TOC entry 3194 (class 2604 OID 17358)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 17341)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3354 (class 0 OID 17331)
-- Dependencies: 215
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (id, name) FROM stdin;
1	Dan Nistor
3	Danut Popa
2	Alexandru Chipciu
12	Andrei Gorcea
14	Dorinel Oancea
\.


--
-- TOC entry 3360 (class 0 OID 17374)
-- Dependencies: 221
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.log (id, order_id, amount) FROM stdin;
1	15	33000.00
3	17	2600.00
4	18	1300.00
5	19	440.00
6	20	121.00
7	21	880.00
\.


--
-- TOC entry 3358 (class 0 OID 17355)
-- Dependencies: 219
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, client_id, product_id, quantity, total_amount) FROM stdin;
1	1	1	1	5000.00
2	2	2	2	6000.00
3	2	1	2	10000.00
6	1	6	1	2450.50
7	2	6	1	2450.50
8	2	3	2	2600.00
9	1	6	1	2450.50
11	1	4	4	484.00
12	1	3	12	15600.00
13	1	1	3	15000.00
14	1	1	2	10000.00
15	3	2	11	33000.00
16	3	4	2	242.00
17	2	3	2	2600.00
18	12	3	1	1300.00
19	3	12	20	440.00
20	1	4	1	121.00
21	3	12	40	880.00
\.


--
-- TOC entry 3356 (class 0 OID 17338)
-- Dependencies: 217
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, price, stock) FROM stdin;
6	Washing Machine	2450.50	1
1	Laptop	5000.00	3
2	Smartphone	3000.00	4
3	Headphones	1300.00	5
4	Mouse	121.00	3
12	USB Cable	22.00	340
\.


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 214
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 15, true);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 220
-- Name: log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.log_id_seq', 7, true);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 218
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 21, true);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 216
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 12, true);


--
-- TOC entry 3200 (class 2606 OID 17336)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 3206 (class 2606 OID 17379)
-- Name: log log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);


--
-- TOC entry 3204 (class 2606 OID 17361)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 17345)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 17362)
-- Name: orders fk_client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- TOC entry 3208 (class 2606 OID 17367)
-- Name: orders fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3209 (class 2606 OID 17380)
-- Name: log log_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);


-- Completed on 2024-05-13 16:21:31

--
-- PostgreSQL database dump complete
--

