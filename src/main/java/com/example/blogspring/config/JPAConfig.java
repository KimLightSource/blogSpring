package com.example.blogspring.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JPAConfig {

    @PersistenceContext
    private final EntityManager entityManager;

//    @Bean
//    public JPAQueryFactory queryFactory() {
//        return new JPAQueryFactory(entityManager);
//    }

}
