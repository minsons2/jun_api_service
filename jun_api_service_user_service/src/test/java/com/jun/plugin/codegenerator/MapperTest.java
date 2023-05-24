package com.jun.plugin.codegenerator;

import java.io.IOException;
import java.util.List;

import com.jun.plugin.module.utils.PinYinUtil;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.system.entity.SysDept;
import com.jun.plugin.system.entity.SysUser;
import com.jun.plugin.system.mapper.SysDeptMapper;
import com.jun.plugin.system.mapper.SysUserMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MapperTest {

//	@Autowired
//	private BizCommonMapper bizCommonMapper;

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysDeptMapper sysDeptMapper;

	@SuppressWarnings("rawtypes")
	@Test
	public void userInsertTest() throws IOException {
//    	List<Map> m = bizCommonMapper.getProjectMenberList("XM2021-ZXSJ01", "ref_member_name");
/*
		String[] user = new String[] { "漆兰英","郑怀忠","汪伟","余梅","鲍建明","郑霞","刘建勋","纪爱君","宋坤","罗凯","梁倩","雷丽莎","艾荷英","蔡紫薇","孙佳颖","吴亚娟","程凡","陈璐","降秀兰","吴思懿","李诗帆","金波","雷益玲","林萍","王钶","高双华","黄银平","雷婷","王璇","余佩","吴量","余忠","方曼丽","颜胜强","宋杰","郑军","叶靖","邱静","殷杰","谢枫","方熙希","陈巍升","王丹","孙礼文","陶慧","高红芳","林小平","王晓林","刘群" };
		String[] dept = new String[] { "董事长","总经理","付总经理","付总经理","付总经理","绩效评价部","管理咨询部","行政部","造价部","评估部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","管理咨询部","绩效评价部","绩效评价部","绩效评价部","绩效评价部","审计部","审计部","审计部","审计部","审计部","审计部","审计部","审计部","审计部","审计部","审计部","工程造价部","工程造价部","评估部","评估部","评估部","评估部","评估部","行政部","行政部","行政部","行政部","行政部","行政部","行政部" };
		LambdaQueryWrapper<SysUser> queryWrapper0 = Wrappers.lambdaQuery();
		List<SysUser> all = sysUserMapper.selectList(queryWrapper0);
		for(SysUser sysUser : all){
				if(!JSON.toJSONString(user).contains(sysUser.getRealName())){
					System.err.println(" delete user is   "+sysUser.getRealName());
					sysUserMapper.deleteById(sysUser.getId());
				}
		}
		System.err.println(all.size());
		System.err.println(user.length);
		for (int i =0;i< user.length;i++) {
			LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
			queryWrapper.eq(SysUser::getRealName, user[i]);
			//queryWrapper.like(SysUser::getRealName, vo.getRealName());
			List<SysUser> u = sysUserMapper.selectList(queryWrapper);
			if(CollectionUtils.isEmpty(u)) {
				System.err.println(user[i]+" is null  add user 00000000000000000                             ----------------------------");
				insertUser(user[i]);
			}else {
//				System.out.println(JSON.toJSONString(u));
			}
		}*/
//    	List<Map> m2 = bizCommonMapper.getDictDetailList("dict_yes_no");
//    	int i = bizCommonMapper.checkRecordExists("pj_project_member", "ref_project_code", "QQQQQQQQQQ");
//    	int i = bizCommonMapper.checkRecordExistsV2("pj_project_member", "ref_project_code", "QQQQQQQQQQ", "ref_member_name", "沈艳");
//    	List<Map> m3 = bizCommonMapper.getAllProjectDetailList();
	}

	private void insertUser(String user) {
		SysUser entity = new SysUser();
		entity.setRealName(user);
		entity.setUsername(PinYinUtil.getPinyin(user));
		entity.setSalt("324ce32d86224b00a02b");
		entity.setPassword("52FE5rgczVzOYXed3dag7A==");
		entity.setPhone("");
		entity.setDeptId("1");
		entity.setNickName(user);
		entity.setDeleted(1);
		sysUserMapper.insert(entity);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void mapperTest() throws IOException {

//    	List<Map> m = bizCommonMapper.getProjectMenberList("XM2021-ZXSJ01", "ref_member_name");
//    	List<Map> m2 = bizCommonMapper.getDictDetailList("dict_yes_no");
//    	int i = bizCommonMapper.checkRecordExists("pj_project_member", "ref_project_code", "QQQQQQQQQQ");
//    	int i = bizCommonMapper.checkRecordExistsV2("pj_project_member", "ref_project_code", "QQQQQQQQQQ", "ref_member_name", "沈艳");
//    	List<Map> m3 = bizCommonMapper.getAllProjectDetailList();
		log.info("\n-------------------------------------------------------------------------------------------\n\t"
				+ JSON.toJSONString(sysUserMapper.getUserByName("admin")));
//    	log.info("\n-------------------------------------------------------------------------------------------\n\t"
//    			+ JSON.toJSONString(m2));
//    	log.info("\n-------------------------------------------------------------------------------------------\n\t"
//    			+ JSON.toJSONString(m3));
//    	log.info("\n-------------------------------------------------------------------------------------------\n\t"
//    			+ JSON.toJSONString(i));
	}

}
