package learn.lhb.security.oauth2.sso.vue.demo04.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 梁鸿斌
 * @date 2020/2/29
 * @time 18:09
 */
@SpringBootApplication
@MapperScan("learn.lhb.security.oauth2.sso.vue.demo04.permission.dao")
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
    }
}
