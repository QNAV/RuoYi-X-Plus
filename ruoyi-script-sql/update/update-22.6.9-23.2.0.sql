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
INSERT INTO `sys_dict_data`(
    `dict_code`,
    `dict_sort`,
    `dict_label`,
    `dict_value`,
    `dict_type`,
    `css_class`,
    `list_class`,
    `is_default`,
    `status`,
    `create_by`,
    `create_time`,
    `update_by`,
    `update_time`,
    `remark`
)
VALUES(
  29,
  10,
  '其他',
  '10',
  'sys_oper_type',
  '',
  'info',
  'N',
  '0',
  'admin',
  '2022-06-19 06:27:41',
  '',
  NULL,
  '其他操作'
);
ALTER TABLE `sys_menu` CHANGE `visible` `visible` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）';
ALTER TABLE `sys_oss` CHANGE `service` `service` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'minio' COMMENT '服务商';
ALTER TABLE `sys_oss_config` CHANGE `config_key` `config_key` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置key';
ALTER TABLE `sys_oss_config` ADD `access_policy` CHAR(1) NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)' AFTER `region`;
DELETE FROM sys_menu WHERE `sys_menu`.`menu_id` = 111;
DELETE FROM sys_role_menu WHERE `sys_role_menu`.`menu_id` = 111;