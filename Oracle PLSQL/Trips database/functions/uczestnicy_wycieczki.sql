CREATE OR REPLACE FUNCTION UCZESTNICY_WYCIECZKI (id_wycieczki INT)
    RETURN osoby_wycieczki_table
AS
    result osoby_wycieczki_table;
    -- 1 when true, 0 otherwise (false)
    trip_exists INT;
BEGIN
    -- check whether trip exists; if it does, should return > 0
    SELECT COUNT(*) INTO trip_exists FROM WYCIECZKI W
        WHERE UCZESTNICY_WYCIECZKI.id_wycieczki = W.ID_WYCIECZKI;

    IF trip_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20000, 'Trip does not exit');
    END IF;

    SELECT osoba_wycieczka(o.IMIE, o.NAZWISKO, w.ID_WYCIECZKI,
        w.NAZWA, w.KRAJ, w.DATA, r.STATUS) bulk collect
    INTO result
    FROM wycieczki w
        JOIN rezerwacje r ON r.ID_WYCIECZKI = w.ID_WYCIECZKI
        JOIN OSOBY o ON r.ID_OSOBY = o.ID_OSOBY
    WHERE w.ID_WYCIECZKI = UCZESTNICY_WYCIECZKI.id_wycieczki AND r.STATUS <> 'A';

    RETURN result;
END UCZESTNICY_WYCIECZKI;

