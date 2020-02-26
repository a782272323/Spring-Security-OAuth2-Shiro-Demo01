package learn.lhb.security.spring.boot.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 15:38
 */
@RestController
public class LoginController {

    /**
     * 认证登录成功跳转的页面,退出的话直接在spring security的登录成功页面url后面加logout
     * @return
     */
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=utf-8"})
    public String loginSuccess()    {
        // 提示具体的用户名称登录成功
        String username = getUsername();
        return username + ",登录成功";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=utf-8"})
    // 方法级别的授权
//    @PreAuthorize("hasAuthority('p1')") // 拥有 p1 权限才可以访问
    @PreAuthorize("hasAnyAuthority('p1','p2',p3)") // 拥有多个权限才可以访问
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

    /**
     * 获取当前登录用户名
     * @return
     */
    private String getUsername() {
        // 得到当前通过认证的用户身份信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 判断是否为空
        if (!authentication.isAuthenticated()) {
            return null;
        }
        // 获取用户身份
        Object principal = authentication.getPrincipal();
        String username = null;
        if (username == null)   {
            username = "匿名";
        }
        // 判断用户身份是不是UserDetails的对象属性
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username = ((org.springframework.security.core.userdetails.UserDetails)principal).getUsername();
        }   else {
            username = principal.toString();
        }
        return username;
    }
}
