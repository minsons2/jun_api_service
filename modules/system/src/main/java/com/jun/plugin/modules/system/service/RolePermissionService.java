package com.jun.plugin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.modules.system.vo.req.RolePermissionOperationReqVO;
import com.jun.plugin.modules.system.entity.SysRolePermission;

/**
 * 角色权限关联
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
public interface RolePermissionService extends IService<SysRolePermission> {

    /**
     * 角色绑定权限
     *
     * @param vo vo
     */
    void addRolePermission(RolePermissionOperationReqVO vo);
}
