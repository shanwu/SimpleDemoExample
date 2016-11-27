package com.example.shanwu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. assume you already have parsed data from json
        ArrayList<JsonDataModel> dataList = new ArrayList<>();
        dataList.add(new JsonDataModel(1, " 1", Color.GREEN));
        dataList.add(new JsonDataModel(2, " 2", Color.BLUE));
        dataList.add(new JsonDataModel(3, " 3", Color.MAGENTA));
        dataList.add(new JsonDataModel(4, " 4", Color.CYAN));
        dataList.add(new JsonDataModel(5, " 5", Color.GREEN));
        dataList.add(new JsonDataModel(6, " 6", Color.BLUE));
        dataList.add(new JsonDataModel(7, " 7", Color.MAGENTA));
        dataList.add(new JsonDataModel(8, " 8", Color.CYAN));
        dataList.add(new JsonDataModel(9, " 9", Color.GREEN));
        dataList.add(new JsonDataModel(10, " 10", Color.BLUE));
        dataList.add(new JsonDataModel(11, " 11", Color.RED));
        dataList.add(new JsonDataModel(12, " 12", Color.YELLOW));
        ListView listView = (ListView) findViewById(R.id.test_lv);
        MyAdapter adapter = new MyAdapter(this, dataList);
        listView.setAdapter(adapter);
    }

    public static class MyAdapter extends BaseAdapter implements View.OnClickListener {
        private ArrayList<JsonDataModel> mDataList = null;
        private LayoutInflater mInflator = null;
        private Context mContext = null;

        public MyAdapter(Context cxt, ArrayList<JsonDataModel> dataList) {
            mDataList = dataList;
            mContext = cxt;
            mInflator = LayoutInflater.from(cxt);
        }

        @Override
        public int getCount() {
            if (mDataList == null || mDataList.isEmpty()) {
                return 0;
            } else {
                return mDataList.size();
            }
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflator.inflate(R.layout.child, parent, false);
            }

            final JsonDataModel data = mDataList.get(position);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.bgLayout = (LinearLayout) convertView.findViewById(R.id.background);
            viewHolder.bgLayout.setBackgroundColor(data.color);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.name);
            viewHolder.nameTv.setText(data.value);
            convertView.setTag(data); // That's how you keep your data with the View
            convertView.setOnClickListener(this);

            return convertView;
        }

        @Override
        public void onClick(View v) {
            JsonDataModel data = (JsonDataModel) v.getTag();
            Intent intent = new Intent(mContext, ActivityB.class);
            intent.putExtra(JsonDataModel.JSON_DATA_MODEL, data.id);
            mContext.startActivity(intent);
        }
    }

    public static class ViewHolder {
        LinearLayout bgLayout;
        TextView nameTv;
    }

    public static class JsonDataModel {
        final static String JSON_DATA_MODEL = "json_data";
        int id;
        String value;
        int color;

        public JsonDataModel(final int id, final String value, final int color) {
            this.id = id;
            this.value = value;
            this.color = color;
        }
        // blablabla so on
    }
}
