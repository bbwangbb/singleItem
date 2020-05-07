package com.zxtnet.singleItem.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @module: dto
 * @description: 数据传输对象
 * @author: 郭海斌
 * @createDate: 2020/5/7
 * @version: 1.0
 */
@Data
@ApiModel("条件传输对象")
public class ConditionDto {
    @ApiModelProperty("条件1")
    private String c1;
}
