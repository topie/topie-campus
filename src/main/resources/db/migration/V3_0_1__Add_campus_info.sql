INSERT INTO `sys_user` VALUES
  ('3', 'info', '$2a$10$A2QymSulb6pvk5NDDt4/i.Xcij23OClaC/qFqZyKHaTcrviejrIUq', '信息管理员', '1', '1',
        '1', '1', NULL, NULL, 'info@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `sys_role` VALUES ('3', '信息管理员', '/', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('6', '0', '信息管理', '1', '1', NULL, '#', '3', NULL, NULL);
INSERT INTO `sys_function` VALUES ('7', '6', '信息上传', '1', '1', NULL, '/api/info/upload/page', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('8', '6', '教师管理', '1', '1', NULL, '/api/info/teacher/page', '2', NULL, NULL);
INSERT INTO `sys_function` VALUES ('9', '6', '学生管理', '1', '1', NULL, '/api/info/student/page', '3', NULL, NULL);
INSERT INTO `sys_function` VALUES ('10', '6', '公告管理', '1', '1', NULL, '/api/info/notice/page', '4', NULL, NULL);
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('12', '6', '就业管理', '1', '1', '', '/api/job/page', '0', '2016-09-25 22:53:40', '2016-09-25 22:53:40');

INSERT INTO `sys_user_role` (user_id, role_id) VALUES ('3', '3');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '6');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '7');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '8');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '9');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '10');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '1');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '6');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '7');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '8');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '9');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '10');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('12', '1');


INSERT INTO `sys_role` VALUES ('4', '教师', '/', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('5', '学生', '/', '1', NULL, NULL);

INSERT INTO `sys_function` VALUES ('11', '0', '首页', '1', '2', NULL, '/api/front/index', '4', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('4', '11');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('5', '11');

