package org.health_it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.health_it.model.HealthItResponseDto;
import org.health_it.service.HealthItService;
import org.health_it.web.HealthItClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class HealthItServiceTests {

    @Mock
    private HealthItClient client;

    @InjectMocks
    private HealthItService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDefaultDataFetch() {

        when(client.retrieveData()).thenReturn(this.getExpectedReturn());

        ResponseEntity<List<HealthItResponseDto>> responseEntity = service.defaultDataFetch();

        assertEquals(4, responseEntity.getBody().size());
    }

    private ResponseEntity<List<HealthItResponseDto>> getExpectedReturn() {

        List<HealthItResponseDto> list = new ArrayList<>();

        list.add(HealthItResponseDto.builder()
                .region("Illinois")
                .region_code("IL")
                .period("2014")
                .pct_hospitals_mu(0.9)
                .build());

        list.add(HealthItResponseDto.builder()
                .region("West Virginia")
                .region_code("WV")
                .period("2014")
                .pct_hospitals_mu(0.01)
                .build());

        list.add(HealthItResponseDto.builder()
                .region("Georgia")
                .region_code("GA")
                .period("2014")
                .pct_hospitals_mu(0.5)
                .build());

        list.add(HealthItResponseDto.builder()
                .region("Alaska")
                .region_code("AK")
                .period("2014")
                .pct_hospitals_mu(0.3)
                .build());

        list.add(HealthItResponseDto.builder()
                .region("South Carolina")
                .region_code("SC")
                .period("2016")
                .build());

        list.add(HealthItResponseDto.builder()
                .region("Oklahoma")
                .region_code("OK")
                .period("2017")
                .build());

        return ResponseEntity.ok(list);
    }
}
