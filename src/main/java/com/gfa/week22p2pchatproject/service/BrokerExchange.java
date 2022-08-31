package com.gfa.week22p2pchatproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerExchange {

    private final URL getBrokerPath;
    private final AutContext autContext;

    public <T> HttpEntity<T> createRequestEntity(T requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", autContext.getApiKey());

       return new HttpEntity<>(requestDto, headers);
    }

    public <T, R> R post(String endpoint, HttpMethod httpMethod, T requestDto, Class<R> responseDtoClass) {
        URI uri;
        try {
            uri = new URI(getBrokerPath + endpoint);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        ResponseEntity<R> responseEntity = new RestTemplateBuilder(). build().exchange(
                uri,
                httpMethod,
                createRequestEntity(requestDto),
                responseDtoClass);
        return responseEntity.getBody();
    }

}
