package com.perfdog.automation.test;

import com.perfdog.automation.config.TestRunner;
import com.perfdog.automation.model.PetDTO;
import com.perfdog.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for querying available pets
 */
public class GetAvailablePetsTest extends TestRunner {

    /**
     * Retrieves all pets with the status 'available' (GET /pet/findByStatus)
     * Validates the HTTP status code and ensures every returned pet has the correct status
     */
    @Test(testName = "List pets with status available")
    public void testGetAvailablePets() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("status", "available");
        Response response = RequestBuilder.getRequest(getBaseurl(), "/pet/findByStatus", queryParams);

        PetDTO[] petsArray = response.as(PetDTO[].class);
        List<PetDTO> petsList = Arrays.asList(petsArray);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200 OK");
        Assert.assertFalse(petsList.isEmpty(), "La lista de mascotas con estado available está vacía");
        for (PetDTO pet : petsList) {
            Assert.assertEquals(pet.getStatus(), "available",
                    "Mascota con un estado diferente a available - ID: " + pet.getId());
        }
    }
}
