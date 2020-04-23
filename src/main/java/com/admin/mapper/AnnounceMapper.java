package com.admin.mapper;

import com.admin.domain.Announce;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-23
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public interface AnnounceMapper {

    @Insert({
            "insert into announce (announceStartDate,announceTitle,announceContent) values ",
            "(#{announceStartDate},#{announceTitle},#{announceContent})"
    })
    int insert(Announce announce);


    @Select({
            "select * from announce where announceId ",
            "not in",
            "(select announceId from users_announces where userId = #{userId})"
    })
    List<Announce> selectNoUserIdByUserId(Long userId);


    @Select("select * from announce")
    List<Announce> selectAll();


    @Insert("insert into users_announces (userId,announceId) values(#{userId},#{announceId})")
    int insertAnnounceAndUser(@Param("userId") Long userId,@Param("announceId") Long announceId);
}
