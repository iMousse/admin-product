package com.admin.controller;


import com.admin.domain.Member;
import com.admin.domain.Orders;
import com.admin.service.MemberService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-24
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    
    @GetMapping("/login.do")
    public String getLogin() {
        return "member-login";
    }


    @PostMapping("/login.do")
    public String login(Member member, HttpServletRequest request) {


        Member memberDB = memberService.login(member.getMemberName(), member.getMemberPhone());

        if (memberDB == null) {
            request.getSession().setAttribute("errorMsg", "用户名或手机错误");

            return "member-login";

        }


        request.getSession().setAttribute("member", memberDB);

        return "redirect:findAll.do";

    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = false, defaultValue = "4") Integer size,
                                HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        Member member = (Member) request.getSession().getAttribute("member");
        List<Orders> ordersList = memberService.findAll(member.getMemberId(), page, size);
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("member-orders");

        return mv;
    }


    //管理员进入审核页面
    @RequestMapping("/auditLoad.do")
    public ModelAndView auditLoad(Long orderId) {

        ModelAndView mv = new ModelAndView();

        Orders orders = memberService.getOrderById(orderId);

        mv.addObject("orders", orders);

        mv.setViewName("member-audit");

        return mv;
    }

    @RequestMapping("/auditManger.do")
    public String auditManger(Orders orders) {


        memberService.audit(orders);


        return "redirect:findAll.do";
    }


    //用户注销
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();

        return "redirect:/member/login.do";
    }
}
