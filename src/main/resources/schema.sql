drop table if exists counters cascade;
create table counters(
    key VARCHAR,
    value INT,

    PRIMARY KEY (key)
);