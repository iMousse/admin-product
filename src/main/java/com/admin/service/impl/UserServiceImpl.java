package com.admin.service.impl;

import com.admin.constant.Constant;
import com.admin.domain.Announce;
import com.admin.domain.Message;
import com.admin.domain.Role;
import com.admin.domain.User;
import com.admin.mapper.AnnounceMapper;
import com.admin.mapper.MessageMapper;
import com.admin.mapper.UserMapper;
import com.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Synthesizer;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-28
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AnnounceMapper announceMapper;


    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User getUserByUsernameAndPassword(User user) {
        return userMapper.selectByUsernameAndPassword(user);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userMapper.selectByUserEmail(userEmail);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<User> getAll(Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return userMapper.selectAll();
    }

    @Override
    @Transactional
    public int addUser(User user) {

        user.setUserStatus(Constant.UserStatus.USER_OPEN);

        userMapper.insert(user);
        //加入用户身份
        return userMapper.insertRoleToUser(user.getUserId(), 1L);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<Role> getOtherRoles(Long userId) {
        return userMapper.selectOtherRolesByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int addRoleToUser(Long userId, Long[] roleIds) {
        for (Long roleId : roleIds) {
            userMapper.insertRoleToUser(userId, roleId);
        }
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUserStatusAndPhone(user);
    }

    @Override
    public int updateUserPwd(Long userId, String newPwd) {
        return userMapper.updateUserPwd(userId, newPwd);
    }

    @Override
    public int deleteUserById(Long userId) {

        userMapper.deleteUserAndRoleByUserId(userId);


        userMapper.deleteByPrimaryKey(userId);

        return 0;
    }

    @Override
    public int setUserFile(Long userId, String userFilename, String userFileURL) {
        return userMapper.updateUserFile(userId,userFilename,userFileURL);
    }

    @Override
    public List<Message> getMessageByUserId(Long userId, Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return messageMapper.selectByUserId(userId);
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.insertSelective(message);
    }

    @Override
    public Message getMessageById(Long messageId) {
        return messageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public int updateMessage(Message message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public List<Message> getMessageByStatus(Integer status, Integer page, Integer size) {
        
        PageHelper.startPage(page, size);

        return messageMapper.selectByMessageStatus(status);
    }

    @Override
    public int addAnnounce(Announce announce) {
        return announceMapper.insert(announce);
    }

    @Override
    public List<Announce> getAllMessage(Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return announceMapper.selectAll();
    }

    @Override
    public List<Announce> getAnnounceByNoUerId(Long userId, Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return announceMapper.selectNoUserIdByUserId(userId);
    }

    @Override
    public int addAnnounceAndUser(Long userId, Long announceId) {
        return announceMapper.insertAnnounceAndUser(userId,announceId);
    }

    @Override
    public List<User> findNoOrdersUsername() {
        return userMapper.selectNoOrderUsername();
    }


}
