CREATE OR REPLACE TYPE wycieczka AS OBJECT
(
    id_wycieczki INT,
    nazwa varchar2(100),
    kraj varchar2(50),
    data date,
    opis varchar2(200),
    liczba_miejsc INT
);

CREATE OR REPLACE TYPE wycieczki_table AS TABLE OF wycieczka;