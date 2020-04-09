CREATE OR REPLACE TYPE osoba_wycieczka AS OBJECT
(
    imie VARCHAR2(50),
    nazwisko VARCHAR2(50),
    id_wycieczki INT,
    nazwa VARCHAR2(100),
    kraj VARCHAR2(50),
    data DATE,
    status CHAR(1)
);

CREATE OR REPLACE TYPE osoby_wycieczki_table AS TABLE OF osoba_wycieczka;