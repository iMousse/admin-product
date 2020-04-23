package com.admin.service;


import com.admin.domain.Announce;
import com.admin.domain.Message;
import com.admin.domain.Role;
import com.admin.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    /**
     * 查询用户名字
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User getUserByUsernameAndPassword(User user);


    /**
     * 根据邮箱查询用户
     *
     * @param userEmail
     * @return
     */
    User getUserByEmail(String userEmail);


    /**
     * 查询所有用户
     *
     * @return
     * @param page
     * @param size
     */
    List<User> getAll(Integer page, Integer size);


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);


    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    User getUserById(Long userId);

    /**
     * 查询用户没有的角色
     *
     * @param userId
     * @return
     */
    List<Role> getOtherRoles(Long userId);

    /**
     * 用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    int addRoleToUser(Long userId, Long[] roleIds);


    /**
     * 修改用户状态或手机号
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 修改用户密码
     *
     * @param userId
     * @param newPwd
     * @return
     */
    int updateUserPwd(Long userId, String newPwd);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    int deleteUserById(Long userId);

    /**
     * 文件信息上传
     *
     * @param userId
     * @param userFilename
     * @param userFileURL
     * @return
     */
    int setUserFile(Long userId, String userFilename, String userFileURL);

    /**
     * 获取用户的消息列表
     * 
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<Message> getMessageByUserId(Long userId, Integer page, Integer size);

    /**
     * 添加用户的消息
     *
     * @param message
     * @return
     */
    int addMessage(Message message);

    /**
     * 查询用户的具体信息
     *
     * @param messageId
     * @return
     */
    Message getMessageById(Long messageId);

    /**
     * 修改消息的内容或标题
     *
     * @param message
     * @return
     */
    int updateMessage(Message message);


    /**
     * 查询没有处理过的消息
     *
     * @param status
     * @param page
     * @param size
     * @return
     */
    List<Message> getMessageByStatus(Integer status, Integer page, Integer size);

    /**
     * 添加公告
     *
     * @param announce
     * @return
     */
    int addAnnounce(Announce announce);


    /**
     * 获取所有公告
     *
     * @param page
     * @param size
     * @return
     */
    List<Announce> getAllMessage(Integer page, Integer size);

    /**
     * 查看没有被用户已读的公告，已读的公告会插入中间表中
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<Announce> getAnnounceByNoUerId(Long userId, Integer page, Integer size);

    /**
     * 插入公告和用户的中建表
     * 
     * @param userId
     * @param announceId
     * @return
     */
    int addAnnounceAndUser(Long userId, Long announceId);


    /**
     * 查询没有订单的角色
     * @return
     */
    List<User> findNoOrdersUsername();


}
