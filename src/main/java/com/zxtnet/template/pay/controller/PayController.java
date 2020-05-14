package com.zxtnet.template.pay.controller;

import com.zxtnet.template.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @module: 支付模块
 * @description: 支付相关接口
 * @author: 郭海斌
 * @createDate: 2020/5/14
 * @version: 1.0
 */
@Api("支付模块接口")
@CrossOrigin
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/payCallback")
    @ApiOperation("支付回调函数")
    public void payCallback() throws Exception {
        payService.payCallback();
    }

}
