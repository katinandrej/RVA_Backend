DROP TABLE IF EXISTS obrazovanje CASCADE;
DROP TABLE IF EXISTS preduzece CASCADE;
DROP TABLE IF EXISTS radnik CASCADE;
DROP TABLE IF EXISTS sektor CASCADE;

DROP SEQUENCE IF EXISTS obrazovanje_seq CASCADE;
DROP SEQUENCE IF EXISTS preduzece_seq CASCADE;
DROP SEQUENCE IF EXISTS radnik_seq CASCADE;
DROP SEQUENCE IF EXISTS sektor_seq CASCADE;

CREATE TABLE obrazovanje (
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
	stepen_strucne_spreme varchar(10) NOT NULL,
    opis varchar(500) NOT NULL
);

CREATE TABLE preduzece(
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
    pib integer NOT NULL,
    sediste varchar(100) NOT NULL,
	opis varchar (500) NOT NULL
);

CREATE TABLE radnik(
	id integer NOT NULL,
    ime varchar(50) NOT NULL,
    prezime varchar(50) NOT NULL,
    broj_lk integer NOT NULL,
	obrazovanje integer NOT NULL,
	sektor integer NOT NULL
);

CREATE TABLE sektor (
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
    oznaka varchar(10) NOT NULL,
	preduzece integer NOT NULL
);

ALTER TABLE obrazovanje
	ADD CONSTRAINT PK_Obrazovanje PRIMARY KEY (id);
ALTER TABLE preduzece
	ADD CONSTRAINT PK_Preduzece PRIMARY KEY (id);
ALTER TABLE radnik
	ADD CONSTRAINT PK_Radnik PRIMARY KEY (id);
ALTER TABLE sektor
	ADD CONSTRAINT PK_Sektor PRIMARY KEY (id);
	
ALTER TABLE radnik
	ADD CONSTRAINT FK_Radnik_Obrazovanje FOREIGN KEY (obrazovanje)
		REFERENCES obrazovanje (id);
ALTER TABLE radnik
	ADD CONSTRAINT FK_Radnik_Sektor FOREIGN KEY (sektor)
		REFERENCES sektor (id);
ALTER TABLE sektor
	ADD CONSTRAINT FK_Sektor_Preduzece FOREIGN KEY (preduzece)
		REFERENCES preduzece (id);
		
CREATE INDEX IDXFK_Radnik_Obrazovanje 
	ON radnik (obrazovanje);
CREATE INDEX IDXFK_Radnik_Sektor
	ON radnik (sektor);
CREATE INDEX IDXFK_Sektor_Preduzece
	ON sektor (preduzece);
	
CREATE SEQUENCE obrazovanje_seq
	INCREMENT 1;
CREATE SEQUENCE preduzece_seq
	INCREMENT 1;
CREATE SEQUENCE radnik_seq
	INCREMENT 1;
CREATE SEQUENCE sektor_seq
	INCREMENT 1;