package com.example.shanwu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
        dataList.add(new JsonDataModel(111111, "Bob", Color.RED)); // fake data-- pretend it's your parsed data from json value
        dataList.add(new JsonDataModel(222222, "Peter", Color.YELLOW));
        dataList.add(new JsonDataModel(333333, "Jean", Color.GRAY));
        dataList.add(new JsonDataModel(444444, "David", Color.GREEN));
        dataList.add(new JsonDataModel(555555, "Dorothy", Color.BLUE));
        dataList.add(new JsonDataModel(666666, "Howard", Color.MAGENTA));
        dataList.add(new JsonDataModel(777777, "Nancy", Color.CYAN));

        ListView listView = (ListView) findViewById(R.id.test_lv);
        MyAdapter adapter = new MyAdapter(this,dataList);
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
            if (convertView != null) {
                return convertView;
            }
            final JsonDataModel data = (JsonDataModel) getItem(position);

            View childView = mInflator.inflate(R.layout.child, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.bgLayout = (LinearLayout) childView.findViewById(R.id.background);
            viewHolder.bgLayout.setBackgroundColor(data.color);
            viewHolder.nameTv = (TextView) childView.findViewById(R.id.name);
            viewHolder.nameTv.setText(data.value);
            childView.setTag(data); // That's how you keep your data with the View
            childView.setOnClickListener(this);

            return childView;
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
