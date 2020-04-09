create or replace trigger zmiana_liczby_miejsc
    after update of LICZBA_MIEJSC
    on WYCIECZKI
    for each row
begin
    update WYCIECZKI
    set LICZBA_WOLNYCH_MIEJSC = LICZBA_WOLNYCH_MIEJSC + (:NEW.LICZBA_MIEJSC - LICZBA_MIEJSC);
end;