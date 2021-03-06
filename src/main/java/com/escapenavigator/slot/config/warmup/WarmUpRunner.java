package com.escapenavigator.slot.config.warmup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

@Component
@Profile("!test")
@RequiredArgsConstructor
@Slf4j
public class WarmUpRunner implements ApplicationRunner, ApplicationListener<WebServerInitializedEvent> {

    private String host;
    private Integer port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            this.host = InetAddress.getLocalHost().getHostAddress();
            this.port = event.getWebServer().getPort();
        } catch (Exception e) {
            // skip warm up
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (host != null && port != null) {
            warmUp(host, port);
            log.info("Application warmed up");
        } else {
            log.warn("Application not warmed up");
        }
    }

    private void warmUp(String host, int port) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + WarmUpController.ENDPOINT;
        HttpEntity<?> request = createEmptyRequestEntity();
        restTemplate.exchange(url, HttpMethod.GET, request, Void.class);
    }

    private HttpEntity<?> createEmptyRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(headers);
    }
}

