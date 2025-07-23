
    package org.springboot.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HomeController {

        @GetMapping("/")
        public ResponseEntity<String> home() {
            return ResponseEntity.ok("🚀 Welcome to the Blog API! Use Postman or Swagger to test.");
        }
    }

