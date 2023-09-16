-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1455374497492750338', '用户信息' ,'54', '/index/sysUser2','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1455374497492750342', '1455374497492750338', '列表' , 'sysUser2/listByPage','sysUser2:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1455374497492750339', '1455374497492750338', '新增' , 'sysUser2/add','sysUser2:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1455374497492750340', '1455374497492750338', '修改' , 'sysUser2/update','sysUser2:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1455374497492750341', '1455374497492750338', '删除' , 'sysUser2/delete','sysUser2:delete', '3',1, 1,now(),now());
