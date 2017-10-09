package com.river.controller.accredit;

import com.alibaba.fastjson.JSONObject;
import com.river.util.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * create by river  2017/9/24
 * desc:
 */
@Controller
public class WechatAccreditController {

    private static final Logger logger = LoggerFactory.getLogger(WechatAccreditController.class);

    /**
     * 微信授权登录第一步，跳转授权页面，
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String wechatTest() {

        String backUrl = "http://c32f415f.ngrok.io/wxAuth/callBack";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + AuthUtil.APPID
                + "&redirect_uri=" + URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=SCOPE"
                + "&state=STATE#wechat_redirect";
        return "redirect:" + url;
    }

    /**
     * 微信授权毁回调地址
     * @param code
     * @throws IOException
     */
    @RequestMapping(value = "/wxAuth/callBack",method = RequestMethod.GET)
    public void callBack(String code) throws IOException {

        //获取access_token openid
        String url = " https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + AuthUtil.APPID +
                "&secret=" + AuthUtil.APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code ";

        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String accessToken = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        System.out.println(jsonObject.toJSONString());

        //获取用户信息
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + accessToken +
                "&openid=" + openId +
                "&lang=zh_CN";
        JSONObject infoJsonObject = AuthUtil.doGetJson(url);
        System.out.println(infoJsonObject.toJSONString());
    }
}
