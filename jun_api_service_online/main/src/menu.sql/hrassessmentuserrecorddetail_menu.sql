-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status,create_time,update_time)
    VALUES ('1466425861913313281', '考核记录明细' ,'54', '/index/hrAssessmentUserRecordDetail','_self', '2', '910',1, 1,now(),now());
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status,create_time,update_time)
    VALUES ('1466425861913313285', '1466425861913313281', '列表' , 'hrAssessmentUserRecordDetail/listByPage','hrAssessmentUserRecordDetail:list', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466425861913313282', '1466425861913313281', '新增' , 'hrAssessmentUserRecordDetail/add','hrAssessmentUserRecordDetail:add', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466425861913313283', '1466425861913313281', '修改' , 'hrAssessmentUserRecordDetail/update','hrAssessmentUserRecordDetail:update', '3',1, 1,now(),now());
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
    VALUES ('1466425861913313284', '1466425861913313281', '删除' , 'hrAssessmentUserRecordDetail/delete','hrAssessmentUserRecordDetail:delete', '3',1, 1,now(),now());
    
-- INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status,create_time,update_time)
 --   VALUES ('146642586191331328411', '1466425861913313281', '提交' , 'hrAssessmentUserRecordDetail/submit','hrAssessmentUserRecordDetail:submit', '3',1, 1,now(),now());
