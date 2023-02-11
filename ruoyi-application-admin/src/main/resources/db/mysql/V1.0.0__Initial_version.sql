SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

CREATE TABLE IF NOT EXISTS `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='代码生成业务表';

CREATE TABLE IF NOT EXISTS `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '列类型',
  `column_default` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '列默认值',
  `java_type` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='代码生成业务表字段';

CREATE TABLE IF NOT EXISTS `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='参数配置表';

INSERT INTO `sys_config` VALUES(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES(2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES(3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES(4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES(11, 'OSS预览列表资源开关', 'sys.oss.previewListResource', 'true', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, 'true:开启, false:关闭');

CREATE TABLE IF NOT EXISTS `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';

INSERT INTO `sys_dept` VALUES(100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);
INSERT INTO `sys_dept` VALUES(109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);

CREATE TABLE IF NOT EXISTS `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) COLLATE utf8mb4_unicode_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典数据表';

INSERT INTO `sys_dict_data` VALUES(1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES(2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES(3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES(4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES(5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES(6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES(7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES(12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES(13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES(14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES(15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES(16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES(17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES(18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES(19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES(20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES(21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES(22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES(23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES(24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES(25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES(26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES(27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES(28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES(29, 10, '其他', '10', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '其他操作');

CREATE TABLE IF NOT EXISTS `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型表';

