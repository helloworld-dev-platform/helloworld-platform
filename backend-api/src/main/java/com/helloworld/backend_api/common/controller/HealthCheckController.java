package com.helloworld.backend_api.common.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/api/health") // Nginx가 호출할 경로
  public Map<String, String> healthCheck() {
    Map<String, String> response = new HashMap<>();
    response.put("status", "UP");
    response.put("message", "Service is running on AWS EC2");
    return response;
  }
}
