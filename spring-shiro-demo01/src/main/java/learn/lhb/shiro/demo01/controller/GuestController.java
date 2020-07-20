package learn.lhb.shiro.demo01.controller;

import learn.lhb.shiro.demo01.utils.BaseResult;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 10:49
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public BaseResult login() {
        return BaseResult.ok("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public BaseResult submitLogin() {
        return BaseResult.ok("您拥有获得该接口的信息的权限！");
    }

}
