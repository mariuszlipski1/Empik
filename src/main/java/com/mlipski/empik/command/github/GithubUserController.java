package com.mlipski.empik.command.github;

import com.mlipski.empik.command.github.model.GithubResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/users")
@Slf4j
public class GithubUserController {

    private final GithubService githubService;

    @GetMapping("/{login}")
    public ResponseEntity<Mono<GithubResponse>> getUserData(@PathVariable String login) {
        return ResponseEntity.ok(githubService.fetchGithubUser(login));
    }
}