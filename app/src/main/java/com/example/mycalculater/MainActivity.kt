package com.example.mycalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mycalculater.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var LastNumaric : Boolean = false
    var LastDot :Boolean =false
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener { binding.tvInput.append("1") }

    }

    fun onDigit(view: View) {

        binding.tvInput.append((view as Button).text)
        LastDot =false
        LastNumaric =true
//        Toast.makeText(this,"$te",Toast.LENGTH_LONG).show()

    }
    fun onClear(view:View){
        binding.tvInput.text=""
        LastDot = false
    }
    fun onDecimalPoint(view: View){
        if(LastNumaric && !LastDot){
            binding.tvInput.append(".")
            LastNumaric =false
            LastDot = true

        }

    }
}