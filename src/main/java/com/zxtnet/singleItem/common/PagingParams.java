package com.zxtnet.singleItem.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 分页参数实体类
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Data
@ApiModel(value = "分页参数实体类")
public class PagingParams {

    @ApiModelProperty(value = "页码")
    private int pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 10;

    @ApiModelProperty(value = "偏移量")
    private int offset;

    @ApiModelProperty(value = "分页参数")
    private Map<String,Object> cnd = new HashMap<>();

}
