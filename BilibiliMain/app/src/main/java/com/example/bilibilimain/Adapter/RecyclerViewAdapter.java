package com.example.bilibilimain.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilibilimain.Beans.itemBean;
import com.example.bilibilimain.BilibiliDetail;
import com.example.bilibilimain.MainActivity;
import com.example.bilibilimain.R;

import java.util.List;

public class RecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.InnerHolder> {

    private final List<itemBean> mData;
    private OnLongItemClickListener mOnLongItemClickListener;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(List<itemBean> datas){

        this.mData=datas;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item_up,null);
        //条目界面
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.InnerHolder holder, int position) {
        //设置数据
        holder.setDate(mData.get(position),position);

    }

    @Override
    public int getItemCount() {
        if(null!=mData){
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener longlistener){
        this.mOnLongItemClickListener= longlistener;
    }

    public interface OnLongItemClickListener{
        void onLongItemClick(int position);
    }

    public class  InnerHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mName;
        private int mPosition;


        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.list_view_icon_1);
            mName = itemView.findViewById(R.id.list_view_name_1);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mOnLongItemClickListener != null) {
                        mOnLongItemClickListener.onLongItemClick(mPosition);
                        return true;
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mPosition);
                    }
                }
            });
        }

        //用于设置数据
        public void setDate(itemBean itemBean,int position) {

            this.mPosition=position;
            mIcon.setImageResource(itemBean.icon);
            mName.setText(itemBean.name);
        }
    }
}
