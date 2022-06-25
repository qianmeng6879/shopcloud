package top.mxzero.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StartUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartUserServiceApplication.class, args);
    }
}
