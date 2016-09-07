INSERT INTO `sys_user` VALUES
  ('3', 'info', '$2a$10$A2QymSulb6pvk5NDDt4/i.Xcij23OClaC/qFqZyKHaTcrviejrIUq', '信息管理员', '1', '1',
        '1', '1', NULL, NULL, 'info@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `sys_role` VALUES ('3', '信息管理员', '/', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('6', '0', '信息管理', '1', '1', NULL, '#', '3', NULL, NULL);
INSERT INTO `sys_function` VALUES ('7', '6', '信息上传', '1', '1', NULL, '/api/info/user/upload', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('8', '6', '教师列表', '1', '1', NULL, '/api/info/teacher/pageList', '1', NULL, NULL);
INSERT INTO `sys_user_role` (user_id, role_id) VALUES ('3', '3');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '6');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '7');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '8');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '1');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '6');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '7');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '8');


