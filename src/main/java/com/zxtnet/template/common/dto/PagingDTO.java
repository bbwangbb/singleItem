package com.zxtnet.template.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 分页DTO，具体条件就可以设置对应的dto，这样前端在swagger中方便查看
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Data
@ApiModel(value = "分页DTO")
public class PagingDTO<T> {

    @ApiModelProperty(value = "页码")
    private int pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 10;

    @ApiModelProperty(value = "偏移量")
    private int offset;

    @ApiModelProperty(value = "分页参数")
    private T cnd;

}
