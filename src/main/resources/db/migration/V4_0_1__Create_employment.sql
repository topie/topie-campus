
-- ----------------------------
-- Table structure for t_employment
-- ----------------------------
DROP TABLE IF EXISTS `t_employment`;
CREATE TABLE `t_employment` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `stu_id` varchar(50) DEFAULT '' COMMENT '学号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT '' COMMENT '电话',
  `education` varchar(255) DEFAULT '' COMMENT '学历',
  `college` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `enter_date` varchar(255) DEFAULT '' COMMENT '入学日期',
  `graduate_date` varchar(255) DEFAULT '' COMMENT '毕业日期',
  `identify_id` varchar(255) DEFAULT '' COMMENT '身份证号',
  `home_address` varchar(255) DEFAULT NULL,
  `employ_process` varchar(255) DEFAULT '' COMMENT '就业状态:\r\n领表、就业、签约',
  `employ_pattern` varchar(255) DEFAULT '' COMMENT '就业模式：证明、自谋',
  `sign_pattern` varchar(255) DEFAULT '' COMMENT '签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部',
  `sign_company` varchar(255) DEFAULT '' COMMENT '签约公司',
  `sign_company_address` varchar(255) DEFAULT '' COMMENT '签约公司地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
