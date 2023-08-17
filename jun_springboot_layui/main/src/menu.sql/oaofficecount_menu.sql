-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457530458131046401', '办公用品申领申购' ,'54', '/index/oaOfficeCount','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457530458131046405', '1457530458131046401', '列表' , 'oaOfficeCount/listByPage','oaOfficeCount:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457530458131046402', '1457530458131046401', '新增' , 'oaOfficeCount/add','oaOfficeCount:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457530458131046403', '1457530458131046401', '修改' , 'oaOfficeCount/update','oaOfficeCount:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457530458131046404', '1457530458131046401', '删除' , 'oaOfficeCount/delete','oaOfficeCount:delete', '3',1, 1,now(),now());
