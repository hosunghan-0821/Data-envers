package com.example.contractcore.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Configuration
@RequiredArgsConstructor
public class DataEnverConfig {


    private final EntityManagerFactory entityManagerFactory;

    @Bean
    AuditReader auditReader(){
        System.out.println("실행");
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }
}
