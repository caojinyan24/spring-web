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

CREATE TABLE `account` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(16) NOT NULL COMMENT 'userid',
  `password` varchar(16) NOT NULL COMMENT '用户名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户信息表';

# 暂时把会话信息保存在数据库，稍后更改为使用redis
CREATE TABLE `session` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `session` varchar(48) NOT NULL DEFAULT '' COMMENT 'userid',
  `user_name` varchar(16) NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_session` (`session`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='会话信息表';