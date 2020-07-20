package learn.lhb.shiro.demo01.config;

import learn.lhb.shiro.demo01.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(" 权限认证 ");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户角色
        String role = userMapper.getRole(username);
        Set<String> set = new HashSet<>();
        set.add(role);
        // 设置用户拥有的角色
        authorizationInfo.setRoles(set);
        return authorizationInfo;
    }

    /**
     * 获取身份认证信息
     * @param authenticationToken token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(" 身份认证 ");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        String password = userMapper.getPassword(token.getUsername());
        if (password == null) {
            throw new AccountException("用户名错误");
        }
        if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码错误");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}
