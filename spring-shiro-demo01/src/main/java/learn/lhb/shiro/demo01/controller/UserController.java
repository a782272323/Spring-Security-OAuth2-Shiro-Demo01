package learn.lhb.shiro.demo01.controller;

import learn.lhb.shiro.demo01.utils.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 10:56
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public BaseResult getMessage() {
        return BaseResult.ok("您拥有用户权限，可以获得该接口的信息！");
    }

    @GetMapping("/info")
    public BaseResult info() {
        return BaseResult.ok();
    }
}
