-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1453204119966060545', '岗位信息表' ,'54', '/index/sysPost','_self', '2', '10',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1453204119966060549', '1453204119966060545', '列表' , 'sysPost/listByPage','sysPost:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1453204119966060546', '1453204119966060545', '新增' , 'sysPost/add','sysPost:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1453204119966060547', '1453204119966060545', '修改' , 'sysPost/update','sysPost:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1453204119966060548', '1453204119966060545', '删除' , 'sysPost/delete','sysPost:delete', '3',1, 1,now(),now());
