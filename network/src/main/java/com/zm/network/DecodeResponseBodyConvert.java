package com.zm.network;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.TypeAdapter;
import com.zm.utils.AESUtils;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class DecodeResponseBodyConvert <T> implements Converter<ResponseBody,T> {
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public DecodeResponseBodyConvert(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String response= value.string();
        LogUtils.dTag("response"+response);
        String result = AESUtils.aesDecrypt(response, CustomConvertFactory.NET_AES_KEY);
        LogUtils.dTag("result: " + result);
        return adapter.fromJson(result);
    }
}
