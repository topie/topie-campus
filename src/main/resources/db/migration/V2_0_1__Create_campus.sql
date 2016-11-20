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
  `message_date_time`      TIMESTAMP NULL
  COMMENT '留言时间',
  `is_read`                TINYINT(1)          DEFAULT 0
  COMMENT '是否读取:0 未读取 1:已读取',
  `update_time`            TIMESTAMP           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (`message_id`),
  KEY k_from(`message_from_user_id`, `update_time`, `message_date_time`),
  KEY k_to(`message_to_user_id`, `update_time`, `message_date_time`),
  KEY k_update_time(`update_time`, `message_date_time`),
  KEY k_time(`message_date_time`)
)
  DEFAULT CHARSET = utf8
  COMMENT '留言信息表';
DROP TABLE IF EXISTS `t_message_reply`;
CREATE TABLE `t_message_reply` (
  `reply_id`        INT(11)   NOT NULL  AUTO_INCREMENT,
  `message_id`      INT(11)   NOT NULL
  COMMENT '留言id',
  `reply_content`   VARCHAR(255)        DEFAULT ''
  COMMENT '回复内容',
  `reply_user_id`   INT(11)   NOT NULL  DEFAULT 0
  COMMENT '回复用户id',
  `reply_user_name` VARCHAR(32)         DEFAULT ''
  COMMENT '回复用户名称',
  `reply_date_time` TIMESTAMP NULL
  COMMENT '留言时间',
  `is_read`         TINYINT(1)          DEFAULT 0
  COMMENT '是否读取:0 未读取 1:已读取',
  PRIMARY KEY (`reply_id`),
  KEY k_time(`message_id`, `reply_date_time`, `reply_user_id`)
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


DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `user_id` int(11) NOT NULL COMMENT '平台用户id',
  `employee_no` varchar(32) NOT NULL COMMENT '职工号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` varchar(1) NOT NULL DEFAULT '1' COMMENT '性别:0 女 1 男',
  `ethnic_group` smallint(2) DEFAULT '0' COMMENT '民族',
  `birth` varchar(20) DEFAULT NULL COMMENT '出生日期',
  `political_status` tinyint(1) DEFAULT '0' COMMENT '政治面貌',
  `academe` varchar(64) DEFAULT '' COMMENT '学院',
  `department` varchar(64) DEFAULT '' COMMENT '科室',
  `contact_phone` varchar(32) DEFAULT '' COMMENT '联系电话',
  `email` varchar(64) DEFAULT '' COMMENT '邮箱',
  `graduated_university` varchar(64) DEFAULT '' COMMENT '毕业院校',
  `graduated_major` varchar(64) DEFAULT '' COMMENT '毕业专业',
  `education_background` smallint(6) DEFAULT '0' COMMENT '学历',
  `degree` smallint(6) DEFAULT '0' COMMENT '学位',
  `professional_title` varchar(64) DEFAULT '' COMMENT '职称',
  `professional_position` varchar(64) DEFAULT '' COMMENT '职务',
  `subject_direction` varchar(64) DEFAULT '' COMMENT '学科方向',
  `teacher_certificate_no` varchar(32) DEFAULT '' COMMENT '教师资格证号',
  `main_teacher_certificate_no` varchar(32) DEFAULT '' COMMENT '主讲教师资格证号',
  `staff_no` varchar(32) DEFAULT '' COMMENT '人事职工号',
  `is_lab_staff` tinyint(1) DEFAULT '0' COMMENT '是否实验室人员 0：否 1：是',
  `short_introduce` varchar(2000) DEFAULT NULL COMMENT '简介',
  `has_teacher_certificate` tinyint(1) DEFAULT '0' COMMENT '是否教师资格证(1是，0无）',
  `teacher_type` varchar(64) DEFAULT '' COMMENT '教师类型',
  `teacher_lever` varchar(64) DEFAULT '' COMMENT '教师级别',
  `is_outside` tinyint(1) DEFAULT '0' COMMENT '是否外聘',
  `short_name` varchar(64) DEFAULT '' COMMENT '姓名简拼',
  `password` varchar(64) DEFAULT '' COMMENT '密码明文',
  `teach_major` varchar(64) DEFAULT '' COMMENT '所属专业（教那个系）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `employee_no` (`employee_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='老师信息表';  
  
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `user_id` int(11) NOT NULL COMMENT '平台用户id',
  `student_no` varchar(32) NOT NULL COMMENT '学号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` varchar(10) DEFAULT '1' COMMENT '性别:0 女 1 男',
  `birth` varchar(100) DEFAULT NULL COMMENT '出生日期',
  `political_status` varchar(100) DEFAULT '0' COMMENT '政治面貌',
  `ethnic_group` varchar(255) DEFAULT '0' COMMENT '民族',
  `src_region` varchar(255) DEFAULT NULL COMMENT '入取地区',
  `academe` varchar(32) DEFAULT '' COMMENT '学院',
  `subject` varchar(32) DEFAULT '' COMMENT '专业',
  `grade` varchar(255) DEFAULT NULL,
  `shool_len` varchar(255) DEFAULT NULL COMMENT '学制',
  `school_roll` varchar(255) DEFAULT '' COMMENT '学籍',
  `grade_year` varchar(255) DEFAULT '' COMMENT '年级',
  `major_direction` varchar(255) DEFAULT '' COMMENT '专业方向',
  `enter_date` varchar(255) DEFAULT '' COMMENT '入学日期',
  `email` varchar(64) DEFAULT '' COMMENT '邮箱',
  `comment` varchar(255) DEFAULT '' COMMENT '备注',
  `english_type` varchar(255) DEFAULT '' COMMENT '学生类别（英语A,英语B）',
  `full_name` varchar(255) DEFAULT '' COMMENT '全拼',
  `major_code` varchar(255) DEFAULT '' COMMENT '专业代码',
  `gradation` varchar(255) DEFAULT '' COMMENT '层次（本科，高职等）',
  `inschool` varchar(10) DEFAULT '' COMMENT '是否在校',
  `is_register` varchar(255) DEFAULT '' COMMENT '是否注册',
  `graduate_date` varchar(255) DEFAULT NULL COMMENT '毕业日期',
  `contact_phone` varchar(32) DEFAULT '' COMMENT '联系电话',
  `is_graduate` varchar(10) DEFAULT NULL COMMENT '是否毕业',
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2098 DEFAULT CHARSET=utf8 COMMENT='学生信息表';

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
DROP TABLE IF EXISTS `t_teacher_type`;
CREATE TABLE `t_teacher_type` (
  `type_id`   INT(11)   NOT NULL     AUTO_INCREMENT,
  `type_name` VARCHAR(32)            DEFAULT ''
  COMMENT '类型名称',
  `c_time`    TIMESTAMP NULL         DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `u_time`    TIMESTAMP NULL         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '创建时间',
  PRIMARY KEY (`type_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '老师类型';
DROP TABLE IF EXISTS `t_relate_teacher_teacher_type`;
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

