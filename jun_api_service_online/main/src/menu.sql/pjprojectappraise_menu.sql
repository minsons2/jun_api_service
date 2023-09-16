-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1455010761078661121', '项目总结及评价' ,'54', '/index/pjProjectAppraise','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1455010761078661125', '1455010761078661121', '列表' , 'pjProjectAppraise/listByPage','pjProjectAppraise:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1455010761078661122', '1455010761078661121', '新增' , 'pjProjectAppraise/add','pjProjectAppraise:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1455010761078661123', '1455010761078661121', '修改' , 'pjProjectAppraise/update','pjProjectAppraise:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1455010761078661124', '1455010761078661121', '删除' , 'pjProjectAppraise/delete','pjProjectAppraise:delete', '3',1, 1,now(),now());
