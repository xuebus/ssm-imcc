package com.zhaopin.model;

import java.io.Serializable;

/**
 * 云信抄送消息
 * Created by SYJ on 2017/4/14.
 */
public class Imcc implements Serializable{

    private Integer id;

    //会话类型消息
    private String attach;
    private String body;
    private String convType;
    private String eventType;
    private String fromAccount;
    private String fromClientType;
    private String fromDeviceId;
    private String fromNick;
    private String msgTimestamp;
    private String msgType;
    private String msgidClient;
    private String msgidServer;
    private String resendFlag;
    private String to;
    private String customSafeFlag;
    private String customApnsText;
    private String tMembers;
    private String ext;
    private String antispam;

    //登录登出事件消息
    private String accid;
    private String clientIp;
    private String clientType;
    private String code;
    private String sdkVersion;
    private String timestamp;

    //聊天室消息
    private String fromAvator;
    private String fromExt;
    private String roleInfoTimetag;
    private String roomId;

    //音视频通话/白板消息
    private String channelId;
    private String createtime;
    private String duration;
    private String live;
    private String members;
    private String status;
    private String type;

    //音视频/白板文件下载信息
    private String fileinfo;

    //单聊/群聊消息撤回
    private String clientId;
    private String deleteTime;
    private String from;
    private String msgId;
    private String selfMsg;
    private String sendTime;

    //主播管理员进出聊天室事件
    private String event;

    //专线电话通话结束回调
    private String callback;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getConvType() {
        return convType;
    }

    public void setConvType(String convType) {
        this.convType = convType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getFromDeviceId() {
        return fromDeviceId;
    }

    public void setFromDeviceId(String fromDeviceId) {
        this.fromDeviceId = fromDeviceId;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(String msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgidClient() {
        return msgidClient;
    }

    public void setMsgidClient(String msgidClient) {
        this.msgidClient = msgidClient;
    }

    public String getMsgidServer() {
        return msgidServer;
    }

    public void setMsgidServer(String msgidServer) {
        this.msgidServer = msgidServer;
    }

    public String getResendFlag() {
        return resendFlag;
    }

    public void setResendFlag(String resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCustomSafeFlag() {
        return customSafeFlag;
    }

    public void setCustomSafeFlag(String customSafeFlag) {
        this.customSafeFlag = customSafeFlag;
    }

    public String getCustomApnsText() {
        return customApnsText;
    }

    public void setCustomApnsText(String customApnsText) {
        this.customApnsText = customApnsText;
    }

    public String gettMembers() {
        return tMembers;
    }

    public void settMembers(String tMembers) {
        this.tMembers = tMembers;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAntispam() {
        return antispam;
    }

    public void setAntispam(String antispam) {
        this.antispam = antispam;
    }

    @Override
    public String toString() {
        return "Imcc{" +
                "id=" + id +
                ", attach='" + attach + '\'' +
                ", body='" + body + '\'' +
                ", convType='" + convType + '\'' +
                ", eventType='" + eventType + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", fromClientType='" + fromClientType + '\'' +
                ", fromDeviceId='" + fromDeviceId + '\'' +
                ", fromNick='" + fromNick + '\'' +
                ", msgTimestamp='" + msgTimestamp + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgidClient='" + msgidClient + '\'' +
                ", msgidServer='" + msgidServer + '\'' +
                ", resendFlag='" + resendFlag + '\'' +
                ", to='" + to + '\'' +
                ", customSafeFlag='" + customSafeFlag + '\'' +
                ", customApnsText='" + customApnsText + '\'' +
                ", tMembers='" + tMembers + '\'' +
                ", ext='" + ext + '\'' +
                ", antispam='" + antispam + '\'' +
                '}';
    }
}
