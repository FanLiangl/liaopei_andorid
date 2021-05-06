package com.zm.network.ssl;

import com.zm.network.HttpHead;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @deprecated 证书管理类
 */
public class SSLFactory {

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            //创建信任证书管理器
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            };

            final TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};

            //安装信任证书
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());

            //创建ssl证书工厂
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();

            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory, x509TrustManager)
                    .addInterceptor(new HttpHeadInterceptor())
                    .connectTimeout(HttpHead.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(HttpHead.READ_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(HttpHead.WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}


    private static class HttpHeadInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            Map<String, String> header = HttpHead.getHeadParams();
            for (String key : header.keySet()) {
                builder.addHeader(key, header.get(key));
            }
//            if (URLManager.IS_ENCODE && "POST".equals(request.method())) {
//                if (request.body() instanceof FormBody) {
//                    FormBody body = (FormBody) request.body();
//                    JSONObject jsonObject = new JSONObject();
//                    for (int i = 0; i < body.size(); i++) {
//                        String key = URLDecoder.decode(body.encodedName(i), "UTF-8");
//                        String value = URLDecoder.decode(body.encodedValue(i), "UTF-8");
//                        if (key.contains("[]")) {
//                            key = key.replace("[]", "");
//                            JSONArray array;
//                            if (jsonObject.has(key)) {
//                                array = jsonObject.getJSONArray(key);
//                                array.put(value);
//                            } else {
//                                array = new JSONArray();
//                                array.put(value);
//                                jsonObject.put(key, array);
//                            }
//                        } else {
//                            jsonObject.put(key, value);
//                        }
//                    }
//                    LogUtil.e(jsonObject.toString());
//                    RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"),
//                            AESUtils.aesEncrypt(jsonObject.toString(), CustomConvertFactory.NET_AES_KEY));
//                    builder.post(requestBody);
//                }
//            }
            return chain.proceed(builder.build());
        }
    }
}
