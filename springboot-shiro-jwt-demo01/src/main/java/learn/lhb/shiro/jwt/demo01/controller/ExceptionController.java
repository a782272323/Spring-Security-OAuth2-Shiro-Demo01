package learn.lhb.shiro.jwt.demo01.controller;


import learn.lhb.shiro.jwt.demo01.utils.BaseResult;
import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description  
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 10:58
 */
@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public BaseResult handle401() {
        return BaseResult.error(401, "您没有权限访问！");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public BaseResult globalException(HttpServletRequest request, Throwable ex) {
        return BaseResult.error(500,"访问出错，无法访问: " + ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
