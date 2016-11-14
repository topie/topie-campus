INSERT INTO `topie_campus`.`sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('38', '39', '留言统计', '1', '1', NULL, '/api/info/message/page', '2', '2016-11-14 20:11:58', '2016-11-14 20:37:20');
INSERT INTO `topie_campus`.`sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('39', '0', '系统统计与导出', '1', '1', NULL, '#', '0', '2016-11-14 20:06:15', '2016-11-14 20:06:15');
INSERT INTO `topie_campus`.`sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('40', '39', '导师工作统计', '1', '1', NULL, '/api/info/exportStatic/teacherPage', '0', '2016-11-14 20:34:35', '2016-11-14 20:34:35');
INSERT INTO `topie_campus`.`sys_function` (`id`, `parent_id`, `function_name`, `display`, `display_type`, `icon`, `action`, `function_desc`, `insert_time`, `update_time`) VALUES ('41', '39', '短信统计', '1', '1', NULL, '/api/info/exportStatic/page', '0', '2016-11-14 20:38:34', '2016-11-14 20:38:34');

INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('41', '3');
INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('40', '3');
INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('39', '3');
INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('38', '3');
INSERT INTO `topie_campus`.`sys_role_function` (`function_id`, `role_id`) VALUES ('38', '1');
