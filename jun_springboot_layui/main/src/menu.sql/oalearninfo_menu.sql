-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457605699029057537', '培训学习' ,'54', '/index/oaLearnInfo','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457605699029057541', '1457605699029057537', '列表' , 'oaLearnInfo/listByPage','oaLearnInfo:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457605699029057538', '1457605699029057537', '新增' , 'oaLearnInfo/add','oaLearnInfo:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457605699029057539', '1457605699029057537', '修改' , 'oaLearnInfo/update','oaLearnInfo:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457605699029057540', '1457605699029057537', '删除' , 'oaLearnInfo/delete','oaLearnInfo:delete', '3',1, 1,now(),now());
