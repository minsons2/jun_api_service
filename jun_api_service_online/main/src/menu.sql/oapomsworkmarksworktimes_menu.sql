-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457885538210463745', '考勤记录' ,'54', '/index/oaPomsWorkmarksWorktimes','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457885538210463749', '1457885538210463745', '列表' , 'oaPomsWorkmarksWorktimes/listByPage','oaPomsWorkmarksWorktimes:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885538210463746', '1457885538210463745', '新增' , 'oaPomsWorkmarksWorktimes/add','oaPomsWorkmarksWorktimes:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885538210463747', '1457885538210463745', '修改' , 'oaPomsWorkmarksWorktimes/update','oaPomsWorkmarksWorktimes:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885538210463748', '1457885538210463745', '删除' , 'oaPomsWorkmarksWorktimes/delete','oaPomsWorkmarksWorktimes:delete', '3',1, 1,now(),now());
