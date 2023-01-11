ALTER TABLE `gen_table_column` CHANGE `table_id` `table_id` BIGINT NULL DEFAULT NULL COMMENT '归属表编号';
ALTER TABLE sys_notice modify column notice_id bigint(20) not null COMMENT '公告ID';
ALTER TABLE sys_config modify column config_id bigint(20) not null COMMENT '参数主键';