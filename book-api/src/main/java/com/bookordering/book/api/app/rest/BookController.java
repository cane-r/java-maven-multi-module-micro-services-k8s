package com.bookordering.book.api.app.rest;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.UUID;

@RestController
@RequestMapping(value = "/book", produces = "application/vnd.api.v1+json")
public class BookController {
    @GetMapping("/{id}")
    public String hello(@PathVariable BigInteger id) {
        return "Hello " + id;
    }
}
