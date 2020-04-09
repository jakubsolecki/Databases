create or replace view RezerwacjeWszystkie as
    select w.id_wycieczki, w.nazwa, w.kraj, w.data, o.imie, o.nazwisko,
           r.status
    from wycieczki w
        join rezerwacje r on w.id_wycieczki = r.id_wycieczki
        join osoby o on r.id_osoby = o.id_osoby;