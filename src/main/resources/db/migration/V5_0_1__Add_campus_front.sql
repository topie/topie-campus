INSERT INTO `sys_role` VALUES ('4', '教师', '/', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('5', '学生', '/', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('12', '0', '首页', '1', '2', NULL, '/api/front/index', '4', NULL, NULL);
INSERT INTO `sys_function` VALUES ('13', '0', '收到的留言', '1', '2', NULL, '/api/front/message/receive', '5', NULL, NULL);
INSERT INTO `sys_function` VALUES ('14', '0', '发出的留言', '1', '2', NULL, '/api/front/message/send', '6', NULL, NULL);
INSERT INTO `sys_function` VALUES ('15', '0', '学生列表', '1', '2', NULL, '/api/front/student/page', '7', NULL, NULL);
INSERT INTO `sys_function` VALUES ('16', '0', '教师列表', '1', '2', NULL, '/api/front/teacher/page', '8', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '12');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '12');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '13');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '13');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '14');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '14');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '15');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '16');

INSERT INTO `sys_user` VALUES
  ('4', 'teacher', '$2a$10$3ie1MKBFBjCxfkXuvY60quZqvds.BxeHUxfYwxcaL0lJMSItpPoKq', '教师', '1', '1',
        '1', '1', NULL, NULL, 'teacher@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `t_teacher` VALUES
  (1, '', 4, '32319', '老师', 1, 1, '1990-10-12', 0, '软件学院', '', '18600200791', '18600200791@163.com', '', '', 0, 0, '',
                                                               '', '', '', '', '', 0, '我是陈老师');
INSERT INTO `sys_user_role` (user_id, role_id) VALUES ('4', '4');
INSERT INTO `sys_user` VALUES
  ('5', 'student', '$2a$10$g9iJtxSdTyXj7I2jSDFpPOWN7xHlyasFOVb8Q5m/rX3EP5nTS4Pxi', '学生', '1', '1',
        '1', '1', NULL, NULL, 'teacher@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `t_student`
VALUES (1, '', 5, '32322', '学生', 1, 1, '1990-10-12', 0, '软件学院', '软件工程', '18600200791', '18600200791@163.com');
INSERT INTO `sys_user_role` (user_id, role_id) VALUES ('5', '5');

INSERT INTO `t_relate_student_teacher` (teacher_id, student_id) VALUES (1, 1);


