create table wycieczki
(
    id_wycieczki int generated always as identity not null,
    nazwa varchar2(100),
    kraj varchar2(50),
    data date,
    opis varchar2(200),
    liczba_miejsc int,
    constraint wycieczki_pk primary key
    (
        id_wycieczki
    )
    enable
);