package com.jun.plugin.modules.system.service.impl;

import com.jun.plugin.modules.system.entity.SysDept;
import com.jun.plugin.modules.system.entity.SysUser;
import com.jun.plugin.modules.system.service.DeptService;
import com.jun.plugin.modules.system.service.HomeService;
import com.jun.plugin.modules.system.service.PermissionService;
import com.jun.plugin.modules.system.service.UserService;
import com.jun.plugin.modules.system.vo.resp.HomeRespVO;
import com.jun.plugin.modules.system.vo.resp.PermissionRespNode;
import com.jun.plugin.modules.system.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {


        SysUser sysUser = userService.getById(userId);
        UserInfoRespVO vo = new UserInfoRespVO();

        if (sysUser != null) {
            BeanUtils.copyProperties(sysUser, vo);
            SysDept sysDept = deptService.getById(sysUser.getDeptId());
            if (sysDept != null) {
                vo.setDeptId(sysDept.getId());
                vo.setDeptName(sysDept.getName());
            }
        }

        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO = new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }
}
