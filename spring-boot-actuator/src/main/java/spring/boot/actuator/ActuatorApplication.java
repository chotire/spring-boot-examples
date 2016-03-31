package spring.boot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer //Spring Boot Admin 서버 등록
public class ActuatorApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ActuatorApplication.class, args);
    }
}