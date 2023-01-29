-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- 主机： mysql
-- 生成日期： 2022-06-27 15:41:31
-- 服务器版本： 5.7.34-log
-- PHP 版本： 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `ruoyi-x-plus`
--

-- --------------------------------------------------------

--
-- 表的结构 `gen_table`
--

CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表';

-- --------------------------------------------------------

--
-- 表的结构 `gen_table_column`
--

CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL COMMENT '编号',
  `table_id` bigint(20) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `column_default` varchar(200) DEFAULT NULL COMMENT '列默认值',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表字段';

-- --------------------------------------------------------

--
-- 表的结构 `sys_config`
--

CREATE TABLE `sys_config` (
  `config_id` bigint(20) NOT NULL COMMENT '参数主键',
  `config_name` varchar(100) NOT NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) NOT NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) NOT NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数配置表';

--
-- 转存表中的数据 `sys_config`
--

INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
(2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '初始化密码 123456'),
(3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '深色主题theme-dark，浅色主题theme-light'),
(4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '是否开启验证码功能（true开启，false关闭）'),
(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, '是否开启注册用户功能（true开启，false关闭）'),
(11, 'OSS预览列表资源开关', 'sys.oss.previewListResource', 'true', 'Y', 'admin', '2022-06-19 06:27:41', '', NULL, 'true:开启, false:关闭');

-- --------------------------------------------------------

--
-- 表的结构 `sys_dept`
--

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(500) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

--
-- 转存表中的数据 `sys_dept`
--

INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL),
(109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_dict_data`
--

CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) NOT NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

--
-- 转存表中的数据 `sys_dict_data`
--

INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '性别男'),
(2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '性别女'),
(3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '性别未知'),
(4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '显示菜单'),
(5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '隐藏菜单'),
(6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '正常状态'),
(7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '停用状态'),
(12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统默认是'),
(13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统默认否'),
(14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '通知'),
(15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '公告'),
(16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '正常状态'),
(17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '关闭状态'),
(18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '新增操作'),
(19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '修改操作'),
(20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '删除操作'),
(29, 10, '其他', '10', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '其他操作'),
(21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '授权操作'),
(22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '导出操作'),
(23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '导入操作'),
(24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '强退操作'),
(25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '生成操作'),
(26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '清空操作'),
(27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '正常状态'),
(28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '停用状态');

-- --------------------------------------------------------

--
-- 表的结构 `sys_dict_type`
--

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL COMMENT '字典主键',
  `dict_name` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

--
-- 转存表中的数据 `sys_dict_type`
--

INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '用户性别列表'),
(2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '菜单状态列表'),
(3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统开关列表'),
(6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '系统是否列表'),
(7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '通知类型列表'),
(8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '通知状态列表'),
(9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '操作类型列表'),
(10, '系统状态', 'sys_common_status', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '登录状态列表');

-- --------------------------------------------------------

--
-- 表的结构 `sys_logininfor`
--

CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

--
-- 转存表中的数据 `sys_logininfor`
--

INSERT INTO `sys_logininfor` (`info_id`, `user_name`, `ipaddr`, `login_location`, `browser`, `os`, `status`, `msg`, `login_time`) VALUES
(1, 'admin', '36.36.92.51', '广东省 深圳市', 'Chrome', 'OSX', '0', '登录成功', '2022-06-26 19:20:47');

-- --------------------------------------------------------

--
-- 表的结构 `sys_menu`
--

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query_param` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) NOT NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

