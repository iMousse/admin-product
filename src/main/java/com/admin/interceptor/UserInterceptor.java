package com.admin.interceptor;

import com.admin.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-29
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public class UserInterceptor implements HandlerInterceptor {

    /**
     * 预处理
     * controller方法执行前
     * return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
     * return false不放行
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("/user/login.do").forward(request, response);
            return false;
        }

        return true;
    }
}
