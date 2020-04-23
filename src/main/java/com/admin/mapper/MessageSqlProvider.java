package com.admin.mapper;

import com.admin.domain.Message;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-23
 * Time: 01:58
 * To change this template use File | Settings | File Templates.
 */
public class MessageSqlProvider {

    public String updateByPrimaryKeySelective(Message record) {
        SQL sql = new SQL();
        sql.UPDATE("message");

        if (record.getMessageUserId() != null) {
            sql.SET("messageUserId = #{messageUserId}");
        }

        if (record.getMessageStartDate() != null) {
            sql.SET("messageStartDate = #{messageStartDate}");
        }

        if (record.getMessageTitle() != null) {
            sql.SET("messageTitle = #{messageTitle}");
        }

        if (record.getMessageContent() != null) {
            sql.SET("messageContent = #{messageContent}");
        }

        if (record.getMessageResult() != null) {
            sql.SET("messageResult = #{messageResult}");
        }

        if (record.getMessageStatus() != null) {
            sql.SET("messageStatus = #{messageStatus}");
        }

        sql.WHERE("messageId = #{messageId}");

        return sql.toString();
    }
}
