package com.zxtnet.template.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @module: DTO
 * @description: 数据传输对象
 * @author: 郭海斌
 * @createDate: 2020/5/7
 * @version: 1.0
 */
@Data
@ApiModel("条件传输对象")
public class ConditionDTO {
    @ApiModelProperty("条件1")
    private String c1;
}
