package com.engsoft2.apigateway;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerControler {
     @RequestMapping("/error-handler")
        public ResponseEntity<String> handleFallback() {
            String errorMessage = "An error occurred while processing your request.";
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
