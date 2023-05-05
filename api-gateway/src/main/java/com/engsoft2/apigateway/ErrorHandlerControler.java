package com.engsoft2.apigateway;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Classe que trata os erros de circuit breaker quando os microserviços não estão disponíveis
@Controller
public class ErrorHandlerControler {
    @RequestMapping("/error-handler")
    public ResponseEntity<String> handleFallback() {
        String errorMessage = "An error occurred while processing your request. The service may be unavailable. (Circuit breaker enabled))";
        return ResponseEntity
                .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }
}
