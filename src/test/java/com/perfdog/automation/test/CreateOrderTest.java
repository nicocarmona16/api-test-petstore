package com.perfdog.automation.test;

import com.perfdog.automation.config.TestRunner;
import com.perfdog.automation.model.CategoryDTO;
import com.perfdog.automation.model.OrderDTO;
import com.perfdog.automation.model.PetDTO;
import com.perfdog.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for creating a pet purchase order
 */
public class CreateOrderTest extends TestRunner {

    private PetDTO orderPet;

    /**
     * Prepares the test environment
     * Creates a new pet to ensure it is available for purchase
     */
    @BeforeMethod
    public void setupData() {
        orderPet = PetDTO.builder()
                .id(123456789123L)
                .category(CategoryDTO.builder().id(1L).name("Dogs").build())
                .name("Test Dog")
                .status("available")
                .build();
        RequestBuilder.postRequest(getBaseurl(), "/pet", orderPet);
    }

    /**
     * Tests the creation of a new order (POST /store/order)
     * Validates the HTTP status code and verifies the order details in the response
     */
    @Test(testName = "Create order to buy a pet")
    public void testCreateOrder() {
        OrderDTO newOrder = OrderDTO.builder()
                .id(102030L)
                .petId(orderPet.getId())
                .quantity(1)
                .shipDate("2026-03-18T00:00:00.000Z")
                .status("placed")
                .complete(true)
                .build();

        Response response = RequestBuilder.postRequest(getBaseurl(), "/store/order", newOrder);
        OrderDTO actualOrder = response.as(OrderDTO.class);

        Assert.assertEquals(response.statusCode(), 200, "El status code no es 200 OK");
        Assert.assertEquals(actualOrder.getId(), newOrder.getId(), "El ID de la orden no coincide");
        Assert.assertEquals(actualOrder.getPetId(), newOrder.getPetId(), "El ID de la mascota no coincide");
        Assert.assertEquals(actualOrder.getStatus(), "placed", "El estado de la orden no es placed");
    }
}