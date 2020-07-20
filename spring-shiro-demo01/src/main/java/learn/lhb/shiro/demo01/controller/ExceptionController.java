package learn.lhb.shiro.demo01.controller;

import learn.lhb.shiro.demo01.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountException;

/**
 * @Description  
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 10:58
 */
@RestControllerAdvice
public class ExceptionController {

    // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AccountException.class)
    public BaseResult handleShiroException(Exception ex) {
        return BaseResult.error(ex.getMessage());
    }
}
