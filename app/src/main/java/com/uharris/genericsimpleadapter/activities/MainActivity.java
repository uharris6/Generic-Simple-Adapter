package com.uharris.genericsimpleadapter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.uharris.genericsimpleadapter.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnRecyclerView, R.id.btnSpinner, R.id.btnListView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecyclerView:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btnSpinner:
                startActivity(new Intent(this, SpinnerActivity.class));
                break;
            case R.id.btnListView:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
        }
    }
}
