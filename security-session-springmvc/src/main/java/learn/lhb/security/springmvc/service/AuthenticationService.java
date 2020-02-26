package learn.lhb.security.springmvc.service;

import learn.lhb.security.springmvc.model.AuthenticationRequest;
import learn.lhb.security.springmvc.model.UserDto;

/**
 *
 * 认证服务
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 09:32
 */
public interface AuthenticationService {

    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
