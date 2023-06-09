package com.bookordering.order.api.rest;

import com.bookordering.common.domain.entity.Customer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Collections;

@RestController
@RequestMapping(value = "/order", produces = "application/vnd.api.v1+json")
public class OrderController {

    @Autowired
    private Logger logger;

    @GetMapping("/{id}")
    public String hello(@PathVariable BigInteger id) {
        return "Hello " + id;
    }
    @GetMapping(value = {"/",""})
    public ResponseEntity<String> helloCustomer(@Valid @RequestBody Customer customer) {
        RestTemplate client  = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(2)).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> request = new HttpEntity<>(customer,headers);
        logger.info("helloCustomer");
        try {
            String c = client.exchange("http://customer-service/customer/",
                    HttpMethod.POST,
                    request,
                    String.class).getBody();
            logger.info("Returning response");
            return ResponseEntity.ok("Response from service: " + c);
        }
        catch (Exception e) {
            logger.error("Error from service!");
            return ResponseEntity.badRequest().body("Error! " + e.getMessage());
        }
    }
}
