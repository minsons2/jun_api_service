-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1457885536851509249', '外出信息' ,'54', '/index/oaPomsWorkmarksOutsite','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1457885536851509253', '1457885536851509249', '列表' , 'oaPomsWorkmarksOutsite/listByPage','oaPomsWorkmarksOutsite:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885536851509250', '1457885536851509249', '新增' , 'oaPomsWorkmarksOutsite/add','oaPomsWorkmarksOutsite:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885536851509251', '1457885536851509249', '修改' , 'oaPomsWorkmarksOutsite/update','oaPomsWorkmarksOutsite:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1457885536851509252', '1457885536851509249', '删除' , 'oaPomsWorkmarksOutsite/delete','oaPomsWorkmarksOutsite:delete', '3',1, 1,now(),now());

INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('145788553685150925211', '1457885536851509249', '提交' , 'oaPomsWorkmarksOutsite/submit','oaPomsWorkmarksOutsite:submit', '3',1, 1,now(),now());