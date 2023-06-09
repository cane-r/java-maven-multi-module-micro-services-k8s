package com.bookordering.customer.api.rest;

import com.bookordering.common.domain.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
@RestController("customer")
@RequestMapping(value = "/customer", produces = "application/vnd.api.v1+json")
public class CustomerController {
    @GetMapping("/{id}")
    public String hello(@PathVariable BigInteger id) {
        System.out.println("hey");
        return "Hello " + id;
    }
    @PostMapping(value = {"/",""})
    public String helloCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return "Hello " + customer.getName();
    }
}
