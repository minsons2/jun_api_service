package com.jun.plugin.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.system.entity.SysUser;

/**
 * 用户 Mapper
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	@Select("select * from sys_user where username=#{username}    ")
	SysUser getUserByName(@Param("username") String username);
	
	@Select(" SELECT * from sys_user u \r\n"
			+ "INNER JOIN sys_user_role ur on u.id = ur.user_id\r\n"
			+ "INNER JOIN sys_role r on ur.role_id = r.id\r\n"
			+ "where r.id in (\r\n"
			+ "SELECT r1.id from sys_role r1 where r.name like '%#{roleName}%' )   ")
	List<SysUser> getUserByRoleName(@Param("roleName") String roleName);
}