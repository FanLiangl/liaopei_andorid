package com.zm.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zm.network.ssl.SSLFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fanll
 * @deprecated retrofit工厂方法 进一步封装
 */
public class NetWorkBuilder {

    private NetWorkBuilder(){

    }

    /**
     * </p>
     * @return Retrofit
     */
    public static Retrofit getRetrofit(){
        return Instance.instance;
    }

    /**
     * 获取访问对应url的Retrofit实例
     * @param url   访问url
     * @return      Retrofit
     */
    public static void init(String url) {
        Instance.instance = Instance.getInstance(url);
    }

    private static class Instance{

        private static Retrofit instance = getInstance("URLManager.getBaseUrl()");

        private static Retrofit getInstance(String url){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .client(SSLFactory.getUnsafeOkHttpClient())
                    .baseUrl(url);
            builder.addConverterFactory(GsonConverterFactory.create());
//            if (URLManager.IS_ENCODE) {
//                builder.addConverterFactory(CustomConvertFactory.create());
//            } else {
//                builder.addConverterFactory(GsonConverterFactory.create());
//            }
            Retrofit retrofit = builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
            return retrofit;
        }
    }

}