INSERT INTO `sys_dict_type` VALUES(1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES(2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES(3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES(6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES(7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES(8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES(9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES(10, '系统状态', 'sys_common_status', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '登录状态列表');

CREATE TABLE IF NOT EXISTS `sys_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统访问记录';

CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件路径',
  `query_param` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';

INSERT INTO `sys_menu` VALUES(1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'SettingOutlined', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:18:32', '系统管理目录');
INSERT INTO `sys_menu` VALUES(2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2022-06-19 06:27:41', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES(3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2022-06-19 06:27:41', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES(4, 'RuoYiX官网', 0, 4, 'https://github.com/QNAV/RuoYi-X-Plus', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:08:08', 'RuoYi-X-Plus官网地址');
INSERT INTO `sys_menu` VALUES(5, '测试菜单', 0, 5, 'demo', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'star', 'admin', '2022-06-19 06:28:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES(100, '用户管理', 1, 1, '/system/user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'UserOutlined', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:21:48', '用户管理菜单');
INSERT INTO `sys_menu` VALUES(101, '角色管理', 1, 2, '/system/role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'TeamOutlined', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:22:29', '角色管理菜单');
INSERT INTO `sys_menu` VALUES(102, '菜单管理', 1, 3, '/system/menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2022-06-19 06:27:41', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES(103, '部门管理', 1, 4, '/system/dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2022-06-19 06:27:41', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES(104, '岗位管理', 1, 5, '/system/post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2022-06-19 06:27:41', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES(105, '字典管理', 1, 6, '/system/dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2022-06-19 06:27:41', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES(106, '参数设置', 1, 7, '/system/config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2022-06-19 06:27:41', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES(107, '通知公告', 1, 8, '/system/notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2022-06-19 06:27:41', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES(108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2022-06-19 06:27:41', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES(109, '在线用户', 2, 1, '/monitor/online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2022-06-19 06:27:41', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES(113, '缓存监控', 2, 5, '/monitor/cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2022-06-19 06:27:41', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES(114, '表单构建', 3, 1, '/tool/build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 13:48:25', '表单构建菜单');
INSERT INTO `sys_menu` VALUES(115, '代码生成', 3, 2, '/tool/gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2022-06-19 06:27:41', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES(116, '系统接口', 3, 3, '/tool/swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2022-06-19 06:27:41', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES(117, 'Admin监控', 2, 5, '/monitor/admin', 'monitor/admin/index', '', 1, 0, 'C', '0', '0', 'monitor:admin:list', 'dashboard', 'admin', '2022-06-19 06:27:41', '', NULL, 'Admin监控菜单');
INSERT INTO `sys_menu` VALUES(118, '文件管理', 1, 10, '/system/oss', 'system/oss/index', '', 1, 0, 'C', '0', '0', 'system:oss:list', 'upload', 'admin', '2022-06-19 06:27:41', '', NULL, '文件管理菜单');
INSERT INTO `sys_menu` VALUES(120, '任务调度中心', 2, 5, '/monitor/xxljob', 'monitor/xxljob/index', '', 1, 0, 'C', '0', '0', 'monitor:xxljob:list', 'job', 'admin', '2022-06-19 06:27:41', '', NULL, 'Xxl-Job控制台菜单');
INSERT INTO `sys_menu` VALUES(500, '操作日志', 108, 1, '/monitor/operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2022-06-19 06:27:41', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES(501, '登录日志', 108, 2, '/monitor/logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2022-06-19 06:27:41', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES(1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1500, '测试单表', 5, 1, '/demo/demo', 'demo/demo/index', NULL, 1, 0, 'C', '0', '0', 'demo:demo:list', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '测试单表菜单');
INSERT INTO `sys_menu` VALUES(1501, '测试单表查询', 1500, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:query', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1502, '测试单表新增', 1500, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:add', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1503, '测试单表修改', 1500, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:edit', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1504, '测试单表删除', 1500, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:remove', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1505, '测试单表导出', 1500, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:export', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1506, '测试树表', 5, 1, '/demo/tree', 'demo/tree/index', NULL, 1, 0, 'C', '0', '0', 'demo:tree:list', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '测试树表菜单');
INSERT INTO `sys_menu` VALUES(1507, '测试树表查询', 1506, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:query', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1508, '测试树表新增', 1506, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:add', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1509, '测试树表修改', 1506, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:edit', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1510, '测试树表删除', 1506, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:remove', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1511, '测试树表导出', 1506, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:export', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1600, '文件查询', 118, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1601, '文件上传', 118, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:upload', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1602, '文件下载', 118, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:download', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1603, '文件删除', 118, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1604, '配置添加', 118, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1605, '配置编辑', 118, 6, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES(1606, '缓存列表', 2, 6, '/monitor/cache', 'monitor/cache/list', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2023-01-13 12:57:05', '', NULL, '缓存列表菜单');

CREATE TABLE IF NOT EXISTS `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NOT NULL COMMENT '公告内容',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知公告表';

INSERT INTO `sys_notice` VALUES(1, '温馨提醒：2018-07-01 新版本发布啦', '2', '新版本内容', '0', 'admin', '2022-06-19 06:27:42', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES(2, '维护通知：2018-07-01 系统凌晨维护', '1', '维护内容', '0', 'admin', '2022-06-19 06:27:42', '', NULL, '管理员');

CREATE TABLE IF NOT EXISTS `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志记录';

CREATE TABLE IF NOT EXISTS `sys_oss` (
  `oss_id` bigint NOT NULL AUTO_INCREMENT COMMENT '对象存储主键',
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '原名',
  `file_suffix` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'URL地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '上传人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新人',
  `service` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'minio' COMMENT '服务商',
  PRIMARY KEY (`oss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OSS对象存储表';

CREATE TABLE IF NOT EXISTS `sys_oss_config` (
  `oss_config_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主建',
  `config_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '配置key',
  `access_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'accessKey',
  `secret_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '秘钥',
  `bucket_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '桶名称',
  `prefix` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '前缀',
  `endpoint` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '访问站点',
  `domain` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '自定义域名',
  `is_https` char(1) COLLATE utf8mb4_unicode_ci DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
  `region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '域',
  `access_policy` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '状态（0=正常,1=停用）',
  `ext1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '扩展字段',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oss_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='对象存储配置表';

INSERT INTO `sys_oss_config` VALUES(1, 'minio', 'ruoyi', 'ruoyi123', 'ruoyi', '', '127.0.0.1:9000', '', 'N', '', '1', '0', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL);
INSERT INTO `sys_oss_config` VALUES(2, 'qiniu', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 's3-cn-north-1.qiniucs.com', '', 'N', '', '1', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL);
INSERT INTO `sys_oss_config` VALUES(3, 'aliyun', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 'oss-cn-beijing.aliyuncs.com', '', 'N', '', '1', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL);
INSERT INTO `sys_oss_config` VALUES(4, 'qcloud', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi-1250000000', '', 'cos.ap-beijing.myqcloud.com', '', 'N', 'ap-beijing', '1', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL);
INSERT INTO `sys_oss_config` VALUES(5, 'image', 'ruoyi', 'ruoyi123', 'ruoyi', 'image', '127.0.0.1:9000', '', 'N', '', '1', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL);

CREATE TABLE IF NOT EXISTS `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='岗位信息表';

INSERT INTO `sys_post` VALUES(1, 'ceo', '董事长', 1, '0', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_post` VALUES(2, 'se', '项目经理', 2, '0', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_post` VALUES(3, 'hr', '人力资源', 3, '0', 'admin', '2022-06-19 06:27:41', '', NULL, '');
INSERT INTO `sys_post` VALUES(4, 'user', '普通员工', 4, '0', 'admin', '2022-06-19 06:27:41', '', NULL, '');

CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色信息表';

INSERT INTO `sys_role` VALUES(1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES(2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '普通角色');
INSERT INTO `sys_role` VALUES(3, '本部门及以下', 'test1', 3, '4', 1, 1, '0', '0', 'admin', '2022-06-19 06:28:02', 'admin', NULL, NULL);
INSERT INTO `sys_role` VALUES(4, '仅本人', 'test2', 4, '5', 1, 1, '0', '0', 'admin', '2022-06-19 06:28:02', 'admin', NULL, NULL);
INSERT INTO `sys_role` VALUES(9, 'dsd', '2wd', 0, '1', 1, 1, '0', '0', 'admin', '2022-06-26 19:21:02', 'admin', '2022-06-26 19:21:02', NULL);

CREATE TABLE IF NOT EXISTS `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色和部门关联表';

INSERT INTO `sys_role_dept` VALUES(2, 100);
INSERT INTO `sys_role_dept` VALUES(2, 101);
INSERT INTO `sys_role_dept` VALUES(2, 105);

CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色和菜单关联表';

INSERT INTO `sys_role_menu` VALUES(2, 1);
INSERT INTO `sys_role_menu` VALUES(2, 2);
INSERT INTO `sys_role_menu` VALUES(2, 3);
INSERT INTO `sys_role_menu` VALUES(2, 4);
INSERT INTO `sys_role_menu` VALUES(2, 100);
INSERT INTO `sys_role_menu` VALUES(2, 101);
INSERT INTO `sys_role_menu` VALUES(2, 102);
INSERT INTO `sys_role_menu` VALUES(2, 103);
INSERT INTO `sys_role_menu` VALUES(2, 104);
INSERT INTO `sys_role_menu` VALUES(2, 105);
INSERT INTO `sys_role_menu` VALUES(2, 106);
INSERT INTO `sys_role_menu` VALUES(2, 107);
INSERT INTO `sys_role_menu` VALUES(2, 108);
INSERT INTO `sys_role_menu` VALUES(2, 109);
INSERT INTO `sys_role_menu` VALUES(2, 110);
INSERT INTO `sys_role_menu` VALUES(2, 112);
INSERT INTO `sys_role_menu` VALUES(2, 113);
INSERT INTO `sys_role_menu` VALUES(2, 114);
INSERT INTO `sys_role_menu` VALUES(2, 115);
INSERT INTO `sys_role_menu` VALUES(2, 116);
INSERT INTO `sys_role_menu` VALUES(2, 500);
INSERT INTO `sys_role_menu` VALUES(2, 501);
INSERT INTO `sys_role_menu` VALUES(2, 1000);
INSERT INTO `sys_role_menu` VALUES(2, 1001);
INSERT INTO `sys_role_menu` VALUES(2, 1002);
INSERT INTO `sys_role_menu` VALUES(2, 1003);
INSERT INTO `sys_role_menu` VALUES(2, 1004);
INSERT INTO `sys_role_menu` VALUES(2, 1005);
INSERT INTO `sys_role_menu` VALUES(2, 1006);
INSERT INTO `sys_role_menu` VALUES(2, 1007);
INSERT INTO `sys_role_menu` VALUES(2, 1008);
INSERT INTO `sys_role_menu` VALUES(2, 1009);
INSERT INTO `sys_role_menu` VALUES(2, 1010);
INSERT INTO `sys_role_menu` VALUES(2, 1011);
INSERT INTO `sys_role_menu` VALUES(2, 1012);
INSERT INTO `sys_role_menu` VALUES(2, 1013);
INSERT INTO `sys_role_menu` VALUES(2, 1014);
INSERT INTO `sys_role_menu` VALUES(2, 1015);
INSERT INTO `sys_role_menu` VALUES(2, 1016);
INSERT INTO `sys_role_menu` VALUES(2, 1017);
INSERT INTO `sys_role_menu` VALUES(2, 1018);
INSERT INTO `sys_role_menu` VALUES(2, 1019);
INSERT INTO `sys_role_menu` VALUES(2, 1020);
INSERT INTO `sys_role_menu` VALUES(2, 1021);
INSERT INTO `sys_role_menu` VALUES(2, 1022);
INSERT INTO `sys_role_menu` VALUES(2, 1023);
INSERT INTO `sys_role_menu` VALUES(2, 1024);
INSERT INTO `sys_role_menu` VALUES(2, 1025);
INSERT INTO `sys_role_menu` VALUES(2, 1026);
INSERT INTO `sys_role_menu` VALUES(2, 1027);
INSERT INTO `sys_role_menu` VALUES(2, 1028);
INSERT INTO `sys_role_menu` VALUES(2, 1029);
INSERT INTO `sys_role_menu` VALUES(2, 1030);
INSERT INTO `sys_role_menu` VALUES(2, 1031);
INSERT INTO `sys_role_menu` VALUES(2, 1032);
INSERT INTO `sys_role_menu` VALUES(2, 1033);
INSERT INTO `sys_role_menu` VALUES(2, 1034);
INSERT INTO `sys_role_menu` VALUES(2, 1035);
INSERT INTO `sys_role_menu` VALUES(2, 1036);
INSERT INTO `sys_role_menu` VALUES(2, 1037);
INSERT INTO `sys_role_menu` VALUES(2, 1038);
INSERT INTO `sys_role_menu` VALUES(2, 1039);
INSERT INTO `sys_role_menu` VALUES(2, 1040);
INSERT INTO `sys_role_menu` VALUES(2, 1041);
INSERT INTO `sys_role_menu` VALUES(2, 1042);
INSERT INTO `sys_role_menu` VALUES(2, 1043);
INSERT INTO `sys_role_menu` VALUES(2, 1044);
INSERT INTO `sys_role_menu` VALUES(2, 1045);
INSERT INTO `sys_role_menu` VALUES(2, 1046);
INSERT INTO `sys_role_menu` VALUES(2, 1047);
INSERT INTO `sys_role_menu` VALUES(2, 1048);
INSERT INTO `sys_role_menu` VALUES(2, 1055);
INSERT INTO `sys_role_menu` VALUES(2, 1056);
INSERT INTO `sys_role_menu` VALUES(2, 1057);
INSERT INTO `sys_role_menu` VALUES(2, 1058);
INSERT INTO `sys_role_menu` VALUES(2, 1059);
INSERT INTO `sys_role_menu` VALUES(2, 1060);
INSERT INTO `sys_role_menu` VALUES(3, 1);
INSERT INTO `sys_role_menu` VALUES(3, 5);
INSERT INTO `sys_role_menu` VALUES(3, 100);
INSERT INTO `sys_role_menu` VALUES(3, 101);
INSERT INTO `sys_role_menu` VALUES(3, 102);
INSERT INTO `sys_role_menu` VALUES(3, 103);
INSERT INTO `sys_role_menu` VALUES(3, 104);
INSERT INTO `sys_role_menu` VALUES(3, 105);
INSERT INTO `sys_role_menu` VALUES(3, 106);
INSERT INTO `sys_role_menu` VALUES(3, 107);
INSERT INTO `sys_role_menu` VALUES(3, 108);
INSERT INTO `sys_role_menu` VALUES(3, 500);
INSERT INTO `sys_role_menu` VALUES(3, 501);
INSERT INTO `sys_role_menu` VALUES(3, 1001);
INSERT INTO `sys_role_menu` VALUES(3, 1002);
INSERT INTO `sys_role_menu` VALUES(3, 1003);
INSERT INTO `sys_role_menu` VALUES(3, 1004);
INSERT INTO `sys_role_menu` VALUES(3, 1005);
INSERT INTO `sys_role_menu` VALUES(3, 1006);
INSERT INTO `sys_role_menu` VALUES(3, 1007);
INSERT INTO `sys_role_menu` VALUES(3, 1008);
INSERT INTO `sys_role_menu` VALUES(3, 1009);
INSERT INTO `sys_role_menu` VALUES(3, 1010);
INSERT INTO `sys_role_menu` VALUES(3, 1011);
INSERT INTO `sys_role_menu` VALUES(3, 1012);
INSERT INTO `sys_role_menu` VALUES(3, 1013);
INSERT INTO `sys_role_menu` VALUES(3, 1014);
INSERT INTO `sys_role_menu` VALUES(3, 1015);
INSERT INTO `sys_role_menu` VALUES(3, 1016);
INSERT INTO `sys_role_menu` VALUES(3, 1017);
INSERT INTO `sys_role_menu` VALUES(3, 1018);
INSERT INTO `sys_role_menu` VALUES(3, 1019);
INSERT INTO `sys_role_menu` VALUES(3, 1020);
INSERT INTO `sys_role_menu` VALUES(3, 1021);
INSERT INTO `sys_role_menu` VALUES(3, 1022);
INSERT INTO `sys_role_menu` VALUES(3, 1023);
INSERT INTO `sys_role_menu` VALUES(3, 1024);
INSERT INTO `sys_role_menu` VALUES(3, 1025);
INSERT INTO `sys_role_menu` VALUES(3, 1026);
INSERT INTO `sys_role_menu` VALUES(3, 1027);
INSERT INTO `sys_role_menu` VALUES(3, 1028);
INSERT INTO `sys_role_menu` VALUES(3, 1029);
INSERT INTO `sys_role_menu` VALUES(3, 1030);
INSERT INTO `sys_role_menu` VALUES(3, 1031);
INSERT INTO `sys_role_menu` VALUES(3, 1032);
INSERT INTO `sys_role_menu` VALUES(3, 1033);
INSERT INTO `sys_role_menu` VALUES(3, 1034);
INSERT INTO `sys_role_menu` VALUES(3, 1035);
INSERT INTO `sys_role_menu` VALUES(3, 1036);
INSERT INTO `sys_role_menu` VALUES(3, 1037);
INSERT INTO `sys_role_menu` VALUES(3, 1038);
INSERT INTO `sys_role_menu` VALUES(3, 1039);
INSERT INTO `sys_role_menu` VALUES(3, 1040);
INSERT INTO `sys_role_menu` VALUES(3, 1041);
INSERT INTO `sys_role_menu` VALUES(3, 1042);
INSERT INTO `sys_role_menu` VALUES(3, 1043);
INSERT INTO `sys_role_menu` VALUES(3, 1044);
INSERT INTO `sys_role_menu` VALUES(3, 1045);
INSERT INTO `sys_role_menu` VALUES(3, 1500);
INSERT INTO `sys_role_menu` VALUES(3, 1501);
INSERT INTO `sys_role_menu` VALUES(3, 1502);
INSERT INTO `sys_role_menu` VALUES(3, 1503);
INSERT INTO `sys_role_menu` VALUES(3, 1504);
INSERT INTO `sys_role_menu` VALUES(3, 1505);
INSERT INTO `sys_role_menu` VALUES(3, 1506);
INSERT INTO `sys_role_menu` VALUES(3, 1507);
INSERT INTO `sys_role_menu` VALUES(3, 1508);
INSERT INTO `sys_role_menu` VALUES(3, 1509);
INSERT INTO `sys_role_menu` VALUES(3, 1510);
INSERT INTO `sys_role_menu` VALUES(3, 1511);
INSERT INTO `sys_role_menu` VALUES(4, 5);
INSERT INTO `sys_role_menu` VALUES(4, 1500);
INSERT INTO `sys_role_menu` VALUES(4, 1501);
INSERT INTO `sys_role_menu` VALUES(4, 1502);
INSERT INTO `sys_role_menu` VALUES(4, 1503);
INSERT INTO `sys_role_menu` VALUES(4, 1504);
INSERT INTO `sys_role_menu` VALUES(4, 1505);
INSERT INTO `sys_role_menu` VALUES(4, 1506);
INSERT INTO `sys_role_menu` VALUES(4, 1507);
INSERT INTO `sys_role_menu` VALUES(4, 1508);
INSERT INTO `sys_role_menu` VALUES(4, 1509);
INSERT INTO `sys_role_menu` VALUES(4, 1510);
INSERT INTO `sys_role_menu` VALUES(4, 1511);

CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

INSERT INTO `sys_user` VALUES(1, 103, 'admin', '疯狂的狮子Li', 'sys_user', 'crazyLionLi@163.com', '15888888888', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '116.253.117.142', '2023-02-08 21:10:41', 'admin', '2022-06-19 06:27:41', 'admin', '2023-02-08 21:10:41', '管理员');
INSERT INTO `sys_user` VALUES(2, 105, 'lionli', '疯狂的狮子Li', 'sys_user', 'crazyLionLi@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2022-06-19 06:27:41', 'admin', '2022-06-19 06:27:41', '', NULL, '测试员');
INSERT INTO `sys_user` VALUES(3, 108, 'test', '本部门及以下 密码666666', 'sys_user', '', '', '0', '', '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2022-06-19 06:28:02', 'admin', '2022-06-19 06:28:02', 'test', '2022-06-19 06:28:02', NULL);
INSERT INTO `sys_user` VALUES(4, 102, 'test1', '仅本人 密码666666', 'sys_user', '', '', '0', '', '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2022-06-19 06:28:02', 'admin', '2022-06-19 06:28:02', 'test1', '2022-06-19 06:28:02', NULL);

CREATE TABLE IF NOT EXISTS `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户与岗位关联表';

INSERT INTO `sys_user_post` VALUES(1, 1);
INSERT INTO `sys_user_post` VALUES(2, 2);

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户和角色关联表';

INSERT INTO `sys_user_role` VALUES(1, 1);
INSERT INTO `sys_user_role` VALUES(2, 2);
INSERT INTO `sys_user_role` VALUES(3, 3);
INSERT INTO `sys_user_role` VALUES(4, 4);

CREATE TABLE IF NOT EXISTS `test_demo` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `order_num` int DEFAULT '0' COMMENT '排序号',
  `test_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'key键',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '值',
  `version` int DEFAULT '0' COMMENT '版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `del_flag` int DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试单表';

INSERT INTO `test_demo` VALUES(1, 102, 4, 1, '测试数据权限', '测试', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(2, 102, 3, 2, '子节点1', '111', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(3, 102, 3, 3, '子节点2', '222', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(4, 108, 4, 4, '测试数据', 'demo', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(5, 108, 3, 13, '子节点11', '1111', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(6, 108, 3, 12, '子节点22', '2222', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(7, 108, 3, 11, '子节点33', '3333', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(8, 108, 3, 10, '子节点44', '4444', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(9, 108, 3, 9, '子节点55', '5555', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(10, 108, 3, 8, '子节点66', '6666', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(11, 108, 3, 7, '子节点77', '7777', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(12, 108, 3, 6, '子节点88', '8888', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(13, 108, 3, 5, '子节点99', '9999', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_demo` VALUES(14, NULL, NULL, 0, '测试的key', '测试的value', 0, '2023-02-09 15:14:37', NULL, '2023-02-09 15:14:37', NULL, 0);

CREATE TABLE IF NOT EXISTS `test_tree` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint DEFAULT '0' COMMENT '父id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `tree_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '值',
  `version` int DEFAULT '0' COMMENT '版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  `del_flag` int DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试树表';

INSERT INTO `test_tree` VALUES(1, 0, 102, 4, '测试数据权限', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(2, 1, 102, 3, '子节点1', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(3, 2, 102, 3, '子节点2', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(4, 0, 108, 4, '测试树1', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(5, 4, 108, 3, '子节点11', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(6, 4, 108, 3, '子节点22', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(7, 4, 108, 3, '子节点33', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(8, 5, 108, 3, '子节点44', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(9, 6, 108, 3, '子节点55', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(10, 7, 108, 3, '子节点66', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(11, 7, 108, 3, '子节点77', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(12, 10, 108, 3, '子节点88', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);
INSERT INTO `test_tree` VALUES(13, 10, 108, 3, '子节点99', 0, '2022-06-19 06:28:02', 'admin', NULL, NULL, 0);

CREATE TABLE IF NOT EXISTS `xxl_job_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COLLATE utf8mb4_unicode_ci COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `xxl_job_group` VALUES(1, 'xxl-job-executor', '示例执行器', 0, NULL, '2023-01-27 16:15:55');

CREATE TABLE IF NOT EXISTS `xxl_job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COLLATE utf8mb4_unicode_ci COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `xxl_job_info` VALUES(1, 1, '测试任务1', '2018-11-03 22:21:31', '2018-11-03 22:21:31', 'XXL', '', 'CRON', '0 0 0 * * ? *', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);

CREATE TABLE IF NOT EXISTS `xxl_job_lock` (
  `lock_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `xxl_job_lock` VALUES('schedule_lock');

CREATE TABLE IF NOT EXISTS `xxl_job_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COLLATE utf8mb4_unicode_ci COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text COLLATE utf8mb4_unicode_ci COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `xxl_job_logglue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COLLATE utf8mb4_unicode_ci COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `xxl_job_log_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `xxl_job_registry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `registry_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `registry_value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `xxl_job_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `xxl_job_user` VALUES(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);