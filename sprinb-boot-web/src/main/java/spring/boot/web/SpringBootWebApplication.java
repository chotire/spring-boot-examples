package spring.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import spring.boot.web.config.MybatisConfiguration;

@SpringBootApplication
@Import(MybatisConfiguration.class)
public class SpringBootWebApplication {
    public static void main(String[] args) {
//      SpringApplication app = new SpringApplication(SpringBootWebApplication.class);
//      app.setBannerMode(Mode.OFF);
//      app.run(args);
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}