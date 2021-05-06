package com.zm.network;


import android.content.Context;
import android.util.SparseArray;
import java.util.List;

/**
 * @author fanll
 * @deprecated 网络请求管理类
 */
public class NetWorkManager {
    private static final SparseArray<ApiExceptionCallBack> API_EXCEPTION_CALL_BACK = new SparseArray<>();

    private NetWorkManager() {
    }

    public static void init(String market, Context context, List<Integer> codeList, ApiExceptionCallBack commonCallBack) {
        HttpHead.init(market,context);
        registerApiCallBack(codeList, commonCallBack);
    }

    public static void registerApiCallBack(int code, ApiExceptionCallBack callBack) {
        if (callBack != null) {
            API_EXCEPTION_CALL_BACK.put(code, callBack);
        }
    }

    public static void registerApiCallBack(List<Integer> codeList,ApiExceptionCallBack callBack){
        if (callBack != null) {
            for (int i = 0;i <codeList.size();i++) {
                registerApiCallBack(codeList.get(i),callBack);
            }
        }
    }

    public static ApiExceptionCallBack getCallBack(int code) {
        return API_EXCEPTION_CALL_BACK.get(code);
    }
}
