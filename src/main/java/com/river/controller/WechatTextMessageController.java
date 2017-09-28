package com.river.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by river  2017/9/27
 * desc:
 */
@Controller
@RequestMapping("/text/message")
public class WechatTextMessageController {
    private static final Logger logger = LoggerFactory.getLogger(WechatTextMessageController.class);

    @RequestMapping("/test")
    @ResponseBody
    public String Test() {
        logger.info("***************test**********************");
        return "test";
    }
}
