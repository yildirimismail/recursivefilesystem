package com.fsystem.hibernate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * Configuration Bean that configures hibernateProperties of hibernateSessionFactory in hibernate.xml
 */
@Configuration
public class HibernatePropertiesConfiguration {

    @Value(value = "${hibernate.connection.datasource:#{null}}")
    private String hibernate_connection_datasource;

    @Value("${hibernate.connection.driver_class:#{null}}")
    private String hibernate_connection_driver_class;

    @Value("${hibernate.connection.url:#{null}}")
    private String hibernate_connection_url;

    @Value(value = "${hibernate.connection.username:#{null}}")
    private String hibernate_connection_username;

    @Value("${hibernate.connection.password:#{null}}")
    private String hibernate_connection_password;

    @Value("${hibernate.dialect}")
    private String hibernate_dialect;

    @Value("${hibernate.show_sql}")
    private String hibernate_show_sql;

    @Value("${hibernate.format_sql}")
    private String hibernate_format_sql;

    @Value("${hibernate.generate_statistics}")
    private String hibernate_generate_statistics;

    @Value("${hibernate.use_sql_comments}")
    private String hibernate_use_sql_comments;

    @Value("${hibernate.connection.provider_class:#{null}}")
    private String hibernate_connection_provider_class;

    @Value("${hibernate.temp.use_jdbc_metadata_defaults:#{null}}")
    private String hibernate_temp_use_jdbc_metadata_defaults;

    @Value("${hibernate.c3p0.min_size:#{null}}")
    private String hibernate_c3p0_min_size;

    @Value("${hibernate.c3p0.max_size:#{null}}")
    private String hibernate_c3p0_max_size;

    @Value("${hibernate.c3p0.timeout:#{null}}")
    private String hibernate_c3p0_timeout;

    @Value("${hibernate.c3p0.max_statements:#{null}}")
    private String hibernate_c3p0_max_statements;

    @Value("${hibernate.c3p0.idle_test_period:#{null}}")
    private String hibernate_c3p0_idle_test_period;

    @Value("${hibernate.c3p0.acquire_increment:#{null}}")
    private String hibernate_c3p0_acquire_increment;

    @Value("${hibernate.autoReconnect:#{null}}")
    private String hibernate_autoReconnect;

    @Value("${hibernate.hbm2ddl.auto:#{null}}")
    private String hibernate_create;


    @Bean(name = "hibernateProperties")
    public Properties hibernateProperties() {
        Properties props = new Properties();

        props.put("hibernate.dialect", hibernate_dialect);
        props.put("hibernate.show_sql", hibernate_show_sql);
        props.put("hibernate.format_sql", hibernate_format_sql);
        props.put("hibernate.generate_statistics", hibernate_generate_statistics);
        props.put("hibernate.use_sql_comments", hibernate_use_sql_comments);
        props.put("hibernate.autoReconnect", hibernate_autoReconnect);
        props.put("hibernate.hbm2ddl.auto", hibernate_create);

        if (StringUtils.hasText(hibernate_connection_datasource)) {
            props.put("hibernate.connection.datasource", hibernate_connection_datasource);

        } else {
            props.put("hibernate.connection.driver_class", hibernate_connection_driver_class);
            props.put("hibernate.connection.url", hibernate_connection_url);

            if (!hibernate_connection_url.contains("integratedSecurity")) {
                props.put("hibernate.connection.username", hibernate_connection_username);
                props.put("hibernate.connection.password", hibernate_connection_password);
            }

            props.put("hibernate.connection.provider_class", hibernate_connection_provider_class);

            // <!-- to prevent INFO LobCreatorBuilder:123 - Disabling contextual LOB creation as createClob() method threw error : java.lang.reflect.InvocationTargetException Error -->
            props.put("hibernate.temp.use_jdbc_metadata_defaults", hibernate_temp_use_jdbc_metadata_defaults);
            props.put("hibernate.c3p0.min_size", hibernate_c3p0_min_size);
            props.put("hibernate.c3p0.max_size", hibernate_c3p0_max_size);
            props.put("hibernate.c3p0.timeout", hibernate_c3p0_timeout);
            props.put("hibernate.c3p0.max_statements", hibernate_c3p0_max_statements);
            props.put("hibernate.c3p0.idle_test_period", hibernate_c3p0_idle_test_period);
            props.put("hibernate.c3p0.acquire_increment", hibernate_c3p0_acquire_increment);
        }

        return props;
    }
}
