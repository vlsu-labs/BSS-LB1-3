package com.example.bss_android_lb1_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class ThirdActivity: Activity() {
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        var bHome: Button = findViewById<Button>(R.id.btnHome)

        bHome.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}