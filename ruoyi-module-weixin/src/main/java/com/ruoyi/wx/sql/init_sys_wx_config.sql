CREATE TABLE `sys_wx_config` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `app_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app_id',
  `secret` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密钥',
  `token` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'token',
  `aes_key` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信小程序消息服务器配置的EncodingAESKey',
  `msg_data_format` text COLLATE utf8mb4_general_ci COMMENT '消息格式，XML或者JSON',
  `name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `principal_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主体名称',
  `wx_type` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型: mp-公众号；mini-小程序',
  `logo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `qr_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '二维码地址',
  `mch_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户ID',
  `mch_key` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户key',
  `key_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'p12证书的位置，可以指定绝对路径，也可以指定类路径（以classpath:开头）',
  `verify_type` int DEFAULT '1' COMMENT '认证类型 : 0-未认证 ； 1-已认证',
  `tenant_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID 预留',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '上传人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

