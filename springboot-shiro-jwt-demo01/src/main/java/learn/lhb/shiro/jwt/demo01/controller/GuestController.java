package learn.lhb.shiro.jwt.demo01.controller;


import learn.lhb.shiro.jwt.demo01.utils.BaseResult;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/welcome")
    public BaseResult login() {
        return BaseResult.ok("欢迎访问游客页面！");
    }

}
