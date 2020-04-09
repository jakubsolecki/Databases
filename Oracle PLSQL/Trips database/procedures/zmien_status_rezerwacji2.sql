create or replace procedure zmien_status_rezerwacji2 (id_rezerwacji int, status char)
as
    reservation_exists int;
    old_status char(1);
begin
    -- check whether reservation exist; if it does, should return > 0 (1)
    select count(*)
    into reservation_exists
    from REZERWACJE r
    where NR_REZERWACJI = zmien_status_rezerwacji2.id_rezerwacji;

    if reservation_exists = 0 then
        raise_application_error(-20001, 'This reservation does not exist');
    end if;

    -- check reservation status
    select r.STATUS
    into old_status
    from REZERWACJE r
    where NR_REZERWACJI = zmien_status_rezerwacji2.id_rezerwacji;

    case
        when old_status = 'A' then
            raise_application_error(-20001, 'This reservation has been cancelled, which is persistent');
        when old_status = zmien_status_rezerwacji2.status then
           raise_application_error(-20001, 'This reservation already has desired status');
        when old_status = 'P' then
            if zmien_status_rezerwacji2.status <> 'Z' and zmien_status_rezerwacji2.status <> 'A' then
                raise_application_error(-20001, 'Status of this reservation ("P") can only be changed to "Z" or "A"');
            end if;
        when old_status = 'N' then
            if zmien_status_rezerwacji2.status <> 'Z' and zmien_status_rezerwacji2.status <> 'A' then
                raise_application_error(-20001, 'This reservation''s ("N") status can only be changed to "Z" or "A"');
            end if;
        when old_status = 'Z' then
            if zmien_status_rezerwacji2.status <> 'A' then
                raise_application_error(-20001, '');
            end if;
        else
            raise_application_error(-20001, 'Internal application error: status not found');
    end case;

    update REZERWACJE r
    set
        r.STATUS = zmien_status_rezerwacji2.status
    where NR_REZERWACJI = zmien_status_rezerwacji2.id_rezerwacji;
    
    if zmien_status_rezerwacji2.status = 'A' then
        update WYCIECZKI w
            set w.LICZBA_WOLNYCH_MIEJSC = w.LICZBA_WOLNYCH_MIEJSC + 1
        where w.ID_WYCIECZKI =
              (select r.ID_WYCIECZKI
               from REZERWACJE r
               where r.NR_REZERWACJI = zmien_status_rezerwacji2.id_rezerwacji);
    end if;

    insert into REZERWACJE_LOG (ID_REZERWACJI, DATA, STATUS)
    values (zmien_status_rezerwacji2.id_rezerwacji, sysdate, 'N');

    commit;
end;