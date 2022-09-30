create table candidate(
   id serial primary key,
   name text,
   description text,
   created timestamp,
   visible boolean,
   photo bytea,
   city_id int
);