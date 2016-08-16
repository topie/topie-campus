DROP TABLE IF EXISTS `t_info_basic`;
CREATE TABLE `t_info_basic` (
  `user_id`      INT(11)     NOT NULL DEFAULT 0
  COMMENT '用户id',
  `user_name`    VARCHAR(64)          DEFAULT ''
  COMMENT '用户名称',
  `user_type`    TINYINT(1)  NOT NULL DEFAULT 0
  COMMENT '用户类型 0 学生 1 老师 2 领导 3 其他',
  `user_code`    VARCHAR(32) NOT NULL
  COMMENT '用户唯一编码',
  `mobile_phone` VARCHAR(32)          DEFAULT ''
  COMMENT '手机',
  `gender`       TINYINT(1)           DEFAULT 1
  COMMENT '性别:0 女 1 男',
  `birth`        DATE
  COMMENT '出生日期',
  `intro`        VARCHAR(255)         DEFAULT ''
  COMMENT '个人简介',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY (`user_code`)
)
  DEFAULT CHARSET = utf8
  COMMENT '基本信息';
DROP TABLE IF EXISTS `t_common_course`;
CREATE TABLE `t_common_course` (
  `id`                INT(11)     NOT NULL AUTO_INCREMENT,
  `course_id`         INT(11)     NOT NULL DEFAULT 0
  COMMENT '课程id',
  `course_name`       VARCHAR(32) NOT NULL DEFAULT ''
  COMMENT '课程名称',
  `course_date`       DATE        NOT NULL
  COMMENT '课程日期',
  `course_begin_time` TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
  COMMENT '课程开始时间',
  `course_end_time`   TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
  COMMENT '课程结束时间',
  PRIMARY KEY (`id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '课程表';
DROP TABLE IF EXISTS `t_common_message`;
CREATE TABLE `t_common_message` (
  `message_id`           INT(11)   NOT NULL  AUTO_INCREMENT,
  `refer_message_id`     INT(11)             DEFAULT 0
  COMMENT '引用留言id',
  `message_content`      VARCHAR(255)        DEFAULT ''
  COMMENT '留言内容',
  `message_to_user_id`   INT(11)   NOT NULL  DEFAULT 0
  COMMENT '接收留言用户id',
  `message_from_user_id` INT(11)   NOT NULL  DEFAULT 0
  COMMENT '留言用户id',
  `message_date_time`    TIMESTAMP NOT NULL
  COMMENT '留言时间',
  `is_read`              TINYINT(1)          DEFAULT 0
  COMMENT '是否读取:0 未读取 1:已读取',
  PRIMARY KEY (`message_id`),
  KEY k_user(`message_to_user_id`, `message_from_user_id`, `message_date_time`),
  KEY k_read(`message_to_user_id`, `message_date_time`),
  KEY k_time(`message_date_time`)
)
  DEFAULT CHARSET = utf8
  COMMENT '留言信息表';
DROP TABLE IF EXISTS `t_common_notice`;
CREATE TABLE `t_common_notice` (
  `notice_id`           INT(11)   NOT NULL AUTO_INCREMENT
  COMMENT '通知公告id',
  `notice_title`        VARCHAR(128)       DEFAULT ''
  COMMENT '通知公告标题',
  `notice_publish_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '通知公告发布时间',
  `notice_content`      LONGTEXT
  COMMENT '通知公告内容',
  PRIMARY KEY (`notice_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '通知公告';
DROP TABLE IF EXISTS `t_common_attachment`;
CREATE TABLE `t_common_attachment` (
  `attachment_id`   INT(11) NOT NULL DEFAULT 0
  COMMENT '附件id',
  `attachment_name` VARCHAR(128)     DEFAULT ''
  COMMENT '附件名称',
  `attachment_type` TINYINT(1)       DEFAULT 0
  COMMENT '附件类型:0:图片 1:文档 2:其他',
  `attachment_path` VARCHAR(255)     DEFAULT ''
  COMMENT '附件文件路径',
  PRIMARY KEY (`attachment_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '附件表';
DROP TABLE IF EXISTS `t_relate_course`;
CREATE TABLE `t_relate_course` (
  `user_id`   INT(11) NOT NULL DEFAULT 0
  COMMENT '用户id',
  `course_id` INT(11) NOT NULL DEFAULT 0
  COMMENT '课程表id',
  PRIMARY KEY (`user_id`, `course_id`),
  KEY (`course_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '用户课程关联表';
DROP TABLE IF EXISTS `t_relate_notice_attachment`;
CREATE TABLE `t_relate_notice_attachment` (
  `notice_id`     INT(11) NOT NULL DEFAULT 0
  COMMENT '通知公告id',
  `attachment_id` INT(11) NOT NULL DEFAULT 0
  COMMENT '附件id',
  PRIMARY KEY (`notice_id`, `attachment_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '通知公告附件关联表';
DROP TABLE IF EXISTS `t_relate_message_attachment`;
CREATE TABLE `t_relate_message_attachment` (
  `message_id`    INT(11) NOT NULL DEFAULT 0
  COMMENT '通知公告id',
  `attachment_id` INT(11) NOT NULL DEFAULT 0
  COMMENT '附件id',
  PRIMARY KEY (`message_id`, `attachment_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '留言附件关联表';
DROP TABLE IF EXISTS `t_relate_student_teacher`;
CREATE TABLE `t_relate_student_teacher` (
  `student_id` INT(11) NOT NULL DEFAULT 0
  COMMENT '学生id',
  `teacher_id` INT(11) NOT NULL DEFAULT 0
  COMMENT '老师id',
  PRIMARY KEY (`student_id`, `teacher_id`),
  KEY (`teacher_id`)

)
  DEFAULT CHARSET = utf8
  COMMENT '学生老师关联表';
