package top.mxzero.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "top.mxzero.common.api")
@EnableDiscoveryClient
class StartOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartOrderServiceApplication.class, args);
    }
}
