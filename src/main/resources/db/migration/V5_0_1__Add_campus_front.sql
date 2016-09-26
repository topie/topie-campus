INSERT INTO `sys_role` VALUES ('4', '教师', '/', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('5', '学生', '/', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('11', '0', '首页', '1', '2', NULL, '/api/front/index', '4', NULL, NULL);
INSERT INTO `sys_function` VALUES ('12', '0', '收到的留言', '1', '2', NULL, '/api/front/message/receive', '5', NULL, NULL);
INSERT INTO `sys_function` VALUES ('13', '0', '发出的留言', '1', '2', NULL, '/api/front/message/send', '6', NULL, NULL);
INSERT INTO `sys_function` VALUES ('14', '0', '学生列表', '1', '2', NULL, '/api/front/student/page', '7', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '11');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '11');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '12');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '12');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '13');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '13');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '14');

