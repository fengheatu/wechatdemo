package com.river.controller.message;

import com.river.constant.MessageConst;
import com.river.model.pojo.TextTypeMessage;
import com.river.util.MessageUtil;
import com.river.util.SHA1;
import com.sun.xml.internal.rngom.binary.DataExceptPattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class WechatAuthTokenAndReceiveMessageController {

	private static final Logger logger = LoggerFactory.getLogger(WechatAuthTokenAndReceiveMessageController.class);

	// 自定义 token
	private static final String TOKEN = "river";

	/**
	 * 微信token认证,消息接收
	 * @param signature
	 * @param echostr
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/token")
	@ResponseBody
	public String authToken(HttpServletRequest request, HttpServletResponse response, String signature , String echostr , String timestamp , String nonce) throws IOException {
		boolean isGet  = StringUtils.equalsIgnoreCase(request.getMethod(),"get");


		String result = null;
		if(isGet) {
			logger.info("开始token认证");
			String[] str = {TOKEN, timestamp, nonce};
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
			// 确认请求来至微信
			return (digest.equals(signature)) ? echostr : null;

		}else {
			logger.info("消息接收");
			Map<String,String> msgMap = null;
			try {
				msgMap = MessageUtil.xmlToMap(request);
				if(msgMap.isEmpty()) {
					logger.info("消息为空");
				}
				if (MessageConst.MESSAGE_TYPE_TEXT.equalsIgnoreCase(msgMap.get("MsgType"))) {
					TextTypeMessage textTypeMessage = new TextTypeMessage();
					textTypeMessage.setToUserName(msgMap.get("FromUserName"));
					textTypeMessage.setFromUserName(msgMap.get("ToUserName"));
					textTypeMessage.setCreateTime(new Date().getTime());
					textTypeMessage.setContent("小明是个逗比");
					textTypeMessage.setMsgType(MessageConst.MESSAGE_TYPE_TEXT);
					result = MessageUtil.textMessageToXml(textTypeMessage);
				}

			} catch (Exception e) {

			}
			return result;
		}
	}
}
