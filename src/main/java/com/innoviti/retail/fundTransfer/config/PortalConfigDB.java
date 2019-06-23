package com.innoviti.retail.fundTransfer.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("file:config/application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "pcEntityManagerFactory", transactionManagerRef = "pcTransactionManager", basePackages = {
		"com.innoviti.retail.fundTransfer.repository.config" })
public class PortalConfigDB {

	@Autowired
	private Environment env;

	@Bean(name = "pcDataSource")
	@ConfigurationProperties(prefix = "config.datasource")
	public DataSource dataSource() {
		// return DataSourceBuilder.create().build();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("config.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("config.datasource.url"));
		dataSource.setUsername(env.getProperty("config.datasource.user"));
		dataSource.setPassword(env.getProperty("config.datasource.password"));

		return dataSource;
	}

	@Bean(name = "pcEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean pmEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("pcDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.innoviti.retail.fundTransfer.model.config")
				.persistenceUnit("configPU").build();
	}

	@Bean(name = "pcTransactionManager")
	public PlatformTransactionManager pmTransactionManager(
			@Qualifier("pcEntityManagerFactory") EntityManagerFactory pcEntityManagerFactory) {
		return new JpaTransactionManager(pcEntityManagerFactory);
	}
}
