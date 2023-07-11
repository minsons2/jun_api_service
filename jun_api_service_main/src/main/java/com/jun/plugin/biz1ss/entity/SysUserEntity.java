package com.jun.plugin.biz1ss.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitthub.wujun728.engine.common.BaseEntity;

/**
 * @description 测试用户表
 * @author Wujun
 * @date 2023-07-11
 */
@Data
@TableName("sys_user")
public class SysUserEntity  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID主键
    */
    @TableId(value = "id" ,type = IdType.AUTO )
    private Long id;

    /**
    * 年纪
    */
    @TableField(value = "age" )
    private Integer age;

    /**
    * 姓名
    */
    @TableField(value = "name" )
    private String name;

    public SysUserEntity() {}
}
