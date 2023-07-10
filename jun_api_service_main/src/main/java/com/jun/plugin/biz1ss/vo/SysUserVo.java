package com.jun.plugin.biz1ss.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import com.gitthub.wujun728.engine.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description 
 * @author Wujun
 * @date 2023-07-10
 */
@Data
@ApiModel("")
public class SysUserVo  extends BaseEntity  implements Serializable {

    public interface Retrieve{}
    public interface Delete {}
    public interface Update {}
    public interface Create {}

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

    public SysUserVo() {}
}
