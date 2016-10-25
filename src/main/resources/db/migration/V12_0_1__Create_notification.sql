DROP TABLE IF EXISTS t_user_notification;
CREATE TABLE t_user_notification (
  user_id           INT(11)           NOT NULL
  COMMENT '用户id',
  new_message_count INT(11) DEFAULT 0 NOT NULL
  COMMENT '新的留言数',
  new_reply_count   INT(11) DEFAULT 0
  COMMENT '新的回复数',
  PRIMARY KEY (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户消息提醒';
