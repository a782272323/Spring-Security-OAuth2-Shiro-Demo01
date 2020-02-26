package learn.lhb.security.init;

import learn.lhb.security.config.WebSecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Spring Security 初始化
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 14:09
 */
public class SpringSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    SpringSecurityApplicationInitializer() {
        // 由于已经在SpringApplicationInitializer中加载了Spring Security，所以这里可以什么都不用写
        // 若前面没加载，则用下面的代码来加载，使spring security初始化
//        super(WebSecurityConfig.class);
    }
}
