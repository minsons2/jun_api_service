package com.jun.plugin.biz1ss.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.gitthub.wujun728.engine.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description 测试用户表
 * @author Wujun
 * @date 2023-07-11
 */
@Data
@ApiModel("测试用户表")
public class SysUserDto  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID主键
    */
    @ApiModelProperty("ID主键") 
    private Long id;

    /**
    * 年纪
    */
    @ApiModelProperty("年纪") 
    private Integer age;

    /**
    * 姓名
    */
    @ApiModelProperty("姓名") 
    private String name;

    public SysUserDto() {}
}
