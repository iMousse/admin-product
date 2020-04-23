package com.admin.controller;


import com.admin.constant.Constant;
import com.admin.domain.Announce;
import com.admin.domain.Message;
import com.admin.domain.Role;
import com.admin.domain.User;
import com.admin.service.UserService;
import com.admin.utils.DownUtils;
import com.admin.vo.AjaxResult;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    //主页界面
    @RequestMapping("/index.do")
    public String index() {
        return "beta-template";
    }


    //进入登录界面
    @GetMapping("/login.do")
    public String getLogin() {
        return "user-login";
    }

    //进入注册页面
    @GetMapping("/registry.do")
    public String getRegistry() {
        return "user-registry";
    }

    @PostMapping("/registry.do")
    public String postRegistry(User user) {

        //设置用户为关闭
        user.setUserStatus(Constant.UserStatus.USER_CLOSE);

        userService.addUser(user);

        return "redirect:login.do";

    }
    //验证用户名
    @ResponseBody
    @RequestMapping(value = "/username.do", produces = "application/json;charset=utf-8")
    public AjaxResult validateUsername(HttpServletRequest request){

        String username = request.getParameter("username");

        AjaxResult result = new AjaxResult();

        User user = userService.getUserByUsername(username);

        if (user == null) {
            result.setName("success");
        } else {
            result.setName("fail");
        }

        return result;
    }


    //验证邮箱
    @ResponseBody
    @RequestMapping(value = "/email.do", produces = "application/json;charset=utf-8")
    public AjaxResult validateEmail(HttpServletRequest request){

        String userEmail = request.getParameter("userEmail");

        AjaxResult result = new AjaxResult();

        User user = userService.getUserByEmail(userEmail);

        if (user == null) {
            result.setName("success");
        } else {
            result.setName("fail");
        }

        return result;
    }

    //验证密码
    @ResponseBody
    @RequestMapping(value = "/password.do", produces = "application/json;charset=utf-8")
    public AjaxResult validatePassword(HttpServletRequest request){

        String oldPwd = request.getParameter("password");

        User userInfo = (User) request.getSession().getAttribute("user");
        userInfo.setPassword(oldPwd);

        AjaxResult result = new AjaxResult();

        User user = userService.getUserByUsernameAndPassword(userInfo);

        if (user != null) {
            result.setName("success");
        } else {
            result.setName("fail");
        }

        return result;
    }
    


    //添加用户
    @PostMapping("/save.do")
    public String save(User user) {
        userService.addUser(user);
        return "redirect:findAll.do";
    }



    //用户登录
    @PostMapping("/login.do")
    public String postLogin(User userInfo, HttpServletRequest request) {

        User user = userService.getUserByUsernameAndPassword(userInfo);


        //如果用户为空
        if (user == null) {
            request.setAttribute("errorMsg", "用户名或密码错误");
            return "user-login";
        }



        //如果用户状态为关闭
        if (user.getUserStatus().equals(Constant.UserStatus.USER_CLOSE)) {
            request.setAttribute("errorMsg","用户已被禁用");
            return "user-login";
        }


        //如果用户有角色
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getRoleName().equalsIgnoreCase("user")) {
                request.getSession().setAttribute("user", user);
                return "redirect:index.do";
            }
        }

        //否则重新登录
        request.setAttribute("errorMsg", "用户权限不足");
        return "user-login";
    }

    //用户注销
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();

        return "redirect:/index.jsp";
    }


    //所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = false, defaultValue = "8") Integer size) {

        List<User> userList = userService.getAll(page,size);

        PageInfo<User> pageInfo = new PageInfo<>(userList);

        ModelAndView mv = new ModelAndView();

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");

        return mv;
    }




    //修改用户
    @PostMapping("/update.do")
    public String postUpdate(User user) {

        userService.updateUser(user);

        return "redirect:findAll.do";
    }



    //查看用户
    @RequestMapping("/findById.do")
    public ModelAndView getById(Long userId) {

        User user = userService.getUserById(userId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user-information");

        return mv;
    }

    //查看个人信息
    @RequestMapping("/information.do")
    public ModelAndView findUser(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        User userInfo = userService.getUserById(user.getUserId());

        ModelAndView mv = new ModelAndView();

        mv.addObject("user", userInfo);
        mv.setViewName("user-information");

        return mv;
    }

    //修改密码
    @PostMapping("/update/pwd.do")
    public String postUpdatePwd(HttpServletRequest request, String newPwd) {

        User userInfo = (User) request.getSession().getAttribute("user");

        userService.updateUserPwd(userInfo.getUserId(), newPwd);

        return "redirect:/user/logout.do";
    }


    //查看用户没有的角色
    @RequestMapping("/findByIdAndAllRole.do")
    public ModelAndView findByIdAndAllRole(Long userId){

        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        User user = userService.getUserById(userId);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.getOtherRoles(userId);

        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-add-role");

        return mv;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(Long userId, Long[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

    //删除用户,角色和居住信息
    @RequestMapping("/deleteById.do")
    public String delete(Long userId) {

        userService.deleteUserById(userId);


        return "redirect:findAll.do";
    }


    //文件上传
    @RequestMapping("/upload.do")
    public String saleUpload(HttpServletRequest request,
                             MultipartFile upload,
                             @Param("userId") Long userId,
                             @Param("userFilename")String userFilename) throws IOException {

        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");

        File file = new File(realPath);
        if (!file.exists()) { file.mkdir(); }

        String userFileURL = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        userFileURL = uuid + "_" + userFileURL;

        upload.transferTo(new File(realPath, userFileURL));

        userService.setUserFile(userId, userFilename,userFileURL);

        return "redirect:findAll.do";
    }



    //文件下载
    @RequestMapping("/download.do")
    public void managerDownload(HttpServletRequest request, HttpServletResponse response, String filename)
            throws IOException {
        //将filename解码成UTF-8
        filename = new String(filename.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //获取到服务器文件路径
        String realPath = request.getServletContext().getRealPath("/uploads/" + filename);

        //获取文件输入流
        FileInputStream fis = new FileInputStream(realPath);

        //获取文件到mimeType和响应头类型
        String mimeType = request.getServletContext().getMimeType(filename);
        response.setHeader("content-type", mimeType);

        //解决中文文件名问题
        String header = request.getHeader("user-agent");
        filename = DownUtils.getFileName(header, filename);

        //设置下载文件的名字
        String[] filenames = filename.split("_");
        filename = filenames[1];

        //设置响应头的打开方式
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        //文件下载
        ServletOutputStream sos = response.getOutputStream();

        byte[] buff = new byte[1024 * 8];
        int length = 0;

        while ((length = fis.read(buff)) != -1) {
            sos.write(buff, 0, length);
        }

        fis.close();
    }

    //留言列表
    @GetMapping("/message.do")
    public ModelAndView getAudit(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
                                 HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        List<Message> messages = userService.getMessageByUserId(user.getUserId(),page,size);

        PageInfo<Message> pageInfo = new PageInfo<>(messages);

        ModelAndView mv = new ModelAndView();

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-message");

        return mv;
    }

    //添加留言
    @PostMapping("/message/add.do")
    public String addMessage(HttpServletRequest request,Message message) {

        User user  = (User) request.getSession().getAttribute("user");

        message.setMessageUserId(user.getUserId());
        message.setMessageStartDate(new Date());
        message.setMessageStatus(Constant.MessageStatus.MESSAGE_OPEN);

        userService.addMessage(message);


        return "redirect:/user/message.do";
    }



    //修改留言
    @PostMapping("/message/update.do")
    public String messageUpdatePost(Message message) {

        userService.updateMessage(message);

        return "redirect:/user/message.do";
    }

    //查看没有回复过的留言
    @GetMapping("/suggestion.do")
    public ModelAndView suggestion(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                   @RequestParam(name = "size", required = false, defaultValue = "8") Integer size) {

        List<Message> messages =  userService.getMessageByStatus(Constant.MessageStatus.MESSAGE_OPEN,page,size);
        PageInfo<Message> pageInfo = new PageInfo<>(messages);

        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-suggestion");

        return mv;
    }

    //回复留言
    @PostMapping("/suggestion.do")
    public String suggestionPost(Message message) {

        message.setMessageStatus(Constant.MessageStatus.MESSAGE_DONE);

        userService.updateMessage(message);

        return "redirect:/user/suggestion.do";
    }

    //公告列表
    @GetMapping("/announce.do")
    public ModelAndView announceGet(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", required = false, defaultValue = "8") Integer size) {

        List<Announce> announces = userService.getAllMessage(page,size);
        PageInfo<Announce> pageInfo = new PageInfo<>(announces);

        ModelAndView mv = new ModelAndView();

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-announce");

        return mv;
    }

    //添加公告
    @PostMapping("/announce.do")
    public String announce(Announce announce) {

        announce.setAnnounceStartDate(new Date());
        userService.addAnnounce(announce);


        return "redirect:/user/announce.do";
    }

    //系统消息
    @GetMapping("/notification.do")
    public ModelAndView getNotification(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                         @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
                                         HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        List<Announce> announces = userService.getAnnounceByNoUerId(user.getUserId(),page,size);
        PageInfo<Announce> pageInfo = new PageInfo<>(announces);

        ModelAndView mv = new ModelAndView();

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-notification");

        return mv;

    }

    //消息已读
    @RequestMapping("/read.do")
    public String readAnnounce(Long announceId, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        userService.addAnnounceAndUser(user.getUserId(), announceId);

        return "redirect:notification.do";
    }

}
