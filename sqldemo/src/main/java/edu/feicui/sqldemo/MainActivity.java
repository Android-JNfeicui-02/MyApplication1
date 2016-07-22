package edu.feicui.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.feicui.sqldemo.Constants.Constants;
import edu.feicui.sqldemo.SQL.MySQLHelper;

// insert = 1, find = 2, modify = 3 , delete = 4.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnInsert, mBtnFind, mBtnModify, mBtnDelete;
    private SQLiteDatabase db;
    private MySQLHelper    mHelper;
    private Context        mContext;

    private int i = 0;
    private StringBuilder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        mHelper = new MySQLHelper(mContext);
        initView();

        db = mHelper.getWritableDatabase();

    }

    private void initView() {
        mBtnInsert = (Button) findViewById(R.id.button1);
        mBtnFind = (Button) findViewById(R.id.button2);
        mBtnModify = (Button) findViewById(R.id.button3);
        mBtnDelete = (Button) findViewById(R.id.button4);

        mBtnInsert.setOnClickListener(this);
        mBtnFind.setOnClickListener(this);
        mBtnModify.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                // 插入数据 操作数据库的类 ContentValues
                ContentValues values1 = new ContentValues();
                values1.put("name", "小张" + i);
                db.insert(Constants.TABLE_NAME, null, values1);
                Toast.makeText(mContext, "插入成功", Toast.LENGTH_SHORT).show();
                i++;
                break;

            case R.id.button2:
                // 查询
                mBuilder = new StringBuilder();
                Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
                // 使用cursor进行数据库的遍历
                if (cursor.moveToFirst()) {
                    do {
                        // 使用cursor获取想要的数据 指定表头
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        mBuilder.append("name = " + name + "\n");
                    } while (cursor.moveToNext());
                }
                // 关闭cursor
                cursor.close();

                Toast.makeText(MainActivity.this, mBuilder.toString(), Toast.LENGTH_SHORT).show();

                break;
            case R.id.button3:
                // 修改数据库的值
                ContentValues values3 = new ContentValues();
                values3.put("name","黑蛋O_O");

                // 表名，值，指定位置where条件， 约束限定条件，不指定则修改所有内容
                // 后面两个参数 如果不指定 则修改所有内容
                //db.update(Constants.TABLE_NAME,values3,"name = ?", new String[]{"小张0"});
                db.update(Constants.TABLE_NAME,values3,null,null);
                break;
            case R.id.button4:
                // 删除
                // table_name where条件， 约束
                mBuilder = new StringBuilder();
                db.delete(Constants.TABLE_NAME,"_id = ?",new String[]{""});
                break;

        }
    }
}
