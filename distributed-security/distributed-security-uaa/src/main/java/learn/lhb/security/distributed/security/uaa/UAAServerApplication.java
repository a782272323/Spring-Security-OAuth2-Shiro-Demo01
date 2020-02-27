package learn.lhb.security.distributed.security.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 00:50
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"learn.lhb.security.distributed.security.uaa"})
public class UAAServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UAAServerApplication.class, args);
    }
}
