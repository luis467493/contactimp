CREATE TABLE public.users (
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_un UNIQUE (username)
);

CREATE TABLE public.contacts (
	name varchar NOT NULL,
	dob varchar NOT NULL,
	phone varchar NOT NULL,
	address varchar NOT NULL,
	credit_card varchar NOT NULL,
	franchise varchar NOT NULL,
	email varchar NOT NULL,
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE public.user_contacts (
	"user" uuid NOT NULL,
	contact uuid NOT NULL,
	id uuid NOT NULL,
	CONSTRAINT user_contacts_pk PRIMARY KEY (id),
	CONSTRAINT user_contacts_un UNIQUE ("user", contact),
	CONSTRAINT user_contacts_fk FOREIGN KEY (contact) REFERENCES public.contacts(id),
	CONSTRAINT user_contacts_fk_1 FOREIGN KEY ("user") REFERENCES public.users(id)
);
