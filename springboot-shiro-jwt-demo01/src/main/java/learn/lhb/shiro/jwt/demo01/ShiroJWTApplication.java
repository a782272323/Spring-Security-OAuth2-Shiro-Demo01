package learn.lhb.shiro.jwt.demo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "learn.lhb.shiro.jwt.demo01.mapper")
public class ShiroJWTApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroJWTApplication.class, args);
    }
}
