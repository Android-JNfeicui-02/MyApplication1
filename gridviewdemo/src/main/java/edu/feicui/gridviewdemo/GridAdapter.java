package edu.feicui.gridviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by admin on 2016/7/18.
 */
public class GridAdapter extends BaseAdapter {

    Context mContext;

    public GridAdapter(Context context) {

        mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(180,180)); // 对图片进行了宽高限制
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);

        }else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

    private Integer[] mThumbIds = {
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_4, R.mipmap.sample_5,
            R.mipmap.sample_6, R.mipmap.sample_7,
            R.mipmap.sample_0, R.mipmap.sample_1,
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_4, R.mipmap.sample_5,
            R.mipmap.sample_6, R.mipmap.sample_7,
            R.mipmap.sample_0, R.mipmap.sample_1,
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_4, R.mipmap.sample_5,
            R.mipmap.sample_6, R.mipmap.sample_7
    };
}
