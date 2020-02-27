package learn.lhb.security.distributed.security.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 13:07
 */
@RestController
public class OrderController {

    @GetMapping("/r1")
    @PreAuthorize("hasAnyAuthority('p1')") // 拥有p1权限才能访问
    public String r1()  {
        return "访问资源1";
    }
}
