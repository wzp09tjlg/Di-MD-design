package com.wuzp.didi.md_design_analyse.view.stepOne.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wuzp.didi.md_design_analyse.R;

/**
 * 商铺首页中 商铺标题栏的behavior
 *
 * @author wuzhenpeng03 (wuzhenpeng03@didichuxing.com)
 */
@SuppressWarnings("all")
public class TitleBarBehavior extends CoordinatorLayout.Behavior<FrameLayout> {

    private float mTargetBottom = -1f;

    public TitleBarBehavior(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency) {
        return dependency instanceof LinearLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency) {
        if (mTargetBottom == -1f) {
            mTargetBottom = dependency.getBottom();
        }
        float bottom = dependency.getBottom();


        float percent = bottom / mTargetBottom;

        //根据效果采用缩短 0-1 之间的渐变速度，将速度变化扩大化。取0-0.7之间的变换值为0-1
        if (percent < 0.3f) {
            child.findViewById(R.id.fl_business_header1).setAlpha(1f);
            child.findViewById(R.id.tv_business_title_name).setAlpha(1f);
            ((ImageView) child.findViewById(R.id.iv_business_back))
                    .setImageDrawable(parent.getContext().getResources()
                            .getDrawable(R.drawable.common_icon_back_press));
        } else if (percent <= 1.0f) {
            child.findViewById(R.id.fl_business_header1).setAlpha(1f - (percent - 0.3f) / 0.7f);
            child.findViewById(R.id.tv_business_title_name).setAlpha(1f - (percent - 0.3f) / 0.7f);
            ((ImageView) child.findViewById(R.id.iv_business_back))
                    .setImageDrawable(parent.getContext().getResources()
                            .getDrawable(R.drawable.common_icon_back_normal));
        }
        return true;
    }
}
