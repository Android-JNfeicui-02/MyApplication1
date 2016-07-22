package edu.feicui.sqldemo.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/7/14.
 */
public class MySQLHelper extends SQLiteOpenHelper {


    public static final String SQL = "CREATE TABLE IF NOT EXISTS " +
            "Table1(_id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20),phone varchar(20))";

    // 简化一下constructor
    public MySQLHelper(Context context) {
        // Context 数据库名 Cursor Factory 一般为null  数据库版本号
        super(context, "DBName.db", null, 1);
    }

    /**
     * 创建数据库的方法
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    /**
     * 数据库需要更新时 改变版本号
     * @param db
     * @param oldVersion
     * 旧的版本
     * @param newVersion
     * 新的版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE Table1 ADD email VARCHAR(20)");
    }
}
