package com.admin.mapper;

import com.admin.domain.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-18
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class UserSqlProvider {
    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");

        if (record.getUserId() != null) {
            sql.VALUES("userId", "#{userId}");
        }

        if (record.getUserEmail() != null) {
            sql.VALUES("userEmail", "#{userEmail}");
        }

        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username}");
        }

        if (record.getPassword()!= null) {
            sql.VALUES("password", "#{password}");
        }

        if (record.getUserPhone() != null) {
            sql.VALUES("userPhone", "#{userPhone}");
        }

        if (record.getUserStatus() != null) {
            sql.VALUES("userStatus", "#{userStatus}");
        }


        return sql.toString();
    }


    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("user");

        if (record.getUserEmail() != null) {
            sql.SET("userEmail = #{userEmail}");
        }

        if (record.getUsername() != null) {
            sql.SET("username = #{username}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{password}");
        }

        if (record.getUserPhone() != null) {
            sql.SET("userPhone = #{userPhone}");
        }

        if (record.getUserStatus() != null) {
            sql.SET("userStatus = #{userStatus}");
        }

        sql.WHERE("userId = #{userId}");

        return sql.toString();
    }
}
