DROP DATABASE IF EXISTS product;

CREATE DATABASE product DEFAULT CHARACTER SET = utf8;

USE product;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    userId       BIGINT PRIMARY KEY AUTO_INCREMENT,
    userEmail    VARCHAR(100) UNIQUE NOT NULL COMMENT '用户邮箱',
    username     VARCHAR(100) COMMENT '用户名',
    password     VARCHAR(100) COMMENT '密码',
    userPhone    VARCHAR(100) COMMENT '电话',
    userFilename VARCHAR(100) COMMENT '文件名字',
    userFileURL  VARCHAR(100) COMMENT '文件路径',
    userStatus   INT COMMENT '用户状态,0:关闭,1:开启'
);


INSERT INTO user
VALUES (1, '958860184@qq.com', 'mousse', 'abc123', '123', NULL, NULL, 1);
INSERT INTO user
VALUES (2, '958860185@qq.com', 'cookie', 'abc123', '123', NULL,NULL, 1);
INSERT INTO user
VALUES (3, '958860186@qq.com', 'showy', 'abc123', '123', NULL, NULL, 1);
INSERT INTO user
VALUES (4, '958860187@qq.com', 'admin', 'abc123', '123', NULL, NULL, 1);




-- 角色表
DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    roleId   BIGINT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(50) COMMENT '角色名字',
    roleDesc VARCHAR(50) COMMENT '角色描述'
);

INSERT INTO role(roleId, roleName, roleDesc)
VALUES (1, 'USER', '用户');
INSERT INTO role(roleId, roleName, roleDesc)
VALUES (2, 'SALES', '销售');
INSERT INTO role(roleId, roleName, roleDesc)
VALUES (3, 'MANAGER', '经理');
INSERT INTO role(roleId, roleName, roleDesc)
VALUES (4, 'ADMIN', '管理员');


-- 用户角色关联表
DROP TABLE IF EXISTS users_role;
CREATE TABLE users_role
(
    userId BIGINT(32) COMMENT '用户主键',
    roleId BIGINT(32) COMMENT '角色主键',
    PRIMARY KEY (userId, roleId)
);

INSERT INTO users_role
VALUES (1, 1);
INSERT INTO users_role
VALUES (1, 2);
INSERT INTO users_role
VALUES (1, 4);
INSERT INTO users_role
VALUES (2, 1);
INSERT INTO users_role
VALUES (2, 2);
INSERT INTO users_role
VALUES (2, 4);
INSERT INTO users_role
VALUES (2, 3);


-- 资源权限表
DROP TABLE IF EXISTS permission;
CREATE TABLE permission
(
    permissionId   BIGINT PRIMARY KEY AUTO_INCREMENT,
    permissionName VARCHAR(50) COMMENT '权限名字',
    permissionUrl  VARCHAR(50) COMMENT '权限路径'
);

INSERT INTO permission (permissionId, permissionName, permissionUrl)
VALUES (1, '查询所有用户', '/user/findAll.do');
INSERT INTO permission (permissionId, permissionName, permissionUrl)
VALUES (2, '查询所有角色', '/role/findAll.do');
INSERT INTO permission (permissionId, permissionName, permissionUrl)
VALUES (3, '查询所有权限', '/permission/findAll.do');


-- 角色权限关联表
DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission
(
    roleId       BIGINT(32),
    permissionId BIGINT(32),
    PRIMARY KEY (permissionId, roleId)
);

INSERT INTO role_permission
VALUES (1, 1);
INSERT INTO role_permission
VALUES (1, 2);
INSERT INTO role_permission
VALUES (1, 3);


DROP TABLE IF EXISTS member;
CREATE TABLE member
(
    memberId    BIGINT PRIMARY KEY AUTO_INCREMENT,
    memberName  VARCHAR(500) COMMENT '用户名字',
    memberPhone VARCHAR(500) COMMENT '用户手机',
    memberEmail VARCHAR(500) COMMENT '用户邮箱'
);


CREATE TABLE sysLog
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    visitTime     TIMESTAMP,
    username      VARCHAR(50),
    ip            VARCHAR(30),
    url           VARCHAR(50),
    executionTime INT,
    method        VARCHAR(200)
);

