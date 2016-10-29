INSERT INTO `sys_user` VALUES
  ('3', 'info', '$2a$10$A2QymSulb6pvk5NDDt4/i.Xcij23OClaC/qFqZyKHaTcrviejrIUq', '信息管理员', '1', '1',
        '1', '1', NULL, NULL, 'info@163.com', '15802983637', '2015-10-12 00:00:00',
   '2015-10-12 00:00:00', '2015-10-12 00:00:00');
INSERT INTO `sys_role` VALUES ('3', '信息管理员', '/', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('6', '0', '信息管理', '1', '1', NULL, '#', '3', NULL, NULL);
INSERT INTO `sys_function` VALUES ('7', '6', '信息上传', '1', '1', NULL, '/api/info/upload/page', '1', NULL, NULL);
INSERT INTO `sys_function` VALUES ('8', '6', '导师管理', '1', '1', NULL, '/api/info/teacher/page', '2', NULL, NULL);
INSERT INTO `sys_function` VALUES ('9', '6', '学生管理', '1', '1', NULL, '/api/info/student/page', '3', NULL, NULL);
INSERT INTO `sys_function` VALUES ('10', '6', '公告管理', '1', '1', NULL, '/api/info/notice/page', '4', NULL, NULL);
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('28', '6', '短信管理', '1', '1',  NULL, '/api/info/sendMsg', '0', '2016-10-21 15:09:11', '2016-10-21 15:10:57');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('29', '0', '领导管理', '1', '1', '', '#', '0', '2016-10-22 17:02:00', '2016-10-22 17:02:00');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('30', '29', '学生列表', '1', '1', '', '/api/leader/student/page', '0', '2016-10-22 17:04:07', '2016-10-22 17:04:07');
INSERT INTO `sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('31', '29', '导师列表', '1', '1', '', '/api/leader/student/teacher', '0', '2016-10-22 17:05:24', '2016-10-22 17:05:24');


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
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('28', '1');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('28', '3');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('31', '1');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('30', '1');
INSERT INTO `sys_role_function` (`function_id`, `role_id`) VALUES ('29', '1');



INSERT INTO `sys_function` VALUES ('23', '6', '导师类型管理', '1', '1', NULL, '/api/info/teacherType/page', '2', NULL, NULL);
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('1', '23');
INSERT INTO `sys_role_function` (role_id, function_id) VALUES ('3', '23');
