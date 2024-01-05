package cn.yangwanhao.news.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientUtil {

    public static String getDataWithPostMethod(String url,String encode) throws IOException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        HttpPost post = new HttpPost(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(3000)
            .setConnectTimeout(3000)
            .build();
        post.setConfig(requestConfig);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            post.setEntity(entity);
            System.out.println(EntityUtils.toString(entity));
            HttpResponse response = httpclient.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, encode);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            httpclient.close();
        }
        return result;
    }

    public static String getDataWithGetMethod(String url,String encode) throws IOException {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet post = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(3000)
            .setConnectTimeout(3000)
            .build();
        post.setConfig(requestConfig);
        try {
            HttpResponse response = httpclient.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, encode);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            httpclient.close();
        }
        return result;
    }

}
