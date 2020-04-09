create or replace view RezerwacjePotwierdzone as
    select * from RezerwacjeWszystkie
    where status like 'P';