--
-- 转存表中的数据 `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query_param`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'SettingOutlined', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:18:32', '系统管理目录'),
(2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2022-06-19 06:27:41', '', NULL, '系统监控目录'),
(3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2022-06-19 06:27:41', '', NULL, '系统工具目录'),
(4, 'RuoYiX官网', 0, 4, 'https://github.com/QNAV/RuoYi-X-Plus', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:08:08', 'RuoYi-X-Plus官网地址'),
(5, '测试菜单', 0, 5, 'demo', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'star', 'admin', '2022-06-19 06:28:02', NULL, NULL, ''),
(100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'UserOutlined', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:21:48', '用户管理菜单'),
(101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'TeamOutlined', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 14:22:29', '角色管理菜单'),
(102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2022-06-19 06:27:41', '', NULL, '菜单管理菜单'),
(103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2022-06-19 06:27:41', '', NULL, '部门管理菜单'),
(104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2022-06-19 06:27:41', '', NULL, '岗位管理菜单'),
(105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2022-06-19 06:27:41', '', NULL, '字典管理菜单'),
(106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2022-06-19 06:27:41', '', NULL, '参数设置菜单'),
(107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2022-06-19 06:27:41', '', NULL, '通知公告菜单'),
(108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2022-06-19 06:27:41', '', NULL, '日志管理菜单'),
(109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2022-06-19 06:27:41', '', NULL, '在线用户菜单'),
(111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2022-06-19 06:27:41', '', NULL, '数据监控菜单'),
(113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2022-06-19 06:27:41', '', NULL, '缓存监控菜单'),
(114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 13:48:25', '缓存列表菜单'),
(115, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-19 13:48:25', '表单构建菜单'),
(116, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2022-06-19 06:27:41', '', NULL, '代码生成菜单'),
(117, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2022-06-19 06:27:41', '', NULL, '系统接口菜单'),
(118, 'Admin监控', 2, 5, 'Admin', 'monitor/admin/index', '', 1, 0, 'C', '0', '0', 'monitor:admin:list', 'dashboard', 'admin', '2022-06-19 06:27:41', '', NULL, 'Admin监控菜单'),
(119, '文件管理', 1, 10, 'oss', 'system/oss/index', '', 1, 0, 'C', '0', '0', 'system:oss:list', 'upload', 'admin', '2022-06-19 06:27:41', '', NULL, '文件管理菜单'),
(120, '任务调度中心', 2, 5, 'XxlJob', 'monitor/xxljob/index', '', 1, 0, 'C', '0', '0', 'monitor:xxljob:list', 'job', 'admin', '2022-06-19 06:27:41', '', NULL, 'Xxl-Job控制台菜单'),
(500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2022-06-19 06:27:41', '', NULL, '操作日志菜单'),
(501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2022-06-19 06:27:41', '', NULL, '登录日志菜单'),
(1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1050, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1500, '测试单表', 5, 1, 'demo', 'demo/demo/index', NULL, 1, 0, 'C', '0', '0', 'demo:demo:list', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '测试单表菜单'),
(1501, '测试单表查询', 1500, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:query', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1502, '测试单表新增', 1500, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:add', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1503, '测试单表修改', 1500, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:edit', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1504, '测试单表删除', 1500, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:remove', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1505, '测试单表导出', 1500, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:demo:export', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1506, '测试树表', 5, 1, 'tree', 'demo/tree/index', NULL, 1, 0, 'C', '0', '0', 'demo:tree:list', '#', 'admin', '2022-06-19 06:28:02', '', NULL, '测试树表菜单'),
(1507, '测试树表查询', 1506, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:query', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1508, '测试树表新增', 1506, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:add', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1509, '测试树表修改', 1506, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:edit', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1510, '测试树表删除', 1506, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:remove', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1511, '测试树表导出', 1506, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'demo:tree:export', '#', 'admin', '2022-06-19 06:28:02', '', NULL, ''),
(1600, '文件查询', 118, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:query', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1601, '文件上传', 118, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:upload', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1602, '文件下载', 118, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:download', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1603, '文件删除', 118, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:remove', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1604, '配置添加', 118, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:add', '#', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(1605, '配置编辑', 118, 6, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:edit', '#', 'admin', '2022-06-19 06:27:41', '', NULL, '');

-- --------------------------------------------------------

--
-- 表的结构 `sys_notice`
--

CREATE TABLE `sys_notice` (
  `notice_id` bigint(20) NOT NULL COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NOT NULL COMMENT '公告内容',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

--
-- 转存表中的数据 `sys_notice`
--

INSERT INTO `sys_notice` (`notice_id`, `notice_title`, `notice_type`, `notice_content`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '温馨提醒：2018-07-01 新版本发布啦', '2', 0xe696b0e78988e69cace58685e5aeb9, '0', 'admin', '2022-06-19 06:27:42', '', NULL, '管理员'),
(2, '维护通知：2018-07-01 系统凌晨维护', '1', 0xe7bbb4e68aa4e58685e5aeb9, '0', 'admin', '2022-06-19 06:27:42', '', NULL, '管理员');

-- --------------------------------------------------------

--
-- 表的结构 `sys_oper_log`
--

CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

--
-- 转存表中的数据 `sys_oper_log`
--

INSERT INTO `sys_oper_log` (`oper_id`, `title`, `business_type`, `method`, `request_method`, `operator_type`, `oper_name`, `dept_name`, `oper_url`, `oper_ip`, `oper_location`, `oper_param`, `json_result`, `status`, `error_msg`, `oper_time`) VALUES
(1, '角色管理', 1, 'com.ruoyi.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', '', '/system/role', '43.128.25.203', ' ', '{\"createBy\":\"admin\",\"createTime\":\"2022-06-26 19:11:13\",\"updateBy\":\"admin\",\"updateTime\":\"2022-06-26 19:11:13\",\"roleId\":5,\"roleName\":\"测试角色\",\"roleKey\":\"test\",\"roleSort\":0,\"dataScope\":null,\"menuCheckStrictly\":null,\"deptCheckStrictly\":null,\"status\":\"0\",\"delFlag\":null,\"remark\":null,\"flag\":false,\"menuIds\":null,\"deptIds\":null,\"admin\":false}', '', 1, '', '2022-06-26 19:11:14'),
(2, '角色管理', 1, 'com.ruoyi.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', '', '/system/role', '43.128.25.203', ' ', '{\"createBy\":\"admin\",\"createTime\":\"2022-06-26 19:13:06\",\"updateBy\":\"admin\",\"updateTime\":\"2022-06-26 19:13:06\",\"roleId\":6,\"roleName\":\"测试角色\",\"roleKey\":\"test\",\"roleSort\":0,\"dataScope\":null,\"menuCheckStrictly\":null,\"deptCheckStrictly\":null,\"status\":\"0\",\"delFlag\":null,\"remark\":null,\"flag\":false,\"menuIds\":null,\"deptIds\":null,\"admin\":false}', '', 1, '', '2022-06-26 19:13:07'),
(3, '角色管理', 1, 'com.ruoyi.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', '', '/system/role', '43.128.25.203', ' ', '{\"createBy\":\"admin\",\"createTime\":\"2022-06-26 19:13:30\",\"updateBy\":\"admin\",\"updateTime\":\"2022-06-26 19:13:30\",\"roleId\":7,\"roleName\":\"测试角色\",\"roleKey\":\"test\",\"roleSort\":0,\"dataScope\":null,\"menuCheckStrictly\":null,\"deptCheckStrictly\":null,\"status\":\"0\",\"delFlag\":null,\"remark\":null,\"flag\":false,\"menuIds\":null,\"deptIds\":null,\"admin\":false}', '', 1, '', '2022-06-26 19:13:30'),
(4, '角色管理', 1, 'com.ruoyi.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', '', '/system/role', '171.110.176.112', '广西 南宁市', '{\"createBy\":\"admin\",\"createTime\":\"2022-06-26 19:18:33\",\"updateBy\":\"admin\",\"updateTime\":\"2022-06-26 19:18:33\",\"roleId\":8,\"roleName\":\"测试角色\",\"roleKey\":\"test\",\"roleSort\":0,\"dataScope\":null,\"menuCheckStrictly\":null,\"deptCheckStrictly\":null,\"status\":\"0\",\"delFlag\":null,\"remark\":null,\"flag\":false,\"menuIds\":null,\"deptIds\":null,\"admin\":false}', '', 1, '', '2022-06-26 19:18:34'),
(5, '角色管理', 1, 'com.ruoyi.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', '', '/system/role', '36.36.92.51', '广东省 深圳市', '{\"createBy\":\"admin\",\"createTime\":\"2022-06-26 19:21:02\",\"updateBy\":\"admin\",\"updateTime\":\"2022-06-26 19:21:02\",\"roleId\":9,\"roleName\":\"dsd\",\"roleKey\":\"2wd\",\"roleSort\":0,\"dataScope\":null,\"menuCheckStrictly\":true,\"deptCheckStrictly\":true,\"status\":\"0\",\"delFlag\":null,\"remark\":null,\"flag\":false,\"menuIds\":[],\"deptIds\":[],\"admin\":false}', '{\"code\":200,\"msg\":\"操作成功\",\"data\":null}', 0, '', '2022-06-26 19:21:02');

-- --------------------------------------------------------

--
-- 表的结构 `sys_oss`
--

CREATE TABLE `sys_oss` (
  `oss_id` bigint(20) NOT NULL COMMENT '对象存储主键',
  `file_name` varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) NOT NULL DEFAULT '' COMMENT '原名',
  `file_suffix` varchar(10) NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(500) NOT NULL COMMENT 'URL地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '上传人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `service` varchar(10) NOT NULL DEFAULT 'minio' COMMENT '服务商'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='OSS对象存储表';

-- --------------------------------------------------------

--
-- 表的结构 `sys_oss_config`
--

CREATE TABLE `sys_oss_config` (
  `oss_config_id` bigint(20) NOT NULL COMMENT '主建',
  `config_key` varchar(255) NOT NULL DEFAULT '' COMMENT '配置key',
  `access_key` varchar(255) NOT NULL DEFAULT '' COMMENT 'accessKey',
  `secret_key` varchar(255) NOT NULL DEFAULT '' COMMENT '秘钥',
  `bucket_name` varchar(255) NOT NULL DEFAULT '' COMMENT '桶名称',
  `prefix` varchar(255) DEFAULT '' COMMENT '前缀',
  `endpoint` varchar(255) NOT NULL DEFAULT '' COMMENT '访问站点',
  `domain` varchar(255) DEFAULT '' COMMENT '自定义域名',
  `is_https` char(1) DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
  `region` varchar(255) DEFAULT '' COMMENT '域',
  `status` char(1) DEFAULT '1' COMMENT '状态（0=正常,1=停用）',
  `ext1` varchar(255) DEFAULT '' COMMENT '扩展字段',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对象存储配置表';

--
-- 转存表中的数据 `sys_oss_config`
--

INSERT INTO `sys_oss_config` (`oss_config_id`, `config_key`, `access_key`, `secret_key`, `bucket_name`, `prefix`, `endpoint`, `domain`, `is_https`, `region`, `status`, `ext1`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, 'minio', 'ruoyi', 'ruoyi123', 'ruoyi', '', '127.0.0.1:9000', '', 'N', '', '0', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL),
(2, 'qiniu', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 's3-cn-north-1.qiniucs.com', '', 'N', '', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL),
(3, 'aliyun', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 'oss-cn-beijing.aliyuncs.com', '', 'N', '', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL),
(4, 'qcloud', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi-1250000000', '', 'cos.ap-beijing.myqcloud.com', '', 'N', 'ap-beijing', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL),
(5, 'image', 'ruoyi', 'ruoyi123', 'ruoyi', 'image', '127.0.0.1:9000', '', 'N', '', '1', '', 'admin', '2022-06-19 06:27:42', 'admin', '2022-06-19 06:27:42', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_post`
--

CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息表';

--
-- 转存表中的数据 `sys_post`
--

INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, 'ceo', '董事长', 1, '0', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(2, 'se', '项目经理', 2, '0', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(3, 'hr', '人力资源', 3, '0', 'admin', '2022-06-19 06:27:41', '', NULL, ''),
(4, 'user', '普通员工', 4, '0', 'admin', '2022-06-19 06:27:41', '', NULL, '');

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

--
-- 转存表中的数据 `sys_role`
--

INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '超级管理员'),
(2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2022-06-19 06:27:41', '', NULL, '普通角色'),
(3, '本部门及以下', 'test1', 3, '4', 1, 1, '0', '0', 'admin', '2022-06-19 06:28:02', 'admin', NULL, NULL),
(4, '仅本人', 'test2', 4, '5', 1, 1, '0', '0', 'admin', '2022-06-19 06:28:02', 'admin', NULL, NULL),
(9, 'dsd', '2wd', 0, '1', 1, 1, '0', '0', 'admin', '2022-06-26 19:21:02', 'admin', '2022-06-26 19:21:02', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_role_dept`
--

CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

--
-- 转存表中的数据 `sys_role_dept`
--

INSERT INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES
(2, 100),
(2, 101),
(2, 105);

-- --------------------------------------------------------

--
-- 表的结构 `sys_role_menu`
--

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

--
-- 转存表中的数据 `sys_role_menu`
--

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 100),
(2, 101),
(2, 102),
(2, 103),
(2, 104),
(2, 105),
(2, 106),
(2, 107),
(2, 108),
(2, 109),
(2, 110),
(2, 111),
(2, 112),
(2, 113),
(2, 114),
(2, 115),
(2, 116),
(2, 500),
(2, 501),
(2, 1000),
(2, 1001),
(2, 1002),
(2, 1003),
(2, 1004),
(2, 1005),
(2, 1006),
(2, 1007),
(2, 1008),
(2, 1009),
(2, 1010),
(2, 1011),
(2, 1012),
(2, 1013),
(2, 1014),
(2, 1015),
(2, 1016),
(2, 1017),
(2, 1018),
(2, 1019),
(2, 1020),
(2, 1021),
(2, 1022),
(2, 1023),
(2, 1024),
(2, 1025),
(2, 1026),
(2, 1027),
(2, 1028),
(2, 1029),
(2, 1030),
(2, 1031),
(2, 1032),
(2, 1033),
(2, 1034),
(2, 1035),
(2, 1036),
(2, 1037),
(2, 1038),
(2, 1039),
(2, 1040),
(2, 1041),
(2, 1042),
(2, 1043),
(2, 1044),
(2, 1045),
(2, 1046),
(2, 1047),
(2, 1048),
(2, 1050),
(2, 1055),
(2, 1056),
(2, 1057),
(2, 1058),
(2, 1059),
(2, 1060),
(3, 1),
(3, 5),
(3, 100),
(3, 101),
(3, 102),
(3, 103),
(3, 104),
(3, 105),
(3, 106),
(3, 107),
(3, 108),
(3, 500),
(3, 501),
(3, 1001),
(3, 1002),
(3, 1003),
(3, 1004),
(3, 1005),
(3, 1006),
(3, 1007),
(3, 1008),
(3, 1009),
(3, 1010),
(3, 1011),
(3, 1012),
(3, 1013),
(3, 1014),
(3, 1015),
(3, 1016),
(3, 1017),
(3, 1018),
(3, 1019),
(3, 1020),
(3, 1021),
(3, 1022),
(3, 1023),
(3, 1024),
(3, 1025),
(3, 1026),
(3, 1027),
(3, 1028),
(3, 1029),
(3, 1030),
(3, 1031),
(3, 1032),
(3, 1033),
(3, 1034),
(3, 1035),
(3, 1036),
(3, 1037),
(3, 1038),
(3, 1039),
(3, 1040),
(3, 1041),
(3, 1042),
(3, 1043),
(3, 1044),
(3, 1045),
(3, 1500),
(3, 1501),
(3, 1502),
(3, 1503),
(3, 1504),
(3, 1505),
(3, 1506),
(3, 1507),
(3, 1508),
(3, 1509),
(3, 1510),
(3, 1511),
(4, 5),
(4, 1500),
(4, 1501),
(4, 1502),
(4, 1503),
(4, 1504),
(4, 1505),
(4, 1506),
(4, 1507),
(4, 1508),
(4, 1509),
(4, 1510),
(4, 1511);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user`
--

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(10) DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phone_number`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, 103, 'admin', '疯狂的狮子Li', 'sys_user', 'crazyLionLi@163.com', '15888888888', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '36.36.92.51', '2022-06-26 19:20:47', 'admin', '2022-06-19 06:27:41', 'admin', '2022-06-26 19:20:47', '管理员'),
(2, 105, 'lionli', '疯狂的狮子Li', 'sys_user', 'crazyLionLi@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2022-06-19 06:27:41', 'admin', '2022-06-19 06:27:41', '', NULL, '测试员'),
(3, 108, 'test', '本部门及以下 密码666666', 'sys_user', '', '', '0', '', '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2022-06-19 06:28:02', 'admin', '2022-06-19 06:28:02', 'test', '2022-06-19 06:28:02', NULL),
(4, 102, 'test1', '仅本人 密码666666', 'sys_user', '', '', '0', '', '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2022-06-19 06:28:02', 'admin', '2022-06-19 06:28:02', 'test1', '2022-06-19 06:28:02', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_post`
--

CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联表';

--
-- 转存表中的数据 `sys_user_post`
--

INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_role`
--

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

--
-- 转存表中的数据 `sys_user_role`
--

INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- 转储表的索引
--

--
-- 表的索引 `gen_table`
--
ALTER TABLE `gen_table`
  ADD PRIMARY KEY (`table_id`);

--
-- 表的索引 `gen_table_column`
--
ALTER TABLE `gen_table_column`
  ADD PRIMARY KEY (`column_id`);

--
-- 表的索引 `sys_config`
--
ALTER TABLE `sys_config`
  ADD PRIMARY KEY (`config_id`);

--
-- 表的索引 `sys_dept`
--
ALTER TABLE `sys_dept`
  ADD PRIMARY KEY (`dept_id`);

--
-- 表的索引 `sys_dict_data`
--
ALTER TABLE `sys_dict_data`
  ADD PRIMARY KEY (`dict_code`);

--
-- 表的索引 `sys_dict_type`
--
ALTER TABLE `sys_dict_type`
  ADD PRIMARY KEY (`dict_id`),
  ADD UNIQUE KEY `dict_type` (`dict_type`);

--
-- 表的索引 `sys_logininfor`
--
ALTER TABLE `sys_logininfor`
  ADD PRIMARY KEY (`info_id`);

--
-- 表的索引 `sys_menu`
--
ALTER TABLE `sys_menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- 表的索引 `sys_notice`
--
ALTER TABLE `sys_notice`
  ADD PRIMARY KEY (`notice_id`);

--
-- 表的索引 `sys_oper_log`
--
ALTER TABLE `sys_oper_log`
  ADD PRIMARY KEY (`oper_id`);

--
-- 表的索引 `sys_oss`
--
ALTER TABLE `sys_oss`
  ADD PRIMARY KEY (`oss_id`);

--
-- 表的索引 `sys_oss_config`
--
ALTER TABLE `sys_oss_config`
  ADD PRIMARY KEY (`oss_config_id`);

--
-- 表的索引 `sys_post`
--
ALTER TABLE `sys_post`
  ADD PRIMARY KEY (`post_id`);

--
-- 表的索引 `sys_role`
--
ALTER TABLE `sys_role`
  ADD PRIMARY KEY (`role_id`);

--
-- 表的索引 `sys_role_dept`
--
ALTER TABLE `sys_role_dept`
  ADD PRIMARY KEY (`role_id`,`dept_id`);

--
-- 表的索引 `sys_role_menu`
--
ALTER TABLE `sys_role_menu`
  ADD PRIMARY KEY (`role_id`,`menu_id`);

--
-- 表的索引 `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`user_id`);

--
-- 表的索引 `sys_user_post`
--
ALTER TABLE `sys_user_post`
  ADD PRIMARY KEY (`user_id`,`post_id`);

--
-- 表的索引 `sys_user_role`
--
ALTER TABLE `sys_user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `gen_table`
--
ALTER TABLE `gen_table`
  MODIFY `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号';

--
-- 使用表AUTO_INCREMENT `gen_table_column`
--
ALTER TABLE `gen_table_column`
  MODIFY `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号';

--
-- 使用表AUTO_INCREMENT `sys_config`
--
ALTER TABLE `sys_config`
  MODIFY `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键', AUTO_INCREMENT=12;

--
-- 使用表AUTO_INCREMENT `sys_dept`
--
ALTER TABLE `sys_dept`
  MODIFY `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id', AUTO_INCREMENT=110;

--
-- 使用表AUTO_INCREMENT `sys_dict_data`
--
ALTER TABLE `sys_dict_data`
  MODIFY `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码', AUTO_INCREMENT=29;

--
-- 使用表AUTO_INCREMENT `sys_dict_type`
--
ALTER TABLE `sys_dict_type`
  MODIFY `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键', AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `sys_logininfor`
--
ALTER TABLE `sys_logininfor`
  MODIFY `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID', AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `sys_menu`
--
ALTER TABLE `sys_menu`
  MODIFY `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID', AUTO_INCREMENT=1606;

--
-- 使用表AUTO_INCREMENT `sys_notice`
--
ALTER TABLE `sys_notice`
  MODIFY `notice_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公告ID', AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `sys_oper_log`
--
ALTER TABLE `sys_oper_log`
  MODIFY `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键', AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `sys_oss`
--
ALTER TABLE `sys_oss`
  MODIFY `oss_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '对象存储主键';

--
-- 使用表AUTO_INCREMENT `sys_oss_config`
--
ALTER TABLE `sys_oss_config`
  MODIFY `oss_config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主建', AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `sys_post`
--
ALTER TABLE `sys_post`
  MODIFY `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID', AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `sys_role`
--
ALTER TABLE `sys_role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID', AUTO_INCREMENT=10;

--
-- 使用表AUTO_INCREMENT `sys_user`
--
ALTER TABLE `sys_user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID', AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `xxl_job_group`
--
ALTER TABLE `xxl_job_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `xxl_job_info`
--
ALTER TABLE `xxl_job_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `xxl_job_log`
--
ALTER TABLE `xxl_job_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `xxl_job_logglue`
--
ALTER TABLE `xxl_job_logglue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `xxl_job_log_report`
--
ALTER TABLE `xxl_job_log_report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `xxl_job_registry`
--
ALTER TABLE `xxl_job_registry`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `xxl_job_user`
--
ALTER TABLE `xxl_job_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
