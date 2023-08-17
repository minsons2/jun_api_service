-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372764526702593', 'Offer发放' ,'54', '/index/hrUserOffer','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372764526702597', '1458372764526702593', '列表' , 'hrUserOffer/listByPage','hrUserOffer:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372764526702594', '1458372764526702593', '新增' , 'hrUserOffer/add','hrUserOffer:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372764526702595', '1458372764526702593', '修改' , 'hrUserOffer/update','hrUserOffer:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372764526702596', '1458372764526702593', '删除' , 'hrUserOffer/delete','hrUserOffer:delete', '3',1, 1,now(),now());
