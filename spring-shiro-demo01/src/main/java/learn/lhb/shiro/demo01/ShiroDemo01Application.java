package learn.lhb.shiro.demo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "learn.lhb.shiro.demo01.mapper")
public class ShiroDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemo01Application.class, args);
    }
}
