package com.zm.network;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author fanll
 * @deprecated 自定义数据转换器，可以进行加密和解密的处理，对于数据有更加的安全保障
 */
public class CustomConvertFactory extends Converter.Factory {
    public static final String NET_AES_KEY = "V9LJ7T0cDPblpEzK";

    public static CustomConvertFactory create(){
        return create(new Gson());
    }

    public static CustomConvertFactory create(Gson gson){
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        return new CustomConvertFactory(gson);
    }

    private final Gson gson;

    private CustomConvertFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new DecodeResponseBodyConvert<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new EncodeRequestBodyConvert<>();
    }
}
