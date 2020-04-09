create or replace view WycieczkiDostepne2 as
    select * from WycieczkiMiejsca2
    where liczba_wolnych_miejsc > 0 and DATA > sysdate;;