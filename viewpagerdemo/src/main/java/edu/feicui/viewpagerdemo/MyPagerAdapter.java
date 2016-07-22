package edu.feicui.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/12.
 */
public class MyPagerAdapter extends PagerAdapter {

    // 已知 数据来源是 ArrayList 给它一个对应的数据来源
    private ArrayList<View> mList;
    private static final String TAG = "MyPagerAdapter";


    /**
     * 给一个有参数的constructor 原因是 需要在View Pager中 new 出 Adapter 对象
     * 目的是 强制放入一个数据 让 Adapter 来处理
     *
     * @param list
     */
    public MyPagerAdapter(ArrayList<View> list) {
        mList = list;

    }

    /**
     * 获取View pager的页面数量
     *
     * @return
     */
    @Override
    public int getCount() {
        // 判断非空则返回数据
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    /**
     * 初始化每一个Item
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // 给容器里添加视图
         container.addView(mList.get(position), 0);
        // 把具体的视图 返回给调用者
        return mList.get(position);

    }

    /**
     * 判断View与Object是否相等 原则上 返回 view == object
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        boolean b = view == object;
        return b;
    }

    /**
     * 当Item不可见时销毁Item
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        // 让系统处理 移除哪个View
        container.removeView(mList.get(position));
    }


}
