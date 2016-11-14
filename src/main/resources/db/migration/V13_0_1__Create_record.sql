DROP TABLE IF EXISTS `t_plan_summary`;
CREATE TABLE `t_plan_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `type` int(4) DEFAULT NULL COMMENT '类型0,工作计划，1工作总结',
  `study_year` varchar(30) DEFAULT NULL COMMENT '学年',
  `study_year_num` varchar(5) DEFAULT NULL COMMENT '学期',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `teacher_no` varchar(255) DEFAULT NULL COMMENT '职工号',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_student_record
-- ----------------------------
DROP TABLE IF EXISTS `t_student_record`;
CREATE TABLE `t_student_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `study_year` varchar(255) DEFAULT NULL,
  `study_year_num` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `teacher_id` int(11) DEFAULT NULL,
  `teacher_no` varchar(255) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `student_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `topie_campus`.`sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('35', '0', '工作计划与总结', '1', '2', '', '/api/front/teacherRecord/planList', '0', '2016-11-03 22:32:40', '2016-11-03 22:34:48');

INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('35', '4');
INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('26', '5');
INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('32', '4');


