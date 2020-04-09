create or replace procedure dodaj_rezerwacje(id_wycieczki int, id_osoby int)
as
    person_exists int;
    trip_avaliable int;
    reservation_exists int;
    new_reservation_id int;
begin
    -- check id person exists; If it does, should return > 0
    select count(*) into person_exists from OSOBY o where o.ID_OSOBY = dodaj_rezerwacje.id_osoby;

    if person_exists = 0 then
        raise_application_error(-20001, 'Trip with given id does not exist');
    end if;

    -- check if trip exists and has free places; if it does, should return > 0
    select count(*)
    into trip_avaliable
    from WYCIECZKIDOSTEPNE
    where WYCIECZKIDOSTEPNE.id_wycieczki = dodaj_rezerwacje.id_wycieczki;

    if trip_avaliable = 0 then
        raise_application_error(-20001, 'There are no places left for that trip');
    end if;

     select count(*)
     into reservation_exists
     from REZERWACJE r
     where r.ID_OSOBY = dodaj_rezerwacje.id_osoby
        and r.ID_WYCIECZKI = dodaj_rezerwacje.id_wycieczki
        and r.STATUS <> 'A';

    if reservation_exists > 0 then
        raise_application_error(-20001, 'This person already has booked places for that trip');
    end if;

    insert into REZERWACJE (ID_WYCIECZKI, ID_OSOBY, STATUS)
    values (dodaj_rezerwacje.id_wycieczki, dodaj_rezerwacje.id_osoby, 'N');

    insert into REZERWACJE_LOG (ID_REZERWACJI, DATA, STATUS)
    values (dodaj_rezerwacje.id_wycieczki, sysdate, 'N');

    commit;
end;