package com.bookordering.order.api.rest;

import com.bookordering.common.domain.entity.Customer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
@RestController
@RequestMapping(value = "/order", produces = "application/vnd.api.v1+json")
public class OrderController {

    @Autowired
    private Logger logger;

    @GetMapping("/{id}")
    public String hello(@PathVariable BigInteger id) {
        return "Hello " + id;
    }
    @GetMapping
    public String helloCustomer(@Valid @RequestBody Customer customer) {
        return "Hello " + customer.getName();
    }
}
