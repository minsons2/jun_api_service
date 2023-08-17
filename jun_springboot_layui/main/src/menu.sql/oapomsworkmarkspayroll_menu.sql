-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457885537530986497', '工资审核发放' ,'54', '/index/oaPomsWorkmarksPayroll','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457885537530986501', '1457885537530986497', '列表' , 'oaPomsWorkmarksPayroll/listByPage','oaPomsWorkmarksPayroll:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885537530986498', '1457885537530986497', '新增' , 'oaPomsWorkmarksPayroll/add','oaPomsWorkmarksPayroll:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885537530986499', '1457885537530986497', '修改' , 'oaPomsWorkmarksPayroll/update','oaPomsWorkmarksPayroll:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885537530986500', '1457885537530986497', '删除' , 'oaPomsWorkmarksPayroll/delete','oaPomsWorkmarksPayroll:delete', '3',1, 1,now(),now());
