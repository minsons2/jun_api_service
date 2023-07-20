-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('1', '代码生成详细配置', 'generator/sysCodeGenerateConfig', NULL, '1', 'config', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'generator:sysCodeGenerateConfig:list,generator:sysCodeGenerateConfig:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'generator:sysCodeGenerateConfig:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'generator:sysCodeGenerateConfig:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'generator:sysCodeGenerateConfig:delete', '2', null, '6';

