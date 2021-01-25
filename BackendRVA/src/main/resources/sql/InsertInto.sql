INSERT INTO "preduzece"("id", "naziv", "pib", "sediste", "opis")
VALUES(nextval('preduzece_seq'), 'Naziv preduzeca 1', 100, 'Sediste preduzeca 1', 'Opis preduzeca 1');
INSERT INTO "preduzece"("id", "naziv", "pib", "sediste", "opis")
VALUES(nextval('preduzece_seq'), 'Naziv preduzeca 2', 200, 'Sediste preduzeca 2', 'Opis preduzeca 2');
INSERT INTO "preduzece"("id", "naziv", "pib", "sediste", "opis")
VALUES(nextval('preduzece_seq'), 'Naziv preduzeca 3', 300, 'Sediste preduzeca 3', 'Opis preduzeca 3');
INSERT INTO "preduzece"("id", "naziv", "pib", "sediste", "opis")
VALUES(-100, 'Naziv preduzeca 4', 400, 'Sediste preduzeca 4', 'Opis preduzeca 4');

INSERT INTO "obrazovanje"("id", "naziv", "stepen_strucne_spreme", "opis")
VALUES (nextval('obrazovanje_seq'), 'Naziv obrazovanja 1', 'Stepen 1', 'Opis obrazovanja 1');
INSERT INTO "obrazovanje"("id", "naziv", "stepen_strucne_spreme", "opis")
VALUES (nextval('obrazovanje_seq'), 'Naziv obrazovanja 2', 'Stepen 2', 'Opis obrazovanja 2');
INSERT INTO "obrazovanje"("id", "naziv", "stepen_strucne_spreme", "opis")
VALUES (nextval('obrazovanje_seq'), 'Naziv obrazovanja 3', 'Stepen 3', 'Opis obrazovanja 3');
INSERT INTO "obrazovanje"("id", "naziv", "stepen_strucne_spreme", "opis")
VALUES (-100, 'Naziv obrazovanja 4', 'Stepen 4', 'Opis obrazovanja 4');

INSERT INTO "sektor"("id", "naziv", "oznaka", "preduzece")
VALUES(nextval('sektor_seq'), 'Naziv sektora 1', 'Oznaka 1', 3);
INSERT INTO "sektor"("id", "naziv", "oznaka", "preduzece")
VALUES(nextval('sektor_seq'), 'Naziv sektora 2', 'Oznaka 2', 4);
INSERT INTO "sektor"("id", "naziv", "oznaka", "preduzece")
VALUES(-100, 'Naziv sektora 3', 'Oznaka 3', 3);

INSERT INTO "radnik"("id", "ime", "prezime", "broj_lk", "obrazovanje", "sektor")
VALUES (nextval('radnik_seq'), 'Ime radnika 1', 'Prezime radnika 1', 156, 3, 5);
INSERT INTO "radnik"("id", "ime", "prezime", "broj_lk", "obrazovanje", "sektor")
VALUES (nextval('radnik_seq'), 'Ime radnika 2', 'Prezime radnika 2', 256, 4, 6);
INSERT INTO "radnik"("id", "ime", "prezime", "broj_lk", "obrazovanje", "sektor")
VALUES (nextval('radnik_seq'), 'Ime radnika 3', 'Prezime radnika 3', 356, 3, 5);
INSERT INTO "radnik"("id", "ime", "prezime", "broj_lk", "obrazovanje", "sektor")
VALUES (-100, 'Ime radnika 4', 'Prezime radnika 4', 456, 4, 6);