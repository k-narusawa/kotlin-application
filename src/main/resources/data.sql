INSERT INTO user_master
  (user_id,password,pass_update_date,login_miss_times,unlock,tenant_id,user_name,mail_address,enabled,user_due_date)
VALUES
  ('system','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','2099123123:59:59',0,true,'tenant','システム管理者','system@xxx.co.jp',true,'2099123123:59:59');

INSERT INTO user_master
  (user_id,password,pass_update_date,login_miss_times,unlock,tenant_id,user_name,mail_address,enabled,user_due_date)
VALUES
  ('user','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','2099123123:59:59',0,true,'tenant','一般ユーザー','user@xxx.co.jp',true,'2099123123:59:59'),
  ('sample1','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','1999123123:59:59',0,true,'tenant','ユーザー1','user1@xxx.co.jp',true,'2099123123:59:59'),
  ('sample2','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','2099123123:59:59',0,false,'tenant','ユーザー2','user2@xxx.co.jp',true,'2099123123:59:59'),
  ('sample3','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','2099123123:59:59',0,true,'tenant','ユーザー3','user3@xxx.co.jp',false,'2099123123:59:59'),
  ('sample4','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','2099123123:59:59',0,true,'tenant','ユーザー4','user4@xxx.co.jp',true,'1999123123:59:59');

INSERT INTO role_master(role_id,role_name)VALUES
  ('admin','ROLE_ADMIN'),('general','ROLE_GENERAL');

INSERT INTO user_role
  (id,user_id,role_id)
VALUES
  (1,'system','admin'),
  (2,'system','general'),
  (3,'sample1','general'),
  (4,'sample2','general'),
  (5,'sample3','general'),
  (6,'sample4','general'),
  (7,'user','general');
