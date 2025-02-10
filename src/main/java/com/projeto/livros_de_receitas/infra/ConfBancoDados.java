package com.projeto.livros_de_receitas.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfBancoDados {

    @Autowired Environment env;

    @Bean
    public DataSource dataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        return dataSource;
    }
}
