package com.river.controller;

import com.river.util.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/auth")
public class WechatAuthTokenController {

	private static final Logger logger = LoggerFactory.getLogger(WechatAuthTokenController.class);

	// 自定义 token
	private static final String TOKEN = "river";

	/**
	 * 微信token认证
	 * @param signature
	 * @param echostr
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/token")
	@ResponseBody
	public String authToken(String signature ,String echostr , String timestamp ,String nonce)
			throws  IOException {
		logger.info("开始token认证");
		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		// 确认请求来至微信
		return (digest.equals(signature)) ? echostr : null;
	}
}
