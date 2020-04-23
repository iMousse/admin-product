package com.admin.constant;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-12
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public interface Constant {

    //用户状态,1:开启,0:关闭
    interface UserStatus {
        Integer USER_CLOSE = 0;

        Integer USER_OPEN = 1;
    }

    //发消息的状态 0:发送,1:已读
    interface MessageStatus{
        Integer MESSAGE_OPEN = 0;

        Integer MESSAGE_DONE = 1;
    }

    //公告 0:未读,1:已读
    interface AnnounceStatus{
        Integer ANNOUNCE_UNREAD = 0;

        Integer ANNOUNCE_READ = 1;
    }


    //产品状态--0:关闭,1:开启
    interface ProductStatus {
        Integer PRODUCT_CLOSE = 0;

        Integer PRODUCT_OPEN = 1;
    }


    //订单状态--0:开启,1:完成
    //订单审核--0:审核未通过,1:审核通过
    //是否支付--0:审核未通过,1:支付
    interface OrderStatus {
        Integer ORDERS_OPEN = 0;

        Integer ORDERS_DONE = 1;


        Integer ORDERS_NOAUDIT = 0;

        Integer ORDERS_AUDIT = 1;


        Integer ORDERS_NOPAY = 0;

        Integer ORDERS_PAY = 1;

    }


}
