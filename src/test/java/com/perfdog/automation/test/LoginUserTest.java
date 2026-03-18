package com.perfdog.automation.test;

import com.perfdog.automation.config.TestRunner;
import com.perfdog.automation.model.UserDTO;
import com.perfdog.automation.model.UserResponseDTO;
import com.perfdog.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginUserTest extends TestRunner {

    private UserDTO validUser;

    @BeforeMethod
    public void setupData() {
        validUser = UserDTO.builder()
                .id(252525)
                .username("login_tester")
                .firstName("Tester")
                .lastName("Pruebas")
                .email("test@test.com")
                .password("123456")
                .phone("123456789")
                .userStatus(1)
                .build();

        RequestBuilder.postRequest(getBaseurl(), "/user", validUser);
    }

    @Test(testName = "Login with new user")
    public void testLoginUser() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", validUser.getUsername());
        queryParams.put("password", validUser.getPassword());

        Response response = RequestBuilder.getRequest(getBaseurl(), "/user/login", queryParams);
        UserResponseDTO actualResponse = response.as(UserResponseDTO.class);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200 OK");
        Assert.assertEquals(actualResponse.getCode(), Integer.valueOf(200), "El código en el body no es 200");
        Assert.assertTrue(actualResponse.getMessage().contains("logged in user session"),
                "El mensaje no confirma el inicio de sesión. Mensaje actual: " + actualResponse.getMessage());
    }
}
