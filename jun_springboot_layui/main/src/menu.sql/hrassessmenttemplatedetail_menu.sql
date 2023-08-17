-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1466369581395247105', '考核模板明细' ,'54', '/index/hrAssessmentTemplateDetail','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1466369581395247109', '1466369581395247105', '列表' , 'hrAssessmentTemplateDetail/listByPage','hrAssessmentTemplateDetail:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466369581395247106', '1466369581395247105', '新增' , 'hrAssessmentTemplateDetail/add','hrAssessmentTemplateDetail:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466369581395247107', '1466369581395247105', '修改' , 'hrAssessmentTemplateDetail/update','hrAssessmentTemplateDetail:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466369581395247108', '1466369581395247105', '删除' , 'hrAssessmentTemplateDetail/delete','hrAssessmentTemplateDetail:delete', '3',1, 1,now(),now());
    
-- INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
--     VALUES ('146636958139524710811', '1466369581395247105', '提交' , 'hrAssessmentTemplateDetail/submit','hrAssessmentTemplateDetail:submit', '3',1, 1,now(),now());
