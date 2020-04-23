package com.test.httpclient.demo;

import java.util.ResourceBundle;

public class UrlUtil {

    public String url;
    public ResourceBundle bundle = ResourceBundle.getBundle("httpUrl");

    public String getResourceUrl(){
        return url = bundle.getString("test.url");
    }

}
