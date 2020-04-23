package com.admin.mapper;


import com.admin.domain.Role;
import com.admin.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public interface UserMapper {


    @Select({
            "select * from user ",
            "where username = #{username} and password = #{password}"
    })
    @Results({
            @Result(column = "userId", property = "userId", id = true),
            @Result(column = "userEmail", property = "userEmail"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "userPhone", property = "userPhone"),
            @Result(column = "userFilename", property = "userFilename"),
            @Result(column = "userFileURL", property = "userFileURL"),
            @Result(column = "userStatus", property = "userStatus"),
            @Result(column = "userId", property = "roles", javaType = List.class, many = @Many(select = "com.admin.mapper.RoleMapper.selectByUserId")),
            @Result(column = "userId",property = "announces",javaType = List.class,many = @Many(select = "com.admin.mapper.AnnounceMapper.selectNoUserIdByUserId"))
    })
    User selectByUsernameAndPassword(User user);


    @Select({
            "select * from user ",
            "where userId = #{userId} "
    })
    @Results({
            @Result(column = "userId", property = "userId", id = true),
            @Result(column = "userEmail", property = "userEmail"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "userPhone", property = "userPhone"),
            @Result(column = "userFilename", property = "userFilename"),
            @Result(column = "userFileURL", property = "userFileURL"),
            @Result(column = "userStatus", property = "userStatus"),
            @Result(column = "userId", property = "roles", javaType = List.class, many = @Many(select = "com.admin.mapper.RoleMapper.selectByUserId"))
    })
    User selectByPrimaryKey(Long userId);


    @Select({
            "select * from user",
            " where username = #{username}"
    })
    User selectByUsername(String username);


    @Select({
            "select * from user",
            " where userEmail = #{userEmail}"
    })
    User selectByUserEmail(String userEmail);

    @Select("select * from role where roleId not in (select roleId from users_role where userId=#{userId})")
    List<Role> selectOtherRolesByPrimaryKey(Long userId);


    @Select("select * from user")
    @Results({
            @Result(column = "userId", property = "userId", id = true),
            @Result(column = "userEmail", property = "userEmail"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "userPhone", property = "userPhone"),
            @Result(column = "userFilename", property = "userFilename"),
            @Result(column = "userFileURL", property = "userFileURL"),
            @Result(column = "userStatus", property = "userStatus"),
            @Result(column = "userId", property = "roles", javaType = List.class, many = @Many(select = "com.admin.mapper.RoleMapper.selectByUserId"))
    })
    List<User> selectAll();

    @Insert({
            "insert into user(userEmail,username,password,userPhone,userStatus) ",
            "values ",
            "(#{userEmail},#{username},#{password},#{userPhone},#{userStatus})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);


    @InsertProvider(type = UserSqlProvider.class, method = "insertSelective")
    int insertSelective(User user);


    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    int insertRoleToUser(@Param("userId") Long userId, @Param("roleId") Long roleId);


    @UpdateProvider(type = UserSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User user);


    @Update("update user set userPhone = #{userPhone},userStatus = #{userStatus} where userId = #{userId}")
    int updateUserStatusAndPhone(User user);


    @Update("update user set password = #{newPwd} where userId = #{userId}")
    int updateUserPwd(@Param("userId") Long userId, @Param("newPwd") String newPwd);


    @Update("update user set userFilename = #{userFilename},userFileURL = #{userFileURL} where userId = #{userId}")
    int updateUserFile(@Param("userId") Long userId, @Param("userFilename") String userFilename, @Param("userFileURL") String userFileURL);


    @Delete("delete from user where userId = #{userId}")
    int deleteByPrimaryKey(Long userId);


    @Delete("delete from users_role where userId = #{userId}")
    int deleteUserAndRoleByUserId(Long userId);

    @Select(" SELECT * FROM user WHERE username NOT IN (SELECT orderUsername FROM orders WHERE (orderPay IS NULL AND orderUsername IS NOT NULL) OR (orderUsername IS NOT NULL AND orderPay = 0)) AND userId IN (SELECT userId FROM role LEFT JOIN users_role ur ON role.roleId = ur.roleId WHERE roleName = 'manager')")
    List<User> selectNoOrderUsername();


}
