package com.my.todo.controller.integration;

import com.my.todo.configuration.ToDoAppConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {ToDoAppConfiguration.class})
public class Steps {

    @Value("${service.todoapp.port}")
    private int port;

    public <T, U> U makeHttpGetRequest(String uri, ParameterizedTypeReference<U> reference) throws MalformedURLException {

        return makeHttpRequest(uri, null, HttpMethod.GET, reference);
    }

    public <T, U> U makeHttpPostRequest(String uri, T requestEntity, ParameterizedTypeReference<U> reference) throws MalformedURLException {

        return makeHttpRequest(uri, requestEntity, HttpMethod.POST, reference);
    }

    public <T, U> U makeHttpPutRequest(String uri, T requestEntity, ParameterizedTypeReference<U> reference) throws MalformedURLException {

        return makeHttpRequest(uri, requestEntity, HttpMethod.PUT, reference);
    }


    public <T, U> U makeHttpRequest(String uri, T requestEntity, HttpMethod method, ParameterizedTypeReference<U> typeReference) throws MalformedURLException {

        URL url = new URL(String.format("http://localhost:%1$s/%2$s", port, uri));
        TestRestTemplate template = new TestRestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        ResponseEntity<U> response = template.exchange(url.toString(),
                method,
                new HttpEntity<T>(requestEntity, httpHeaders),
                typeReference);

        return response.getBody();
    }
}
