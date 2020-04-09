create or replace trigger zmiana_statusu
    after update
    on REZERWACJE
    for each row
declare
    new_places int;
begin
    insert into REZERWACJE_LOG (ID_REZERWACJI, DATA, REZERWACJE_LOG.STATUS)
    values (:NEW.NR_REZERWACJI, current_date, :NEW.STATUS);

    if :NEW.STATUS = 'A' then
        new_places := 1;
    else
        new_places := 0;
    end if;

    update WYCIECZKI
    set LICZBA_WOLNYCH_MIEJSC = LICZBA_WOLNYCH_MIEJSC + new_places
    where ID_WYCIECZKI = :NEW.ID_WYCIECZKI;

end;
