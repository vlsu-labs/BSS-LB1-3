package com.example.bss_android_lb1_3

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle ;
import android.view.View;
import android.widget.Button
import android.widget.TextView

class SecondActivity: Activity() {

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        var bBack = findViewById<Button>(R.id.btnBack)
        bBack.setOnClickListener(this::back)

        var bNext = findViewById<Button>(R.id.btnNext)
        bNext.setOnClickListener(this::next)

        var tv = findViewById<TextView>(R.id.tvSecond)
        var eet = findViewById<TextView>(R.id.ettCallBackMessage)


        tv.text = intent.getStringExtra("screen_message").toString()
        eet.text = tv.text

    }
    fun back(view: View){
        var mEditText = findViewById<TextView>(R.id.ettCallBackMessage)
        var message: String = mEditText.text.toString()
        var intent = Intent(this, MainActivity::class.java)

        intent.putExtra("message", message)
        startActivity(intent)
    }

    fun next(view: View) {
        var intent = Intent(this, ThirdActivity::class.java)

        startActivity(intent)
    }
}