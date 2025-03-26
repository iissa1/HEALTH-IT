package org.health_it.controller;

import java.util.List;

import org.health_it.model.HealthItResponseDto;
import org.health_it.service.HealthItService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthItController {

    private HealthItService service;

    public HealthItController(final HealthItService service) {
        this.service = service;
    }

    @GetMapping("/api")
    public ResponseEntity<List<HealthItResponseDto>> getData() {

        return service.defaultDataFetch();
    }
}
