package edu.feicui.teldemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Edit Text
        mEtEdit = (EditText) findViewById(R.id.et_edit);


    }

    public void dial(View view) {

        // 获得Edit Text的内容
        String number = mEtEdit.getText().toString().trim();

        // dial 触发拨号盘
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        startActivity(intent); // 如果报错 提示 permission check 修改 targetSDK为 22以下
    }
}
