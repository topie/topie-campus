DROP TABLE IF EXISTS `t_stu_cet`;
CREATE TABLE `t_stu_cet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `study_year` varchar(255) DEFAULT NULL,
  `study_year_num` varchar(255) DEFAULT NULL,
  `stu_id` varchar(255) DEFAULT NULL,
  `cet_name` varchar(255) DEFAULT NULL,
  `cet_time` varchar(255) DEFAULT NULL,
  `cet_score` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `listen_score` varchar(255) DEFAULT NULL,
  `read_score` varchar(255) DEFAULT NULL,
  `write_score` varchar(255) DEFAULT NULL,
  `compre_score` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stu_cet
-- ----------------------------
INSERT INTO `t_stu_cet` VALUES ('43', '2014-2015', '2', 'student', '英语四级', '2008-06-21', '409', null, '143', '58', '173', '35');
INSERT INTO `t_stu_cet` VALUES ('44', '2007-2008', '2', '200609030090', '英语四级', '2008-06-21', '391', null, '148', '84', '121', '38');
INSERT INTO `t_stu_cet` VALUES ('45', '2007-2008', '2', '200609030095', '英语四级', '2008-06-21', '336', null, '119', '58', '128', '31');
INSERT INTO `t_stu_cet` VALUES ('46', '2007-2008', '2', '2009040400012', '英语四级', '2008-06-21', '483', '生物化学工程学院', '184', '82', '175', '42');
INSERT INTO `t_stu_cet` VALUES ('47', '2007-2008', '2', '2009040400068', '英语四级', '2008-06-21', '430', '北京青年政治学院', '150', '77', '161', '42');
INSERT INTO `t_stu_cet` VALUES ('48', '2007-2008', '2', '2009040400092', '英语四级', '2008-06-21', '480', '师范学院', '184', '93', '157', '46');
INSERT INTO `t_stu_cet` VALUES ('49', '2007-2008', '2', '2009040400135', '英语四级', '2008-06-21', '462', '师范学院', '169', '79', '166', '48');
INSERT INTO `t_stu_cet` VALUES ('50', '2007-2008', '2', '2009040400150', '英语四级', '2008-06-21', '433', '北京青年政治学院', '169', '91', '123', '50');
INSERT INTO `t_stu_cet` VALUES ('51', '2007-2008', '2', '2009040400174', '英语四级', '2008-06-21', '487', '北京青年政治学院', '177', '75', '191', '44');
INSERT INTO `t_stu_cet` VALUES ('52', '2007-2008', '2', '2009040400175', '英语四级', '2008-06-21', '487', '北京青年政治学院', '177', '75', '191', '44');
INSERT INTO `t_stu_cet` VALUES ('53', '2007-2008', '2', '2009040400176', '英语四级', '2008-06-21', '449', '北京青年政治学院', '138', '100', '161', '50');
INSERT INTO `t_stu_cet` VALUES ('54', '2007-2008', '2', '2009040400180', '英语四级', '2008-06-21', '487', '北京青年政治学院', '145', '100', '200', '42');
INSERT INTO `t_stu_cet` VALUES ('55', '2007-2008', '2', '2009040400182', '英语四级', '2008-06-21', '432', '北京青年政治学院', '143', '82', '161', '46');
INSERT INTO `t_stu_cet` VALUES ('56', '2007-2008', '2', '2009040400188', '英语四级', '2008-06-21', '443', '北京青年政治学院', '162', '91', '157', '33');
INSERT INTO `t_stu_cet` VALUES ('57', '2007-2008', '2', '2009040400198', '英语四级', '2008-06-21', '386', '北京青年政治学院', '134', '42', '150', '60');
INSERT INTO `t_stu_cet` VALUES ('58', '2007-2008', '2', '2009040400199', '英语四级', '2008-06-21', '425', '北京青年政治学院', '162', '84', '141', '38');
INSERT INTO `t_stu_cet` VALUES ('59', '2007-2008', '2', '2009040400203', '英语四级', '2008-06-21', '456', '北京青年政治学院', '124', '89', '193', '50');
INSERT INTO `t_stu_cet` VALUES ('60', '2007-2008', '2', '2009040400208', '英语四级', '2008-06-21', '432', '北京青年政治学院', '136', '82', '170', '44');
INSERT INTO `t_stu_cet` VALUES ('61', '2007-2008', '2', '2009040400233', '英语四级', '2008-06-21', '458', null, '150', '93', '175', '40');
INSERT INTO `t_stu_cet` VALUES ('62', '2009-2010', '1', '2008110309117', '英语四级', '2009-12-19', '494', null, '188', '100', '150', '56');
INSERT INTO `t_stu_cet` VALUES ('63', '学年', '学期', '学号', '级别', '考试日期', '总成绩', '备注', '听力', '写作', '阅读', '综合');

-- ----------------------------
-- Table structure for t_stu_sele_course
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_sele_course`;
CREATE TABLE `t_stu_sele_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `study_year` varchar(255) DEFAULT NULL,
  `study_year_num` varchar(11) DEFAULT NULL,
  `select_course_num` varchar(255) DEFAULT NULL COMMENT '选课课号',
  `stu_id` varchar(255) DEFAULT NULL COMMENT '学号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `course_name` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `is_select` varchar(255) DEFAULT NULL COMMENT '是否选上',
  `select_times` varchar(255) DEFAULT NULL COMMENT '选课轮数',
  `exam_time` varchar(255) DEFAULT NULL COMMENT '考试时间',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `restudy_flag` varchar(255) DEFAULT NULL COMMENT '重修标记',
  `classroom_num` varchar(100) DEFAULT NULL COMMENT '教室号',
  `classroom_name` varchar(255) DEFAULT NULL COMMENT '教室名称',
  `couse_study_time` varchar(50) DEFAULT NULL COMMENT '上课时间',
  `select_time` varchar(255) DEFAULT NULL COMMENT '选课时间',
  `grade` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '年级',
  `is_minor` varchar(255) DEFAULT NULL COMMENT '辅修标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stu_sele_course
-- ----------------------------
INSERT INTO `t_stu_sele_course` VALUES ('1', '2014-2015', '1', '(2014-2015-1)-BPT030801L-20027012-1', 'student', '梁赫', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:22', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('2', '2014-2015', '1', '(2014-2015-1)-BPT030801L-20027012-1', '2012041230032', '闫旭', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:22', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('3', '2014-2015', '1', '(2014-2015-1)-BPT030801L-20027012-1', '2012041230065', '葛岚昀', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:22', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('4', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230002', '戴乔', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('5', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230003', '房亚洲', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('6', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230004', '张帆', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('7', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230005', '孙馨蕊', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('8', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230006', '李彤', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('9', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230007', '双宝莹', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('10', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230008', '毕秋悦', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('11', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230009', '王贯元', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('12', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230010', '李娜', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('13', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230011', '王蕊', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('14', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230012', '李慧', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('15', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230013', '赵启琛', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('16', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230014', '李霖雨', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('17', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230016', '穆雨', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('18', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230017', '于雅迪', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('19', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230018', '马祯', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('20', '2014-2015', '1', '(2014-2015-1)-BPT032a01E-19887002-1', '2012041230019', '王浩全', null, '药物制剂技术', '1', null, null, null, null, null, null, '2014-09-09 09:41:23', '1', '2012', null);
INSERT INTO `t_stu_sele_course` VALUES ('21', '学年', '学期', '选课课号', '学号', '姓名', null, '专业名称', '是否选上', '选课轮数', '考试时间备注', null, null, '教室名称', null, '选课时间', null, '年级', '辅修标记\n1辅修，0或null为不付修');

-- ----------------------------
-- Table structure for t_stu_timetable
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_timetable`;
CREATE TABLE `t_stu_timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `study_year` varchar(255) DEFAULT NULL COMMENT '学年',
  `study_year_num` varchar(11) DEFAULT NULL COMMENT '学期',
  `stu_id` varchar(255) DEFAULT NULL COMMENT '学号',
  `select_course_num` varchar(255) DEFAULT NULL COMMENT '选课课号',
  `week` varchar(255) DEFAULT NULL COMMENT '星期几',
  `section` varchar(255) DEFAULT NULL COMMENT '第几节',
  `section_length` varchar(20) DEFAULT NULL,
  `start_week` varchar(255) DEFAULT NULL,
  `end_week` varchar(255) DEFAULT NULL COMMENT '结束周',
  `content` varchar(2000) DEFAULT NULL COMMENT '课表内容',
  `is_selected` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stu_timetable
-- ----------------------------
INSERT INTO `t_stu_timetable` VALUES ('1', '2014-2015', '1', '2013040349067', '(2014-2015-1)-ENG010202G-19890046-1', '4', '10', '2', '1', '16', '公共关系概论<br>{第1-16周|2节/周}<br>殷智红<br>生教-209', '1');
INSERT INTO `t_stu_timetable` VALUES ('2', '2014-2015', '2', '2014040351004', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('3', '2014-2015', '1', '2013041230025', '(2014-2015-1)-HUA121247T-20087103-1', '2', '11', '3', '3', '13', '服饰搭配艺术<br>{第3-13周|3节/周}<br>王璐<br>生教-阶二', '1');
INSERT INTO `t_stu_timetable` VALUES ('4', '2014-2015', '1', '2013041230024', '(2014-2015-1)-HUA121247T-20087103-1', '2', '11', '3', '3', '13', '服饰搭配艺术<br>{第3-13周|3节/周}<br>王璐<br>生教-阶二', '1');
INSERT INTO `t_stu_timetable` VALUES ('5', '2014-2015', '1', '2013040351027', '(2014-2015-1)-NAS121337T-20067012-1', '2', '11', '3', '3', '13', '包装与健康<br>{第3-13周|3节/周}<br>曾凤彩<br>生教-阶四', '1');
INSERT INTO `t_stu_timetable` VALUES ('6', '2014-2015', '1', '2013040351046', '(2014-2015-1)-NAS121337T-20067012-1', '2', '11', '3', '3', '13', '包装与健康<br>{第3-13周|3节/周}<br>曾凤彩<br>生教-阶四', '1');
INSERT INTO `t_stu_timetable` VALUES ('7', '2014-2015', '1', '2013040351050', '(2014-2015-1)-HUA121250T-19997022-1', '4', '11', '3', '3', '13', '大学生礼仪修养<br>{第3-13周|3节/周}<br>申秋燕<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('8', '2014-2015', '2', '2014040351005', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('9', '2014-2015', '2', '2014040351006', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('10', '2014-2015', '2', '2014040351007', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('11', '2014-2015', '2', '2014040351008', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('12', '2014-2015', '2', '2014040351010', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('13', '2014-2015', '2', '2014040351011', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('14', '2014-2015', '2', '2014040351013', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('15', '2014-2015', '2', '2014040351015', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('16', '2014-2015', '2', '2014040351016', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('17', '2014-2015', '2', '2014040351017', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('18', '2014-2015', '2', '2014040351020', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('19', '2014-2015', '2', '2014040351021', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('20', '2014-2015', '2', '2014040346038', '(2014-2015-2)-MXI110005E-19917005-1', '4', '6', '2', '6', '6', '形势与政策<br>周四第6,7节{第6-6周}<br>郭亚莉<br>生教-阶三', '1');
INSERT INTO `t_stu_timetable` VALUES ('21', '学年', '学期', '学号', '选课课号', '星期几', '第几节\n(13节)', '上课长度', '起始周', '结束周', '课程表', '选上否\n1自己选的6教务手工选择');
