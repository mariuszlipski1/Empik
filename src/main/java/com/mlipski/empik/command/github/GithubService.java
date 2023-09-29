package com.mlipski.empik.command.github;

import com.mlipski.empik.command.api_usage.ApiUsageService;
import com.mlipski.empik.command.github.model.GithubResponse;
import com.mlipski.empik.command.github.model.PayloadGithub;
import com.mlipski.empik.exception.BadRequestException;
import com.mlipski.empik.exception.NoFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class GithubService {

    private static final String API_GITHUBS_COM = "https://api.github.com";
    WebClient client = WebClient.create(API_GITHUBS_COM);
    private final ApiUsageService apiUsageService;

    public Mono<GithubResponse> fetchGithubUser(String login) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{login}")
                        .build(login))
                .exchangeToMono(this::handleGithubResponse);
    }

    private Mono<GithubResponse> handleGithubResponse(ClientResponse response) {
        HttpStatusCode statusCode = response.statusCode();

        if (statusCode.is2xxSuccessful()) {
            log.info("Github response: {}", response.statusCode());
            return response.bodyToMono(PayloadGithub.class)
                    .map(this::calculateAndCreateResponse);
        } else {
            log.error("Github response: {}", response.statusCode());
            return Mono.error(translateError(statusCode));
        }
    }

    private RuntimeException translateError(HttpStatusCode statusCode) {
        if (statusCode.equals(NOT_FOUND)) {
            log.error("User not found");
            return new NoFoundException("User not found");
        } else {
            log.error("Unknown error with status: {}", statusCode);
            return new BadRequestException("Unknown error with status: " + statusCode);
        }
    }

    private GithubResponse calculateAndCreateResponse(PayloadGithub payloadGithub) {
        double calculations = 6.0 / payloadGithub.followers() * (2 + payloadGithub.public_repos());
        apiUsageService.updateRequestCount(payloadGithub.login());
        return new GithubResponse(
                payloadGithub.id(),
                payloadGithub.login(),
                payloadGithub.name(),
                payloadGithub.type(),
                payloadGithub.avatar_url(),
                payloadGithub.created_at(),
                calculations
        );
    }
}