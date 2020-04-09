create table rezerwacje_log
(
    id int generated always as identity not null,
    id_rezerwacji int,
    data date,
    status char(1),
    constraint rezerwacje_log_pk primary key
    (
        id
    )
    enable
);