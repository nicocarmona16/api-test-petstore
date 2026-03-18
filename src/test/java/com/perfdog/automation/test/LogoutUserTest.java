package com.perfdog.automation.test;

import com.perfdog.automation.config.TestRunner;
import com.perfdog.automation.model.UserResponseDTO;
import com.perfdog.automation.model.UserDTO;
import com.perfdog.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LogoutUserTest extends TestRunner {

    @BeforeMethod
    public void setupData() {
        UserDTO logoutUser = UserDTO.builder()
                .id(252525)
                .username("logout_user")
                .password("123456")
                .build();
        RequestBuilder.postRequest(getBaseurl(), "/user", logoutUser);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", logoutUser.getUsername());
        queryParams.put("password", logoutUser.getPassword());
        RequestBuilder.getRequest(getBaseurl(), "/user/login", queryParams);
    }

    @Test(testName = "Logout user")
    public void testLogoutUser() {
        Response response = RequestBuilder.getRequest(getBaseurl(), "/user/logout", null);
        UserResponseDTO actualResponse = response.as(UserResponseDTO.class);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200 OK");
        Assert.assertEquals(actualResponse.getCode(), Integer.valueOf(200), "El reponse code no es 200");
        Assert.assertEquals(actualResponse.getMessage(), "ok", "No se esta confirmando el cierre de sesion por medio del mensaje");
    }
}