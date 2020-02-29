package learn.lhb.security.oauth2.sso.vue.demo01.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 18:58
 */
@SpringBootApplication
@MapperScan(value = "learn.lhb.security.oauth2.sso.vue.demo01.permission.dao")
@EnableResourceServer
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
    }
}
