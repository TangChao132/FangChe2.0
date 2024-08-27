create database fangche;
use fangche;

create table if not exists course
(
    name         varchar(255)  null,
    introduction varchar(2048) null,
    chapters     varchar(2048) null,
    resource     varchar(255)  null,
    teacher_id   bigint        null,
    category     varchar(255)  null,
    image        varchar(255)  null,
    visits       int           null,
    id           bigint        not null
        primary key,
    created_at   datetime      null,
    updated_at   datetime      null
);

create table if not exists course_collection
(
    uid        bigint        null,
    courses    varchar(2048) null,
    id         bigint        not null
        primary key,
    created_at datetime      null,
    updated_at datetime      null
);

create table if not exists history
(
    id          bigint       not null
        primary key,
    uid         bigint       null,
    type        varchar(255) null,
    submit_time datetime     null
)
    collate = utf8mb4_unicode_ci;

create table if not exists user
(
    account      varchar(255)  null,
    password     varchar(255)  null,
    nickname     varchar(255)  null,
    avatar       varchar(255)  null,
    gender       int           null,
    signature    varchar(512)  null,
    role         int           null,
    introduction varchar(1024) null,
    session_id   bigint        null,
    id           bigint        not null
        primary key,
    created_at   datetime      null,
    updated_at   datetime      null
);

create table if not exists verify_code
(
    account    varchar(255) not null
        primary key,
    code       varchar(255) null,
    created_at datetime     null,
    used       bit          null
);

create table if not exists video
(
    id           bigint       not null
        primary key,
    name         varchar(255) null,
    url          varchar(255) null,
    time_length  bigint       null,
    hits         bigint       null,
    release_time datetime     null,
    update_time  datetime     null
)
    collate = utf8mb4_unicode_ci;

