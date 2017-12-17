package com.wuzp.didi.md_design_analyse;

import android.support.annotation.ColorRes;

/**
 * @author wuzhenpeng03 (wuzhenpeng03@didichuxing.com)
 */
public enum DemoModel {
    NAVIGATION("Navigation Demos", R.color.red_300),
    TRANSITIONS("Transition Demos", R.color.blue_grey_300),
    SHARED_ELEMENT_TRANSITIONS("Shared Element Demos", R.color.purple_300),
    CHILD_CONTROLLERS("Child Controllers", R.color.orange_300),
    VIEW_PAGER("ViewPager", R.color.green_300),
    TARGET_CONTROLLER("Target Controller", R.color.pink_300),
    MULTIPLE_CHILD_ROUTERS("Multiple Child Routers", R.color.deep_orange_300),
    MASTER_DETAIL("Master Detail", R.color.grey_300),
    DRAG_DISMISS("Drag Dismiss", R.color.lime_300),
    PICTURES("Pictures", R.color.orange_300),
    EXTERNAL_MODULES("Bonus Modules", R.color.teal_300),
    NOVA_SUPPORT("Nova Support", R.color.indigo_300),
    NOVA_MAGAZINE("Nova magazine", R.color.yellow_300),
    DIALOG_CONTROLLER("Dialog Controller", R.color.orange_300),
    DIALOG_DEMO_CONTROLLER("Dialog demo Controller", R.color.brown_300),
    LOGIN_PAGE("Login", R.color.deep_purple_300),
    ROUTER_PAGE("Router", R.color.red_300),
    Banner("轮播图", R.color.grey_300);

    public String title;
    @ColorRes
    public int color;

    DemoModel(String title, @ColorRes int color) {
        this.title = title;
        this.color = color;
    }
}
