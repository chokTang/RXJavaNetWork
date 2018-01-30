package com.diy.rxjavalib;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import simple.ExceptionSubscriber;
import simple.SimpleCallback;

/**
 * Created by Zhang on 2016/8/8.
 */
public class BaseFragment extends Fragment {


    private FragmentStatus currentStatus; // 当前activity运行状态
    private String pageName; // 友盟的页面统计（不设值将不统计）
    private ProgressDialogUtils progressDialogUtils;

    public enum FragmentStatus {
        RESUME, PAUSE, STOP, DESTROY // 分别对应activity的生命周期
    }


    //rxjava  请求 封装到页面
    protected List<Subscription> subscriptionList = new ArrayList<>();

    public interface NetworkCallback {
        void onNetworkCallback(String responseBody, int requestCode);
    }

    protected NetworkCallback networkCallback;

    public void bindCallback(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
                            networkCallback.onNetworkCallback("{\"status\":-1}", requestCode);//-1 代表网络返回失败
                        }
                    }

                })));

    }


    @Override
    public void onResume() {
        currentStatus = FragmentStatus.RESUME;
        super.onResume();
//        if (!ZZValidator.isEmpty(pageName)) { // 不需要统计的页面不设值
//            ZZAnalysis.onPageStart(pageName);
//        }
        initDialog();
    }

    @Override
    public void onPause() {
        currentStatus = FragmentStatus.PAUSE;
        super.onPause();
//        if (!ZZValidator.isEmpty(pageName)) {
//            ZZAnalysis.onPageEnd(pageName);
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
        currentStatus = FragmentStatus.STOP;
        dismissAllDialog();
    }

    @Override
    public void onDestroyView() {
        currentStatus = FragmentStatus.DESTROY;
        super.onDestroyView();
        for (int i = 0; i < subscriptionList.size(); i++) {
            if (subscriptionList.get(i) != null
                    && subscriptionList.get(i).isUnsubscribed()) {
                subscriptionList.get(i).unsubscribe();
            }

        }
    }

    /**
     * getCurrentStatus
     * 获取当前运行状态
     *
     * @return ActivityStatus 当前activity的运行状态
     * @author zhang
     */
    public FragmentStatus getCurrentStatus() {
        return this.currentStatus;
    }

    /**
     * setLoadingDialog
     * 设置自定义的加载等待对话框
     *
     * @param view 自定义网络等待view
     * @author zhang
     */
    protected void setLoadingDialog(View view) {
        if (view != null) {
            if (progressDialogUtils == null) {
                //第二种-->frame动画
//                ZZUtil.log("实例化");
                progressDialogUtils = new ProgressDialogUtils(getActivity(), R.style.CustomProgressDialog);
            }
        }
    }

    /**
     * initDialog
     * 初始化对话框，子类可重写该方法，与dismiss对应
     *
     * @author zhang
     */
    protected void initDialog() {
        if (progressDialogUtils == null) {
            //第二种-->frame动画
//            ZZUtil.log("实例化");
            progressDialogUtils = new ProgressDialogUtils(getActivity(), R.style.Translucent_NoTitle);
            Window window = progressDialogUtils.getWindow();
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }

    }

    /**
     * showLoadingDialog
     * 显示等待对话框
     *
     * @author zhang
     */
    protected void showLoadingDialog() {
        if (progressDialogUtils != null && !progressDialogUtils.isShowing()) {
            progressDialogUtils.show();
        }
    }

    /**
     * dismissAllDialog
     * 结束掉所有的对话框，弹出层
     * activity销毁时应先结束所有弹出层，以防止window bad token
     *
     * @author zhang
     */
    protected void dismissAllDialog() {
        if (progressDialogUtils != null && progressDialogUtils.isShowing()) {
            progressDialogUtils.dismiss();
        }
    }

    /**
     * setPageName
     * 设置该页面统计时的名称
     *
     * @param pageName
     * @author zhang
     */
    protected void setPageName(String pageName) {
        this.pageName = pageName;
    }


    /**
     * 判断是否拥有权限
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }


    /**
     * 请求权限
     */
    protected void requestPermission(int code, String... permissions) {

        requestPermissions(permissions, code);
//        ZZUtil.showToast(getActivity(), "如果拒绝授权,会导致应用无法正常使用");
    }


    /**
     * 请求权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

//说明：
//Constants.CODE_CAMERA
//这是在外部封装的一个常量类，里面有许多静态的URL以及权限的CODE，可以自定义
//但是在调用的时候，记得这个CODE要和你自己定义的CODE一一对应
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
