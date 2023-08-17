-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372761905262594', '转正' ,'54', '/index/hrUserBecomeMember','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372761905262598', '1458372761905262594', '列表' , 'hrUserBecomeMember/listByPage','hrUserBecomeMember:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372761905262595', '1458372761905262594', '新增' , 'hrUserBecomeMember/add','hrUserBecomeMember:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372761905262596', '1458372761905262594', '修改' , 'hrUserBecomeMember/update','hrUserBecomeMember:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372761905262597', '1458372761905262594', '删除' , 'hrUserBecomeMember/delete','hrUserBecomeMember:delete', '3',1, 1,now(),now());
