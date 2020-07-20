package learn.lhb.shiro.jwt.demo01.controller;


import learn.lhb.shiro.jwt.demo01.mapper.UserMapper;
import learn.lhb.shiro.jwt.demo01.utils.BaseResult;
import learn.lhb.shiro.jwt.demo01.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
    public BaseResult login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        String realPassword = userMapper.getPassword(username);
        Map<String, Object> map = new HashMap<>();
        if (realPassword == null) {
            return BaseResult.error(401, "用户名错误");
        } else if (!realPassword.equals(password)) {
            return BaseResult.error(401, "密码错误");
        } else {
            map.put("token", JWTUtils.createToken(username));
            return BaseResult.ok().put(200,"登录成功","data", map);
        }
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public BaseResult unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return BaseResult.error(401, message);
    }
}
