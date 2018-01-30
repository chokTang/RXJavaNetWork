package network.rxjavanetwork.retrofit.simple;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

import com.diy.rxjavalib.CustomAlertDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


import network.rxjavanetwork.R;
import rx.Subscriber;

/**
 * 错误统一处理
 *
 */

public class ExceptionSubscriber<T> extends Subscriber<T> {

    @SuppressLint("StaticFieldLeak") // 这里在对话框消失后，会将dialog置为null，不会持有context对象
    private static CustomAlertDialog dialog; // 防止重复弹出对话框
    private SimpleCallback<T> simpleCallback;
    Context context;

    public ExceptionSubscriber(Context context,SimpleCallback simpleCallback){
        this.simpleCallback = simpleCallback;
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(simpleCallback != null)
            simpleCallback.onStart();
    }

    @Override
    public void onCompleted() {
        if(simpleCallback != null)
            simpleCallback.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
//            ZZUtil.log("网络中断，请检查您的网络状态");
            showDialog();
        } else if (e instanceof ConnectException) {
            showDialog();
//            ZZUtil.log("网络中断，请检查您的网络状态");
        } else {
//            ZZUtil.showToast(context,"error:" + e.getMessage());
//            ZZUtil.log("error:" + e.getMessage());
        }
        if(simpleCallback != null)
            simpleCallback.onComplete();
    }


    private void showDialog(){
        if (dialog == null) {
            dialog = new CustomAlertDialog(context);
        }
        dialog.setMessage(context.getString(R.string.error_net_hint));
        dialog.setTitle(context.getString(R.string.system_hint));
        dialog.setPositive(context.getString(R.string.positive), null);
        // 注：这里是有意义的，切勿删除
        // 在对话框没消失时，可有效拦截多个对话框的弹出
        // 对话框消失后置空，可有效防止windowBadToken错误
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ExceptionSubscriber.dialog = null;
            }
        });
        if (null != dialog && !dialog.isShowing()) {
            try {
                dialog.show();
            } catch (Exception e1) { // windowBadToken
                dialog = null; // 重置对话框
            }
        }
    }
    @Override
    public void onNext(T t) {
        if(simpleCallback != null)
            simpleCallback.onNext(t);
    }
}
