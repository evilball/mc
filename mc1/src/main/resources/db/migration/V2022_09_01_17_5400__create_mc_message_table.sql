create table mc_message(
    id integer,
    session_id integer,
    mc1_timestamp datetime,
    mc2_timestamp datetime,
    mc3_timestamp datetime,
    end_timestamp datetime,
    constraint pk_mc_message primary key (id));

create sequence mc_message_id_seq;