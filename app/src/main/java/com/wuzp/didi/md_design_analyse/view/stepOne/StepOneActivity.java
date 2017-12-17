package com.wuzp.didi.md_design_analyse.view.stepOne;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wuzp.didi.md_design_analyse.R;
import com.wuzp.didi.md_design_analyse.view.stepOne.adapter.DishAdapter;
import com.wuzp.didi.md_design_analyse.view.stepOne.entity.DishBean;
import com.wuzp.didi.md_design_analyse.widget.CoordinatorLayoutCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzhenpeng03 (wuzhenpeng03@didichuxing.com)
 */
public class StepOneActivity extends AppCompatActivity {

    private CoordinatorLayoutCompat coordinatorLayoutCompat;
    private RecyclerView recyclerView;

    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one_base);
        initView();
        initData();
    }

    private void initView() {
        coordinatorLayoutCompat = $(R.id.clc_root);
        recyclerView = $(R.id.rv_contain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dishAdapter = new DishAdapter(this, getData());
        ((AppBarLayout) findViewById(R.id.abl_business)).
                addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        int maxOffset = appBarLayout.getTotalScrollRange();
                        float percent = Math.abs((float) verticalOffset) / (float) maxOffset;

                        if (percent <= 0.001) {
                            coordinatorLayoutCompat.setStatus(CoordinatorLayoutCompat.STATUS_NORMAL);
                        } else {
                            coordinatorLayoutCompat.setStatus(CoordinatorLayoutCompat.STATUS_COLLAPSED);
                        }
                    }
                });
    }

    private void initData() {
        recyclerView.setAdapter(dishAdapter);
    }

    private List<DishBean> getData() {
        List<DishBean> mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DishBean bean = new DishBean("name is no:" + i);
            mData.add(bean);
        }
        return mData;
    }

    private <T> T $(int id) {
        return (T) findViewById(id);
    }
}
