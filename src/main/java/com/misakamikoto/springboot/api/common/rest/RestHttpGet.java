package com.misakamikoto.springboot.api.common.rest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RestHttpGet {
    private static String USER_AGENT = "Mozilla/5.0";

    public String call(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public String call(String url, String query, Map<String, String> headerMap) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        String safeUrl = url + "?query=" + URLEncoder.encode(query, "UTF-8");
        HttpGet request = new HttpGet(safeUrl);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");

        for(String key : headerMap.keySet()) {
            request.addHeader(key, headerMap.get(key));
        }
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        String result = "";
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity,"UTF-8");
        }
        return result;
    }
}

