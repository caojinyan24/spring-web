CREATE DATABASE user;
CREATE TABLE user_info (
  `id`          INT(8) UNSIGNED NOT NULL  AUTO_INCREMENT PRIMARY KEY
  COMMENT 'id',
  `user_id`     INT(8) UNSIGNED NOT NULL  DEFAULT 0
  COMMENT 'userid',
  `user_name`   VARCHAR(16)     NOT NULL  DEFAULT ''
  COMMENT '用户名',
  `create_time` TIMESTAMP       NOT NULL  DEFAULT current_timestamp
  COMMENT '创建时间',
  `update_time` TIMESTAMP       NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY `idx_user_name`(`user_name`),
  UNIQUE KEY `idx_user_id`(`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户信息表';

CREATE TABLE `account` (
  `id`          INT(8) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `user_name`   VARCHAR(16)     NOT NULL
  COMMENT 'userid',
  `password`    VARCHAR(16)     NOT NULL
  COMMENT '用户名',
  `create_time` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `update_time` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '账户信息表';

# 暂时把会话信息保存在数据库，稍后更改为使用redis
CREATE TABLE `session` (
  `id`        INT(8) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `session`   VARCHAR(48)     NOT NULL DEFAULT ''
  COMMENT 'userid',
  `user_name` VARCHAR(16)     NOT NULL DEFAULT ''
  COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_session` (`session`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '会话信息表';