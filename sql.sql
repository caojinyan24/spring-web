create database user;
create table user_info (
  `id`  int(8) unsigned not null  auto_increment primary key comment 'id',
  `user_id` int(8) unsigned not null default 0 comment 'userid',
  `user_name` varchar(16) not null default '' comment '用户名',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  key `idx_user_name`(`user_name`),
  UNIQUE key `idx_user_id`(`user_id`)
)engine=InnoDB default charset=utf8 comment '用户信息表';