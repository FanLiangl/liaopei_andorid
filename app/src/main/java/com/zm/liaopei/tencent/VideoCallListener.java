package com.zm.liaopei.tencent;

/**
 * @author fanliangliang
 * @description:、重写通话中的事件
 * @date : 2021/5/6 13:33
 */
public interface VideoCallListener{
    /**
     * 通话停止
     */
    void videoCallStop();

    /**
     * 开始通话
     */
    void videoCallStart();

    /**
     * 通话接听中
     */
    void videoCallAnswering();

    /**
     * 接听之前
     */
    void videoCallBeforeAnswering();
}
