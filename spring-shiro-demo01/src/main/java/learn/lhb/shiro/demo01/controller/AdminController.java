package learn.lhb.shiro.demo01.controller;

import learn.lhb.shiro.demo01.utils.BaseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public BaseResult getMessage() {
        return BaseResult.ok("您拥有管理员权限，可以获得该接口的信息！");
    }
}
