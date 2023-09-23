package com.mlipski.empik.command.feature;

import com.mlipski.empik.command.feature.model.GithubResponse;
import com.mlipski.empik.command.feature.model.PayloadGithub;
import com.mlipski.empik.exception.BadRequestException;
import com.mlipski.empik.exception.NoFoundException;
import com.mlipski.empik.query.ApiUsage;
import com.mlipski.empik.query.ApiUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GithubService {

    private static final String API_GITHUBS_COM = "https://api.github.com";
    WebClient client = WebClient.create(API_GITHUBS_COM);
    private final ApiUsageRepository apiUsageRepository;

    public Mono<GithubResponse> fetchGithubUser(String login) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{login}")
                        .build(login))
                .exchangeToMono(this::handleGithubResponse);
    }

    private Mono<GithubResponse> handleGithubResponse(ClientResponse response) {
        HttpStatusCode statusCode = response.statusCode();

        if (statusCode.is2xxSuccessful()) {
            return response.bodyToMono(PayloadGithub.class)
                    .map(this::calculateAndCreateResponse);
        } else {
            return Mono.error(translateError(statusCode));
        }
    }

    private RuntimeException translateError(HttpStatusCode statusCode) {
        if (statusCode.equals(NOT_FOUND)) {
            return new NoFoundException("User not found");
        } else {
            return new BadRequestException("Unknown error with status: " + statusCode);
        }
    }

    private void updateRequestCount(String login) {
        ApiUsage usage = apiUsageRepository.findById(login).orElse(new ApiUsage(login, 0L));
        usage.setRequestCount(usage.getRequestCount() + 1);
        apiUsageRepository.save(usage);
    }

    private GithubResponse calculateAndCreateResponse(PayloadGithub payloadGithub) {
        double calculations = 6.0 / payloadGithub.followers() * (2 + payloadGithub.public_repos());
        updateRequestCount(payloadGithub.login());
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
