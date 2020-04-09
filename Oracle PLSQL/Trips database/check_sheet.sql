
select * from wycieczki;

select * from osoby;

select * from REZERWACJE_LOG;

select * from REZERWACJE;

select * from table(UCZESTNICY_WYCIECZKI(42));

select * from table(DOSTEPNE_WYCIECZKI2('Polska', '2020-01-01', '2020-05-01'));

select * from table(REZERWACJEOSOBY(81));

begin
    delete from rezerwacje where NR_REZERWACJI = 42;
    commit;
end;

begin
    DODAJ_REZERWACJE3(42, 81);
end;

begin
    ZMIEN_LICZBE_MIEJSC3(42, 6);
end;
