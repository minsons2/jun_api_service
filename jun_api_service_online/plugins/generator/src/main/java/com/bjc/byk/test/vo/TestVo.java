package com.bjc.byk.test.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import com.jun.plugin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description 
 * @author Wujun
 * @date 2023-10-17
 */
@Data
@ApiModel("")
public class TestVo  extends BaseEntity  implements Serializable {

    public interface Retrieve{}
    public interface Delete {}
    public interface Update {}
    public interface Create {}

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @ApiModelProperty("主键") 
    
    
    private Integer id;

    /**
    * 标题
    */
    @ApiModelProperty("标题") 
    
    
    private String title;

    /**
    * 内容
    */
    @ApiModelProperty("内容") 
    
    
    private String content;

    /**
    * 备注
    */
    @ApiModelProperty("备注") 
    @NotNull(message = "备注不能为空", groups = {Create.class,Update.class,Delete.class})
    @Size( max = 1024,message = "备注长度限制1024位")
    private String remark;

    /**
    * 测试1
    */
    @ApiModelProperty("测试1") 
    @NotNull(message = "测试1不能为空", groups = {Create.class,Update.class,Delete.class})
    @Size( max = 1024,message = "测试1长度限制1024位")
    private String fieldNameTest;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间") 
    @NotNull(message = "创建时间不能为空", groups = {Create.class,Update.class,Delete.class})
    @Size( max = 19,message = "创建时间长度限制19位")
    private Date createTime;

    public TestVo() {}
}
