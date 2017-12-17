package com.wuzp.didi.md_design_analyse.view.stepOne.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuzp.didi.md_design_analyse.R;
import com.wuzp.didi.md_design_analyse.view.stepOne.entity.DishBean;

import java.util.List;

/**
 * @project Hello2T
 * @since 2017/11/29 - 上午11:57
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<DishBean> mData;

    public DishAdapter(Context context, List<DishBean> data){
        this.mContext = context;
        this.mData = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_dish,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         holder.name.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        public ViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.tv_name);
        }
    }
}
