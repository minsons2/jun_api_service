-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1458372763524263937', '录用审批' ,'54', '/index/hrUserHire','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1458372763524263941', '1458372763524263937', '列表' , 'hrUserHire/listByPage','hrUserHire:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763524263938', '1458372763524263937', '新增' , 'hrUserHire/add','hrUserHire:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763524263939', '1458372763524263937', '修改' , 'hrUserHire/update','hrUserHire:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1458372763524263940', '1458372763524263937', '删除' , 'hrUserHire/delete','hrUserHire:delete', '3',1, 1,now(),now());

INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('145837276352426394011', '1458372763524263937', '提交' , 'hrUserHire/submit','hrUserHire:submit', '3',1, 1,now(),now());