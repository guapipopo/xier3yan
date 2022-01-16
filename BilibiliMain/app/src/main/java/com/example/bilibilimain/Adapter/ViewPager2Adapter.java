package com.example.bilibilimain.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilibilimain.Beans.itemBean;
import com.example.bilibilimain.Beans.itemView;
import com.example.bilibilimain.R;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewPagerHolder> {

    private List<itemBean> Views;
    private Context context;

    public ViewPager2Adapter(List<itemBean> Views,Context context){
        this.Views=Views;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewPager2Adapter.ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerHolder(LayoutInflater.from(context).inflate((R.layout.item_view), parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager2Adapter.ViewPagerHolder holder, int position) {
            holder.setDate(Views.get(position),position);
    }



    @Override
    public int getItemCount() {
        if(Views!=null){
            return Views.size();
        }
        return 0;
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder{
        private ImageView mView;
        private TextView mText;
        private int mPosition;

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView.findViewById(R.id.ViewPager);
            mText=itemView.findViewById(R.id.name);
        }

        public void setDate(itemBean itemBean, int position){
            this.mPosition=position;
            mView.setImageResource(itemBean.pager);
            mText.setText(itemBean.name+"的动态");
        }
    }


}
