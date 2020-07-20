package learn.lhb.shiro.jwt.demo01.controller;

import learn.lhb.shiro.jwt.demo01.mapper.UserMapper;
import learn.lhb.shiro.jwt.demo01.utils.BaseResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public BaseResult getUser() {
        List<String> list = userMapper.getUser();
        return BaseResult.ok().put(200,"请求成功","data",list);
    }

    /**
     * 封号操作
     */
    @PostMapping("/banUser")
    @RequiresRoles("admin")
    public BaseResult updatePassword(String username) {
        userMapper.banUser(username);
        return BaseResult.ok("账户锁定成功");
    }

}
