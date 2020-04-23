package com.admin.mapper;

import com.admin.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-22
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public interface MemberMapper {

    @Insert("insert into member(memberName,memberPhone,memberEmail) values(#{memberName},#{memberPhone},#{memberEmail})")
    @Options(useGeneratedKeys = true, keyProperty = "memberId")
    int insert(Member member);


    @Select("select * from member where memberId = #{memberId}")
    Member selectByPrimaryKey(Long memberId);



    @Select("select * from member where memberName = #{memberName} and memberPhone = #{memberPhone}")
    Member selectByNameAndPhone(@Param("memberName") String memberName, @Param("memberPhone") String memberPhone);
}
