create table account
(
    id            bigserial,
    first_name    varchar(100) not null,
    last_name     varchar(100),
    email         varchar(100) not null unique,
    hash_password varchar(100) not null,
    phone         varchar(25)  not null,
    constraint account_pk primary key (id)
);
create table marks
(
    id   serial,
    mark varchar(40) unique,
    constraint marks_id_pk primary key (id)
);


create table comment
(
    id      bigserial,
    user_id bigint,
    text    text not null,
    data    date not null default current_date,
    car_id  bigint,
    constraint comment_pk primary key (id),
    constraint user_id_fk foreign key (user_id) references account (id),
    constraint car_id_fk foreign key (car_id) references car (id)
);
create table car
(
    id          bigserial,
    mark        bigint,
    model       varchar(50),
    price       numeric not null,
    description text    not null,
    mileage     numeric,
    constraint car_pk primary key (id),
    constraint mark_fk foreign key (mark) references marks (id)
);
create table "order"
(
    id       bigserial,
    users_id bigint not null,
    car_id   bigint not null,
    active   bool,
    constraint id_pk primary key (id)
);
alter table "order"
    add constraint account_id_fk foreign key (users_id) references account (id)

create table file
(
    id                 serial,
    storage_file_name  uuid,
    original_file_name varchar(200),
    type               varchar(20),
    size               bigint,
    constraint file_pk primary key (id)
);
alter table car add column file_id int constraint file_id_fk references file (id);
insert into marks(mark)
values ('renault'),
       ('saab'),
       ('skoda'),
       ('ssangyoung'),
       ('lada'),
       ('volvo'),
       ('suzuki');
insert into car (mark, price, description, mileage)
values (1, 5000, 'good car', 125000),
       (2, 15000, 'very good car', 15000);
select *
from car
         join
     (SELECT car_id, array_agg(location)
      from album
               join image i on i.id = album.image_id
      group by car_id) as t on car.id = car_id;