-- 公告表
DROP TABLE IF EXISTS announce;
CREATE TABLE announce
(
    announceId        BIGINT PRIMARY KEY AUTO_INCREMENT,
    announceStartDate DATETIME COMMENT '发布公告的时间',
    announceTitle     VARCHAR(100) COMMENT '公告内容',
    announceContent   VARCHAR(100) COMMENT '公告内容'
);


CREATE TABLE users_announces
(
    userId     BIGINT(32) COMMENT '用户表主键',
    announceId BIGINT(32) COMMENT '公告表主键',
    PRIMARY KEY (userId, announceId)
);


-- 消息表
CREATE TABLE message
(
    messageId        BIGINT PRIMARY KEY AUTO_INCREMENT,
    messageUserId    BIGINT COMMENT '发消息的用户',
    -- messageFromUserId    BIGINT COMMENT '发消息的用户',
    -- messageByUserId    BIGINT COMMENT '接受消息的用户',
    messageStartDate DATETIME COMMENT '发消息的时间',
    messageTitle     VARCHAR(100) COMMENT '发消息的时间',
    messageContent   VARCHAR(500) COMMENT '发消息的理由',
    messageResult    VARCHAR(100) COMMENT '发消息的结果',
    messageStatus    INT COMMENT '发消息的状态,0:发送,1:已读'
);

-- 回复表
CREATE TABLE reply
(
    replyId             BIGINT PRIMARY KEY AUTO_INCREMENT,
    replyAnnounceId     BIGINT COMMENT '公告表主键',
    replyAnnounceUserId BIGINT COMMENT '公告表的用户主键',
    replyFromUserId     BIGINT COMMENT '回帖的用户',
    replyByUserId       BIGINT COMMENT '被回复的用户',
    replyLike           INT COMMENT '点赞数',
    replyContent        VARCHAR(500) COMMENT '回复表内容',
    replyStatus         INT COMMENT '回复状态',
    replyCreateDate     DATETIME COMMENT '创建时间'
);


DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    productId          BIGINT PRIMARY KEY AUTO_INCREMENT,
    productName        VARCHAR(500) COMMENT '产品名字',
    productStartTime   TIMESTAMP COMMENT '生产日期',
    productPrice       NUMERIC(20, 2) COMMENT '产品价格',
    productDesc        VARCHAR(500) COMMENT '产品描述',
    productContract    VARCHAR(500) COMMENT '产品合同',
    productContractUrl VARCHAR(500) COMMENT '产品合同URL',
    productStatus      INT COMMENT '产品状态--0:关闭,1:开启'
);

INSERT INTO product
VALUES (1, 'PlayStation4', now(), 3999.0, '家庭游戏机', 'PS4协议', NULL, 1);
INSERT INTO product
VALUES (2, 'MacBook Pro', now(), 13999.0, '笔记本电脑', '苹果保密协议', NULL, 0);
INSERT INTO product
VALUES (3, 'MacBook Pro', now(), 13999.0, '笔记本电脑', '苹果保密协议', NULL, 1);


-- 订单order为关键字所以加上s
DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    orderId         BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderNum        VARCHAR(100) UNIQUE NOT NULL,
    orderCreateTime TIMESTAMP COMMENT '订单创建时间',
    orderStartTime  DATETIME COMMENT '订单开始时间',
    orderEndTime    DATETIME COMMENT '订单申请时间',
    orderDesc       VARCHAR(500) COMMENT '订单描述',
    orderStatus     INT COMMENT '订单状态--0:开启,1:完成',
    orderAudit      INT COMMENT '订单审核--0:审核未通过,1:审核通过',
    orderPay        INT COMMENT '是否支付--0:审核为通过,1:支付,',
    orderProductId  BIGINT COMMENT '订单外键产品主键',
    orderProductNum INT comment '订单产品数量',
    orderPrice      NUMERIC (20,2) comment '订单价格',
    orderMemberId   BIGINT COMMENT '订单会员主键',
    orderUsername   VARCHAR(500) COMMENT '产品经理名字',
    orderReport     VARCHAR(500) COMMENT '订单验收报告',
    orderResult     VARCHAR(500) COMMENT '订单验收结果'
);

CREATE TABLE users_orders
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderId BIGINT COMMENT '订单主键',
    userId  BIGINT COMMENT '用户主键'
);
