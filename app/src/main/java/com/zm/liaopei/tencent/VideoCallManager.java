package com.zm.liaopei.tencent;

import com.zm.liaopei.app.ApplicationConfig;
import com.zm.liaopei.app.GenerateTestUserSig;
import com.zm.liaopei.tencent.model.TRTCCalling;
import com.zm.liaopei.tencent.model.TRTCCallingDelegate;
import com.zm.liaopei.tencent.model.impl.TRTCCallingImpl;
import java.util.List;
import java.util.Map;

/**
 * @author fanliangliang
 * @description:语音通话管理类
 * @date : 2021/5/6 13:21
 */
public class VideoCallManager {

    private static VideoCallManager videoCallManager;

    public static void VideoCallManager(){
        initVideoCall();

    }

    public static VideoCallManager getInstance(){
        if (videoCallManager==null){
            synchronized (VideoCallManager.class){
                if (videoCallManager==null){
                    videoCallManager=new VideoCallManager();
                }
            }
        }
        return videoCallManager;
    }

    /**
     * 初始语音相关配置
     */
    public static void initVideoCall(){
        TRTCCalling sCall= TRTCCallingImpl.sharedInstance(ApplicationConfig.getContext());
        sCall.addDelegate(new TRTCCallingDelegate() {
            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void onInvited(String sponsor, List<String> userIdList, boolean isFromGroup, int callType) {
                // 收到来自 sponsor 发过来的通话请求，此处代码选择接听，您也可以调用 reject() 拒绝之。
                sCall.accept();
            }

            @Override
            public void onGroupCallInviteeListUpdate(List<String> userIdList) {

            }

            @Override
            public void onUserEnter(String userId) {

            }

            @Override
            public void onUserLeave(String userId) {

            }

            @Override
            public void onReject(String userId) {

            }

            @Override
            public void onNoResp(String userId) {

            }

            @Override
            public void onLineBusy(String userId) {

            }

            @Override
            public void onCallingCancel() {

            }

            @Override
            public void onCallingTimeout() {

            }

            @Override
            public void onCallEnd() {

            }

            @Override
            public void onUserVideoAvailable(String userId, boolean isVideoAvailable) {

            }

            @Override
            public void onUserAudioAvailable(String userId, boolean isVideoAvailable) {

            }

            @Override
            public void onUserVoiceVolume(Map<String, Integer> volumeMap) {

            }
         });

         sCall.login(GenerateTestUserSig.SDKAPPID,"","", new TRTCCalling.ActionCallBack() {
            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void onSuccess() {
                sCall.call("",TRTCCalling.TYPE_AUDIO_CALL);

            }
          });

    }
}
