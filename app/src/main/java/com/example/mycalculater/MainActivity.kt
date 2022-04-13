package com.example.mycalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mycalculater.databinding.ActivityMainBinding
import java.lang.ArithmeticException

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
    fun onOperattor(view : View){
        binding.tvInput?.text?.let {

            if (LastNumaric && !isOperatorAdded(it.toString())){
                binding.tvInput.append((view as Button).text)
                LastNumaric = false
                LastDot =false
            }
        }
    }
    fun onEqual(view: View){
        if (LastNumaric){
            var tvVal = binding.tvInput.text.toString()
            var prefix =""
            try {
                if(tvVal.startsWith("-")){
                    prefix =""

                    tvVal =tvVal.substring(1)
                }
                if(tvVal.contains("-")){
                    val splitVal = tvVal.split("-")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }else if(tvVal.contains("+")){
                    val splitVal = tvVal.split("+")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }else if(tvVal.contains("*")){
                    val splitVal = tvVal.split("*")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }else if(tvVal.contains("/")){
                    val splitVal = tvVal.split("/")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }

            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(result :String) : String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length - 2)
        return value
    }
    private fun isOperatorAdded(value :String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }
}