drop database if exists kotlin_application;

create database if not exists kotlin_application;

use kotlin_application;

-- user_master
create table if not exists user_master
(
  user_id varchar(50)  not null PRIMARY KEY comment '会員ID',
  login_id varchar(50)  not null comment 'ログインID',
  password varchar(255)  not null comment 'パスワード',
  refresh_token varchar(255) comment 'リフレッシュトークン',
  refresh_token_issue_at datetime comment 'リフレッシュトークンの発行日時',
  created_at datetime  default current_timestamp comment '作成日時',
  updated_at datetime default current_timestamp on update current_timestamp comment '更新日時'
) engine=InnoDB charset=utf8;
create index idx_user_master on user_master(user_id);

-- user_attribute
create table if not exists user_attribute
(
  user_id varchar(50)  not null PRIMARY KEY comment '会員ID',
  name varchar(50)  not null comment '会員名',
  profile_image_path varchar(255)  not null comment 'プロフィール画像のパス',
  created_at datetime  default current_timestamp comment '作成日時',
  updated_at datetime default current_timestamp on update current_timestamp comment '更新日時'
) engine=InnoDB charset=utf8;
create index idx_user_attribute on user_attribute(user_id);

-- user_todo
create table if not exists user_todo
(
  todo_id bigint  not null PRIMARY KEY  auto_increment comment 'todoID',
  user_id varchar(50)  not null comment '会員ID',
  todo varchar(50)  not null comment 'todo',
  time_limit datetime comment '締切',
  priority enum('HIGH', 'MIDDLE', 'LOW') comment '優先度',
  done_flg boolean not null default false comment '完了フラグ',
  created_at datetime  default current_timestamp comment '作成日時',
  updated_at datetime default current_timestamp on update current_timestamp comment '更新日時'
) engine=InnoDB charset=utf8;
create index idx_user_todo on user_todo(user_id);