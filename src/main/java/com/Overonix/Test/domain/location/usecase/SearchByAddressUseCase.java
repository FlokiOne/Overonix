package com.Overonix.Test.domain.location.usecase;

import com.Overonix.Test.config.NominatimConfig;
import com.Overonix.Test.presentation.dto.RsAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Component
public class SearchByAddressUseCase {

    private final NominatimConfig nominatimConfig;
    private final RestTemplate restTemplate;
    private final GetJSON getJSON;

    @Autowired
    public SearchByAddressUseCase(NominatimConfig nominatimConfig, RestTemplate restTemplate, GetJSON getJSON) {
        this.nominatimConfig = nominatimConfig;
        this.restTemplate = restTemplate;
        this.getJSON = getJSON;
    }

    public List<RsAddress> searchByAddress(String address) {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        this.url(address),
                        String.class
                );

        return this.getJSON.getJSONArray(response.getBody());
    }

    private String url(String address) {
        return UriComponentsBuilder.fromHttpUrl(this.nominatimConfig.getSearch())
                .queryParam("q", address)
                .queryParam("format", this.nominatimConfig.getFormat())
                .queryParam("addressdetails", this.nominatimConfig.getAddressdetails())
                .toUriString();
    }
}
