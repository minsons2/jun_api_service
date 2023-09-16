-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457605699473653762', '公告通知' ,'54', '/index/oaNotesInfo','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457605699473653766', '1457605699473653762', '列表' , 'oaNotesInfo/listByPage','oaNotesInfo:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457605699473653763', '1457605699473653762', '新增' , 'oaNotesInfo/add','oaNotesInfo:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457605699473653764', '1457605699473653762', '修改' , 'oaNotesInfo/update','oaNotesInfo:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457605699473653765', '1457605699473653762', '删除' , 'oaNotesInfo/delete','oaNotesInfo:delete', '3',1, 1,now(),now());
