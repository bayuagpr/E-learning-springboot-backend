/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *
 * @author Administrator
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.elearning.server.repository")
@PropertySource("classpath:application.properties")
@EnableJpaAuditing
public class DataConfig {

	private final String PROPERTY_DRIVER = "driver";
	private final String PROPERTY_URL = "url";
	private final String PROPERTY_USERNAME = "user";
	private final String PROPERTY_PASSWORD = "password";
	private final String PROPERTY_DDL_AUTO = "hibernate.ddl-auto";
        private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
	private final String PROPERTY_DIALECT = "hibernate.dialect";

	@Autowired
	Environment environment;

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
                Properties jpaProperties = new Properties();
                jpaProperties.put(PROPERTY_DIALECT, environment.getRequiredProperty(PROPERTY_DIALECT));
                jpaProperties.put(PROPERTY_DDL_AUTO, environment.getRequiredProperty(PROPERTY_DDL_AUTO));
                jpaProperties.put(PROPERTY_SHOW_SQL, environment.getRequiredProperty(PROPERTY_SHOW_SQL));
                LocalContainerEntityManagerFactoryBean em = new  LocalContainerEntityManagerFactoryBean();
                em.setDataSource(dataSource());
                em.setJpaProperties(jpaProperties);
                em.setPackagesToScan("com.elearning.server.model");     
                JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
                em.setJpaVendorAdapter(vendorAdapter);
                return em;
	}

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(environment.getProperty(PROPERTY_URL));
		ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
		ds.setPassword(environment.getProperty(PROPERTY_PASSWORD));
		ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
		return ds;
	}


	@Bean
	JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
        
        @Bean
        public Module hibernate5Module()
        {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.enable(Feature.FORCE_LAZY_LOADING);
        return hibernate5Module;
        }
        
        @Bean
        public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(this.jacksonBuilder().build());

        return converter;
        }

    public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

    Hibernate5Module hibernateModule = new Hibernate5Module();

    hibernateModule.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);

    builder.modules(hibernateModule);

    // Spring MVC default Objectmapper configuration
    builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    builder.featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION);

    return builder;
}

//        @Bean
//        @Primary
//        public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.createXmlMapper(true).build();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
////  objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
//        return objectMapper;
//}
}
