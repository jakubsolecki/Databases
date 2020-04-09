create or replace function dostepne_wycieczki(kraj wycieczki.kraj%type, data_od date, data_do date)
    return WYCIECZKI_TABLE
as
    result WYCIECZKI_TABLE;
begin
    -- check whether dates are correct
    if data_od > data_do then
        raise_application_error(-20001, 'data_od must be <= data_do.');
    end if;

    select WYCIECZKA(w.ID_WYCIECZKI, w.NAZWA, w.KRAJ, w.DATA, w.OPIS, w.LICZBA_MIEJSC)
        bulk collect
    into result
    from WYCIECZKI w
    where w.KRAJ = dostepne_wycieczki.kraj
        and w.data >= data_od
        and w.data <= data_do
        and w.LICZBA_MIEJSC >
           (select count(*)
            from REZERWACJE r
            where r.STATUS <> 'A'
                and r.ID_WYCIECZKI = w.ID_WYCIECZKI);

    return result;
end;