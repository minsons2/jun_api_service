-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1450740793050775553', '项目开票' ,'54', '/index/pjProjectInvoice','_self', '2', '10',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1450740793050775557', '1450740793050775553', '列表' , 'pjProjectInvoice/listByPage','pjProjectInvoice:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1450740793050775554', '1450740793050775553', '新增' , 'pjProjectInvoice/add','pjProjectInvoice:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1450740793050775555', '1450740793050775553', '修改' , 'pjProjectInvoice/update','pjProjectInvoice:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1450740793050775556', '1450740793050775553', '删除' , 'pjProjectInvoice/delete','pjProjectInvoice:delete', '3',1, 1,now(),now());


INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('145074079305077555611', '1450740793050775553', '提交' , 'pjProjectInvoice/submit','pjProjectInvoice:submit', '3',1, 1,now(),now());