-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1454797971643949058', '项目进度与任务(WBS)' ,'54', '/index/pjProjectProdessTask','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1454797971643949062', '1454797971643949058', '列表' , 'pjProjectProdessTask/listByPage','pjProjectProdessTask:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1454797971643949059', '1454797971643949058', '新增' , 'pjProjectProdessTask/add','pjProjectProdessTask:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1454797971643949060', '1454797971643949058', '修改' , 'pjProjectProdessTask/update','pjProjectProdessTask:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1454797971643949061', '1454797971643949058', '删除' , 'pjProjectProdessTask/delete','pjProjectProdessTask:delete', '3',1, 1,now(),now());
