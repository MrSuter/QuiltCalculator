package com.hfad.quiltcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcLength = findViewById<Button>(R.id.calc_length)
        calcLength.setOnClickListener{
            val lengthQuiltText = findViewById<EditText>(R.id.LQ_number)
            val lengthQuilt = lengthQuiltText.text.toString().toDouble()
            val widthQuilt = findViewById<EditText>(R.id.WQ_number).text.toString().toDouble()
            val widthFabric = findViewById<EditText>(R.id.WF_number).text.toString().toDouble()
            val length = getLength(lengthQuilt, widthQuilt, widthFabric)
            val fraction = roundUp(length)
            val lengthText = findViewById<TextView>(R.id.length_text)
            lengthText.text = fraction
        }
    }

    private fun getLength(quiltLength: Double, quiltWidth: Double, fabricWidth: Double): Double {
        return (quiltLength * fabricWidth / (2 * fabricWidth - quiltWidth)) / 36
    }

    private fun Double.equalsDelta(other: Double) = abs(this - other) < 0.000001

    private fun roundUp(number: Double): String {
        var lengthInt = number.toInt()
        val lengthDecimal = number - lengthInt
        var fraction = ""
        var and = " and "
        if (lengthDecimal.equalsDelta(0.0)) {
            fraction = ""
        } else if (lengthDecimal <= 0.125) {
            fraction = "1/8"
        } else if (lengthDecimal <= 0.25) {
            fraction = "1/4"
        } else if (lengthDecimal <= 0.375){
            fraction = "3/8"
        } else if (lengthDecimal <= 0.5) {
            fraction = "1/2"
        } else if (lengthDecimal <= 0.625) {
            fraction = "5/8"
        } else if (lengthDecimal <= 0.75) {
            fraction = "3/4"
        } else if (lengthDecimal <= 0.875) {
            fraction = "7/8"
        } else {
            lengthInt += 1
            and = ""
        }
        return "$lengthInt$and$fraction yards"
    }
}