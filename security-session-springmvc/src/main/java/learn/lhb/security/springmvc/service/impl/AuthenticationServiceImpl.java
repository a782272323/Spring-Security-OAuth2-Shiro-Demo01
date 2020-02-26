package learn.lhb.security.springmvc.service.impl;

import learn.lhb.security.springmvc.model.AuthenticationRequest;
import learn.lhb.security.springmvc.model.UserDto;
import learn.lhb.security.springmvc.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 用户认证接口实现类
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 09:37
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * 用户认证,校验用户身份信息是否合法
     *
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        // 校验参数是否为空
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账户或密码为空");
        }

        // 根据账户查询数据库，这里的数据采用模拟的
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if (userDto == null) {
            throw new RuntimeException("查询不到该用户");
        }

        // 校验密码
        if (!authenticationRequest.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("账户或密码错误！");
        }

        // 认证通过，返回用户身份信息
        return userDto;
    }

    // 根据账户查询用户信息,模拟用户查询
    public UserDto getUserDto(String username) {
        return userMap.get(username);
    }

    // 用户信息
    private Map<String, UserDto> userMap = new HashMap<>();
    {
        Set<String> authorities1 = new HashSet<>();
        // 和访问资源1对应
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        // 和访问资源2对应
        authorities2.add("p2");
        userMap.put("zhangsan", new UserDto("1010", "zhangsan", "123", "张三", "133443",authorities1));
        userMap.put("lisi", new UserDto("1011", "lisi", "456", "李四", "155443",authorities2));
    }



}
