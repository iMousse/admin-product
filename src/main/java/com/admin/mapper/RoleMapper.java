package com.admin.mapper;


import com.admin.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 21:14
 * To change this template use File | Settings | File Templates.
 */
public interface RoleMapper {

    @Select("select * from role where roleId in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "roleId", column = "roleId"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
    })
    List<Role> selectByUserId(Long userId);

    @Select("select * from role")
    List<Role> selectAll();

    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    int insert(Role role);

    @Select("select * from role where roleId = #{roleId}")
    @Results({
            @Result(id = true, property = "roleId", column = "roleId"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
    })
    Role selectByPrimaryKey(Long roleId);


    @Delete("delete from users_role where roleId = #{roleId}")
    int deleteUserAndRoleByRoleId(Long roleId);

    @Delete("delete from role where roleId = #{roleId}")
    int deleteRole(Long roleId);
}
