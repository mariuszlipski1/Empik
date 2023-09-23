package com.mlipski.empik.command.feature;

import com.mlipski.empik.command.feature.model.GithubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/users")
public class GithubUserController {

    private final GithubService githubService;

    @GetMapping("/{login}")
    public ResponseEntity<Mono<GithubResponse>> getUserData(@PathVariable String login) {
        //validation
        return ResponseEntity.ok(githubService.fetchGithubUser(login));
    }
}
