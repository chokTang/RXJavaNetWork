package com.diy.rxjavalib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * 自定义对话框，兼容高低版本显示差异问题
 * Created by Administrator on 2015/11/6.
 */
public class CustomAlertDialog extends Dialog implements View.OnClickListener {

    public CustomAlertDialog(Context context) {
        super(context, R.style.Translucent_HALF);
    }

    public CustomAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    protected CustomAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    View divider; // 分隔线，底部2个按钮之间
    TextView message; // 显示的消息内容
    TextView positive; // 确定
    TextView negative; // 取消
    TextView title; // 标题
    String msg; // 消息文字
    String pos; // 确定文字
    String neg; // 取消文字
    String textTitle; // 标题文字
    int mColor = Color.rgb(0x33, 0x33, 0x33); // 消息字体颜色
    int pColor = Color.rgb(0x76, 0xc1, 0x5d); // 确定字体颜色
    int nColor = Color.rgb(0x66, 0x66, 0x66); // 取消字体颜色

    OnClickListener onPositive; // 点击确定的回调
    OnClickListener onNegative; // 点击取消的回调

    boolean isCancel = true; // 是否可点击外部取消

    public void setCancel(boolean isCancel){
        this.isCancel = isCancel;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        int id = view.getId();
        if (id == R.id.message_positive) {
            if (onPositive != null) {
                onPositive.onClick(view);
            }
        } else if (id == R.id.message_negative) {
            if (onNegative != null) {
                onNegative.onClick(view);
            }
        }
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_message);
        divider = findViewById(R.id.message_divider);
        message = (TextView) findViewById(R.id.message_message);
        positive = (TextView) findViewById(R.id.message_positive);
        negative = (TextView) findViewById(R.id.message_negative);
        title = (TextView) findViewById(R.id.message_title);
        positive.setOnClickListener(this);
        negative.setOnClickListener(this);
        setView();
    }

    /**
     * 设置文字，颜色，显示
     */
    private void setView() {
        if (msg == null) {
            message.setVisibility(View.GONE);
        } else {
            message.setVisibility(View.VISIBLE);
            message.setText(msg);
            message.setTextColor(mColor);
        }
        if (textTitle == null) {
            title.setVisibility(View.GONE);
        } else {
            title.setVisibility(View.VISIBLE);
            title.setText(textTitle);
            title.setTextColor(mColor);
        }
        if (pos == null) {
            positive.setVisibility(View.GONE);
        } else {
            positive.setVisibility(View.VISIBLE);
            positive.setText(pos);
            positive.setTextColor(pColor);
        }
        if (neg == null) {
            negative.setVisibility(View.GONE);
        } else {
            negative.setVisibility(View.VISIBLE);
            negative.setText(neg);
            negative.setTextColor(nColor);
        }
        if (pos == null || neg == null) {
            divider.setVisibility(View.GONE);
        } else {
            divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (isCancel){
            super.onBackPressed();
        }
    }

    public CustomAlertDialog setMessage(String msg, int mColor) {
        this.msg = msg;
        if (msg == null) {
            if (message != null)
                message.setVisibility(View.GONE);
            return this;
        }
        this.mColor = mColor;
        if (message != null) {
            message.setVisibility(View.VISIBLE);
            message.setText(msg);
            message.setTextColor(mColor);
        }
        return this;
    }

    public CustomAlertDialog setTitle(String textTitle) {
        this.textTitle = textTitle;
        if (textTitle == null) {
            title.setVisibility(View.GONE);
            return this;
        }
        if (title != null) {
            title.setVisibility(View.VISIBLE);
            title.setText(textTitle);
            title.setTextColor(mColor);
        }
        return this;
    }

    public CustomAlertDialog setMessage(String msg) {
        this.msg = msg;
        if (msg == null) {
            message.setVisibility(View.GONE);
            return this;
        }
        if (message != null) {
            message.setVisibility(View.VISIBLE);
            message.setText(msg);
            message.setTextColor(mColor);
        }
        return this;
    }

    public CustomAlertDialog setPositive(String pos, int pColor, OnClickListener onPositive) {
        this.pos = pos;
        if (pos == null) {
            positive.setVisibility(View.GONE);
            return this;
        }
        this.pColor = pColor;
        this.onPositive = onPositive;
        if (positive != null) {
            positive.setVisibility(View.VISIBLE);
            positive.setText(pos);
            positive.setTextColor(pColor);
        }
        return this;
    }

    public CustomAlertDialog setPositive(String pos, OnClickListener onPositive) {
        this.pos = pos;
        if (pos == null) {
            positive.setVisibility(View.GONE);
            return this;
        }
        this.onPositive = onPositive;
        if (positive != null) {
            positive.setVisibility(View.VISIBLE);
            positive.setText(pos);
            positive.setTextColor(pColor);
        }
        return this;
    }

    public CustomAlertDialog setNegative(String neg, int nColor, OnClickListener onNegative) {
        this.neg = neg;
        if (neg == null) {
            negative.setVisibility(View.GONE);
            return this;
        }
        this.nColor = nColor;
        this.onNegative = onNegative;
        if (negative != null) {
            negative.setVisibility(View.VISIBLE);
            negative.setText(neg);
            negative.setTextColor(nColor);
        }
        return this;
    }

    public CustomAlertDialog setNegative(String neg, OnClickListener onNegative) {
        this.neg = neg;
        if (neg == null) {
            negative.setVisibility(View.GONE);
            return this;
        }
        this.onNegative = onNegative;
        if (negative != null) {
            negative.setVisibility(View.VISIBLE);
            negative.setText(neg);
            negative.setTextColor(nColor);
        }
        return this;
    }

    public static class Builder {

        CustomAlertDialog customAlertDialog;
        Context context;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
            customAlertDialog = new CustomAlertDialog(this.context);
        }

        public CustomAlertDialog build() {
            return customAlertDialog;
        }

        public Builder setTitle(String title) {
            customAlertDialog.setTitle(title);
            return this;
        }

        public Builder setMessage(String msg) {
            customAlertDialog.setMessage(msg);
            return this;
        }

        public Builder setPositive(String pos, OnClickListener onPositive) {
            customAlertDialog.setPositive(pos, onPositive);
            return this;
        }

        public Builder setNegative(String neg, OnClickListener onNegative) {
            customAlertDialog.setNegative(neg, onNegative);
            return this;
        }

    }


}
