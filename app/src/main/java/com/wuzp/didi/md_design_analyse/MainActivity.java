package com.wuzp.didi.md_design_analyse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuzp.didi.md_design_analyse.adapter.MainAdapter;
import com.wuzp.didi.md_design_analyse.view.stepOne.StepOneActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = $(R.id.rv_contain);
    }

    private void initData() {
        mainAdapter = new MainAdapter(this, DemoModel.values());
        mainAdapter.setOnItemClicked(onItemClicked);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mainAdapter);
    }

    <T> T $(int id) {
        return (T) findViewById(id);
    }


    MainAdapter.OnItemClicked onItemClicked = new MainAdapter.OnItemClicked() {
        @Override
        public void onItemClicked(View view, int position) {
            switch (position) {
                case 0:
                    Intent intentMD = new Intent(MainActivity.this, StepOneActivity.class);
                    startActivity(intentMD);
                    break;
            }
        }
    };
}
