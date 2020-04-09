create or replace view WycieczkiMiejsca as
    select w.id_wycieczki, w.kraj, w.data, w.nazwa,
           w.LICZBA_MIEJSC - (
               select count(*)
               from REZERWACJE R
               where R.ID_WYCIECZKI = w.ID_WYCIECZKI and R.STATUS <> 'A'
           ) as wolne_miejsca
    from wycieczki w;