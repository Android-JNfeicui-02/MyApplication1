package edu.feicui.packagedemo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ImageView mImageView;
    List mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PackageManager manager = this.getPackageManager();
        List<PackageInfo> infoList = manager.getInstalledPackages(0);

        mList = new ArrayList();
        mImageView = new ImageView(this);
        for (int i = 0; i < infoList.size(); i++) {
            Log.d(TAG, "onCreate: " + infoList.get(i));
            PackageInfo packageInfo = infoList.get(i);
            String name = packageInfo.packageName;
            Drawable drawable = packageInfo.applicationInfo.loadIcon(manager);
            mImageView.setImageDrawable(drawable);

        }


        for (int i = 0; i < infoList.size(); i++) {
            PackageInfo packageInfo = infoList.get(i);
            ApplicationInfo info = packageInfo.applicationInfo;

            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                // 大于0 表示的是系统预装
                mList.add(packageInfo);
                for (int j = 0; j < mList.size(); j++) {
                    mList.get(j);
                    Log.d("自己安装的app", "onCreate: " + mList.get(j));
                }
            }
        }

    }
}
