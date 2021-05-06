package com.zm.network;


import com.blankj.utilcode.util.LogUtils;
import com.zm.utils.AESUtils;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class EncodeRequestBodyConvert <T> implements Converter<T , RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        LogUtils.e("convert: "+value.toString());
        String encode = AESUtils.aesEncryptToString(value.toString(), CustomConvertFactory.NET_AES_KEY);
        return RequestBody.create(MEDIA_TYPE,encode);
    }

}
