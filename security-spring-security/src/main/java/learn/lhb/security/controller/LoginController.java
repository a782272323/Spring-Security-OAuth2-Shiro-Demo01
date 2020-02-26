package learn.lhb.security.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 14:19
 */
@RestController
public class LoginController {

    /**
     * 认证登录成功跳转的页面,退出的话直接在spring security的登录成功页面url后面加logout
     * @return
     */
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=utf-8"})
    public String loginSuccess()    {
        return "登录成功";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=utf-8"})
    public String r1() {
        return "访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=utf-8"})
    public String r2() {
        return "访问资源2";
    }
}
