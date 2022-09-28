create table post(
   id serial primary key,
   name text,
   city_id int,
   visible boolean,
   description text,
   created timestamp,
);