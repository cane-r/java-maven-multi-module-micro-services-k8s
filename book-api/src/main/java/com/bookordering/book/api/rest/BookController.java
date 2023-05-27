package com.bookordering.book.api.rest;

import com.bookordering.common.domain.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
@RestController("book")
@RequestMapping(value = "/book", produces = "application/vnd.api.v1+json")
public class BookController {
    @GetMapping("/{id}")
    public String hello(@PathVariable BigInteger id) {
        System.out.println("hey");
        return "Hello " + id;
    }
    @GetMapping
    public String helloCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return "Hello " + customer.getName();
    }
}
