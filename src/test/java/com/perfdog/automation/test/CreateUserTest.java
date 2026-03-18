package com.perfdog.automation.test;

import com.perfdog.automation.config.TestRunner;
import com.perfdog.automation.model.UserDTO;
import com.perfdog.automation.model.UserResponseDTO;
import com.perfdog.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest extends TestRunner {

    @Test (testName = "Create a user")
    public void testCreateUser() {
        UserDTO newUser = UserDTO.builder()
                .id(1515151)
                .username("tester")
                .firstName("Carlos")
                .lastName("Automatizador")
                .email("carlos@perfdog.com")
                .password("Password123!")
                .phone("3001234567")
                .userStatus(1)
                .build();

        UserResponseDTO expectedResponse = UserResponseDTO.builder()
                .code(200)
                .type("unknown")
                .message(String.valueOf(newUser.getId()))
                .build();

        Response response = RequestBuilder.postRequest(getBaseurl(), "/user", newUser);
        UserResponseDTO actualResponse = response.as(UserResponseDTO.class);

        Assert.assertEquals(response.statusCode(), 200, "El código de estado no es 200 OK");
        Assert.assertEquals(actualResponse, expectedResponse, "El cuerpo de la respuesta no coincide con lo esperado");
    }
}
