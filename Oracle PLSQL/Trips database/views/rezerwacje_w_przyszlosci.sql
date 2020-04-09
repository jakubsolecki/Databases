create or replace view RezerwacjeWPrzyszlosci as
    select * from RezerwacjeWszystkie
    where data > sysdate;