package com.example.bilibilimain.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MypagerAdapter extends PagerAdapter {

    ArrayList<View> pagers;

    public MypagerAdapter(ArrayList<View> pagers){
        this.pagers=pagers;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pagers.get(position));
        return pagers.get(position);
    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

}

