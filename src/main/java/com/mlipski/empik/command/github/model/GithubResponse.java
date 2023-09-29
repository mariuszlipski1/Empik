package com.mlipski.empik.command.github.model;

public record GithubResponse(
        Long id,
        String login,
        String name,
        String type,
        String avatarUrl,
        String createdAt,
        Double calculations
) {}