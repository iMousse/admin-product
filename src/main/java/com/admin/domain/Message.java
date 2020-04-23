package com.admin.domain;

import com.admin.constant.Constant;
import com.admin.utils.DateUtils;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-22
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private Long messageId;
    private Long messageUserId;
    private Date messageStartDate;
    private String messageStartDateStr;
    private String messageTitle;
    private String messageContent;
    private String messageResult;
    private Integer messageStatus;
    private String messageStatusStr;

    public String getMessageStatusStr() {
        if (messageStatus != null) {
            if (messageStatus.equals(Constant.MessageStatus.MESSAGE_OPEN)){
                messageStatusStr = "正在处理";
            } else if (messageStatus.equals(Constant.MessageStatus.MESSAGE_DONE)) {
                messageStatusStr = "处理完成";
            }
        }
        return messageStatusStr;
    }

    public String getMessageStartDateStr() {
        if (messageStartDate != null) {
            messageStartDateStr = DateUtils.date2String(messageStartDate, "yyyy-MM-dd");
        }
        return messageStartDateStr;
    }

    public void setMessageStartDateStr(String messageStartDateStr) {
        this.messageStartDateStr = messageStartDateStr;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Long messageUserId) {
        this.messageUserId = messageUserId;
    }

    public Date getMessageStartDate() {
        return messageStartDate;
    }

    public void setMessageStartDate(Date messageStartDate) {
        this.messageStartDate = messageStartDate;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageResult() {
        return messageResult;
    }

    public void setMessageResult(String messageResult) {
        this.messageResult = messageResult;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public void setMessageStatusStr(String messageStatusStr) {
        this.messageStatusStr = messageStatusStr;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageUserId=" + messageUserId +
                ", messageStartDate=" + messageStartDate +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageResult='" + messageResult + '\'' +
                ", messageStatus=" + messageStatus +
                ", messageStatusStr='" + messageStatusStr + '\'' +
                '}';
    }
}
