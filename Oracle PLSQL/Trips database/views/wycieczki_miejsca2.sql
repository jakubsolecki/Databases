create or replace view WycieczkiMiejsca2 as
    select w.id_wycieczki, w.kraj, w.data, w.nazwa,
           w.LICZBA_MIEJSC, w.LICZBA_WOLNYCH_MIEJSC
    from wycieczki w;