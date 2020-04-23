package com.test.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;

public class HcCookiesDemo {

    private UrlUtil urlUtil = new UrlUtil();
    private String url;
    private ResourceBundle bundle = urlUtil.bundle;
    private CookieStore store;

    @BeforeTest
    public void getUrl(){
        this.url = urlUtil.getResourceUrl();
    }

    @Test
    public void getCookies() throws IOException {
        String res;
        String path = this.url + bundle.getString("test.get.cookies");

        HttpGet httpGet = new HttpGet(path);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpGet);
        // HttpEntity转String
        res = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(res);
        // 获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie : cookieList){
            System.out.println("key:" + cookie.getName());
            System.out.println("value:" + cookie.getValue());
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void reqGetWithCookies() throws IOException {
        String res;
        String path = this.url + bundle.getString("test.getCookies");

        HttpGet httpGet = new HttpGet(path);
        DefaultHttpClient client = new DefaultHttpClient();
        // 设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode(); // 获取响应状态码
        System.out.println("响应状态码：" + statusCode);
        if(statusCode == 200){
            // HttpEntity转String
            res = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(res);
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void reqPostWithCookies() throws IOException {
        String res;
        String path = this.url + bundle.getString("test.postCookies");

        // httpclient对象
        DefaultHttpClient client = new DefaultHttpClient();
        // 设置cookies信息
        client.setCookieStore(this.store);
        // http post方法
        HttpPost post = new HttpPost(path);
        // 设置请求头信息 application/json
        post.setHeader("content-type", "application/json");
        // 设置请求参数
        JSONObject param = new JSONObject();
        param.put("name", "nina");
        param.put("sex", "M");
        StringEntity entity = new StringEntity(param.toString());
        post.setEntity(entity);

        // HttpResponse对象
        HttpResponse response = client.execute(post);
        res = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(res);
        JSONObject jsonObject = new JSONObject(res);
        String name = (String) jsonObject.get("name");
        String age = (String) jsonObject.get("age");
        Assert.assertEquals(name,"john");
        Assert.assertEquals(age,"26");
    }
}
