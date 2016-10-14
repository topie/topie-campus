INSERT INTO `sys_role` VALUES ('4', '教师', '/', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('5', '学生', '/', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('12', '0', '首页', '1', '2', NULL, '/api/front/index', '4', NULL, NULL);
INSERT INTO `sys_function` VALUES ('13', '0', '收到的留言', '1', '2', NULL, '/api/front/message/receive', '5', NULL, NULL);
INSERT INTO `sys_function` VALUES ('14', '0', '发出的留言', '1', '2', NULL, '/api/front/message/send', '6', NULL, NULL);
INSERT INTO `sys_function` VALUES ('15', '0', '学生列表', '1', '2', NULL, '/api/front/student/page', '7', NULL, NULL);
INSERT INTO `sys_function` VALUES ('16', '0', '教师列表', '1', '2', NULL, '/api/front/teacher/page', '8', NULL, NULL);
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`)
VALUES ('19', '0', '成绩查询', '1', '2', '', '/api/front/student/score', '0', '2016-10-07 14:08:26', '2016-10-07 14:09:04');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`)
VALUES
  ('20', '0', '等级考试成绩查询', '1', '2', '', '/api/front/student/cetscore', '0', '2016-10-07 14:08:50',
   '2016-10-07 14:09:06');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`)
VALUES
  ('21', '0', '选课情况', '1', '2', '', '/api/front/student/selectCourse', '0', '2016-10-07 14:08:50',
   '2016-10-07 14:09:06');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`)
VALUES
  ('22', '0', '课程表', '1', '2', '', '/api/front/student/timeTable', '0', '2016-10-07 14:08:50', '2016-10-07 14:09:06');


INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '12');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '12');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '13');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '13');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '14');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '14');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '15');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '16');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('19', '5');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('20', '5');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('21', '5');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('22', '5');


INSERT INTO `sys_user` VALUES
  ('4', 'teacher', '$2a$10$3ie1MKBFBjCxfkXuvY60quZqvds.BxeHUxfYwxcaL0lJMSItpPoKq', '教师', '1', '1',
        '1', '1', NULL, NULL, 'teacher@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `t_teacher` VALUES
  (1, '', 4, '32319', '老师', 1, 1, '1990-10-12', 0, '软件学院', '', '18600200791', '18600200791@163.com', '', '', 0, 0, '',
                                                               '', '', '', '', '', 0, '我是陈老师', '0', '1', '1', '0',
                                                                           'chenguojun', 'teacher', '');
INSERT INTO `sys_user_role` (user_id, role_id) VALUES ('4', '4');
INSERT INTO `sys_user` VALUES
  ('5', 'student', '$2a$10$g9iJtxSdTyXj7I2jSDFpPOWN7xHlyasFOVb8Q5m/rX3EP5nTS4Pxi', '学生', '1', '1',
        '1', '1', NULL, NULL, 'teacher@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `t_student` (`id`, `user_id`, `student_no`, `name`, `birth`, `academe`, `subject`, `contact_phone`, `email`)
VALUES
  (1, 5, '32322', '学生', '1990-10-12', '软件学院', '软件工程', '18600200791',
   '18600200791@163.com');
INSERT INTO `sys_user` VALUES
  ('9', 'student2', '$2a$10$g9iJtxSdTyXj7I2jSDFpPOWN7xHlyasFOVb8Q5m/rX3EP5nTS4Pxi', '学生2', '1', '1',
        '1', '1', NULL, NULL, 'teacher@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `t_student` (`id`, `user_id`, `student_no`, `name`, `birth`, `academe`, `subject`, `contact_phone`, `email`)
VALUES
  (2, 9, '32323', '学生2', '1990-10-12', '软件学院', '软件工程', '18600200791',
   '18600200791@163.com');
INSERT INTO `sys_user_role` (user_id, role_id) VALUES ('5', '5');

INSERT INTO `t_teacher_type` (type_id, type_name) VALUES (1, '普通教师');
INSERT INTO `t_relate_teacher_teacher_type` VALUES (1, 0);

INSERT INTO `t_relate_student_teacher` (type_id, teacher_id, student_id, employee_no, student_no)
VALUES (1, 1, 1, '32319', '32322');


