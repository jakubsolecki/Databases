CREATE OR REPLACE FUNCTION RezerwacjeOsoby(id_osoby INT)
    RETURN osoby_wycieczki_table
AS
    result osoby_wycieczki_table;
    -- 1 when true, 0 otherwise (false)
    person_exists INT;
BEGIN

    -- check whether person exists; if it does, should return > 0
    SELECT COUNT(*) INTO person_exists FROM OSOBY O
        WHERE O.ID_OSOBY = id_osoby;

    if person_exists = 0 then
        raise_application_error(-20001, 'There is no person with given ID');
    end if;

    select OSOBA_WYCIECZKA(o.IMIE, o.NAZWISKO, w.ID_WYCIECZKI, w.NAZWA,
        w.KRAJ, w.DATA, r.STATUS)
        bulk collect
    into result
    from OSOBY o
        join REZERWACJE r on o.ID_OSOBY = r.ID_OSOBY
        join WYCIECZKI w on r.ID_WYCIECZKI = w.ID_WYCIECZKI
    where o.ID_OSOBY = RezerwacjeOsoby.id_osoby;

    return result;
END;
