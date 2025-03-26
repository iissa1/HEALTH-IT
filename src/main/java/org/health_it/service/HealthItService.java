package org.health_it.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.health_it.model.HealthItResponseDto;
import org.health_it.web.HealthItClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class HealthItService {

    private HealthItClient client;

    public HealthItService(final HealthItClient client) {
        this.client = client;
    }

    /**
     * 
     * 
     * @returns Descending order by state with percentage of eligible and
     *          critical access to hospital
     */
    public ResponseEntity<List<HealthItResponseDto>> defaultDataFetch() {

        ResponseEntity<List<HealthItResponseDto>> responseEntity = client.retrieveData();

        if (responseEntity.getBody() == null) {
            return responseEntity;
        }

        List<HealthItResponseDto> response = responseEntity
                .getBody()
                .stream()
                .filter(e -> e.getPeriod().equals("2014"))
                .sorted(Comparator.comparing(HealthItResponseDto::getPct_hospitals_mu).reversed())
                .collect(Collectors.toList());

        // Console print
        consolePrint(response);

        return ResponseEntity.ok(response);
    }

    private void consolePrint(List<HealthItResponseDto> responseDtos) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        for (HealthItResponseDto healthItResponseDto : responseDtos) {

            String valResp = "";
            try {
                valResp = mapper.writeValueAsString(healthItResponseDto);
            } catch (Exception e) {
                // Do nothing just print object, parsing failed
                System.out.println(healthItResponseDto);
            }
            System.out.println(valResp);
        }
    }

}
