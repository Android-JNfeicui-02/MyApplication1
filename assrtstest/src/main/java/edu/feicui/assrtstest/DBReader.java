package edu.feicui.assrtstest;


import java.io.File;

/**
 * Created by admin on 2016/7/13.
 * 这个类用来读取数据库内容的
 */
public class DBReader {

    public static File telFile;


    {
        // 拿到当前的包名
        String packageName = "data/data/edu.feicui.assrtstest/";

        File fileDir = new File(packageName);
        fileDir.mkdirs(); //  文件目录的创建
        telFile = new File(fileDir, "commonnum.db");
    }

    /**  检测是否存在通讯大全 DB  文件 */
    public static boolean isExistsTeldbFile() {
        //  没有对应的数据库文件
        File toFile = DBReader.telFile;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }


}
