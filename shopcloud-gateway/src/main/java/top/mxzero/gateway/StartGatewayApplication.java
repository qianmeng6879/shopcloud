package top.mxzero.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StartGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartGatewayApplication.class, args);
    }
}
