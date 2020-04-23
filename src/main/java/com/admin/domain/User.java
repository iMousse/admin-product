package com.admin.domain;


import com.admin.constant.Constant;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private Long userId;
    private String userEmail;
    private String username;
    private String password;
    private String userPhone;
    private String userFileURL;
    private String userFilename;
    private Integer userStatus;

    private String userStatusStr;

    private List<Role> roles;

    private List<Announce> announces;

    public List<Announce> getAnnounces() {
        return announces;
    }

    public void setAnnounces(List<Announce> announces) {
        this.announces = announces;
    }

    public String getUserFileURL() {
        return userFileURL;
    }

    public void setUserFileURL(String userFileURL) {
        this.userFileURL = userFileURL;
    }

    public String getUserFilename() {
        return userFilename;
    }

    public void setUserFilename(String userFilename) {
        this.userFilename = userFilename;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatusStr() {

        if (userStatus.equals(Constant.UserStatus.USER_CLOSE)) {
            userStatusStr = "关闭";
        } else if (userStatus.equals(Constant.UserStatus.USER_OPEN)) {
            userStatusStr = "开启";
        } else {
            userStatusStr = "错误";
        }
        return userStatusStr;
    }

    public void setUserStatusStr(String userStatusStr) {
        this.userStatusStr = userStatusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userStatus=" + userStatus +
                ", userStatusStr='" + userStatusStr + '\'' +
                '}';
    }
}
