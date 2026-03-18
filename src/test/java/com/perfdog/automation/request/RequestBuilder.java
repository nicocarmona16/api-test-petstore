package com.perfdog.automation.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.util.Map;

/**
 * Class to build and execute HTTP requests using REST Assured
 */
public class RequestBuilder {

    /**
     * Executes an HTTP GET request with optional query parameters
     * @param baseUrl     The base URL of the API
     * @param path        The specific endpoint path
     * @param queryParams A map of query parameters to attach to the request
     * @return The HTTP response
     */
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

    /**
     * Executes an HTTP POST request with a JSON body
     * @param baseUrl The base URL of the API
     * @param path    The specific endpoint path
     * @param body    The object payload to be sent in the request body
     * @return The HTTP response
     */
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
