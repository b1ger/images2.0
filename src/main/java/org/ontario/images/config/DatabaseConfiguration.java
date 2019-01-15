package org.ontario.images.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("org.ontario.images.repositories")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
