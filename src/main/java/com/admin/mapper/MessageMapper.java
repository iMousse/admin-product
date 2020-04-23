package com.admin.mapper;

import com.admin.domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-22
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public interface MessageMapper {

    @Select("select * from message where messageUserId = #{userId}")
    List<Message> selectByUserId(Long userId);

    @Insert({
            "insert into message ",
            "(messageUserId,messageStartDate,messageTitle,messageContent,messageStatus) values ",
            "(#{messageUserId},#{messageStartDate},#{messageTitle},#{messageContent},#{messageStatus})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertSelective(Message message);

    @Select("select * from message where messageId = #{messageId}")
    Message selectByPrimaryKey(Long messageId);

    @InsertProvider(type = MessageSqlProvider.class,method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message message);

    @Select("select * from message where messageStatus = #{status}")
    List<Message> selectByMessageStatus(Integer status);
}
