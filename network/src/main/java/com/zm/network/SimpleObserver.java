package com.zm.network;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.zm.network.common.CommonService;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class SimpleObserver <T> implements Observer<T>{

    protected Disposable disposable;
    private CompositeDisposable compositeDisposable;
    private CommonService commonService;

    public SimpleObserver(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        //默认的判断  需要调整
        commonService = (CommonService) ARouter.getInstance().build("/login/CommonServiceImp").navigation();
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        if (unDisposed()) {
            commonService.checkNetworkAvailable();
            compositeDisposable.add(d);
        }
    }

    private boolean unDisposed() {
        return compositeDisposable != null && !disposable.isDisposed() && !compositeDisposable.isDisposed();
    }

    @Override
    public void onNext(T t) {
        try {
            if (t != null) {
                call(t);
            } else {
                throw new NullPointerException("t is null");
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            onError(e);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!(e instanceof ApiException)) {
            onFail(e);
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 网络请求回调
     * @param t 请求返回的数据
     */
    public abstract void call(T t);

    /**
     * 网络请求错误
     * @param e 异常
     */
    public abstract void onFail(Throwable e);
}
