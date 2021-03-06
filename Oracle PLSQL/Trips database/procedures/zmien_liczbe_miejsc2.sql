create or replace procedure zmien_liczbe_miejsc2 (id_wycieczki int, liczba_miejsc int)
as
    reserved_seats int;
    trip_seats int;
begin
    select w.LICZBA_MIEJSC
    into trip_seats
    from WYCIECZKI w
    where w.ID_WYCIECZKI = zmien_liczbe_miejsc2.id_wycieczki;

    if trip_seats is null then
        raise_application_error(-20001, 'Trip with given ID does not exist');
    end if;

    select count(*)
    into reserved_seats
    from REZERWACJE r
    where r.ID_WYCIECZKI = zmien_liczbe_miejsc2.id_wycieczki;

    if zmien_liczbe_miejsc2.liczba_miejsc <= 0 then
        raise_application_error(-20001, 'New number of seats must be grater than 0');
    end if;

    if zmien_liczbe_miejsc2.liczba_miejsc <= reserved_seats then
        raise_application_error(-20001, 'New number of seats must be grater than number of reserved seats');
    end if;

    update WYCIECZKI
    set
        LICZBA_MIEJSC = zmien_liczbe_miejsc2.liczba_miejsc,
        LICZBA_WOLNYCH_MIEJSC = LICZBA_WOLNYCH_MIEJSC +
                                (ZMIEN_LICZBE_MIEJSC2.liczba_miejsc - LICZBA_MIEJSC)
    where ID_WYCIECZKI = zmien_liczbe_miejsc2.id_wycieczki;

    commit;
end;