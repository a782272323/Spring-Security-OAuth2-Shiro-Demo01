package learn.lhb.security.springmvc.interceptor;

import learn.lhb.security.springmvc.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权拦截器
 * <p>
 * todo 有空再回来看源码
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 11:50
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    /**
     * 请求拦截方法(在调用所以controller方法之前来调用这个方法)
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在这个方法中校验用户请求的url是否在用户的授权范围内
        // 读取会话信息，从中取出用户身份信息
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null) {
            // 没有信息，也就是无法认证，要求登录
            writeContent(response, "请登录");
        }
        // 获取用户权限
        UserDto userDto = (UserDto) object;
        // 请求的url
        String requestURL = request.getRequestURI();
        // 判断是否有权限，有权限就放行（return true）
        // todo contains这个方法是在数组中查找指定元素
        if (userDto.getAuthorities().contains("p1") && requestURL.contains("/r/r1")) {
            return true;
        }
        if (userDto.getAuthorities().contains("p2") && requestURL.contains("/r/r2")) {
            return true;
        }
        writeContent(response,"没有权限，拒绝访问");

        return false;

    }


    /**
     * 响应信息给客户端（即浏览器）
     *
     * @param response
     * @param msg
     */
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        PrintWriter writer = response.getWriter();
        // 打印
        writer.println(msg);
        // 关闭
        writer.close();
        // 清楚response缓存
        response.resetBuffer();
    }
}


