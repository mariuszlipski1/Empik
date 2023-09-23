package com.mlipski.empik.command.feature.model;

public record PayloadGithub(
        Long id,
        String login,
        String name,
        String type,
        String avatar_url,
        String created_at,
        Integer followers,
        Integer public_repos
        ) {}