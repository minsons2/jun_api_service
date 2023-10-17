package com.bjc.byk.test.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jun.plugin.common.entity.BaseEntity;

/**
 * @description 
 * @author Wujun
 * @date 2023-10-17
 */
@Data
@TableName("test")
public class TestEntity  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @TableId(value = "id" ,type = IdType.AUTO )
    private Integer id;

    /**
    * 标题
    */
    @TableField(value = "title" )
    private String title;

    /**
    * 内容
    */
    @TableField(value = "content" )
    private String content;

    /**
    * 备注
    */
    @TableField(value = "remark" )
    private String remark;

    /**
    * 测试1
    */
    @TableField(value = "field_name_test" )
    private String fieldNameTest;

    /**
    * 创建时间
    */
    @TableField(value = "create_time" )
    private Date createTime;

    public TestEntity() {}
}
