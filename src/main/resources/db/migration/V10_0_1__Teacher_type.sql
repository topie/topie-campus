CREATE TABLE `t_teacher_type` (
  `type_id`   INT(11)   NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(32)        DEFAULT ''
  COMMENT '类型名称',
  `c_time`    TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `u_time`    TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '创建时间',
  PRIMARY KEY (`type_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '老师类型';

CREATE TABLE `t_relate_teacher_teacher_type` (
  `teacher_id` INT(11) NOT NULL
  COMMENT 'teacher_id',
  `type_id`    INT(11) NOT NULL
  COMMENT '类型id',
  PRIMARY KEY (`teacher_id`, `type_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '老师和老师类型关联表';

DROP TABLE IF EXISTS `t_relate_student_teacher`;
CREATE TABLE `t_relate_student_teacher` (
  `type_id`     INT(11)     NOT NULL DEFAULT 0
  COMMENT '老师类型id',
  `student_id`  INT(11)     NOT NULL DEFAULT 0
  COMMENT '学生id',
  `teacher_id`  INT(11)     NOT NULL DEFAULT 0
  COMMENT '老师id',
  `student_no`  VARCHAR(64) NOT NULL DEFAULT ''
  COMMENT '学号',
  `employee_no` VARCHAR(64) NOT NULL DEFAULT ''
  COMMENT '职工号',
  PRIMARY KEY (`type_id`, `teacher_id`, `student_id`),
  KEY (`student_id`, `type_id`),
  KEY (`student_no`),
  KEY (`employee_no`)
)
  DEFAULT CHARSET = utf8
  COMMENT '学生老师关联表';
