package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View){

        binding.tvInput.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View){
        val clear = findViewById<Button>(R.id.btnClear)
        binding.tvInput.text =""
        lastDot = false
        lastNumeric = false
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = binding.tvInput.text.toString()
            var prefix =""

            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    //99-1
                    var one = splitValue[0]  //99
                    var two = splitValue[1]  //1

                    if (!prefix.isEmpty()){
                        one = prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    //99-1
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one = prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    //99-1
                    var one = splitValue[0]  //99
                    var two = splitValue[1]  //1

                    if (!prefix.isEmpty()){
                        one = prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    //99-1
                    var one = splitValue[0]  //99
                    var two = splitValue[1]  //1

                    if (!prefix.isEmpty()){
                        one = prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    fun onDecimalPoint(view: View){
        //val input = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value=result
        if (result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/")||value.contains("*")
                    ||value.contains("+") ||value.contains("-")
        }
    }
}
