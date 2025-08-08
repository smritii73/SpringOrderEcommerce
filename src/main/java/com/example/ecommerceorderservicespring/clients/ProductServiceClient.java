package com.example.ecommerceorderservicespring.clients;

import com.example.ecommerceorderservicespring.dto.ProductDTO;
import jakarta.persistence.Column;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class ProductServiceClient {
    private final RestTemplateBuilder restTemplateBuilder;
    public ProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public ProductDTO getProductById(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        //not the best way to do > so we do using Service Discovery using > NetflixEureka
        String url = "http://localhost:3000/api/products/" + productId;
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
        return response.getBody();
    }
    //Do we need API gateway?
    // No.We need gateway when interacting with 3rd party api/service
    // When our service is communicating w other service or in microservice, we dont need gateway
}
