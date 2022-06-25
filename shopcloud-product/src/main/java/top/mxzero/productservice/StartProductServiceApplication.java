package top.mxzero.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "top.mxzero.common.api")
@EnableDiscoveryClient
class StartProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartProductServiceApplication.class, args);
    }
}
