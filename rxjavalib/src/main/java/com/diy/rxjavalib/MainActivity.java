package com.diy.rxjavalib;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import simple.ExceptionSubscriber;
import simple.SimpleCallback;


/**
 * 提供一个静态handler来操作主线程
 * Created by Zhang on 2016/8/8.
 */
public abstract class MainActivity extends BaseActivity {


    Context context;
    //rxjava  请求 封装到页面
    protected List<Subscription> subscriptionList;
    protected NetworkCallback networkCallback;

    public interface NetworkCallback {
        void onNetworkCallback(String responseBody, int requestCode);
    }

    public void bindCallback(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        subscriptionList = new ArrayList<>();
        context = this;

    }

    /**
     * parserMessage
     * 处理事务，方便子类需要定制个性回调
     *
     * @param msg handler消息对象
     * @author zb
     */
    protected void parserMessage(Message msg) {
        dismissAllDialog();
    }

    /**
     * 静态类，不会影响系统回收activity
     *
     * @author zb
     */
    protected static class AsyncMessageHandler extends Handler {

        WeakReference<MainActivity> mActivityReference; // 弱引用

        public AsyncMessageHandler(MainActivity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = mActivityReference.get();
            if (activity != null) {
                // activity 已经处于销毁阶段
                if (activity.getCurrentStatus() != ActivityStatus.DESTROY && activity.getWindowManager() != null) {
                    // activity.dismissAllDialog();
                    activity.parserMessage(msg); // handler回调完全交给子类处理
                }
            }
        }
    }


    //rxjava  请求 封装到页面
    public void launchRequest(Context context, Observable<String> observable, final int requestCode) {

        // hai yong rxjava1 ?  那相关解除绑定那个国人搞哦！
        // context?

        subscriptionList.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<String>(context, new SimpleCallback<String>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onNext(String s) {
                        if (networkCallback != null) {
                            networkCallback.onNetworkCallback(s, requestCode);
                        }

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError() {
                        if (networkCallback != null) {
                            networkCallback.onNetworkCallback("{\"status\":-1}", requestCode);//-1代表网络请求失败
                        }

                    }
                })));


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < subscriptionList.size(); i++) {
            if (subscriptionList.get(i) != null
                    && subscriptionList.get(i).isUnsubscribed()) {
                subscriptionList.get(i).unsubscribe();
            }

        }
    }
}
