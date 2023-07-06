package com.jun.plugin.biz1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.jun.plugin.biz1.entity.SysUserEntity;
import java.util.List;
/**
 * @description Mapper
 * @author wujun
 * @date 2023-07-06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    @Select(
    "<script>select t0.* from sys_user t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=井{id}</when> " +
    "<when test='age!=null and age!=&apos;&apos; '> and t0.age=井{age}</when> " +
    "<when test='name!=null and name!=&apos;&apos; '> and t0.name=井{name}</when> " +
    //add here if need page limit
    //" limit ￥{page},￥{limit} " +
    " </script>")
    List<SysUserEntity> pageAll(SysUserEntity dto,int page,int limit);

    @Select("<script>select count(1) from sys_user t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=井{id}</when> " +
    "<when test='age!=null and age!=&apos;&apos; '> and t0.age=井{age}</when> " +
    "<when test='name!=null and name!=&apos;&apos; '> and t0.name=井{name}</when> " +
     " </script>")
    int countAll(SysUserEntity dto);
    
    @Select("SELECT count(1) from sys_user ")
    int countAll();

}
