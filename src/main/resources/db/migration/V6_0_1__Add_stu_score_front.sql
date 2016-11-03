DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `dict_id` int(32) NOT NULL AUTO_INCREMENT,
  `dict_desc` varchar(100) DEFAULT NULL,
  `dict_name` varchar(50) DEFAULT NULL,
  `dict_seq` bigint(20) DEFAULT NULL,
  `dict_status` bigint(20) DEFAULT NULL,
  `dict_type` varchar(10) DEFAULT NULL,
  `dict_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '学年', '学年', '1', '0', '学年', 'studyYear');
INSERT INTO `t_dict` VALUES ('2', '广告位置', '广告位', null, '1', 'static', 'ADLC');
INSERT INTO `t_dict` (`dict_id`, `dict_desc`, `dict_name`, `dict_seq`, `dict_status`, `dict_type`, `dict_code`) VALUES ('3', '短信签名', '短信签名', NULL, NULL, NULL, 'msgSign');

-- ----------------------------
-- Table structure for t_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_item`;
CREATE TABLE `t_dict_item` (
  `item_id` int(32) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(50) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `item_seq` bigint(20) DEFAULT NULL,
  `dict_id` int(32) DEFAULT NULL,
  `item_pid` int(32) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fke56003f12e061065` (`item_pid`),
  KEY `fke56003f1e8962a97` (`dict_id`),
  CONSTRAINT `t_dict_item_ibfk_1` FOREIGN KEY (`dict_id`) REFERENCES `t_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict_item
-- ----------------------------
INSERT INTO `topie_campus`.`t_dict_item` (`item_id`, `item_code`, `item_name`, `item_seq`, `dict_id`, `item_pid`) VALUES ('1', '2014-2015', '2014-2015', NULL, '1', NULL);
INSERT INTO `topie_campus`.`t_dict_item` (`item_id`, `item_code`, `item_name`, `item_seq`, `dict_id`, `item_pid`) VALUES ('2', '2015-2016', '2015-2016', NULL, '1', NULL);
INSERT INTO `topie_campus`.`t_dict_item` (`item_id`, `item_code`, `item_name`, `item_seq`, `dict_id`, `item_pid`) VALUES ('3', '生化导学系统', '生化导学系统', NULL, '3', NULL);
INSERT INTO `topie_campus`.`t_dict_item` (`item_id`, `item_code`, `item_name`, `item_seq`, `dict_id`, `item_pid`) VALUES ('4', '2016-2017', '2016-2017', NULL, '1', NULL);
INSERT INTO `topie_campus`.`t_dict_item` (`item_id`, `item_code`, `item_name`, `item_seq`, `dict_id`, `item_pid`) VALUES ('5', '2017-2018', '2017-2018', NULL, '1', NULL);
INSERT INTO `t_dict` (`dict_id`, `dict_desc`, `dict_name`, `dict_seq`, `dict_status`, `dict_type`, `dict_code`) VALUES ('4', '毕业年份', '毕业年份', NULL, NULL, NULL, NULL);
INSERT INTO `t_dict_item` (`item_id`, `item_code`, `item_name`, `item_seq`, `dict_id`, `item_pid`) VALUES ('6', '2017', '2017', NULL, '4', NULL);

-- ----------------------------
-- Table structure for t_stu_score
-- ----------------------------
DROP TABLE IF EXISTS `t_stu_score`;
CREATE TABLE `t_stu_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `study_year` varchar(20) DEFAULT '' COMMENT '学年',
  `study_year_num` varchar(10) DEFAULT NULL COMMENT '学期',
  `course_num` varchar(100) DEFAULT NULL COMMENT '选课课号',
  `stu_id` varchar(100) DEFAULT NULL COMMENT '学号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `cource_name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `credit` varchar(20) DEFAULT NULL COMMENT '学分',
  `score` varchar(20) DEFAULT NULL COMMENT '分数',
  `score_point` double(20,1) DEFAULT NULL COMMENT '绩点',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `re_study_flag` varchar(255) DEFAULT NULL COMMENT '\r\n重修标记（0或空，\r\nnull为不重修）为首次修读。1为跟班重修3自学重修',
  `common_score` varchar(20) DEFAULT NULL COMMENT '平时成绩',
  `page_score` varchar(20) DEFAULT NULL COMMENT '卷面成绩',
  `second_score` varchar(20) DEFAULT NULL COMMENT '补考成绩',
  `restudy_score` varchar(20) DEFAULT NULL COMMENT '重修成绩',
  `cource_type` varchar(255) DEFAULT NULL COMMENT '课程类型',
  `cource_code` varchar(255) DEFAULT NULL COMMENT '课程编码',
  `minor_flag` varchar(255) DEFAULT NULL COMMENT '辅修标记\r\n0正常，1辅修',
  `cource_attr` varchar(255) DEFAULT NULL COMMENT '课程归属',
  `invalid` varchar(255) DEFAULT NULL COMMENT '是否作废',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2545 DEFAULT CHARSET=utf8;

