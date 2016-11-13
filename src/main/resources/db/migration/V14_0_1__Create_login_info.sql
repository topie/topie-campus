DROP TABLE IF EXISTS `t_login_info`;
CREATE TABLE `t_login_info` (
  `id`            INT(11)   NOT NULL AUTO_INCREMENT,
  `login_user_id` INT(11)   NOT NULL DEFAULT 0
  COMMENT '登录用户id',
  `login_name`    VARCHAR(64)        DEFAULT ''
  COMMENT '登录用户登录名',
  `login_ip`      VARCHAR(32)        DEFAULT ''
  COMMENT '登录ip',
  `login_time`    TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '登录时间',
  PRIMARY KEY (`id`),
  KEY k_user_time(`login_user_id`, `login_time`),
  KEY k_time(`login_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户登录信息';

INSERT INTO `sys_function` VALUES ('37', '6', '登录管理', '1', '1', NULL, '/api/info/loginInfo/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '37');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '37');
