-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372764920967170', '面试候选人' ,'54', '/index/hrUserResume','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372764920967174', '1458372764920967170', '列表' , 'hrUserResume/listByPage','hrUserResume:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372764920967171', '1458372764920967170', '新增' , 'hrUserResume/add','hrUserResume:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372764920967172', '1458372764920967170', '修改' , 'hrUserResume/update','hrUserResume:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372764920967173', '1458372764920967170', '删除' , 'hrUserResume/delete','hrUserResume:delete', '3',1, 1,now(),now());
