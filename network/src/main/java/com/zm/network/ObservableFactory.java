package com.zm.network;

import com.zm.network.base.BaseRespone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author fanll
 * @deprecated 对于网络请求返回的Observable进行及时的处理 并且进行及时的转换
 */
public class ObservableFactory {
    private static final ObservableTransformer TRANSFORMER = new SimpleTransformer();

    public static <T extends BaseRespone> ObservableSource<T> compose(Observable<T> observable){
        return observable.compose(TRANSFORMER);
    }

    private static class SimpleTransformer<T extends BaseRespone> implements ObservableTransformer<T ,T>{

        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Function<T, ObservableSource<T>>() {
                        @Override
                        public ObservableSource<T> apply(T response) throws Exception {
                            return flatResponse(response);
                        }
                    });
        }

        private <T extends BaseRespone> Observable<T> flatResponse(T response){
            return Observable.just(response)
                    .map(new Function<T, T>() {
                        @Override
                        public T apply(T response) throws Exception {
                            boolean result = response.isResult();
                            if (!result){
                                ApiExceptionCallBack callBack = NetWorkManager.getCallBack(response.getErrorCode());
                                if (callBack != null) {
                                    callBack.CallBack(response);
                                    throw new ApiException(response.getErrMsg());
                                }
                            }
                            return response;
                        }
                    });
        }
    }
}
