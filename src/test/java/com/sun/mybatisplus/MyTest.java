package com.sun.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.sun.mybatisplus.entity.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class MyTest {

    String addr = "http://localhost:8080/";
    String url = addr + "/test";
    public static Integer id = 25;


    @org.junit.Test
    public void save() throws IOException {
        Test bean = mkBean();
        ResponseEntity responseEntity = getRestTemplate().exchange(url + "/save", HttpMethod.POST, new HttpEntity<>(bean), Void.class);
        checkResponseEntity(responseEntity);
        System.out.println(JSON.toJSONString(responseEntity.getBody()));
    }

    public Test mkBean() {
        Test bean = new Test();
        bean.setTest("a");
        return bean;
    }

    @org.junit.Test
    public void update() throws IOException {
        Test bean = mkBean();
        bean.setId(id);
        ResponseEntity<Test> responseEntity = getRestTemplate().exchange(
                url + "/update", HttpMethod.PUT, new HttpEntity<>(bean), Test.class);
        checkResponseEntity(responseEntity);
        System.out.printf(JSON.toJSONString(bean));
    }

    @org.junit.Test
    public void delete() throws IOException {
        ResponseEntity<Integer> responseEntity = getRestTemplate().exchange(
                url + "/" + id, HttpMethod.DELETE, null, Integer.class);

        checkResponseEntity(responseEntity);
        System.out.println(responseEntity.getBody());
    }

    @org.junit.Test
    public void getById() throws IOException {
        ResponseEntity<Test> responseEntity = getRestTemplate().exchange(
                url + "/" + id, HttpMethod.GET, null, Test.class);
        checkResponseEntity(responseEntity);
        System.out.println(JSON.toJSONString(responseEntity.getBody()));
    }

    @org.junit.Test
    public void selectPage() throws IOException {
        Test bean = new Test();

        url = url + "/page";
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(url, HttpMethod.PUT, new HttpEntity(bean), String.class);
        checkResponseEntity(responseEntity);

        System.out.println(responseEntity.getBody());
    }

    public static RestTemplate getRestTemplate() throws IOException {
//        token = FileUtils.readFileToString(new File(tokenPath), Charset.forName("UTF-8"));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

//                httpRequest.getHeaders().add("userId", String.valueOf(userId));
                return clientHttpRequestExecution.execute(httpRequest, body);
            }
        });

        return restTemplate;
    }

    public static void checkResponseEntity(ResponseEntity responseEntity) {
        if (responseEntity.getStatusCodeValue() >= 300) {
            System.out.println(responseEntity.getStatusCode());
            throw new RuntimeException(responseEntity.getStatusCode().toString());
        }
    }
}
