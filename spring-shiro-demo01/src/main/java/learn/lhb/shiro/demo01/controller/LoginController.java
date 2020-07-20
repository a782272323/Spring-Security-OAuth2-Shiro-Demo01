package learn.lhb.shiro.demo01.controller;


import learn.lhb.shiro.demo01.mapper.UserMapper;
import learn.lhb.shiro.demo01.utils.BaseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/notLogin")
    public BaseResult notLogin() {
        return BaseResult.ok("您尚未登陆");
    }

    @GetMapping("/notRole")
    public BaseResult notRole() {
        return BaseResult.ok("您没有权限");
    }

    @GetMapping("logout")
    public BaseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        // 注销
        subject.logout();
        return BaseResult.ok("注销成功");
    }

    @PostMapping("/login")
    public BaseResult login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = userMapper.getRole(username);
        if ("user".equals(role)) {
            return BaseResult.ok("登录成功");
        }
        if ("admin".equals(role)) {
            return BaseResult.ok("欢迎来到管理员页面");
        }
        return BaseResult.error(401,"没有权限");
    }
}
