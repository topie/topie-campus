DROP TABLE IF EXISTS `t_employment`;
CREATE TABLE `t_employment` (
  `id`                   INT(50) NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  `stu_id`               VARCHAR(50)      DEFAULT ''
  COMMENT '学号',
  `name`                 VARCHAR(100)     DEFAULT NULL
  COMMENT '姓名',
  `gender`               VARCHAR(20)      DEFAULT NULL
  COMMENT '性别',
  `phone`                VARCHAR(255)     DEFAULT ''
  COMMENT '电话',
  `education`            VARCHAR(255)     DEFAULT ''
  COMMENT '学历',
  `college`              VARCHAR(255)     DEFAULT ''
  COMMENT '学院',
  `major`                VARCHAR(255)     DEFAULT ''
  COMMENT '专业',
  `class_num`            VARCHAR(255)     DEFAULT NULL
  COMMENT '班级号',
  `enter_date`           VARCHAR(255)     DEFAULT ''
  COMMENT '入学日期',
  `graduate_date`        VARCHAR(255)     DEFAULT ''
  COMMENT '毕业日期',
  `identify_id`          VARCHAR(255)     DEFAULT ''
  COMMENT '身份证号',
  `home_address`         VARCHAR(255)     DEFAULT NULL,
  `take_table`           VARCHAR(255)     DEFAULT NULL
  COMMENT '是否领表0,未领取，1领取',
  `employment_status`    VARCHAR(255)     DEFAULT NULL
  COMMENT '就业状态，证明，自谋',
  `sign_status`          VARCHAR(255)     DEFAULT NULL
  COMMENT '签约状态：参军、出国、合同、三方、升本、升硕、村官、社区、西部',
  `poor_student`         VARCHAR(255)     DEFAULT NULL
  COMMENT '困难生',
  `employ_pattern`       VARCHAR(255)     DEFAULT ''
  COMMENT '就业模式：证明、自谋',
  `sign_pattern`         VARCHAR(255)     DEFAULT ''
  COMMENT '签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部',
  `sign_company`         VARCHAR(255)     DEFAULT ''
  COMMENT '签约公司',
  `tutor`                VARCHAR(255)     DEFAULT NULL
  COMMENT '导师',
  `comment`              VARCHAR(500)     DEFAULT NULL,
  `sign_company_address` VARCHAR(255)     DEFAULT ''
  COMMENT '签约公司地址',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `sys_user` VALUES
  ('6', 'job', '$2a$10$qNEIDINkaQVdxWZ534pT.u60Aq8MVuMXp25AUkjsxKPiuIsOx8H2y', '就业管理员', '1', '1',
        '1', '1', NULL, NULL, 'job@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `sys_role` VALUES ('6', '就业管理员', '/', '1', NULL, NULL);
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`)
VALUES ('17', '6', '就业统计', '1', '1', '', '/api/job/staticEmploy', '0', '2016-10-03 09:22:37', '2016-10-03 09:22:37');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`)
VALUES ('18', '6', '就业管理', '1', '1', '', '/api/job/page', '0', '2016-10-03 09:39:35', '2016-10-03 09:40:20');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('17', '6');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('18', '6');
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES ('6', '6');
