-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372763142582273', '入职报道' ,'54', '/index/hrUserEntryReported','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372763142582277', '1458372763142582273', '列表' , 'hrUserEntryReported/listByPage','hrUserEntryReported:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763142582274', '1458372763142582273', '新增' , 'hrUserEntryReported/add','hrUserEntryReported:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763142582275', '1458372763142582273', '修改' , 'hrUserEntryReported/update','hrUserEntryReported:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763142582276', '1458372763142582273', '删除' , 'hrUserEntryReported/delete','hrUserEntryReported:delete', '3',1, 1,now(),now());
