package dev.arif.blogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.arif.blogbackend")
@EntityScan(basePackages = "dev.arif.blogbackend")
public class BlogBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBackEndApplication.class, args);
    }
}
