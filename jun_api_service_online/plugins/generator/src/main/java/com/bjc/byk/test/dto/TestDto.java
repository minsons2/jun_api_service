package com.bjc.byk.test.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
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
public class TestDto  extends BaseEntity  implements Serializable {

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
    private String remark;

    /**
    * 测试1
    */
    @ApiModelProperty("测试1") 
    private String fieldNameTest;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间") 
    private Date createTime;

    public TestDto() {}
}
