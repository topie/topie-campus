DROP TABLE IF EXISTS `t_stu_cet`;
CREATE TABLE `t_stu_cet` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `study_year`     VARCHAR(255)     DEFAULT NULL,
  `study_year_num` VARCHAR(255)     DEFAULT NULL,
  `stu_id`         VARCHAR(255)     DEFAULT NULL,
  `cet_name`       VARCHAR(255)     DEFAULT NULL,
  `cet_time`       VARCHAR(255)     DEFAULT NULL,
  `cet_score`      VARCHAR(255)     DEFAULT NULL,
  `comment`        VARCHAR(255)     DEFAULT NULL,
  `listen_score`   VARCHAR(255)     DEFAULT NULL,
  `read_score`     VARCHAR(255)     DEFAULT NULL,
  `write_score`    VARCHAR(255)     DEFAULT NULL,
  `compre_score`   VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 64
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_stu_cet
-- ----------------------------
INSERT INTO `t_stu_cet`
VALUES ('43', '2014-2015', '2', 'student', '英语四级', '2008-06-21', '409', NULL, '143', '58', '173', '35');
INSERT INTO `t_stu_cet`
VALUES ('44', '2007-2008', '2', '200609030090', '英语四级', '2008-06-21', '391', NULL, '148', '84', '121', '38');
INSERT INTO `t_stu_cet`
VALUES ('45', '2007-2008', '2', '200609030095', '英语四级', '2008-06-21', '336', NULL, '119', '58', '128', '31');
INSERT INTO `t_stu_cet`
VALUES ('46', '2007-2008', '2', '2009040400012', '英语四级', '2008-06-21', '483', '生物化学工程学院', '184', '82', '175', '42');
INSERT INTO `t_stu_cet`
VALUES ('47', '2007-2008', '2', '2009040400068', '英语四级', '2008-06-21', '430', '北京青年政治学院', '150', '77', '161', '42');
INSERT INTO `t_stu_cet`
VALUES ('48', '2007-2008', '2', '2009040400092', '英语四级', '2008-06-21', '480', '师范学院', '184', '93', '157', '46');
INSERT INTO `t_stu_cet`
VALUES ('49', '2007-2008', '2', '2009040400135', '英语四级', '2008-06-21', '462', '师范学院', '169', '79', '166', '48');
INSERT INTO `t_stu_cet`
VALUES ('50', '2007-2008', '2', '2009040400150', '英语四级', '2008-06-21', '433', '北京青年政治学院', '169', '91', '123', '50');
INSERT INTO `t_stu_cet`
VALUES ('51', '2007-2008', '2', '2009040400174', '英语四级', '2008-06-21', '487', '北京青年政治学院', '177', '75', '191', '44');
INSERT INTO `t_stu_cet`
VALUES ('52', '2007-2008', '2', '2009040400175', '英语四级', '2008-06-21', '487', '北京青年政治学院', '177', '75', '191', '44');
INSERT INTO `t_stu_cet`
VALUES ('53', '2007-2008', '2', '2009040400176', '英语四级', '2008-06-21', '449', '北京青年政治学院', '138', '100', '161', '50');
INSERT INTO `t_stu_cet`
VALUES ('54', '2007-2008', '2', '2009040400180', '英语四级', '2008-06-21', '487', '北京青年政治学院', '145', '100', '200', '42');
INSERT INTO `t_stu_cet`
VALUES ('55', '2007-2008', '2', '2009040400182', '英语四级', '2008-06-21', '432', '北京青年政治学院', '143', '82', '161', '46');
INSERT INTO `t_stu_cet`
VALUES ('56', '2007-2008', '2', '2009040400188', '英语四级', '2008-06-21', '443', '北京青年政治学院', '162', '91', '157', '33');
INSERT INTO `t_stu_cet`
VALUES ('57', '2007-2008', '2', '2009040400198', '英语四级', '2008-06-21', '386', '北京青年政治学院', '134', '42', '150', '60');
INSERT INTO `t_stu_cet`
VALUES ('58', '2007-2008', '2', '2009040400199', '英语四级', '2008-06-21', '425', '北京青年政治学院', '162', '84', '141', '38');
INSERT INTO `t_stu_cet`
VALUES ('59', '2007-2008', '2', '2009040400203', '英语四级', '2008-06-21', '456', '北京青年政治学院', '124', '89', '193', '50');
INSERT INTO `t_stu_cet`
VALUES ('60', '2007-2008', '2', '2009040400208', '英语四级', '2008-06-21', '432', '北京青年政治学院', '136', '82', '170', '44');
INSERT INTO `t_stu_cet`
VALUES ('61', '2007-2008', '2', '2009040400233', '英语四级', '2008-06-21', '458', NULL, '150', '93', '175', '40');
INSERT INTO `t_stu_cet`
VALUES ('62', '2009-2010', '1', '2008110309117', '英语四级', '2009-12-19', '494', NULL, '188', '100', '150', '56');
#INSERT INTO `t_stu_cet` VALUES ('63', '学年', '学期', '学号', '级别', '考试日期', '总成绩', '备注', '听力', '写作', '阅读', '综合');

