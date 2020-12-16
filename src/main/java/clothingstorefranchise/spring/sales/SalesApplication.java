package clothingstorefranchise.spring.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import clothingstorefranchise.spring.common.security.config.JwtConfiguration;

@SpringBootApplication
@EntityScan({"clothingstorefranchise.spring.sales.model"})
@EnableJpaRepositories({"clothingstorefranchise.spring.sales.repositories"})
@EnableEurekaClient
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("clothingstorefranchise")
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
}
