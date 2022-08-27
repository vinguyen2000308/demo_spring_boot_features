DROP TABLE IF EXISTS person;
CREATE table person(
                       id bigint primary key,
                       first_name tinytext not null,
                       last_name tinytext not null,
                       age smallint
);


insert into `test`.`person` values (1, "vi", "nguyen", 20);