-- ----------------------------
-- Table structure for t_stu_sele_course
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_sele_course`;
CREATE TABLE `t_stu_sele_course` (
  `id`                INT(11) NOT NULL      AUTO_INCREMENT,
  `study_year`        VARCHAR(255)          DEFAULT NULL,
  `study_year_num`    VARCHAR(11)           DEFAULT NULL,
  `select_course_num` VARCHAR(255)          DEFAULT NULL
  COMMENT '选课课号',
  `stu_id`            VARCHAR(255)          DEFAULT NULL
  COMMENT '学号',
  `name`              VARCHAR(255)          DEFAULT NULL
  COMMENT '姓名',
  `course_name`       VARCHAR(255)          DEFAULT NULL,
  `major`             VARCHAR(255)          DEFAULT NULL
  COMMENT '专业',
  `is_select`         VARCHAR(255)          DEFAULT NULL
  COMMENT '是否选上',
  `select_times`      VARCHAR(255)          DEFAULT NULL
  COMMENT '选课轮数',
  `exam_time`         VARCHAR(255)          DEFAULT NULL
  COMMENT '考试时间',
  `comment`           VARCHAR(255)          DEFAULT NULL
  COMMENT '备注',
  `restudy_flag`      VARCHAR(255)          DEFAULT NULL
  COMMENT '重修标记',
  `classroom_num`     VARCHAR(100)          DEFAULT NULL
  COMMENT '教室号',
  `classroom_name`    VARCHAR(255)          DEFAULT NULL
  COMMENT '教室名称',
  `couse_study_time`  VARCHAR(50)           DEFAULT NULL
  COMMENT '上课时间',
  `select_time`       VARCHAR(255)          DEFAULT NULL
  COMMENT '选课时间',
  `grade`             VARCHAR(255)
                      CHARACTER SET utf8mb4 DEFAULT NULL
  COMMENT '年级',
  `is_minor`          VARCHAR(255)          DEFAULT NULL
  COMMENT '辅修标记',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_stu_sele_course
-- ----------------------------
INSERT INTO `t_stu_sele_course` VALUES
  ('1', '2014-2015', '1', '(2014-2015-1)-BPT030801L-20027012-1', 'student', '梁赫', NULL, '药物制剂技术', '1', NULL, NULL, NULL,
   NULL, NULL, NULL, '2014-09-09 09:41:22', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('2', '2014-2015', '1', '(2014-2015-1)-BPT030801L-20027012-1', '2012041230032', '闫旭', NULL, '药物制剂技术', '1', NULL, NULL,
   NULL, NULL, NULL, NULL, '2014-09-09 09:41:22', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('3', '2014-2015', '1', '(2014-2015-1)-BPT030801L-20027012-1', '2012041230065', '葛岚昀', NULL, '药物制剂技术', '1', NULL,
        NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:22', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('4', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230002', '戴乔', NULL, '药物制剂技术', '1', NULL, NULL,
   NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('5', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230003', '房亚洲', NULL, '药物制剂技术', '1', NULL,
        NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('6', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230004', '张帆', NULL, '药物制剂技术', '1', NULL, NULL,
   NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('7', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230005', '孙馨蕊', NULL, '药物制剂技术', '1', NULL,
        NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('8', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230006', '李彤', NULL, '药物制剂技术', '1', NULL, NULL,
   NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('9', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230007', '双宝莹', NULL, '药物制剂技术', '1', NULL,
        NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('10', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230008', '毕秋悦', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('11', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230009', '王贯元', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('12', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230010', '李娜', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('13', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230011', '王蕊', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('14', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230012', '李慧', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('15', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230013', '赵启琛', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('16', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230014', '李霖雨', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('17', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230016', '穆雨', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('18', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230017', '于雅迪', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('19', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230018', '马祯', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
INSERT INTO `t_stu_sele_course` VALUES
  ('20', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230019', '王浩全', NULL, '药物制剂技术', '1', NULL,
         NULL, NULL, NULL, NULL, NULL, '2014-09-09 09:41:23', '1', '2012', NULL);
#INSERT INTO `t_stu_sele_course` VALUES ('21', '学年', '学期', '选课课号', '学号', '姓名', null, '专业名称', '是否选上', '选课轮数', '考试时间备注', null, null, '教室名称', null, '选课时间', null, '年级', '辅修标记\n1辅修，0或null为不付修');

-- ----------------------------
-- Table structure for t_stu_timetable
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_timetable`;
CREATE TABLE `t_stu_timetable` (
  `id`                INT(11) NOT NULL AUTO_INCREMENT,
  `study_year`        VARCHAR(255)     DEFAULT ''
  COMMENT '学年',
  `study_year_num`    VARCHAR(11)      DEFAULT ''
  COMMENT '学期',
  `stu_id`            VARCHAR(255)     DEFAULT ''
  COMMENT '学号',
  `select_course_num` VARCHAR(255)     DEFAULT ''
  COMMENT '选课课号',
  `week`              SMALLINT(5)      DEFAULT 0
  COMMENT '星期几',
  `section`           SMALLINT(5)      DEFAULT 0
  COMMENT '第几节',
  `section_length`    SMALLINT(5)      DEFAULT 1
  COMMENT '长度',
  `start_week`        INT(11)          DEFAULT 0,
  `end_week`          INT(11)          DEFAULT 0
  COMMENT '结束周',
  `content`           VARCHAR(2000)    DEFAULT NULL
  COMMENT '课表内容',
  `is_selected`       VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY k_stu(`stu_id`, `study_year`, `study_year_num`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `t_stu_timetable` VALUES
  ('1', '2014-2015', '1', 'student', '(2014-2015-1)-ENG010202G-19890046-1', '2', '10', '2', '1', '16',
        '公共关系概论<br>{第1-16周|2节/周}<br>殷智红<br>生教-209', '1');
INSERT INTO `t_stu_timetable` VALUES
  ('2', '2014-2015', '1', 'student', '(2014-2015-1)-HUA121247T-20087103-1', '2', '6', '2', '6', '6',
        '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES
  ('3', '2014-2015', '1', 'student', '(2014-2015-1)-HUA121247T-20087103-1', '4', '11', '3', '3', '13',
        '服饰搭配艺术<br>{第3-13周|3节/周}<br>王璐<br>生教-阶二', '1');
INSERT INTO `t_stu_timetable` VALUES
  ('4', '2014-2015', '1', 'student', '(2014-2015-1)-HUA121247T-20087103-1', '4', '2', '3', '3', '13',
        '服饰搭配艺术<br>{第3-13周|3节/周}<br>王璐<br>生教-阶二', '1');
INSERT INTO `t_stu_timetable` VALUES
  ('5', '2014-2015', '1', 'student', '(2014-2015-1)-HUA121247T-20087103-1', '5', '3', '2', '3', '13',
        '搭配艺术<br>{第3-13周|3节/周}<br>王璐<br>生教-阶二', '1');
INSERT INTO `t_stu_timetable` VALUES
  ('6', '2014-2015', '1', 'student', '(2014-2015-1)-HUA121247T-20087103-1', '5', '7', '2', '3', '13',
        '搭配艺术<br>{第3-13周|3节/周}<br>王璐<br>生教-阶二', '1');
#INSERT INTO `t_stu_timetable` VALUES ('id', '学年', '学期', '学号', '选课课号', '星期几', '第几节\n(13节)', '上课长度', '起始周', '结束周', '课程表', '选上否\n1自己选的6教务手工选择');
