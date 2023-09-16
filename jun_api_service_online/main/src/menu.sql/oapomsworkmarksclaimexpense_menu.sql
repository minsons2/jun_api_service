-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458456964757184513', '费用报销' ,'54', '/index/oaPomsWorkmarksClaimExpense','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458456964757184517', '1458456964757184513', '列表' , 'oaPomsWorkmarksClaimExpense/listByPage','oaPomsWorkmarksClaimExpense:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458456964757184514', '1458456964757184513', '新增' , 'oaPomsWorkmarksClaimExpense/add','oaPomsWorkmarksClaimExpense:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458456964757184515', '1458456964757184513', '修改' , 'oaPomsWorkmarksClaimExpense/update','oaPomsWorkmarksClaimExpense:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458456964757184516', '1458456964757184513', '删除' , 'oaPomsWorkmarksClaimExpense/delete','oaPomsWorkmarksClaimExpense:delete', '3',1, 1,now(),now());
