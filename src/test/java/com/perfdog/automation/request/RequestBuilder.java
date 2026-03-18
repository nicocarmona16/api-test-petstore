package com.perfdog.automation.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.util.Map;

public class RequestBuilder {

    public static Response getRequest(String baseUrl, String path, Map<String, String> queryParams) {
        RequestSpecification request = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }
        return request.get(path);
    }

    public static Response postRequest(String baseUrl, String path, Object body) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .body(body)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .post(path);
    }
}
