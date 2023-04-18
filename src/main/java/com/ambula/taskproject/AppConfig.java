package com.ambula.taskproject;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig{
	@Bean
	public DataSource dataSource() {
	   DriverManagerDataSource dataSource = new DriverManagerDataSource();
	   dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
	   dataSource.setUrl("jdbc:hsqldb:mem:testdb");
	   dataSource.setUsername("sa");
	   dataSource.setPassword("");
	   return dataSource;
	}

	   @Bean
	   public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	      return new JdbcTemplate(dataSource);
	   }

	   
}
