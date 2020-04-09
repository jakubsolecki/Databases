create or replace view WycieczkiDostepne as
    select * from WycieczkiMiejsca
    where wolne_miejsca > 0 and DATA > sysdate;