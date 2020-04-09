create table rezerwacje
(
    nr_rezerwacji int generated always  as identity  not null,
    id_wycieczki int,
    id_osoby int,
    status char(1),
    constraint rezerwacje_pk primary key
    (
        nr_rezerwacji
    )
    enable
);