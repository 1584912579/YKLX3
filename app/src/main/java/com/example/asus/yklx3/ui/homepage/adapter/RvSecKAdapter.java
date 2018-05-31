package com.example.asus.yklx3.ui.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.yklx3.R;
import com.example.asus.yklx3.bean.GetAdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by asus on 2018/5/31.
 */

public class RvSecKAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<GetAdBean.MiaoshaBean.ListBeanX> list;

    public RvSecKAdapter(Context context, List<GetAdBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.r_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1=(ViewHolder) holder;
        String images = list.get(position).getImages();
        holder1.sim.setImageURI(images.split("\\|")[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView sim;

        public ViewHolder(View itemView) {
            super(itemView);
             sim = itemView.findViewById(R.id.sim);
        }
    }

}
