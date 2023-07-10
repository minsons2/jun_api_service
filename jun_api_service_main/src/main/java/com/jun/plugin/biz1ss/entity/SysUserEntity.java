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
 * @description 
 * @author Wujun
 * @date 2023-07-10
 */
@Data
@TableName("sys_user")
public class SysUserEntity  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId(value = "id" ,type = IdType.AUTO )
    private Long id;

    /**
    * 
    */
    @TableField(value = "age" )
    private Integer age;

    /**
    * 
    */
    @TableField(value = "name" )
    private String name;

    public SysUserEntity() {}
}
