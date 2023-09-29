package com.mlipski.empik.command.api_usage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiUsageService {

    private final ApiUsageRepository apiUsageRepository;
    @Transactional
    public void updateRequestCount(String login) {
        ApiUsage usage = apiUsageRepository.findById(login).orElse(new ApiUsage(login, 0L));
        usage.setRequestCount(usage.getRequestCount() + 1);
        apiUsageRepository.save(usage);
    }
}
