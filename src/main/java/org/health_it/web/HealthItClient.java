package org.health_it.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.health_it.model.HealthItResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Service
public class HealthItClient {

    private final RestClient restClient;

    private final static String HEALTH_IT_URL = "https://www.healthit.gov/data/open-api?source=Meaningful-Use-Acceleration-Scorecard.csv";

    public HealthItClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<List<HealthItResponseDto>> retrieveData() {

        List<HealthItResponseDto> responseList = new ArrayList<>();

        try {
            HealthItResponseDto[] responseDtos = restClient.get()
                    .uri(HEALTH_IT_URL)
                    .retrieve()
                    .body(HealthItResponseDto[].class);

            responseList = Arrays.asList(responseDtos);
        } catch (HttpClientErrorException clientErrorException) {
            return new ResponseEntity<>(clientErrorException.getStatusCode());
        } catch (HttpServerErrorException serverErrorException) {
            return new ResponseEntity<>(serverErrorException.getStatusCode());
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(removeNullValueResponse(responseList));
    }

    public List<HealthItResponseDto> removeNullValueResponse(List<HealthItResponseDto> response) {
        return response.stream()
                .filter(e -> checkForNullValues(e))
                .filter(e -> removeNoneState(e))
                .toList();
    }

    private boolean removeNoneState(HealthItResponseDto e) {
        return !"National".equalsIgnoreCase(e.getRegion());
    }

    public boolean checkForNullValues(HealthItResponseDto responseDto) {
        return StringUtils.hasText(responseDto.getRegion()) &&
                StringUtils.hasText(responseDto.getPeriod()) &&
                StringUtils.hasText(responseDto.getRegion_code());
    }
}
