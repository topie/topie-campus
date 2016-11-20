DROP TABLE IF EXISTS `t_survey_group`;
CREATE TABLE `t_survey_group` (
  `group_id`      INT(11)   NOT NULL AUTO_INCREMENT,
  `group_name`    VARCHAR(65) COMMENT '问卷组名称',
  `group_type`    SMALLINT(1)        DEFAULT 1
  COMMENT '问卷组评分类型：1：评导师 2：评学生',
  `type_id`       INT(11)   NOT NULL
  COMMENT '老师类型id',
  `start`         TIMESTAMP NULL
  COMMENT '开始时间',
  `end`           TIMESTAMP NULL
  COMMENT '结束时间',
  `per_point`     SMALLINT(5)        DEFAULT 2
  COMMENT '每个评分题分数',
  `status`        SMALLINT(5)        DEFAULT 0
  COMMENT '审核状态 0:待审核 1：已发布 2：驳回',
  `online_status` TINYINT(1)         DEFAULT 0
  COMMENT '在线状体 0:未开始 1:进行中 2:已结束',
  PRIMARY KEY (`group_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷组';

DROP TABLE IF EXISTS `t_survey_question`;
CREATE TABLE `t_survey_question` (
  `question_id`      INT(11)     NOT NULL     AUTO_INCREMENT,
  `question_type`    SMALLINT(5) NOT NULL     DEFAULT 1
  COMMENT '问题类型 1：评分题 2：问答题',
  `question_content` VARCHAR(255)             DEFAULT ''
  COMMENT '调查问卷问题内容',
  `c_time`           TIMESTAMP   NULL         DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `u_time`           TIMESTAMP   NULL         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
  `group_id`      INT(11)      NOT NULL DEFAULT 0
  COMMENT '问卷组id',
  `group_type`    SMALLINT(5)  NOT NULL DEFAULT 0
  COMMENT '问卷组类型',
  `teacher_id`    INT(11)      NOT NULL DEFAULT 0
  COMMENT '老师id',
  `student_id`    INT(11)      NOT NULL DEFAULT 0
  COMMENT '学生id',
  `question_id`   INT(11)      NOT NULL DEFAULT 0
  COMMENT '问题id',
  `question_type` SMALLINT(11) NOT NULL DEFAULT 1
  COMMENT '问题类型 1：评分题 2：问答题',
  `record`        INT(11)               DEFAULT 0
  COMMENT '评分分数',
  `content`       VARCHAR(255)          DEFAULT ''
  COMMENT '回答内容',
  PRIMARY KEY (`group_id`, group_type, `question_id`, `teacher_id`, `student_id`),
  KEY k_teacher(group_type, teacher_id, group_id, question_id),
  KEY k_student(group_type, student_id, group_id, question_id)
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

CREATE TABLE `t_survey_group_teacher` (
  `group_id`    INT(11) NOT NULL
  COMMENT '问卷组id',
  `teacher_id`  INT(11) NOT NULL
  COMMENT '导师id',
  `is_complete` TINYINT DEFAULT 0
  COMMENT '是否完成',
  PRIMARY KEY (group_id, teacher_id),
  KEY k_teacher(teacher_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '调查问卷导师情况';


INSERT INTO `sys_function` VALUES ('24', '6', '问卷组管理', '1', '1', NULL, '/api/info/surveyGroup/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '24');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '24');

INSERT INTO `sys_function`
VALUES ('25', '6', '问卷题管理', '1', '1', NULL, '/api/info/surveyQuestion/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '25');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '25');

INSERT INTO `sys_function`
VALUES ('27', '0', '问卷调查', '1', '2', NULL, '/api/front/studentSurvey/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '27');


