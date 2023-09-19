package com.example.bss_android_lb1_3.math

import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException
import kotlin.math.pow
import kotlin.math.sqrt

//import java.lang.Math.sqrt

typealias MathOperation = (Float, Float) -> Float
typealias UnarMathOperation = (Float) -> Float

class MathProcessor {
    private var EMPTY_SCREEN: String

    private var lastNumber: Float = Float.NaN
    private var lastMathOperation: Operations? = null

    private var screen: TextView

    private var mathActions: Map<Operations, MathOperation> = mapOf(
        Operations.Plus to {
            a: Float, b: Float -> a + b
        },
        Operations.Minus to {
                a: Float, b: Float -> a - b
        },
        Operations.Multiply to {
                a: Float, b: Float -> a * b
        },
        Operations.Divide to {
                a: Float, b: Float ->

            if (b == 0f) {
                throw ArithmeticException()
            }

            a / b
        },
    )

    private var unaryMathActions: Map<Operations, UnarMathOperation> = mapOf(
        Operations.Sqrt to {
            a: Float ->

            if (a < 0f) {
                throw ArithmeticException()
            }

            sqrt(a.toDouble()).toFloat()
        },
        Operations.Sqr to {
            a: Float -> a.pow(2)
        }

    )

    constructor(
        buttons: List<Button>,
        screen: TextView,
        operations: Map<Operations, Button>,
        emptyScreenView: String = "0") {
        this.screen = screen

        this.EMPTY_SCREEN = emptyScreenView

        for (button in buttons) {
            button.setOnClickListener {
                this.enterNumber(button)
            }
        }

        for (entry in operations.entries.iterator()) {
            entry.value.setOnClickListener {
                this.processOperation(entry.key)
            }
        }

    }

    private fun processOperation(operation: Operations) {
        when (operation) {
            Operations.Equal -> getResult()
            Operations.BackSpace -> processBackSpace()
            Operations.Sqr, Operations.Sqrt -> processUnaryOperation(operation)
            else -> {
                processMathOperation(operation)
            }
        }
    }

    private fun enterNumber(button: Button) {
        var currentText = screen.text.toString()
        var buttonText = button.text.toString()

        if (currentText == EMPTY_SCREEN) {
            screen.text = buttonText
            return
        }

        currentText += buttonText

        screen.text = currentText
    }

    private fun isNumberOnScreen(): Boolean {
        var numberOnScreen = screen.text.toString();

        try {
            var floatNumber = numberOnScreen.toFloat()
        }
        catch (e: NumberFormatException) {
            return false
        }

        return true
    }

    private fun getResult() {
        if (!isNumberOnScreen() || lastNumber == Float.NaN || lastMathOperation == null) {
            return
        }

        var curNumber = screen.text.toString().toFloat()

        var result: Float

        try {
            result = mathActions[lastMathOperation]!!.invoke(lastNumber, curNumber)
        }
        catch (e: ArithmeticException) {
            return
        }

        screen.text = result.toString()
        lastMathOperation = null
        lastNumber = Float.NaN
    }

    private fun processMathOperation(operation: Operations) {
        if (operation != Operations.Plus &&
            operation != Operations.Minus &&
            operation != Operations.Multiply &&
            operation != Operations.Divide) {
            throw IllegalArgumentException()
        }

        if (!isNumberOnScreen()) {
            return
        }

        var curNumber = screen.text.toString().toFloat()

        lastNumber = curNumber
        lastMathOperation = operation
        screen.text = EMPTY_SCREEN
    }

    private fun processUnaryOperation(operation: Operations) {
        if (operation != Operations.Sqrt &&
            operation != Operations.Sqr) {
            throw IllegalArgumentException()
        }

        if (!isNumberOnScreen()) {
            return
        }

        var curNumber = screen.text.toString().toFloat()

        var result: Float

        try {
            result = unaryMathActions[operation]!!.invoke(curNumber)
        }
        catch (e: ArithmeticException) {
            return
        }

        screen.text = result.toString()
        lastMathOperation = null
        lastNumber = Float.NaN
    }

    private fun processBackSpace() {
        var curString = screen.text.toString()

        if (curString.length == 1) {
            screen.text = EMPTY_SCREEN
            return
        }

        screen.text = curString.subSequence(0, curString.length - 1)
    }
}