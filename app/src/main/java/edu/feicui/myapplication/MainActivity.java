package edu.feicui.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRbSingle;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(); // initial 初始化 View视图
        initClick();

        MyAdapter adapter = new MyAdapter(this);

        mListView.setAdapter(adapter);
    }


    private void initClick() {
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);

    }

    private void initView() {
        mRb1 = (RadioButton) findViewById(R.id.radioButton1);
        mRb2 = (RadioButton) findViewById(R.id.radioButton2);
        mRbSingle = (RadioButton) findViewById(R.id.single);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButton1:
                Toast.makeText(MainActivity.this, "click radio button+" + mRb1.getText(), Toast.LENGTH_SHORT).show();
                mRbSingle.setChecked(false);
                break;
            case R.id.radioButton2:
                Toast.makeText(MainActivity.this, "click radio button+" + mRb2.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
