package com.wuzp.didi.md_design_analyse.view.stepOne.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 商铺首页中 商铺卡片信息的behavior
 *
 * @author wuzhenpeng03 (wuzhenpeng03@didichuxing.com)
 */
@SuppressWarnings("all")
public class CardInfoBehavior extends CoordinatorLayout.Behavior<CardView> {//

    private float mTargetBottom = -1f;
    private float mViewHeight = -1f;

    public CardInfoBehavior() {
    }

    public CardInfoBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CardView child, View dependency) {
        return dependency instanceof LinearLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CardView child, View dependency) {
        if (mTargetBottom == -1f) {
            mTargetBottom = dependency.getBottom();
            mViewHeight = child.getHeight();
        }
        float bottom = dependency.getBottom();
        if (bottom >= mTargetBottom) {
            bottom = mTargetBottom;
        }
        float percent = bottom / mTargetBottom;

        //根据效果采用缩短 0-1 之间的渐变速度，将速度变化扩大化，
        // 对于透明度在取0-0.4之间的变换值为0-1
        // 对于Y轴 移动距离在 0.2-0.6之间取值 (根据距顶部高度和view的高度得一个比例 25／32，由此值计算)
        if (percent < 0.6f) {
            child.setAlpha(0f);
            child.setClickable(false);
        } else if (percent <= 1.0f) {
            child.setClickable(true);
            child.setAlpha((percent - 0.6f) / 0.4f);
            float tempFactor = (percent - 0.5f) / 0.5f;
            child.setY(mViewHeight * 25f / 32f * (0.2f + tempFactor * 0.4f));
        }
        return true;
    }
}
