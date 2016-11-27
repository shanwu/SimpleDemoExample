package com.example.shanwu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by shanwu on 16-11-27.
 */
public class ActivityB extends Activity{
    private TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mTextView = (TextView) findViewById(R.id.tv_b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent receivedIntent = getIntent();
        if(receivedIntent!=null) {
            int id = receivedIntent.getIntExtra(MainActivity.JsonDataModel.JSON_DATA_MODEL, -1);
            mTextView.setText(String.valueOf(id));
        }
    }
}
