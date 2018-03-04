-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE TaskOne;
-- 使用数据库
use TaskOne;
CREATE TABLE user(
  `user_id` VARCHAR(20) NOT NULL  COMMENT '用户名ID',
  `password` VARCHAR(300) NOT NULL COMMENT '密码',
  PRIMARY KEY (user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 初始化数据
INSERT into user(user_id, password)
VALUES
  ('admin',MD5('123')),
  ('123',MD5('123'));

CREATE TABLE h5url(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'urlID',
  `url` VARCHAR(50) NOT NULL UNIQUE COMMENT 'url',
  PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='h5url表';
