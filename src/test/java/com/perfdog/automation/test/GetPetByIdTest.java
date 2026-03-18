package com.perfdog.automation.test;

import com.perfdog.automation.config.TestRunner;
import com.perfdog.automation.model.CategoryDTO;
import com.perfdog.automation.model.PetDTO;
import com.perfdog.automation.model.TagDTO;
import com.perfdog.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Test class for retrieving a specific pet by ID
 */
public class GetPetByIdTest extends TestRunner {

    private PetDTO expectedPet;

    /**
     * Prepares the test environment
     * Creates a new pet in the system to ensure it exists
     */
    @BeforeMethod
    public void setupData() {
        expectedPet = PetDTO.builder()
                .id(123456789123L)
                .category(CategoryDTO.builder().id(1L).name("Dogs").build())
                .name("Test Dog")
                .photoUrls(Collections.singletonList("http://test.com/testDog.jpg"))
                .tags(Collections.singletonList(TagDTO.builder().id(1L).name("Test dog").build()))
                .status("available")
                .build();
        RequestBuilder.postRequest(getBaseurl(), "/pet", expectedPet);
    }

    /**
     * Tests fetching a pet's details (GET /pet/{petId})
     * Validates that the returned data exactly matches the created pet
     */
    @Test(testName = "Get pet information by ID")
    public void testGetPetById() {
        Response response = RequestBuilder.getRequest(getBaseurl(), "/pet/" + expectedPet.getId(), null);
        PetDTO actualPet = response.as(PetDTO.class);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200 OK");
        Assert.assertEquals(actualPet.getId(), expectedPet.getId(), "El ID de la mascota no coincide");
        Assert.assertEquals(actualPet, expectedPet, "Los objetos de la mascota no coinciden");
    }
}
