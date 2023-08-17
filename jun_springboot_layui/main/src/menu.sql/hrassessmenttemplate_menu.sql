-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1466075952592969729', '考核模板' ,'54', '/index/hrAssessmentTemplate','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1466075952592969733', '1466075952592969729', '列表' , 'hrAssessmentTemplate/listByPage','hrAssessmentTemplate:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466075952592969730', '1466075952592969729', '新增' , 'hrAssessmentTemplate/add','hrAssessmentTemplate:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466075952592969731', '1466075952592969729', '修改' , 'hrAssessmentTemplate/update','hrAssessmentTemplate:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466075952592969732', '1466075952592969729', '删除' , 'hrAssessmentTemplate/delete','hrAssessmentTemplate:delete', '3',1, 1,now(),now());
    
-- INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
 --    VALUES ('146607595259296973211', '1466075952592969729', '提交' , 'hrAssessmentTemplate/submit','hrAssessmentTemplate:submit', '3',1, 1,now(),now());
