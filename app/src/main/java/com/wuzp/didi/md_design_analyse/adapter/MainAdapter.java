package com.wuzp.didi.md_design_analyse.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuzp.didi.md_design_analyse.DemoModel;
import com.wuzp.didi.md_design_analyse.R;

/**
 * @author wuzhenpeng03 (wuzhenpeng03@didichuxing.com)
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final DemoModel[] items;
    private Context mContext;

    public MainAdapter(Context context, DemoModel[] items) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(position, items[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClicked != null){
                    onItemClicked.onItemClicked(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView imgDot;
        private DemoModel model;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            imgDot = (ImageView) itemView.findViewById(R.id.img_dot);
        }

        void bind(int position, DemoModel item) {
            model = item;
            tvTitle.setText(item.title);

            imgDot.getDrawable().setColorFilter(ContextCompat.getColor(mContext, item.color), PorterDuff.Mode.SRC_ATOP);
            this.position = position;

            ViewCompat.setTransitionName(tvTitle, mContext.getResources().getString(R.string.transition_tag_title_indexed, position));
            ViewCompat.setTransitionName(imgDot, mContext.getResources().getString(R.string.transition_tag_dot_indexed, position));
        }
    }

    private OnItemClicked onItemClicked;

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    public interface OnItemClicked{
        public void onItemClicked(View view,int position);
    }
}