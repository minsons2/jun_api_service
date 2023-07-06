package com.jun.plugin.biz1.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.gitthub.wujun728.engine.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description 
 * @author wujun
 * @date 2023-07-06
 */
@Data
@ApiModel("")
public class SysUserDto  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty("") 
    private Long id;

    /**
    * 
    */
    @ApiModelProperty("") 
    private Integer age;

    /**
    * 
    */
    @ApiModelProperty("") 
    private String name;

    public SysUserDto() {}
}
