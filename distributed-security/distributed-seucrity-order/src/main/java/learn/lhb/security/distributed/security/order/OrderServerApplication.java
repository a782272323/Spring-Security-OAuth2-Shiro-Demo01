package learn.lhb.security.distributed.security.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 01:27
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
