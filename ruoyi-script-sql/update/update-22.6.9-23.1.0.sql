ALTER TABLE `gen_table_column` CHANGE `table_id` `table_id` BIGINT NULL DEFAULT NULL COMMENT '归属表编号';
ALTER TABLE sys_notice modify column notice_id bigint(20) not null COMMENT '公告ID';
ALTER TABLE sys_config modify column config_id bigint(20) not null COMMENT '参数主键';
INSERT INTO sys_menu
VALUES(
  NULL,
  '缓存列表',
  '2',
  '6',
  'cacheList',
  'monitor/cache/list',
  '',
  1,
  0,
  'C',
  '0',
  '0',
  'monitor:cache:list',
  'redis-list',
  'admin',
  SYSDATE(),
  '',
  NULL,
  '缓存列表菜单'
);
UPDATE `sys_config` SET `config_key` = 'sys.account.captchaEnabled' WHERE `sys_config`.`config_id` = 4;