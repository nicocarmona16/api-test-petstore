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
                .id(252525)
                .username("login_tester")
                .firstName("Tester")
                .lastName("Pruebas")
                .email("test@test.com")
                .password("123456")
                .phone("123456789")
                .userStatus(1)
                .build();

        UserResponseDTO expectedResponse = UserResponseDTO.builder()
                .code(200)
                .type("unknown")
                .message(String.valueOf(newUser.getId()))
                .build();

        Response response = RequestBuilder.postRequest(getBaseurl(), "/user", newUser);
        UserResponseDTO actualResponse = response.as(UserResponseDTO.class);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200 OK");
        Assert.assertEquals(actualResponse, expectedResponse, "El cuerpo de la respuesta no coincide con lo esperado");
    }

    @Test(testName = "Create user with invalid data")
    public void testCreateUserWithInvalidData() {
        String invalidData = "{ Formato de la data invalido }";
        Response response = RequestBuilder.postRequest(getBaseurl(), "/user", invalidData);

        Assert.assertEquals(response.statusCode(), 400, "El servidor debería rechazar la petición con un status code 400");
        UserResponseDTO errorResponse = response.as(UserResponseDTO.class);
        Assert.assertNotNull(errorResponse.getMessage(), "El error debería tener un mensaje");
    }
}
