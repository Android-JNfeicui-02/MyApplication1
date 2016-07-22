package edu.feicui.listviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lv = (ListView) findViewById(R.id.listView);
        list = getData();
        lv.setAdapter(ba);

    }

    private BaseAdapter ba = new BaseAdapter() {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Map<String,Object> map = list.get(position);

            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = lf.inflate(R.layout.demo_item_layout,null);
            ImageView img_name = (ImageView) view.findViewById(R.id.img_name);
            TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
            TextView auther = (TextView) view.findViewById(R.id.auther);
            TextView txt_context = (TextView) view.findViewById(R.id.txt_context);

            img_name.setImageResource(R.drawable.icon_rocket);
            txt_name.setText((String)map.get("name"));
            auther.setText((String)map.get("auther"));
            txt_context.setText((String)map.get("context"));

            return view;
        }
    };

    private List<Map<String,Object>> getData() {

        list = new ArrayList<Map<String,Object>>();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("name","三国演义");
        dataMap.put("auther","罗贯中");
        dataMap.put("context","三国群雄争霸的故事。。。。。");
        list.add(dataMap);

        dataMap = new HashMap<String,Object>();
        dataMap.put("name","水浒传");
        dataMap.put("auther","施耐庵");
        dataMap.put("context","105个男人和3个女的爱情动作片");
        list.add(dataMap);

        dataMap = new HashMap<String,Object>();
        dataMap.put("name","西游记");
        dataMap.put("auther","吴承恩");
        dataMap.put("context","二个人族和三个兽族打副本升级的故事");
        list.add(dataMap);

        dataMap = new HashMap<String,Object>();
        dataMap.put("name","红楼梦");
        dataMap.put("auther","曹雪芹+高鹗");
        dataMap.put("context","四大家族衰亡史");
        list.add(dataMap);

        return list;
    }

}
