create or replace  trigger zakaz_usuwania_rezerwacji
    before delete
    on REZERWACJE
    for each row
begin
    raise_application_error(-20001, 'You cannot remove reservation. ' ||
                                    'Only changing ststaus is allowed');
end;