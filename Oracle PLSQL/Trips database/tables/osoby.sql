create table osoby
(
    id_osoby int generated always as identity not null,
    imie varchar2(50),
    nazwisko varchar2(50),
    pesel varchar2(11),
    kontakt varchar2(100),
    constraint osoby_pk primary key
    (
        id_osoby
    )
    enable
);