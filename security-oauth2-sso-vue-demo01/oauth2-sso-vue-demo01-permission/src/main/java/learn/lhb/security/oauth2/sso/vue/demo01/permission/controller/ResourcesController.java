package learn.lhb.security.oauth2.sso.vue.demo01.permission.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁鸿斌
 * @date 2020/2/29.
 * @time 01:29
 */
@RestController
public class ResourcesController {

    @GetMapping("/r1")
    @PreAuthorize("hasAnyAUthority('p1')")
    private String r1() {
        return "访问资源1";
    }
}
