package com.diy.rxjavalib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by admin on 2017/12/6.
 */

public class ProgressDialogUtils extends Dialog {
    private ImageView mLoadingImageView;
    private AnimationDrawable mLoadingAnimationDrawable;

    public ProgressDialogUtils(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ProgressDialogUtils(Context context, int theme) {
        super(context, theme);
    }

    public ProgressDialogUtils(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View loadingView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_foot_loading, null);
        mLoadingImageView = (ImageView) loadingView.findViewById(R.id.icon_foot);
        mLoadingImageView.setImageResource(R.drawable.animation);
        setContentView(loadingView);
    }

    @Override
    public void show() {
        super.show();
        //注意将动画的启动放置在Handler中.否则只可看到第一张图片
        new Handler() {
        }.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingAnimationDrawable = (AnimationDrawable) mLoadingImageView.getDrawable();
                mLoadingAnimationDrawable.start();
            }
        }, 10);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //结束帧动画
        mLoadingAnimationDrawable = (AnimationDrawable) mLoadingImageView.getDrawable();
        mLoadingAnimationDrawable.stop();
    }
}
