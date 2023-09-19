package com.example.bss_android_lb1_3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bss_android_lb1_3.math.MathProcessor
import com.example.bss_android_lb1_3.math.Operations


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var b0 = findViewById<Button>(R.id.btnN0)
        var b1 = findViewById<Button>(R.id.btnN1)
        var b2 = findViewById<Button>(R.id.btnN2)
        var b3 = findViewById<Button>(R.id.btnN3)
        var b4 = findViewById<Button>(R.id.btnN4)
        var b5 = findViewById<Button>(R.id.btnN5)
        var b6 = findViewById<Button>(R.id.btnN6)
        var b7 = findViewById<Button>(R.id.btnN7)
        var b8 = findViewById<Button>(R.id.btnN8)
        var b9 = findViewById<Button>(R.id.btnN9)

        var screen = findViewById<TextView>(R.id.ettScreen)

        var bPlus = findViewById<Button>(R.id.btnOPlus)
        var bMinus = findViewById<Button>(R.id.btnOMinus)
        var bEqual = findViewById<Button>(R.id.btnOEqual)
        var bMultiply = findViewById<Button>(R.id.btnOMultiply)
        var bDivide = findViewById<Button>(R.id.btnODivide)
        var bPoint = findViewById<Button>(R.id.btnOPoint)

        var bSqr = findViewById<Button>(R.id.btnOSqr)
        var bSqrt = findViewById<Button>(R.id.btnOSqrt)

        var bBack = findViewById<Button>(R.id.btnOBackSpace)


        var buttonList: List<Button> = listOf(b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bPoint)

        var operationsMap: Map<Operations, Button> = mapOf(
            Operations.Plus to bPlus,
            Operations.Minus to bMinus,
            Operations.Divide to bDivide,
            Operations.Multiply to bMultiply,
            Operations.Equal to bEqual,
            Operations.Sqr to bSqr,
            Operations.Sqrt to bSqrt,
            Operations.BackSpace to bBack
        )

        var mathProcessor = MathProcessor(
            buttonList,
            screen,
            operationsMap,
            emptyScreenView = getString(R.string.empty_screen_view)
        )


        var bNext = findViewById<Button>(R.id.btnNextActivity)
        screen.text = intent.getStringExtra("message")
        bNext.setOnClickListener(this::onClick)

        intent.putExtra("name", "name")
    }

    @SuppressLint("NonConstantResourceId")
    fun onClick(view: View) {
        if (view.id === R.id.btnNextActivity) {
            var screen = findViewById<TextView>(R.id.ettScreen)
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("screen_message", screen.text.toString())

            startActivity(intent)
        }
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
//        super.onPointerCaptureChanged(hasCapture)
    }
}