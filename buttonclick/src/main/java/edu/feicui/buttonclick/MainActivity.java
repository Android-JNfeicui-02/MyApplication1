package edu.feicui.buttonclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton;
    Animation mAnimation;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_show);
        mImageView = (ImageView) findViewById(R.id.iv_pic);

        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_show:
                mAnimation = new ScaleAnimation(1,0.5f,1,0.5f, Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0.5f);

                // 设置 animation 的监听
                mAnimation.setAnimationListener(mListener);
                mAnimation.setDuration(3000);
                mAnimation.setRepeatCount(2);
                mAnimation.setRepeatMode(2);

                mImageView.startAnimation(mAnimation);
                break;

        }

    }

    Animation.AnimationListener mListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
            // 当动画开始时
            Toast.makeText(MainActivity.this, "动画开始了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 当动画结束时
            Toast.makeText(MainActivity.this, "动画结束了", Toast.LENGTH_SHORT).show();

            // intent 跳转
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // 当动画重复时
            Toast.makeText(MainActivity.this, "动画重复了", Toast.LENGTH_SHORT).show();

        }
    };
}
