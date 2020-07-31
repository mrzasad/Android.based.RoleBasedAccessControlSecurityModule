package com.android.rbac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.rolebaseaccesscontrollibrary.RBACManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RBACManager.getInstance().runDemoApp();
    }
}