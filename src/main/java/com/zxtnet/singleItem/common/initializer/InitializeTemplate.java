package com.zxtnet.singleItem.common.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: 在服务启动时初始化需要的信息，可以设置多个
 * @author: 郭海斌
 * @createDate: 2020/4/22
 * @version: 1.0
 */
@Component
@Order(2)  //  如果多个初始化类由这个来指定顺序
@Slf4j
public class InitializeTemplate implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //  todo：程序启动时需要做的初始化动作
    }
}
