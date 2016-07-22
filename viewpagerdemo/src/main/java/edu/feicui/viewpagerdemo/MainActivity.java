package edu.feicui.viewpagerdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SP_CONFIG = "sp_config";
    public static final String IS_FIRST_RUN = "is_first_run";
    private ViewPager mViewPager;
    Button mBtnSkip;

    private ArrayList<View> mList;

    int[] pics = {R.mipmap.adware_style_applist, R.mipmap.adware_style_banner, R.mipmap.adware_style_creditswall};
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 生成Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SP_CONFIG, MODE_PRIVATE);
        // 从sp文件中读取 key 是否为true
        boolean isFirstRun = sharedPreferences.getBoolean(IS_FIRST_RUN, true);

        // 如果不是第一次运行
        if (!isFirstRun) {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            finish();
        } else {
            setContentView(R.layout.activity_main);


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initAdapterAction();
    }

    private void initAdapterAction() {
        mViewPager.setAdapter(new MyPagerAdapter(mList));
        mViewPager.addOnPageChangeListener(mListener);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void initView() {

        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mBtnSkip.setOnClickListener(this);
        mBtnSkip.setVisibility(View.VISIBLE);

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        // 不要忘记new 对象 否则会出现 空指针异常
        mList = new ArrayList<>();

        // 把图片资源放在list
        for (int i = 0; i < pics.length; i++) {
            mIv = new ImageView(this);
            mIv.setImageResource(pics[i]);
            mList.add(mIv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip:
                // 对shared Preferences做修改
                SharedPreferences preferences = getSharedPreferences(SP_CONFIG, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(IS_FIRST_RUN,false);
                editor.apply();

                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                // 结束当前的activity
                finish();

                break;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
        private static final String TAG = "MainActivity";

        // 当页面滚动时回调 返回的值是 position 和 偏移百分比 和 偏移像素
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "onPageScrolled: position = " + position + ", positionOffset = " + positionOffset
                    + ", positionOffsetPixels = " + positionOffsetPixels);
        }

        // 当页面选择后会调用
        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected: " + position);
        }


        // 当滑动状态改变时
        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG, "onPageScrollStateChanged: " + state);
            // state = 1  state = 2  state = 0
        }
    };


}
