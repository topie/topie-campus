DROP TABLE IF EXISTS `t_info_basic`;
CREATE TABLE `t_info_basic` (
  `user_id`   INT(11)    NOT NULL DEFAULT 0
  COMMENT '用户id',
  `user_type` TINYINT(1) NOT NULL DEFAULT 0
  COMMENT '用户类型 0 学生 1 老师 2 领导 3 其他',
  PRIMARY KEY (`user_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '基本信息';
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `message_id`             INT(11)   NOT NULL  AUTO_INCREMENT,
  `message_content`        VARCHAR(255)        DEFAULT ''
  COMMENT '留言内容',
  `message_to_user_id`     INT(11)   NOT NULL  DEFAULT 0
  COMMENT '接收留言用户id',
  `message_to_user_name`   VARCHAR(64)         DEFAULT ''
  COMMENT '接收留言用户',
  `message_from_user_id`   INT(11)   NOT NULL  DEFAULT 0
  COMMENT '留言用户id',
  `message_from_user_name` VARCHAR(64)         DEFAULT ''
  COMMENT '留言用户',
  `message_date_time`      TIMESTAMP NOT NULL
  COMMENT '留言时间',
  `is_read`                TINYINT(1)          DEFAULT 0
  COMMENT '是否读取:0 未读取 1:已读取',
  PRIMARY KEY (`message_id`),
  KEY k_user(`message_to_user_id`, `message_from_user_id`, `message_date_time`),
  KEY k_read(`message_to_user_id`, `message_date_time`),
  KEY k_time(`message_date_time`)
)
  DEFAULT CHARSET = utf8
  COMMENT '留言信息表';
DROP TABLE IF EXISTS `t_message_reply`;
CREATE TABLE `t_message_reply` (
  `reply_id`        INT(11)    NOT NULL  AUTO_INCREMENT,
  `message_id`      INT(11)    NOT NULL
  COMMENT '留言id',
  `reply_comment`   VARCHAR(255)         DEFAULT ''
  COMMENT '回复内容',
  `reply_user_id`   INT(11)    NOT NULL  DEFAULT 0
  COMMENT '回复用户id',
  `position`        TINYINT(1) NOT NULL  DEFAULT 0
  COMMENT '被回复用户id',
  `reply_date_time` TIMESTAMP  NOT NULL
  COMMENT '留言时间',
  `is_read`         TINYINT(1)           DEFAULT 0
  COMMENT '是否读取:0 未读取 1:已读取',
  PRIMARY KEY (`reply_id`),
  KEY k_time(`message_id`, `reply_date_time`)
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
  `attachment_id`     INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '附件id',
  `attachment_name`   VARCHAR(128)     DEFAULT ''
  COMMENT '附件名称',
  `attachment_type`   TINYINT(1)       DEFAULT 0
  COMMENT '附件类型:0:图片 1:文档 2:其他',
  `attachment_suffix` VARCHAR(32)      DEFAULT ''
  COMMENT '附件后缀',
  `attachment_path`   VARCHAR(255)     DEFAULT ''
  COMMENT '附件文件路径',
  `attachment_url`    VARCHAR(255)     DEFAULT ''
  COMMENT '附件文件路径',
  `attachment_size`   BIGINT(22)       DEFAULT 0
  COMMENT '附件文件大小',
  `upload_login_name` VARCHAR(64)      DEFAULT ''
  COMMENT '上传附件用户',
  PRIMARY KEY (`attachment_id`),
  KEY k_user(`upload_login_name`)
)
  DEFAULT CHARSET = utf8
  COMMENT '附件表';
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
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id`                          INT(11)     NOT NULL          AUTO_INCREMENT
  COMMENT 'ID',
  `avatar`                      VARCHAR(255)                  DEFAULT ''
  COMMENT '头像',
  `user_id`                     INT(11)     NOT NULL
  COMMENT '平台用户id',
  `employee_no`                 VARCHAR(32) NOT NULL
  COMMENT '职工号',
  `name`                        VARCHAR(64) NOT NULL          DEFAULT ''
  COMMENT '姓名',
  `gender`                      TINYINT(1)  NOT NULL          DEFAULT 1
  COMMENT '性别:0 女 1 男',
  `ethnic_group`                SMALLINT(2)                   DEFAULT 0
  COMMENT '民族',
  `birth`                       DATE
  COMMENT '出生日期',
  `political_status`            TINYINT(1)                    DEFAULT 0
  COMMENT '政治面貌',
  `academe`                     VARCHAR(64)                   DEFAULT ''
  COMMENT '学院',
  `department`                  VARCHAR(64)                   DEFAULT ''
  COMMENT '系(所)',
  `contact_phone`               VARCHAR(32)                   DEFAULT ''
  COMMENT '联系电话',
  `email`                       VARCHAR(64)                   DEFAULT ''
  COMMENT '邮箱',
  `graduated_university`        VARCHAR(64)                   DEFAULT ''
  COMMENT '毕业院校',
  `graduated_major`             VARCHAR(64)                   DEFAULT ''
  COMMENT '毕业专业',
  `education_background`        SMALLINT                      DEFAULT 0
  COMMENT '学历',
  `degree`                      SMALLINT                      DEFAULT 0
  COMMENT '学位',
  `professional_title`          VARCHAR(64)                   DEFAULT ''
  COMMENT '职称',
  `professional_position`       VARCHAR(64)                   DEFAULT ''
  COMMENT '职务',
  `subject_direction`           VARCHAR(64)                   DEFAULT ''
  COMMENT '学科方向',
  `teacher_certificate_no`      VARCHAR(32)                   DEFAULT ''
  COMMENT '教师资格证号',
  `main_teacher_certificate_no` VARCHAR(32)                   DEFAULT ''
  COMMENT '主讲教师资格证号',
  `staff_no`                    VARCHAR(32)                   DEFAULT ''
  COMMENT '人事职工号',
  `is_lab_staff`                TINYINT(1)                    DEFAULT 0
  COMMENT '是否实验室人员 0：否 1：是',
  `teach_quality_comment`       TEXT COMMENT '教学质量评价',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`user_id`),
  UNIQUE KEY (`employee_no`)
)
  DEFAULT CHARSET = utf8
  COMMENT '老师信息表';
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id`               INT(11)     NOT NULL          AUTO_INCREMENT
  COMMENT 'ID',
  `avatar`           VARCHAR(255)                  DEFAULT ''
  COMMENT '头像',
  `user_id`          INT(11)     NOT NULL
  COMMENT '平台用户id',
  `student_no`       VARCHAR(32) NOT NULL
  COMMENT '学号',
  `name`             VARCHAR(64) NOT NULL          DEFAULT ''
  COMMENT '姓名',
  `gender`           TINYINT(1)  NOT NULL          DEFAULT 1
  COMMENT '性别:0 女 1 男',
  `ethnic_group`     SMALLINT(2)                   DEFAULT 0
  COMMENT '民族',
  `birth`            DATE
  COMMENT '出生日期',
  `political_status` TINYINT(1)                    DEFAULT 0
  COMMENT '政治面貌',
  `academe`          VARCHAR(32)                   DEFAULT ''
  COMMENT '学院',
  `subject`          VARCHAR(32)                   DEFAULT ''
  COMMENT '专业',
  `contact_phone`    VARCHAR(32)                   DEFAULT ''
  COMMENT '联系电话',
  `email`            VARCHAR(64)                   DEFAULT ''
  COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`user_id`)
)
  DEFAULT CHARSET = utf8
  COMMENT '学生信息表';
