package com.river.controller;

import com.alibaba.fastjson.JSONObject;
import com.river.util.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * create by river  2017/9/24
 * desc:
 */
@Controller
public class WechatController {


    @RequestMapping("login")
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


    @RequestMapping("/wxAuth/callBack")
    public void callBack(String code) throws IOException {
        String url = " https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + AuthUtil.APPID +
                "&secret=" + AuthUtil.APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code ";

        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String accessToken = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        System.out.println(jsonObject.toJSONString());


        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + accessToken +
                "&openid=" + openId +
                "&lang=zh_CN";
        JSONObject infoJsonObject = AuthUtil.doGetJson(url);
        System.out.println(infoJsonObject.toJSONString());
    }
}
