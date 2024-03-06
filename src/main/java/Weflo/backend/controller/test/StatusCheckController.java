package Weflo.backend.controller.test;

import Weflo.backend.global.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusCheckController {
    @GetMapping("/health-check")
    public ApiResponse<Void> checkHealthStatus() {
        return ApiResponse.onSuccess(HttpStatus.OK);
    }
}
