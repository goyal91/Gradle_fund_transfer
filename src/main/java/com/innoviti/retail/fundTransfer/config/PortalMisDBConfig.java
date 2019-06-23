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
@EnableJpaRepositories(entityManagerFactoryRef = "pmEntityManagerFactory", transactionManagerRef = "pmTransactionManager", basePackages = {
		"com.innoviti.retail.fundTransfer.repository.mis" })
public class PortalMisDBConfig {

	@Autowired
	private Environment env;

	@Bean(name = "pmDataSource")
	@ConfigurationProperties(prefix = "mis.datasource")
	public DataSource dataSource() {
		// return DataSourceBuilder.create().build();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("mis.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("mis.datasource.url"));
		dataSource.setUsername(env.getProperty("mis.datasource.user"));
		dataSource.setPassword(env.getProperty("mis.datasource.password"));

		return dataSource;
	}

	@Bean(name = "pmEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean pmEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("pmDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.innoviti.retail.fundTransfer.model.mis")
				.persistenceUnit("misPU").build();
	}

	@Bean(name = "pmTransactionManager")
	public PlatformTransactionManager pmTransactionManager(
			@Qualifier("pmEntityManagerFactory") EntityManagerFactory pmEntityManagerFactory) {
		return new JpaTransactionManager(pmEntityManagerFactory);
	}
}