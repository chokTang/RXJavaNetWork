package com.diy.rxjavalib;

import android.app.Dialog;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Activity基类，主要功能处理生命周期与弹出层的关系
 * 主要功能，提供默认的ui等待对话框
 * 提供右滑关闭功能
 * 支持设置自定义回调处理（需调用setApiMessage方法）
 * <p>
 * 注：自定义任务类型常量请设置在100 - 199
 * Created by zhang on 2015/12/28.
 */
public class BaseActivity extends AutoLayoutActivity implements GestureDetector.OnGestureListener {

    public final String TAG = getClass().getSimpleName();

    public static final int FINISH = 700; // 结束当前界面
    public static final int INTENT = 701; // 跳转下页
    public static final int INTENT_AND_FINISH = 702; // 跳转并结束当前
    public static final int INTENT_FOR_RESULT = 703; // 有返回值的跳转

    List<BaseActivity> activityList = new ArrayList<>();
    private Dialog loadingDialog; // 加载等待对话框

    private GestureDetector detector; // 手势监听
    private ActivityStatus currentStatus; // 当前activity运行状态
    private boolean LTR_CLOSE = false; // 是否打开手势滑动关闭功能
    private String pageName; // 友盟的页面统计（不设值将不统计）
    private int countLoading = 0; // 这里保证所有请求完成才会消失对话框
    private ProgressDialogUtils progressDialogUtils;

    public enum ActivityStatus {
        RESUME, PAUSE, STOP, DESTROY // 分别对应activity的生命周期
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 显示当前activity的名字
        detector = new GestureDetector(this, this);
        initDialog();

    }

    @Override
    public Resources getResources() {
        // 去掉系统修改字体大小的影响
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        if (res != null) {
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void onResume() {
        currentStatus = ActivityStatus.RESUME;
        super.onResume();
//        if (!ZZValidator.isEmpty(pageName)) { // 不需要统计的页面不设值
//            ZZAnalysis.onPageStart(pageName);
//        }
//        ZZAnalysis.onResume(this);
        initDialog();
    }

    @Override
    protected void onPause() {
        currentStatus = ActivityStatus.PAUSE;
        super.onPause();
//        if (!ZZValidator.isEmpty(pageName)) {
//            ZZAnalysis.onPageEnd(pageName);
//        }
//        ZZAnalysis.onPause(this);
        closeInput();
    }

    /**
     * 关闭软键盘
     */
    protected void closeInput() {
        if (getCurrentFocus() != null) { // 关闭所弹出的软键盘
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentStatus = ActivityStatus.STOP;
        dismissAllDialog();
    }

    @Override
    protected void onDestroy() {
        currentStatus = ActivityStatus.DESTROY;
        super.onDestroy();
    }

    /**
     * 按下
     *
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    /**
     * 单击
     *
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    /**
     * 单击离开
     *
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    /**
     * 滚动
     *
     * @param motionEvent
     * @param motionEvent1
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    /**
     * 长按
     *
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

//    public void addActivity(BaseActivity activity) {
//        ZZUtil.log("99999999999999999");
//        if (activityList == null) {
//            activityList = new ArrayList<BaseActivity>();
//        }
//        activityList.add(activity);
//    }
//
//    /**
//     * app退出
//     */
//    public void exitActvity() {
//        for (BaseActivity activity : activityList) {
//            if (!activity.isFinishing() && activity != null) {
//                activity.finish();
//            }
//        }
//        clearActivity();
//        ZZUtil.log("888888888888888888888");
////        System.exit(0);
//    }
//
//
//    /** 清空列表，取消引用 */
//    public void clearActivity() {
//        activityList.clear();
//    }
    /**
     * 滑动
     *
     * @param e1
     * @param e2
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        //注：回调 3
        //水平滑动距离超过200像素且，速度大于1500，垂直速度小于500可触发
        //仿ios的，向右滑动关闭activity,重写goBack方法即可关闭该功能
        float speed = Math.abs(v / v1);
        if (e2.getX() - e1.getX() > 400 && speed > 2 && v > 1500 && Math.abs(e2.getY() - e1.getY()) < 200) {
            goBack(null);
            return true;
        }
        return false;
    }

    /**
     * setLeftToRightToClose
     * 是否开启右滑关闭功能（默认开启）
     *
     * @param close 是否关闭
     * @author zhang
     */
    protected void setLeftToRightToClose(boolean close) {
        this.LTR_CLOSE = close;
    }

    /**
     * getCurrentStatus
     * 获取当前运行状态
     *
     * @return ActivityStatus 当前activity的运行状态
     * @author zhang
     */
    public ActivityStatus getCurrentStatus() {
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
        if (view != null && progressDialogUtils != null) {
            progressDialogUtils.setContentView(view);
        }
    }

    protected Dialog getLoadingDialog() {
        return this.progressDialogUtils;
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
//            progressDialogUtils = new ProgressDialogUtils(this, R.style.CustomProgressDialog);
//            Window window = progressDialogUtils.getWindow();
//            window.requestFeature(Window.FEATURE_NO_TITLE);
        }
    }

    /**
     * showLoadingDialog
     * 显示等待对话框
     *
     * @author zhang
     */
    protected void showLoadingDialog() {
        progressDialogUtils.show();
        countLoading++;
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
        if (countLoading > 0) {
            countLoading--;
        }
        if (countLoading == 0 && progressDialogUtils != null && progressDialogUtils.isShowing()) {
            progressDialogUtils.dismiss();
        }
    }

    /**
     * Log
     * 打印带有当前activity名称的Log日志
     *
     * @param message 将要打印的内容
     * @author zhang
     */
    protected void Log(String message) {
    }

    /**
     * 重载activity的手势事件
     *
     * @param event
     * @return 手势已经处理true，否则false
     * @author zhang
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!LTR_CLOSE) {
            return super.dispatchTouchEvent(event);
        }
        return detector.onTouchEvent(event) ? true : super.dispatchTouchEvent(event);
    }

    /**
     * goBack
     * 返回（xml中onClick=goBack定义）
     *
     * @param view 被点击的view
     * @author zhang
     */
    public void goBack(View view) {
        // 系统的返回键会优先关闭掉输入键盘，因此这里模拟back按键的事件
        actionKey(KeyEvent.KEYCODE_BACK);
    }

    /**
     * 模拟键盘事件方法
     *
     * @param keyCode 按键
     */
    private void actionKey(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }


    /**
     * 请求权限
     */
    protected void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
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

//    /**
//     * 启动方法
//     * 带参数
//     *
//     * @param context 前一个页面
//     */
//    public static void startActivity(Activity context, Class<?> cls, Bundle bundle) {
//
//
//        Intent intent = new Intent(context, cls);
//        intent.putExtra("bundle", bundle);
//        context.startActivity(intent);
//        context.finish();
//
//
//    }

//
//    /**
//     * 启动方法
//     * 不带参数
//     *
//     * @param context
//     */
//    public static void startActivity(Activity context, Class<?> cls) {
//
//        Intent intent = new Intent(context, cls);
//        context.startActivity(intent);
//        context.finish();
//
//    }

    class ExitReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.finish();
        }

    }


}
