package com.admin.interceptor;

import com.admin.domain.Member;
import com.admin.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-04-12
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */
public class MemberInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Member member = (Member) request.getSession().getAttribute("member");

        if (member == null) {
            request.getRequestDispatcher("/member/login.do").forward(request, response);
            return false;
        }

        return true;
    }
}
