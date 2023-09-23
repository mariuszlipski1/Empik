package com.mlipski.empik.query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_usage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiUsage {

        @Id
        @Column(name = "login", nullable = false, unique = true)
        private String login;

        @Column(name = "request_count", nullable = false)
        private Long requestCount;

}