-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372762651848706', '离职' ,'54', '/index/hrUserDimission','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372762651848710', '1458372762651848706', '列表' , 'hrUserDimission/listByPage','hrUserDimission:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372762651848707', '1458372762651848706', '新增' , 'hrUserDimission/add','hrUserDimission:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372762651848708', '1458372762651848706', '修改' , 'hrUserDimission/update','hrUserDimission:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372762651848709', '1458372762651848706', '删除' , 'hrUserDimission/delete','hrUserDimission:delete', '3',1, 1,now(),now());
