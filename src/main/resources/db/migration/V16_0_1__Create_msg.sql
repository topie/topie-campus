DROP TABLE IF EXISTS `t_msg`;
CREATE TABLE `t_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `reciever` varchar(255) DEFAULT NULL,
  `msg_content` varchar(255) DEFAULT NULL,
  `msg_sign` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `teacher_no` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;