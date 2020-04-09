create or replace trigger dodanie_rezerwacji
    after insert
    on REZERWACJE
    for each row
begin
    insert into REZERWACJE_LOG (ID_REZERWACJI, DATA, STATUS)
    values (:NEW.NR_REZERWACJI, current_date, :NEW.STATUS);

    update WYCIECZKI w
    set w.LICZBA_WOLNYCH_MIEJSC = w.LICZBA_WOLNYCH_MIEJSC - 1
    where w.ID_WYCIECZKI = :NEW.ID_WYCIECZKI;
end;
