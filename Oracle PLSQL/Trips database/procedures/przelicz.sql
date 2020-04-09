create or replace procedure przelicz
as
begin
    update WYCIECZKI w
    set LICZBA_WOLNYCH_MIEJSC =
        w.LICZBA_MIEJSC -
            (select count(*)
             from rezerwacje r
             where r.ID_WYCIECZKI = w.ID_WYCIECZKI
                and r.STATUS <> 'A');
    commit;
end;