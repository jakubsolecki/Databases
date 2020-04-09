alter table rezerwacje
    add constraint rezerwacje_fk1 foreign key (id_osoby)
        references osoby (id_osoby) enable;

alter table rezerwacje
    add constraint rezerwacje_fk2 foreign key (id_wycieczki)
        references wycieczki (id_wycieczki) enable;

alter table rezerwacje
    add constraint rezerwacje_chk1
        check (status in ('N', 'P', 'Z', 'A')) enable;

alter table rezerwacje_log
    add constraint rezerwacje_chk2
        check (status in ('N', 'P', 'Z', 'A')) enable;