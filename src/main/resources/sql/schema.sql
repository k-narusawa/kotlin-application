drop database if exists kotlin_application;

create database if not exists kotlin_application;

use kotlin_application;

create table if not exists user_master
(
  user_id varchar(50)  not null PRIMARY KEY,
  login_id varchar(50)  not null,
  password varchar(255)  not null,
  refresh_token varchar(255),
  refresh_token_issue_at datetime
) ENGINE=InnoDB CHARSET=utf8;