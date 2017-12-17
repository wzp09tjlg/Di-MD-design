package com.wuzp.didi.md_design_analyse.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wuzp.didi.md_design_analyse.R;

/**
 * 使用方式：
 * 1.与CoordinatorLayout使用方式一样。
 * 2.设置需要下拉放大的View的Id。
 *
 * @author wuzp on 2017/12/9.
 */
public class CoordinatorLayoutCompat extends CoordinatorLayout {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_COLLAPSED = 1;

    private View mZoomView;
    private int mZoomViewWidth;
    private int mZoomViewHeight;

    private int mZoomId;
    //是否正在下拉
    private boolean mIsPulling;
    private int mLastY;
    //滑动放大系数，系数越大，滑动时放大程度越大
    private float mScaleRatio = 0.4f;
    //最大的放大倍数
    private float mScaleTimes = 1.5f;
    //回弹时间系数，系数越小，回弹越快
    private float mReplyRatio = 0.5f;
    //0 表示正常态可以对背景图拉伸 1表示收缩态态不能对背景图做拉伸
    private int mStatus = STATUS_NORMAL;
    //记录Down事件的点
    private float mDownY = 0f;

    public CoordinatorLayoutCompat(Context context) {
        super(context);
        init(context, null);
    }

    public CoordinatorLayoutCompat(Context context, AttributeSet attr) {
        super(context, attr);
        init(context, attr);
    }

    public CoordinatorLayoutCompat(Context context, AttributeSet attri, int flag) {
        super(context, attri, flag);
        init(context, attri);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorLayoutCompat);
        try {
            mZoomId = t.getResourceId(R.styleable.CoordinatorLayoutCompat_zoomId, -1);
        } finally {
            if (t != null) {
                t.recycle();
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mZoomView = findViewById(mZoomId);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mZoomView != null) {
            mZoomViewWidth = mZoomView.getMeasuredWidth();
            mZoomViewHeight = mZoomView.getMeasuredHeight();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mZoomView == null || mStatus == STATUS_COLLAPSED) {
            return super.dispatchTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mIsPulling) {
                    //第一次下拉
                    if (getScrollY() == 0) {
                        //在顶部的时候，记录顶部位置
                        mLastY = (int) ev.getY();
                    } else {
                        break;
                    }
                }
                if (ev.getY() - mLastY < 0) {
                    mIsPulling = false;
                    return super.dispatchTouchEvent(ev);
                }

                int distance = (int) ((ev.getY() - mLastY) * mScaleRatio);
                mIsPulling = true;
                setZoom(distance);
                return true;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                float tempUpY = ev.getY();
                mLastY = -1;
                mIsPulling = false;
                replyView();
                if (Math.abs(tempUpY - mDownY) < 5f) {
                    return super.dispatchTouchEvent(ev);
                }
                return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 放大view
     */
    private void setZoom(float distance) {
        float scaleTimes = (float) ((mZoomViewWidth + distance) / (mZoomViewWidth * 1.0f));
        //如超过最大放大倍数，直接返回
        if (scaleTimes > mScaleTimes) return;

        ViewGroup.LayoutParams layoutParams = mZoomView.getLayoutParams();
        layoutParams.width = (int) (mZoomViewWidth + distance);
        layoutParams.height = (int) (mZoomViewHeight * ((mZoomViewWidth + distance) / mZoomViewWidth));
        //设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - mZoomViewWidth) / 2, 0, 0, 0);
        mZoomView.setLayoutParams(layoutParams);
    }

    /**
     * 回弹
     */
    private void replyView() {
        final float distance = mZoomView.getMeasuredWidth() - mZoomViewWidth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }
}
