-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457885534263623681', '员工请假' ,'54', '/index/oaPomsWorkmarksLeave','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457885534263623685', '1457885534263623681', '列表' , 'oaPomsWorkmarksLeave/listByPage','oaPomsWorkmarksLeave:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885534263623682', '1457885534263623681', '新增' , 'oaPomsWorkmarksLeave/add','oaPomsWorkmarksLeave:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885534263623683', '1457885534263623681', '修改' , 'oaPomsWorkmarksLeave/update','oaPomsWorkmarksLeave:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885534263623684', '1457885534263623681', '删除' , 'oaPomsWorkmarksLeave/delete','oaPomsWorkmarksLeave:delete', '3',1, 1,now(),now());
	
	INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('14578855342636236841', '1457885534263623681', '提交' , 'oaPomsWorkmarksLeave/submit','oaPomsWorkmarksLeave:submit', '3',1, 1,now(),now());
