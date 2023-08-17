-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1466425863574257666', '考核记录' ,'54', '/index/hrAssessmentUserRecord','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1466425863574257670', '1466425863574257666', '列表' , 'hrAssessmentUserRecord/listByPage','hrAssessmentUserRecord:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466425863574257667', '1466425863574257666', '新增' , 'hrAssessmentUserRecord/add','hrAssessmentUserRecord:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466425863574257668', '1466425863574257666', '修改' , 'hrAssessmentUserRecord/update','hrAssessmentUserRecord:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466425863574257669', '1466425863574257666', '删除' , 'hrAssessmentUserRecord/delete','hrAssessmentUserRecord:delete', '3',1, 1,now(),now());
    
  INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('146642586357425766911', '1466425863574257666', '提交' , 'hrAssessmentUserRecord/submit','hrAssessmentUserRecord:submit', '3',1, 1,now(),now());
