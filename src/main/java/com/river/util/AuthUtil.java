package com.river.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpConnectionFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * create by river  2017/9/24
 * desc:
 */
public class AuthUtil {

    public static final  String APPID = "wx6f9c63ee69f58d67 ";
    public static final  String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if(httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity, Consts.UTF_8);
            jsonObject = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();

        return jsonObject;
    }
}
