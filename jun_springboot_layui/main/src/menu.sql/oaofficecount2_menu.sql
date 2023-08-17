-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1464431067704037377', '办公用品申领申购' ,'54', '/index/oaOfficeCount2','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1464431067704037381', '1464431067704037377', '列表' , 'oaOfficeCount2/listByPage','oaOfficeCount2:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1464431067704037378', '1464431067704037377', '新增' , 'oaOfficeCount2/add','oaOfficeCount2:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1464431067704037379', '1464431067704037377', '修改' , 'oaOfficeCount2/update','oaOfficeCount2:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1464431067704037380', '1464431067704037377', '删除' , 'oaOfficeCount2/delete','oaOfficeCount2:delete', '3',1, 1,now(),now());
    
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('146443106770403738011', '1464431067704037377', '提交' , 'oaOfficeCount2/submit','oaOfficeCount2:submit', '3',1, 1,now(),now());
