package top.mxzero.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class StartAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartAdminApplication.class, args);
    }
}
