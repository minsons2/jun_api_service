package com.bjc.byk.test.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.bjc.byk.test.entity.TestEntity;
import java.util.List;
/**
 * @description Mapper
 * @author Wujun
 * @date 2023-10-17
 */
@Mapper
public interface TestMapper extends BaseMapper<TestEntity> {

    @Select(
    "<script>select t0.* from test t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=井{id}</when> " +
    "<when test='title!=null and title!=&apos;&apos; '> and t0.title=井{title}</when> " +
    "<when test='content!=null and content!=&apos;&apos; '> and t0.content=井{content}</when> " +
    "<when test='remark!=null and remark!=&apos;&apos; '> and t0.remark=井{remark}</when> " +
    "<when test='fieldNameTest!=null and fieldNameTest!=&apos;&apos; '> and t0.field_name_test=井{fieldNameTest}</when> " +
    "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=井{createTime}</when> " +
    //add here if need page limit
    //" limit ￥{page},￥{limit} " +
    " </script>")
    List<TestEntity> pageAll(TestEntity dto,int page,int limit);

    @Select("<script>select count(1) from test t0 " +
    //add here if need left join
    "where 1=1" +
    "<when test='id!=null and id!=&apos;&apos; '> and t0.id=井{id}</when> " +
    "<when test='title!=null and title!=&apos;&apos; '> and t0.title=井{title}</when> " +
    "<when test='content!=null and content!=&apos;&apos; '> and t0.content=井{content}</when> " +
    "<when test='remark!=null and remark!=&apos;&apos; '> and t0.remark=井{remark}</when> " +
    "<when test='fieldNameTest!=null and fieldNameTest!=&apos;&apos; '> and t0.field_name_test=井{fieldNameTest}</when> " +
    "<when test='createTime!=null and createTime!=&apos;&apos; '> and t0.create_time=井{createTime}</when> " +
     " </script>")
    int countAll(TestEntity dto);
    
    @Select("SELECT count(1) from test ")
    int countAll();

}
