package ar.com.ada.second.springboottests.controller;

import ar.com.ada.second.springboottests.model.dto.ProductDTO;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldBeStatusCreatedOfNewProduct() {
        // GIVEN
        String url = "http://localhost:" + port + "/products";

        ProductDTO body = new ProductDTO()
                .setName("CocaCola Zero")
                .setPrice(new BigInteger("80"))
                .setDescription("Bebida Gaseosa sin azucar");

        HttpEntity<ProductDTO> request = new HttpEntity<>(body);

        // WHEN
        ResponseEntity<ProductDTO> response = testRestTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<ProductDTO>() {
                }
        );

        // THEN
        // FORMA A
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("CocaCola Zero", response.getBody().getName());

        // FORMA B: by method isEqualTo
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getName()).isEqualTo("CocaCola Zero");
    }
}