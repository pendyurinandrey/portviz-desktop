package com.portviz.desktop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class PortVizConfiguration {

    @Bean
    public HttpClient defaultHttpClient(@Value("${portviz.http.client.connect-timeout-ms}") long connectTimeoutMs) {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeoutMs))
                .build();
    }

}
