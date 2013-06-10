# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table drugs (
  id                        bigint not null,
  name                      varchar(255),
  company                   varchar(255),
  price                     bigint,
  quantity                  bigint,
  constraint pk_drugs primary key (id))
;

create table personal (
  id                        bigint not null,
  roll_number               bigint,
  name                      varchar(255),
  father_name               varchar(255),
  national_id_card_number   bigint,
  family_name               varchar(255),
  grand_father_name         varchar(255),
  occuption                 varchar(255),
  date_of_birth             timestamp,
  address                   varchar(255),
  email                     varchar(255),
  telephone_number          bigint,
  age                       bigint,
  emergency_number          bigint,
  salary                    bigint,
  is_percentage             boolean,
  not_percentage            boolean,
  both                      boolean,
  percentage                bigint,
  section                   varchar(255),
  isready                   boolean,
  info                      varchar(255),
  constraint pk_personal primary key (id))
;

create table user (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  password                  varchar(255),
  bed_access                boolean,
  accounting_access         boolean,
  lab_access                boolean,
  time_reserving_access     boolean,
  ambulance_access          boolean,
  operation_access          boolean,
  reception_access          boolean,
  office_access             boolean,
  create_user_access        boolean,
  drug_store_access         boolean,
  constraint pk_user primary key (id))
;

create sequence drugs_seq;

create sequence personal_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists drugs;

drop table if exists personal;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists drugs_seq;

drop sequence if exists personal_seq;

drop sequence if exists user_seq;
