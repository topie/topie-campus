DROP TABLE IF EXISTS `t_survey_group`;
CREATE TABLE `t_survey_group` (
  `group_id`   INT(11)   NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(65) COMMENT '问卷组名称',
  `type_id`    INT(11)   NOT NULL
  COMMENT '老师类型id',
  `start`      TIMESTAMP NULL
  COMMENT '开始时间',
  `end`        TIMESTAMP NULL
  COMMENT '结束时间',
  `status`     SMALLINT(5)        DEFAULT 0
  COMMENT '状态 0:待审核 1：已发布 2：驳回',
  `is_online`  TINYINT(1)         DEFAULT 0
  COMMENT '是否在线 0:否 1:是',
  PRIMARY KEY (`group_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷组';

DROP TABLE IF EXISTS `t_survey_question`;
CREATE TABLE `t_survey_question` (
  `question_id`      INT(11)   NOT NULL     AUTO_INCREMENT,
  `question_content` VARCHAR(255)           DEFAULT ''
  COMMENT '调查问卷问题内容',
  `c_time`           TIMESTAMP NULL         DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `u_time`           TIMESTAMP NULL         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`question_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷题';

DROP TABLE IF EXISTS `t_survey_group_question`;
CREATE TABLE `t_survey_group_question` (
  `group_id`    INT(11) NOT NULL
  COMMENT '问卷组id',
  `question_id` INT(11) NOT NULL
  COMMENT '题目id',
  `sort`        INT(11) DEFAULT 0
  COMMENT '序号',
  PRIMARY KEY (`group_id`, `question_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷题关联表';

CREATE TABLE `t_survey_group_question_answer` (
  `group_id`    INT(11) NOT NULL DEFAULT 0
  COMMENT '问卷组id',
  `question_id` INT(11) NOT NULL DEFAULT 0
  COMMENT '问题id',
  `teacher_id`  INT(11) NOT NULL DEFAULT 0
  COMMENT '老师id',
  `student_id`  INT(11) NOT NULL DEFAULT 0
  COMMENT '学生id',
  `record`      INT(11)          DEFAULT 0
  COMMENT '分数',
  PRIMARY KEY (`group_id`, `question_id`, `teacher_id`, `student_id`),
  KEY k_teacher(teacher_id, group_id, question_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷答案';

CREATE TABLE `t_survey_group_student` (
  `group_id`    INT(11) NOT NULL
  COMMENT '问卷组id',
  `student_id`  INT(11) NOT NULL
  COMMENT '学生id',
  `is_complete` TINYINT DEFAULT 0
  COMMENT '是否完成',
  PRIMARY KEY (group_id, student_id),
  KEY k_student(student_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷学生情况';


INSERT INTO `sys_function` VALUES ('24', '6', '问卷组管理', '1', '1', NULL, '/api/info/surveyGroup/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '24');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '24');

INSERT INTO `sys_function`
VALUES ('25', '6', '问卷题管理', '1', '1', NULL, '/api/info/surveyQuestion/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '25');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '25');

INSERT INTO `sys_function`
VALUES ('26', '6', '问卷调查', '1', '2', NULL, '/api/front/studentSurvey/page', '9', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '26');

