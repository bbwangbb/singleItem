package com.zxtnet.template.common.response.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @module: 响应结果
 * @description: 基本分页响应结果（包含总页数，总条数及具体响应数据，若需要返回其他数据，则继承该类即可）
 * @author: 郭海斌
 * @createDate: 2020/5/7
 * @version: 1.0
 */
@Data
@ApiModel("基本的分页响应结果")
@AllArgsConstructor
@NoArgsConstructor
public class BasePagingResult<T> {
    @ApiModelProperty("总条数")
    long totalCount;
    @ApiModelProperty("总页数")
    int totalPage;
    @ApiModelProperty("响应数据")
    List<T> dataList;
}
