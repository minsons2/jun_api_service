-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372763897556994', '面试汇总' ,'54', '/index/hrUserInterview','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372763897556998', '1458372763897556994', '列表' , 'hrUserInterview/listByPage','hrUserInterview:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763897556995', '1458372763897556994', '新增' , 'hrUserInterview/add','hrUserInterview:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763897556996', '1458372763897556994', '修改' , 'hrUserInterview/update','hrUserInterview:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763897556997', '1458372763897556994', '删除' , 'hrUserInterview/delete','hrUserInterview:delete', '3',1, 1,now(),now());
