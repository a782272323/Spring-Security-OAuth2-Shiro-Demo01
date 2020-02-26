package learn.lhb.security.springmvc.controller;

import learn.lhb.security.springmvc.model.AuthenticationRequest;
import learn.lhb.security.springmvc.model.UserDto;
import learn.lhb.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 登录
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 11:11
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 用户登录
     *
     * @param authenticationRequest 登录请求
     * @param session               http 会话
     * @return
     */
    @RequestMapping(value = "/login", produces = {"text/plain;charset=utf-8"})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDto userDto = authenticationService.authentication(authenticationRequest);

        // 用户信息存入 session
        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getUsername() + "登录成功";
    }

    /**
     * 退出登录，同时清空session里面的用户信息和别信息
     * @param session
     * @return
     */
    @GetMapping(value = "logout", produces = {"text/plain;charset=utf-8"})
    public String logout(HttpSession session) {
        // 清楚session
        session.invalidate();
        return "退出成功";
    }

    /**
     * 访问资源1
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf-8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDto) userObj).getFullname();
        }   else {
            fullname = "匿名";
        }

        return fullname + "访问资源1";
    }
}
