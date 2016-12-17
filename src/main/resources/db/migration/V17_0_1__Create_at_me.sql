DROP TABLE IF EXISTS `t_at_me`;
CREATE TABLE `t_at_me` (
  `id`             INT(11)   NOT NULL AUTO_INCREMENT,
  `from_user_id`   INT(11)   NOT NULL DEFAULT 0
  COMMENT '来源用户',
  `from_user_name` VARCHAR(64)        DEFAULT ''
  COMMENT '来源用户名',
  `to_user_id`     INT(11)   NOT NULL DEFAULT 0
  COMMENT '目标用户',
  `at_time`        TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '@时间',
  `content`        VARCHAR(255)       DEFAULT ''
  COMMENT '动态内容',
  `content_type`   SMALLINT(5)        DEFAULT 1
  COMMENT '动态类型 1：留言 2：回复',
  `content_id`     INT(11)            DEFAULT 0
  COMMENT '动态回复ID（留言ID或者回复ID）',
  PRIMARY KEY (`id`),
  KEY k_to_user_time(`to_user_id`, `at_time`),
  KEY k_at_time(`at_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '动态信息';

INSERT INTO `sys_function` VALUES ('43', '0', '动态中心', '1', '2', NULL, '/api/front/atMe/page', '4', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '43');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '43');
