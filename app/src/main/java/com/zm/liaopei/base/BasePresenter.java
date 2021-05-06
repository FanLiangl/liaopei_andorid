package com.zm.liaopei.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author fanliangliang
 * @description:Presenter基类
 * @date : 2021/4/26 10:49
 */
public abstract class BasePresenter<V extends BaseView>{
    protected V view;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * 绑定View
     * @param view 与P绑定的View对象
     */
    public void onAttach(V view) {
        this.view = view;
    }

    public void onCreate(){

    }

    public void onResume(){

    }

    public void onStop(){

    }


    public void onDestroy(){
        cancelNewWork();
        view = null;
    }

    public void cancelNewWork(){
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void addDispose(Disposable disposable){
        compositeDisposable.add(disposable);
    }
}
